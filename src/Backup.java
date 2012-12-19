/**
 * Carlos Alberto Martins Ferreira = 2010146877
 * João dos Santos Valença         = 2010130607
 * Pedro Ascensão Ferreira Matias  = 2010120038
 */

package socnet;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Backup is the class for all operations related to 
 * files and its manipulation.
 * It provides static methods that enables retrieval/saving from/to the filesystem
 * all data related to the application.
 * 
 * @author Carlos Ferreira
 * @author João Valença
 * @author Pedro Matias
 */
public class Backup {	

	private static final String TOMCAT_HOME = "/Library/Tomcat/";
	private static final String WEBAPP_NAME = "socnetES";

	private static final String FILES_PATH         = TOMCAT_HOME+"webapps/"+WEBAPP_NAME+"/files/";
	private static final String USERS_FILE         = FILES_PATH+"users.obj";
	private static final String CHATS_FILE         = FILES_PATH+"chats.obj";

	private ObjectInputStream iS;
	private ObjectOutputStream oS;
		
	/**
	 * Opens the given file or reading operations
	 * 
	 * @param fileName 			the file path
	 * @throws IOException		if an input or output exception ocurred
	 */
	private void openRead(String fileName) throws IOException {
		iS = new ObjectInputStream(new FileInputStream(fileName));
	}
	/**
	 * Opens the given file or writing operations
	 * 
	 * @param fileName 			the file path
	 * @throws IOException		if an input or output exception ocurred
	 */	
	private void openWrite(String fileName) throws IOException{
		oS = new ObjectOutputStream(new FileOutputStream(fileName));
	}
	/**
	 * Reads an object from the current opened ObjectInputStream iS
	 * 
	 * @return		 					the Object read (should be typecasted
	 *                         			to the pretended object)
	 * @throws IOException				if an input or output exception ocurred
	 * @throws ClassNotFoundException	if the class was not found
	 */		
	private Object readObject() throws IOException,ClassNotFoundException{
		return iS.readObject();	
	}
	/**
	 * Writes an object from the current opened ObjectInputStream oS
	 * 
	 * @param o		 					the object to be saved
	 * @throws IOException				if an input or output exception ocurred
	 */			
	private void writeObject(Object o) throws IOException{
		oS.writeObject(o);
	}
	/**
	 * Closes de current opened ObjectInputStream iS
	 */
	private void closeRead() throws IOException{
		iS.close();
	}
	/**
	 * Closes de current opened ObjectInputStream oS
	 */	
	private void closeWrite() throws IOException{
		oS.close();
	}

	/**
	 * Read the object file that contains the users registered
	 * to memory
	 *
	 * @return 		The users saved in the filesystem
	 */
	@SuppressWarnings("unchecked")
	public static ConcurrentHashMap<String, User> readUsers() {
		Backup b = new Backup();		
		ConcurrentHashMap<String, User> users;
		try{
			b.openRead(USERS_FILE);
			users = (ConcurrentHashMap<String, User>) b.readObject();
			b.closeRead();

			return users;							
		}
		catch (EOFException e){System.out.println("ERRO "+USERS_FILE+" "+e.getMessage());}
		catch (FileNotFoundException e){System.out.println("ERRO "+USERS_FILE+" "+e.getMessage());}
		catch (java.io.IOException e){System.out.println("ERRO "+USERS_FILE+" "+e.getMessage());}
		catch (java.lang.ClassNotFoundException e){System.out.println("ERRO "+USERS_FILE+" "+e.getMessage());}		
		
		return null;
	}

	/**
	 * Saved registered users in the application to an object file
	 *
	 * @param users 	the users to be saved
	 */
	public static synchronized void saveUsers( ConcurrentHashMap<String, User> users ){
		Backup b = new Backup();		
		try{
			b.openWrite(USERS_FILE);
			b.writeObject(users);
			b.closeWrite();
		}
		catch (FileNotFoundException e) {System.out.println("Ficheiro nao existente: "+e.getMessage());}
		catch (IOException e){System.out.println("---Erro de I/O: "+e);}		
	}

	/**
	 * Read the object file that contains the existing chatrooms
	 * to memory
	 *
	 * @return 		The chatrooms saved in the filesystem
	 */
	@SuppressWarnings("unchecked")
	public static ConcurrentHashMap<String, Chatroom> readChatrooms() {
		Backup b = new Backup();		
		ConcurrentHashMap<String, Chatroom> chatrooms;
		try{
			b.openRead(CHATS_FILE);
			chatrooms = (ConcurrentHashMap<String, Chatroom>) b.readObject();
			b.closeRead();

			return chatrooms;							
		}
		catch (EOFException e){System.out.println("ERRO "+CHATS_FILE+" "+e.getMessage());}
		catch (FileNotFoundException e){System.out.println("ERRO "+CHATS_FILE+" "+e.getMessage());}
		catch (java.io.IOException e){System.out.println("ERRO "+CHATS_FILE+" "+e.getMessage());}
		catch (java.lang.ClassNotFoundException e){System.out.println("ERRO "+CHATS_FILE+" "+e.getMessage());}		
		
		return null;
	}

	/**
	 * Saved existing chatrooms in the application to an object file
	 *
	 * @param chatrooms 	the chatrooms to be saved
	 */
	public static synchronized void saveChatrooms( ConcurrentHashMap<String, Chatroom> chatrooms ){
		Backup b = new Backup();		
		try{
			b.openWrite(CHATS_FILE);
			b.writeObject(chatrooms);
			b.closeWrite();
		}
		catch (FileNotFoundException e) {System.out.println("Ficheiro nao existente: "+e.getMessage());}
		catch (IOException e){System.out.println("---Erro de I/O: "+e);}		
	}		
}
