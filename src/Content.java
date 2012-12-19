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
 * Content is an abstract class and have the following attributes: a text, 
 * an optional image and one identifier.
 * Super class of both Ad and Message.
 *
 * @author Carlos Ferreira
 * @author João Valença
 * @author Pedro Matias
 *
 * @see 	Message
 */
public abstract class Content implements Serializable{

	private static int nextID = 1;
	protected int ID;
	protected String source;
	protected String text;
	protected String imagePath;

	/**
	 * Content constructor, initializes default and common attributes to sub classes.
	 * It can be specified the path to an image in the server.
	 *
	 * @param source 		Identifier of the user who wrote this content
	 * @param text 			The actual text of the content
	 * @param imagePath 	Location of the image in the server
	 */
	public Content(String source, String text, String imagePath){
		this.source = source;
		this.text = text;
		this.imagePath = imagePath;
		this.ID = nextID++;
	}


	/**
	 * Content constructor, initializes default and common attributes to sub classes
	 *
	 * @param source 		Identifier of the user who wrote this content
	 * @param text 			The actual text of the content
	 */
	public Content(String source, String text){
		this(source, text, "");
	}

	/**
	 * Retrieves the ID of the content owner
	 * @return 				User identifier
	 */
	public String getSource(){
		return source;
	}
	/**
	 * Sets the ID of the content owner
	 * @param src 			New content owner ID
	 */	
	public void setSource(String src){
		this.source = src;
	}
	/**
	 * Retrieves the text of the content
	 * @return 				Text
	 */
	public String getText(){
		return text;
	}
	/**
	 * Sets the text of the content
	 * @param t 			New text
	 */	
	public void setText(String t){
		this.text = t;
	}
	/**
	 * Retrieves the image location in the server
	 * @return 				Path to the image
	 */	
	public String getImagePath(){
		return imagePath;
	}
	/**
	 * Sets the image location attached to the content
	 * @param imPath 		Path to the image
	 */	
	public void setImagePath(String imPath){
		this.imagePath = imPath;
	}
	/**
	 * Retrieves the ID of the content
	 * @return 				ID
	 */
	public int getID(){
		return ID;
	}
	/**
	 * Sets the ID of the content
	 * @param ID 			New ID
	 */	
	public void setID(int ID){
		this.ID = ID;
	}
	/**
	 * Retrieves the ID of the next new content
	 * @return 				Next ID
	 */
	public static int getNextID(){
		return nextID;
	}
	/**
	 * Sets the ID of the next new content
	 * (this is called by the main application when backing up data)
	 * @param ID 			Next ID
	 */	
	public static void setNextID(int ID){
		Content.nextID = ID;
	}	
}
	