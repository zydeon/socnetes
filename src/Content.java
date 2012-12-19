package socnet;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Content implements Serializable{

	protected int ID;
	protected String source;
	protected String text;
	protected String imagePath;

	public Content(String source, String text, String imagePath){
		this.source = source;
		this.text = text;
		this.imagePath = imagePath;
	}

	public Content(String source, String text){
		this(source, text, "");
	}

	public String getSource(){
		return source;
	}

	public void setSource(String src){
		this.source = src;
	}

	public String getText(){
		return text;
	}

	public void setText(String t){
		this.text = t;
	}

	public String getImagePath(){
		return imagePath;
	}

	public void setImagePath(String imPath){
		this.imagePath = imPath;
	}

	public int getID(){
		return ID;
	}

	public void setID(int ID){
		this.ID = ID;
	}
}
	