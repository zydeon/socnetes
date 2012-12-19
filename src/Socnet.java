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
	}	

	/**
	 * Reads to memory all data stored in the filesystem
	 */
	public static void readBackup(){
		users = new ConcurrentHashMap<String, User>();

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

}