package model;

import java.io.Serializable;

public class Mutter implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String datetime;
	private String text;
	private String iconpath;
	private boolean bookmark;
	
	public Mutter() {}
	
	public Mutter(String userName, String datetime,String text) {
		this.userName = userName;
		this.datetime = datetime;
		this.text = text;
	}
	
	public Mutter(int id, String userName, String datetime, String text, String iconpath, boolean bookmark) {
		this.id = id;
		this.userName = userName;
		this.datetime = datetime;
		this.text = text;
		this.iconpath = iconpath;
		this.bookmark = bookmark;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getDateTime() {
		return datetime;
	}
	
	public String getText() {
		return text;
	}
	
	public String getIconPath() {
		return iconpath;
	}
	
	public boolean getBookmark() {
		return bookmark;
	}
}
