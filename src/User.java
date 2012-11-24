/**
 * Carlos Alberto Martins Ferreira = 2010146877
 * João dos Santos Valença         = 2010130607
 * Pedro Ascensão Ferreira Matias  = 2010120038
 */

package socnet;

import java.io.Serializable;

/**
 * User represents a registered user in the system
 * along with its credentials.
 * Super class of Client.
 *
 * @author Carlos Ferreira
 * @author João Valença
 * @author Pedro Matias
 *
 * @see 	Client
 */

public class User implements Serializable{
	
	/**
	 * User login that enables its authentication
	 */
	protected String login;
	/**
	 * User password that enables its authentication
	 */
	protected String password;


	/**
	 * User constructor, creates a new user
	 *
	 * @param login
	 * @param pass
	 */
	public User(String login, String pass){
		this.login    = login;
		this.password = pass;
	}

	/**
	 * Retrieves the user login
	 * @return User login
	 */
	public String getLogin(){
		return login;
	}
	/**
	 * Sets the user login name
	 * @param login 	user's new login
	 */	
	public void setLogin(String login){
		this.login = login;
	}
	/**
	 * Retrieves the user password
	 * @return User password
	 */
	public String getPassword(){
		return password;
	}
	/**
	 * Sets the user password
	 * @param pass 	user's new password
	 */
	public void setPassword(String pass){
		this.password = pass;
	}
}