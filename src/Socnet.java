/**
 * Carlos Alberto Martins Ferreira = 2010146877
 * João dos Santos Valença         = 2010130607
 * Pedro Ascensão Ferreira Matias  = 2010120038
 */

package socnet;		// needs package to be imported to JSP

import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Timer;
import java.util.Date;
import socnet.*;


/**
 * Main class, responsible for all the
 * operations related to the application
 * 
 * @author Carlos Ferreira
 * @author João Valença
 * @author Pedro Matias
 */
public class Socnet{

	/* User 'login' is the key to the hash map */
	private static ConcurrentHashMap<String, User> users;
	/* Chatroom 'theme' is the key to the hash map */
	private static ConcurrentHashMap<String, Chatroom> chatrooms;
	/* DelayedPost 'id' is the key to the hash map */
	private static ConcurrentHashMap<Integer, DelayedPost> delayedPosts;


	/**
	 * Initializes the application, backing up 
	 * information.
	 * Method called when Tomcat server deploys socnet webapp 
	 * (information on WEB-INF/web.xml descriptor)
	 */
	public static void init(){
		readBackup();		
	}	

	/**
	 * Reads to memory all data stored in the filesystem
	 */
	public static void readBackup(){
		users = new ConcurrentHashMap<String, User>();
		chatrooms = new ConcurrentHashMap<String, Chatroom>();

		ConcurrentHashMap<String, User> u = Backup.readUsers();
		if( u != null )
			users.putAll(u);
	}

	/**
	 * Saves all data (registered users, chat rooms, etc)
	 * in memory into the filesystem.
	 * Method called when Tomcat server closes de socnet webapp
	 * or shutsdown.
	 */
	public static void destroy(){
		Backup.saveUsers(users);
	}

	/**
	 * Authenticates a user in the system
	 *
	 * @param login			Username
	 * @param pass			user password
	 * @return				<code>true</code> if the 'login' and 'pass'
	 *                      match the login and password registered in system;
	 *                      <code>false</code> otherwise
	 *              
	 */
	public static boolean authenticate(String login, String pass){
		User u = users.get(login);
		if( u!=null ){
			return u.getPassword().equals(pass);
		}
		return false;
	}


	/**
	 * Registers a user in the system (either regular User or Client)
	 * 
	 * @param login			Username specified by the user
	 * @param pass			Password specified by the user
	 * @param NIB			If a client is registering
	 * @return 				<code>true</code> if the specified 'login'
	 *                      does not yet exist in the system;
	 *                      <code>false</code> otherwise
	 * 
	 * @see User
	 * @see Client
	 */
	public static boolean registerUser(String login, String pass, String NIB){
		if( !users.containsKey(login) ){
			if( NIB.equals("") )
				users.put(login, new User(login, pass));
			else
				users.put(login, new Client(login, pass, Long.parseLong(NIB)) );

			Backup.saveUsers(users);
			return true;
		}
		return false;
	}


	/**
	 * Checks if a chatroom with the specified theme 
	 * alreay exists.
	 * 
	 * @param theme			Chatroom theme specified by the user
	 * @return 				<code>true</code> if a chatroom with the specified 'theme'
	 * 						does not yet exist in the system;
	 * 						<code>false</code> otherwise
	 * 
	 * @see Chatroom
	 */
	private static Boolean existsChatroom(String theme){
		return (chatrooms.get(theme) != null);
	}

	/**
	 * Creates a new Chatroom instance with
	 * the specified theme and adds it to the
	 * hash map.
	 *
	 * @param theme			Chatroom theme specified by the user
	 * @return 				<code>true</code> if the chatroom was successfully 
	 * 						added to the system
	 * 						<code>false</code> if a chatroom with the specified 'theme'
	 * 						already exists in the system;
	 *
	 * @see Chatroom
	 */
	public synchronized static Boolean addChatroom(String theme){
		if( existsChatroom(theme) )
			chatrooms.put(theme, new Chatroom(theme));
		
		return false;
	}

	public static String[] getChatroomNames(){
		return chatrooms.keySet().toArray( new String[0] );		// type cast to array of strings
	}

	public static Post[] getChatroomPosts(String theme){
		Chatroom cr = chatrooms.get(theme);
		if(cr != null){
			return cr.getPosts();
		}

		return null;
	}

	public static Boolean editPost(String chatroom, int postID, String text, String imagePath){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			return cr.editPost(postID, text, imagePath);
		}

		return false;		
	}

	public static Boolean editPost(String chatroom, int postID, String text){
		return editPost(chatroom, postID, text, "");
	}	

	public static Boolean addPost(String chatroom, String text, String source, String imagePath){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			Post p = new Post(source, text, imagePath);
			cr.addPost(p);
			return true;
		}

		return false;			
	}

	public static Boolean addPost(String chatroom, String text, String source){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			Post p = new Post(source, text);
			cr.addPost(p);
			return true;
		}

		return false;			
	}	

	public static Boolean addDelayedPost(String chatroom, String text, String source, String imagePath, Date futureDate){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			DelayedPost dp = new DelayedPost(source, text, futureDate, imagePath);
			new Timer().schedule( new DelayTask(dp.getID()), futureDate );			
			return true;
		}

		return false;			
	}

	public static Boolean addDelayedPost(String chatroom, String text, String source, Date futureDate){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			DelayedPost dp = new DelayedPost(source, text, futureDate);
			new Timer().schedule( new DelayTask(dp.getID()), futureDate );			
			return true;
		}

		return false;			
	}	

	public synchronized static void putInPosts(int dpID){
		DelayedPost dp = delayedPosts.remove(dpID);
		if(dp!=null){
			dp.setDate( new Date() );
			chatrooms.get( dp.getChatroom() ).addPost(dp);
		}
		else{
			System.out.println("Error removing delayed post!");
		}	
	}

	public static Boolean deletePost(String chatroom, int postID){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			return cr.deletePost(postID);
		}

		return false;		
	}

	public static Boolean addReply(String chatroom, int parentID, String text, String source){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			return cr.addReply(parentID, text, source);
		}

		return false;			
	}

}