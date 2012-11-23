package socnet;

public class Client extends User{
	
	private long NIB;

	public Client(String login, String pass, long NIB){
		super(login, pass);
		this.NIB = NIB;
	}

	public long getNIB(){
		return NIB;
	}
	public void setNIB(long NIB){
		this.NIB = NIB;
	}
}