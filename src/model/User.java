package model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userid;
	private String displayname;
	private String pass;
	private String iconpath;
	
	public User() {}
	
	public User(String userid, String pass) {
		this.userid = userid;
		this.pass = pass;
	}	
	
	public User(String userid, String displayname, String pass) {
		this.userid = userid;
		this.displayname = displayname;
		this.pass = pass;
	}
	
	public String getUserId() {
		return userid;
	}
	
	public String getDisplayName() {
		return displayname;
	}
	
	public void setDisplayName(String displayname) {
		this.displayname = displayname;
	}
	
	public String getPass() {
		return pass;
	}
	
	public String getIconPath() {
		return iconpath;
	}
	
	public void setIconPath(String iconpath) {
		this.iconpath = iconpath;
	}
	
}
