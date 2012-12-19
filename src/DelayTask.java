package socnet;

import socnet.Socnet;
import java.util.TimerTask;

public class DelayTask extends TimerTask{
	private int dpID;
	public DelayTask(int dpID){
		this.dpID = dpID;
	}
	public void run(){
		//Socnet.putInPosts(dpID);
	}
}

