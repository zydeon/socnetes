package socnet;

import java.util.ArrayList;
import java.util.Date;

public class DelayedPost extends Post{

	private Date futureDate;
	private String chatroom;

	public DelayedPost(String src, String text, Date futureDate, String imagePath){
		super(src, text, imagePath);
		this.futureDate = futureDate;
	}

	public DelayedPost(String src, String text, Date futureDate){
		super(src, text);
		this.futureDate = futureDate;
	}	

	public Date getFutureDate(){
		return futureDate;
	}

	public void setFutureDate(Date d){
		this.futureDate = d;
	}

	public String getChatroom(){
		return chatroom;
	}

	public void setChatroom(String chat){
		this.chatroom = chat;
	}

}
