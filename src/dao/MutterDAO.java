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

public class MutterDAO {

	private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/docoTsubu";
	private final String USER = "root";
	private final String PASSWORD = "rootroot";
	
	public List<Mutter> findAll(User user){
		Connection conn = null;
		List<Mutter> mutterList = new ArrayList<Mutter>();
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			
			String sql = "SELECT "
					   + "MUTTER.ID, ACCOUNT.DISPLAY_NAME, MUTTER.TEXT, MUTTER.DATETIME, ACCOUNT.PROFILE_IMAGE, T1.ID AS BOOKMARK_ID "
					   + "FROM MUTTER "
					   + "INNER JOIN ACCOUNT "
					   + "ON MUTTER.NAME = ACCOUNT.USER_ID "
					   + "LEFT OUTER JOIN "
					   + "(SELECT ID, MUTTER_ID FROM BOOKMARK WHERE USER_ID=?) AS T1 "
					   + "ON MUTTER.ID = T1.MUTTER_ID "
					   + "ORDER BY MUTTER.ID DESC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getUserId());
			
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("DISPLAY_NAME");
				String datetime = rs.getString("DATETIME");
				String text = rs.getString("TEXT");
				String icon = rs.getString("PROFILE_IMAGE");
				boolean bookmark = false;
				if(rs.getString("BOOKMARK_ID") != null) {
					bookmark = true;
				}
				
				Mutter mutter = new Mutter(id, name, datetime, text, icon, bookmark);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
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
	
	public boolean create(Mutter mutter) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			
			String sql = "INSERT INTO MUTTER(NAME, DATETIME, TEXT) VALUE(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getDateTime());
			pStmt.setString(3, mutter.getText());
			
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
