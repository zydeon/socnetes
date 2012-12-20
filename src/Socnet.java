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
import java.util.Iterator;
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

	/**
	 * Initializes the application, backing up 
	 * information.
	 * Method called when Tomcat server deploys socnet webapp 
	 * (information on WEB-INF/web.xml descriptor)
	 */
	public static void init(){
		readBackup();	
		Content.setNextID( getMaxPostID() + 1 );
	}	

	/**
	 * Reads to memory all data stored in the filesystem
	 */
	public static void readBackup(){
		users = new ConcurrentHashMap<String, User>();
		chatrooms = new ConcurrentHashMap<String, Chatroom>();

		// read users
		ConcurrentHashMap<String, User> u = Backup.readUsers();
		if( u != null )
			users.putAll(u);

		// read chatrooms
		ConcurrentHashMap<String, Chatroom> c = Backup.readChatrooms();
		if( c != null )
			chatrooms.putAll(c);
	}

	/**
	 * Gets the last ID attributed to a post in all chatrooms,
	 * which is equivalent to the greater ID.
	 *
	 * @return				the maximum post ID in the system
	 */
	public static int getMaxPostID(){
		int maxID = 0, tmp;
		if(chatrooms!=null){
			Iterator<Chatroom> it = chatrooms.values().iterator();
			while( it.hasNext() ){
				tmp = it.next().getMaxPostID();
				if( tmp > maxID )
					maxID = tmp;
			}
		}
		else{
			System.out.println("Chatrooms are null!");			
		}

		return maxID;
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
	    if( !existsChatroom(theme) ){
			chatrooms.put(theme, new Chatroom(theme));
			Backup.saveChatrooms(chatrooms);
			return true;
	    }
		return false;
		
	}

	/**
	 * Retrieves the current chatroom themes in the system.
	 *
	 * @return				An array with the themes
	 */
	public static String[] getChatroomThemes(){
		return chatrooms.keySet().toArray( new String[0] );		// type cast to array of strings
	}

	/**
	 * Retrieves the posts written in a specified chatroom
	 * ordered by date and by its parent recursively.
	 * The first ones are the most up to date.
	 *
	 * @param theme 		Chatroom theme specified
	 * @return				An array with the ordered posts
	 */
	public static Post[] getChatroomPosts(String theme){
	    Chatroom cr = chatrooms.get(theme);
	    System.out.println("socnetgetposts");
		if(cr != null){
			return cr.getPosts();
		}

		return null;
	}

	/**
	 * Edits a post of a specified chatroom
	 * with the possibility of altering
	 * its content and image.
	 *
	 * @param chatroom 		The chatroom identifier (its theme)
	 * @param postID 		The id of the post to be alterer
	 * @param text 			The new content of the post
	 * @param imagePath 	The new path to the image
	 * @return 				<code>true</code> if the edit was successful
	 * 						<code>false</code> otherwise (chatroom or post does not exist)
	 *
	 * @see Post
	 */
	public static Boolean editPost(String chatroom, int postID, String text, String imagePath){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			return cr.editPost(postID, text, imagePath);
		}

		return false;		
	}

	// /**
	//  * Edits a post with the possibility of altering
	//  * its content.
	//  *
	//  * @param chatroom 		The chatroom identifier (its theme)
	//  * @param postID 		The id of the post to be alterer
	//  * @param text 			The new content of the post
	//  * @param imagePath 	The new path to the image
	//  * @return 				<code>true</code> if the edit was successful
	//  * 						<code>false</code> otherwise (chatroom or post does not exist)
	//  */
	// public static Boolean editPost(String chatroom, int postID, String text){
	// 	return editPost(chatroom, postID, text, "");
	// }	

	/**
	 * Adds a post to the specified chatroom.
	 * This post can either be a delayed post (if <code>date!=null</code>)
	 * or a post with an image (if <code>imagePath!=null</code>)
	 *
	 * @param chatroom 			The chatroom identifier (its theme)
	 * @param text				The content of the post
	 * @param source 			The User identifier (login) that wrote the post
	 * @param imagePath			The path to the image (can be null)
	 * @param date 				The future date to a delayed post appear (can be null)
	 * @return 					
	 *
	 * @return 				<code>true</code> if the post was successfully added to the chatroom
	 * 						<code>false</code> otherwise (chatroom does not exist)
	 */
	public static Boolean addPost(String chatroom, String text, String source, String imagePath, Date date){
		Chatroom cr = chatrooms.get(chatroom);
			
		if(cr != null){
			Post p;
			if( imagePath == null ){
				if( date == null ){
					p = new Post(source, text);
				}
				else{
					p = new Post(source, text, date);
				}
			}
			else{
				if( date == null ){
					p = new Post(source, text, imagePath);
				}
				else{
					p = new Post(source, text, imagePath, date);
				}
			}
			cr.addPost(p);
			return true;
		}

		return false;			
	}

	// public static Boolean addPost(String chatroom, String text, String source){
	// 	Chatroom cr = chatrooms.get(chatroom);
	// 	if(cr != null){
	// 		Post p = new Post(source, text);
	// 		cr.addPost(p);
	// 		return true;
	// 	}

	// 	return false;			
	// }	

	// public static Boolean addDelayedPost(String chatroom, String text, String source, String imagePath, Date futureDate){
	// 	Chatroom cr = chatrooms.get(chatroom);
	// 	if(cr != null){
	// 		DelayedPost dp = new DelayedPost(source, text, futureDate, imagePath);
	// 		new Timer().schedule( new DelayTask(dp.getID()), futureDate );			
	// 		return true;
	// 	}

	// 	return false;			
	// }

	// public static Boolean addDelayedPost(String chatroom, String text, String source, Date futureDate){
	// 	Chatroom cr = chatrooms.get(chatroom);
	// 	if(cr != null){
	// 		DelayedPost dp = new DelayedPost(source, text, futureDate);
	// 		new Timer().schedule( new DelayTask(dp.getID()), futureDate );			
	// 		return true;
	// 	}

	// 	return false;			
	// }	

	// public synchronized static void putInPosts(int dpID){
	// 	DelayedPost dp = delayedPosts.remove(dpID);
	// 	if(dp!=null){
	// 		dp.setDate( new Date() );
	// 		chatrooms.get( dp.getChatroom() ).addPost(dp);
	// 	}
	// 	else{
	// 		System.out.println("Error removing delayed post!");
	// 	}	
	// }

	/**
	 * Deletes a post from a chatroom and the
	 * corresponding replies
	 *
	 * @param chatroom 		The chatroom identifier (its theme)
	 * @param postID 		The id of the post to be removed
	 * @return 				<code>true</code> if the post was successfully deleted
	 * 						<code>false</code> otherwise (specified post or chatroom does not exist)
	 */
	public static Boolean deletePost(String chatroom, int postID){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			return cr.deletePost(postID);
		}

		return false;		
	}

	/**
	 * Adds a reply to a specified post.
	 * The reply cannot be delayed or be attached
	 * to an image.
	 *
	 * @param chatroom 		The chatroom identifier (its theme)
	 * @param parentID		The identifier of the parent post
	 * @param text 			The content of the reply
	 * @param source 		The User identifier (login) that wrote the reply
	 *
	 * @return 				<code>true</code> if the reply was successfully added
	 * 						<code>false</code> otherwise (specified parent post ID does not exist)
	 */
	public static Boolean addReply(String chatroom, int parentID, String text, String source){
		Chatroom cr = chatrooms.get(chatroom);
		if(cr != null){
			return cr.addReply(parentID, text, source);
		}

		return false;			
	}

}
