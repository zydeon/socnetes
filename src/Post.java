/**
 * Carlos Alberto Martins Ferreira = 2010146877
 * João dos Santos Valença         = 2010130607
 * Pedro Ascensão Ferreira Matias  = 2010120038
 */

package socnet;

import java.util.ArrayList;
import java.util.Date;

/**
 * Post represents a message created by a registered user
 * and is targeted to a chatroom.
 * It is the sub class of Message, so it is
 * attached an image and is delayed to a future date.
 * A post can have multiple cascade replies and is public
 * to all the users currently in the chatroom.
 *
 * @author Carlos Ferreira
 * @author João Valença
 * @author Pedro Matias
 *
 * @see 	Message
 */
public class Post extends Message{

	protected int parentID;			// if parent==-1 it does not have parents
	protected int replyLevel; 
	protected ArrayList<Integer> replyIDs;

	/**
	 * Post constructor, creates a new post with an image
	 *
	 * @param source 		Identifier of the user who wrote this post
	 * @param text 			The content of the post
	 * @param imagePath 	Location of the image in the server
	 */
	public Post(String src, String text, String imagePath){
		super(src, text, imagePath);
		this.parentID = -1;			// doest not have parents		
		this.replyLevel = 0;
		this.replyIDs = new ArrayList<Integer>();				
	}
	/**
	 * Post constructor, creates a new delayed post with an image.
	 *
	 * @param source 		Identifier of the user who wrote this post
	 * @param text 			The content of the post
	 * @param imagePath 	Location of the image in the server
	 * @param Date 			Date of post sending
	 */
	public Post(String src, String text, String imagePath, Date date){
		super(src, text, imagePath, date);
		this.parentID = -1;			// doest not have parents		
		this.replyLevel = 0;
		this.replyIDs = new ArrayList<Integer>();				
	}		
	/**
	 * Post constructor, creates a new post
	 *
	 * @param source 		Identifier of the user who wrote this post
	 * @param text 			The content of the post
	 */
	public Post(String src, String text){
		super(src, text);
		this.parentID = -1;			// doest not have parents		
		this.replyLevel = 0;
		this.replyIDs = new ArrayList<Integer>();				
	}	
	/**
	 * Post constructor, creates a new delayed post
	 *
	 * @param source 		Identifier of the user who wrote this post
	 * @param text 			The content of the post
	 * @param Date 			Date of post sending
	 */
	public Post(String src, String text, Date date){
		super(src, text, date);
		this.parentID = -1;			// doest not have parents		
		this.replyLevel = 0;
		this.replyIDs = new ArrayList<Integer>();				
	}		
	/**
	 * Post constructor, creates a new reply to a post
	 *
	 * @param source 		Identifier of the user who wrote this reply
	 * @param text 			The content of the post
	 * @param parentID 		Identifier of the post which is being replied
	 * @param rLvl 			Reply depth level
	 */
	public Post(String src, String text, int parentID, int rLvl){
		super(src, text);
		this.parentID = parentID;			// doest not have parents		
		this.replyLevel = rLvl;
		this.replyIDs = new ArrayList<Integer>();
	}		
	/**
	 * Retrieves the reply depth level
	 * @return 				Reply depth level
	 */
	public int getReplyLevel(){
		return replyLevel;
	}
	/**
	 * Sets the reply depth level of this post
	 * @param level			New level
	 */	
	public void setReplyLevel(int level){
		this.replyLevel = level;
	}
	/**
	 * Retrieves all the reply IDs written to this post
	 * @return 				ArrayList of identifiers
	 */
	public ArrayList<Integer> getReplyIDs(){
		return replyIDs;
	}
	/**
	 * Adds a reply to this post.
	 * @param replyID 		The reply identifier
	 */
	public void addReplyID(int replyID){
		replyIDs.add(replyID);
	}
	/**
	 * Removes a reply to this post.
	 * @param replyID 		The reply identifier
	 */
	public void removeReplyID(int replyID){
		replyIDs.remove( new Integer(replyID) );
	}	
	/**
	 * Retrieves the ID of the parent post
	 * @return 				Post identifier
	 */	
	public int getParentID(){
		return parentID;
	}	
	/**
	 * Sets the ID of the the parent post
	 * @param ID 			New ID
	 */	
	public void setParentID(int ID){
		this.parentID = ID;
	}
}
