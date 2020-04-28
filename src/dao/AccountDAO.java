package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class AccountDAO {

	private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/docoTsubu";
	private final String USER = "root";
	private final String PASSWORD = "rootroot";
	
	public Boolean find(User user){
		Connection conn = null;
		String userid = null;
		String displayname = null;
		String pass = null;
		String iconpath = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			
			String sql = "SELECT USER_ID, DISPLAY_NAME, PASSWORD, PROFILE_IMAGE FROM ACCOUNT WHERE USER_ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, user.getUserId());
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				userid = rs.getString("USER_ID");
				displayname = rs.getString("DISPLAY_NAME");
				pass = rs.getString("PASSWORD");
				iconpath = rs.getString("PROFILE_IMAGE");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		
		if (userid == null || !(pass.equals(user.getPass()))) {
			return false;
		}
		
		if (iconpath == null) {
			iconpath = "./image/default_icon.jpg";
		}
		
		user.setDisplayName(displayname);
		user.setIconPath(iconpath);
		return true;
	}
	
	public boolean create(User user) {
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

			String sql = "INSERT INTO ACCOUNT(USER_ID, DISPLAY_NAME, PASSWORD, PROFILE_IMAGE) VALUE(?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getDisplayName());
			pStmt.setString(3, user.getPass());
			pStmt.setString(4,"./image/default_icon.jpg");
			
			int result = pStmt.executeUpdate();
			
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e ) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}
	
	public boolean checkDuplication(User user) {
		Connection conn = null;
		int count = 0;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			String sql = "SELECT COUNT(*) AS COUNT FROM ACCOUNT WHERE USER_ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, user.getUserId());
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}
			
			if (count == 0) {
				return false;
			} 
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e ) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean update(User user, String new_displayname, String profile_image) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			
			//String sql = "INSERT INTO ACCOUNT(USER_ID, DISPLAY_NAME, PASSWORD) VALUE(?, ?, ?)";
			String sql = "UPDATE ACCOUNT SET DISPLAY_NAME=?, PROFILE_IMAGE=? WHERE USER_ID=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			

			pStmt.setString(1, new_displayname);
			pStmt.setString(2, profile_image);
			pStmt.setString(3, user.getUserId());
			
			int result = pStmt.executeUpdate();
			
			if (result != 1) {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e ) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
		
	}
}
