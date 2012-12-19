package socnet;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Message extends Content{

	protected Date date;	

	public Message(String src, String text, String imagePath){
		super(src, text, imagePath);
		this.date = new Date();
	}

	public Message(String src, String text, String imagePath, Date date){
		super(src, text, imagePath);
		this.date = date;
	}	

	public Message(String src, String text){
		super(src, text);
		this.date = new Date();
	}

	public Message(String src, String text, Date date){
		super(src, text);
		this.date = date;
	}	

	public Date getDate(){
		return date;
	}

	public void setDate(Date date){
		this.date = date;
	}
}
