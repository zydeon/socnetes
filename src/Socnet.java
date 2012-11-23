package socnet;		// needs package to be imported to JSP

import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;

public class Socnet{

	private static ConcurrentHashMap<String, User> users;

	public static void init(){
		readBackup();		
	}	

	public static void readBackup(){
		users = new ConcurrentHashMap<String, User>();

		ConcurrentHashMap<String, User> u = Backup.readUsers();
		if( u != null )
			users.putAll(u);
	}

	public static void destroy(){
		
	}

	public static boolean authenticate(String login, String pass){
		User u = users.get(login);
		if( u!=null ){
			return u.getPassword().equals(pass);
		}
		return false;
	}

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
}