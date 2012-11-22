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
		System.out.println("AKSDBSAKBDKASBD");
	}

	public static boolean authenticate(String login, String pass){
		User u = users.get(login);
		if( u!=null ){
			return u.getPassword().equals(pass);
		}
		return false;
	}

	public static boolean registerUser(String login, String pass){
		if( !users.containsKey(login) ){
			users.put(login, new User(login, pass)  );
			Backup.writeUsers(users);
			return true;
		}
		return false;
	}
}