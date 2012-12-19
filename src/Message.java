/**
 * Carlos Alberto Martins Ferreira = 2010146877
 * João dos Santos Valença         = 2010130607
 * Pedro Ascensão Ferreira Matias  = 2010120038
 */

package socnet;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Message represents a <code>Content</code> object
 * with the additional possibility of tracking the message sent date
 * (it is possible to delay posts)
 * Super class of both Post and PM and sub class of Content
 * and is targeted to a chatroom.
 *
 * @author Carlos Ferreira
 * @author João Valença
 * @author Pedro Matias
 *
 * @see 	Content
 */
public class Message extends Content{

	protected Date date;	
	/**
	 * Message constructor, creates a new message with an image
	 *
	 * @param source 		Identifier of the user who wrote this message
	 * @param text 			The content of the message
	 * @param imagePath 	Location of the image in the server
	 */
	public Message(String src, String text, String imagePath){
		super(src, text, imagePath);
		this.date = new Date();
	}
	/**
	 * Message constructor, creates a new delayed message with an image
	 *
	 * @param source 		Identifier of the user who wrote this message
	 * @param text 			The content of the message
	 * @param imagePath 	Location of the image in the server
	 * @param Date 			Date of message sending
	 */
	public Message(String src, String text, String imagePath, Date date){
		super(src, text, imagePath);
		this.date = date;
	}	
	/**
	 * Message constructor, creates a new message
	 *
	 * @param source 		Identifier of the user who wrote this message
	 * @param text 			The content of the message
	 */
	public Message(String src, String text){
		super(src, text);
		this.date = new Date();
	}
	/**
	 * Message constructor, creates a new delayed message
	 *
	 * @param source 		Identifier of the user who wrote this message
	 * @param text 			The content of the message
	 * @param Date 			Date of message sending
	 */
	public Message(String src, String text, Date date){
		super(src, text);
		this.date = date;
	}		
	/**
	 * Retrieves the date of sending
	 * @return 				Date
	 */
	public Date getDate(){
		return date;
	}		
	/**
	 * Sets the date of sending
	 * @param date			New date
	 */	
	public void setDate(Date date){
		this.date = date;
	}
}
