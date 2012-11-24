/**
 * Carlos Alberto Martins Ferreira = 2010146877
 * João dos Santos Valença         = 2010130607
 * Pedro Ascensão Ferreira Matias  = 2010120038
 */

package socnet;

/**
 * Client represents a registered Client in the system
 * along with its credentials and bank account information. *
 * 
 * @see User
 */

public class Client extends User{
	
	private long NIB;


	/**
	 * Client constructor, creates a new Client
	 */
	public Client(String login, String pass, long NIB){
		super(login, pass);
		this.NIB = NIB;
	}

	/**
	 * Retrieves the user bank account number
	 * @return User NIB
	 */
	public long getNIB(){
		return NIB;
	}
	/**
	 * Sets the user bank account number
	 * @param NIB 	user's new bank account number
	 */
	public void setNIB(long NIB){
		this.NIB = NIB;
	}
}