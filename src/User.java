package socnet;

import java.io.Serializable;

public class User implements Serializable{
	
	private String login;
	private String password;

	public User(String login, String pass){
		this.login    = login;
		this.password = pass;
	}

	public String getLogin(){
		return login;
	}
	public void setLogin(String login){
		this.login = login;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String pass){
		this.password = pass;
	}
}