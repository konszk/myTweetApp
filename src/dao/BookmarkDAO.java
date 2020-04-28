package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;
import model.User;

public class BookmarkDAO {
	
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/docoTsubu";
	private final String USER = "root";
	private final String PASSWORD = "rootroot";
	
	public List<Mutter> find(User user){
		Connection conn = null;
		List<Mutter> mutterList = new ArrayList<Mutter>();
		try {
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			
			String sql = "SELECT "
					   + "MUTTER.ID, MUTTER.NAME, ACCOUNT.DISPLAY_NAME, MUTTER.DATETIME, MUTTER.TEXT, ACCOUNT.PROFILE_IMAGE "
					   + "FROM MUTTER "
					   + "INNER JOIN BOOKMARK "
					   + "ON MUTTER.ID = BOOKMARK.MUTTER_ID "
					   + "INNER JOIN ACCOUNT "
					   + "ON MUTTER.NAME = ACCOUNT.USER_ID "
					   + "WHERE BOOKMARK.USER_ID = ? "
					   + "ORDER BY ID DESC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, user.getUserId());
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("DISPLAY_NAME");
				String datetime = rs.getString("DATETIME");
				String text = rs.getString("TEXT");
				String icon = rs.getString("PROFILE_IMAGE");
				boolean bookmark = true;
				
				Mutter mutter = new Mutter(id, name, datetime, text, icon, bookmark);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		
		return mutterList;
	}
	
	public boolean create(User user, int mutter_id) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			
			String sql = "INSERT INTO BOOKMARK(USER_ID, MUTTER_ID) VALUE(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, user.getUserId());
			pStmt.setInt(2, mutter_id);
			
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
	
	public boolean delete(User user, int mutter_id) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			
			String sql = "DELETE FROM BOOKMARK WHERE USER_ID=? AND MUTTER_ID=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, user.getUserId());
			pStmt.setInt(2, mutter_id);
			
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
