package socnet;

import java.util.ArrayList;

public class Post extends Message{

	protected int parentID;			// if parent==-1 it does not have parents
	protected int replyLevel; 
	private ArrayList<Integer> replyIDs;

	public Post(String src, String text, String imagePath){
		super(src, text, imagePath);
		this.parentID = -1;			// doest not have parents		
		this.replyLevel = 0;
		this.replyIDs = new ArrayList<Integer>();
	}	

	public Post(String src, String text){
		super(src, text);
		this.parentID = -1;			// doest not have parents		
		this.replyLevel = 0;
		this.replyIDs = new ArrayList<Integer>();
	}	

	public Post(String src, String text, int parentID, int rLvl){
		super(src, text);
		this.parentID = parentID;			// doest not have parents		
		this.replyLevel = rLvl;
		this.replyIDs = new ArrayList<Integer>();
	}		

	public int getReplyLevel(){
		return replyLevel;
	}

	public ArrayList<Integer> getReplyIDs(){
		return replyIDs;
	}

	public void addReplyID(int replyID){
		replyIDs.add(replyID);
	}
	
	public int getParentID(){
		return parentID;
	}	
}
