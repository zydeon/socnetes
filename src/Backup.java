package socnet;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class Backup {	

	private static final String FILES_PATH         = "./";
	
	private static final String USERS_FILE         = FILES_PATH+"users.obj";

	private ObjectInputStream iS;
	private ObjectOutputStream oS;
	
	private void abreLeitura(String nomeDoFicheiro) throws IOException {
		iS = new ObjectInputStream(new FileInputStream(nomeDoFicheiro));
	}
	private void abreEscrita(String nomeDoFicheiro) throws IOException{
		oS = new ObjectOutputStream(new FileOutputStream(nomeDoFicheiro));
	}
	private Object leObjecto() throws IOException,ClassNotFoundException{
		return iS.readObject();	
	}
	private void escreveObjecto(Object o) throws IOException{
		oS.writeObject(o);
	}
	private void fechaLeitura() throws IOException{
		iS.close();
	}
	private void fechaEscrita() throws IOException{
		oS.close();
	}
	
	public static ConcurrentHashMap<String, User> readUsers() {
		Backup b = new Backup();		
		ConcurrentHashMap<String, User> users;
		try{
			b.abreLeitura(USERS_FILE);
			users = (ConcurrentHashMap<String, User>) b.leObjecto();
			b.fechaLeitura();

			return users;							
		}
		catch (EOFException e){System.out.println("ERRO "+USERS_FILE+" "+e.getMessage());}
		catch (FileNotFoundException e){System.out.println("ERRO "+USERS_FILE+" "+e.getMessage());}
		catch (java.io.IOException e){System.out.println("ERRO "+USERS_FILE+" "+e.getMessage());}
		catch (java.lang.ClassNotFoundException e){System.out.println("ERRO "+USERS_FILE+" "+e.getMessage());}		
		
		return null;
	}

	public static synchronized void saveUsers( ConcurrentHashMap<String, User> users ){
		Backup b = new Backup();		
		try{
			b.abreEscrita(USERS_FILE);
			b.escreveObjecto(users);
			b.fechaEscrita();
		}
		catch (FileNotFoundException e) {System.out.println("Ficheiro nao existente: "+e.getMessage());}
		catch (IOException e){System.out.println("---Erro de I/O: "+e);}		
	}
}
