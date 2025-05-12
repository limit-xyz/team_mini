package com.miniproject.adoption.dto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdoptionDao {

	private static final String USER = "hr";
	private static final String PASS = "hr";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public AdoptionDao() {
		try {
		Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<AdoptionWriteDao> getAdopTionList(){
		String sql = "Select * From adoption_post Order by Post_Id Desc";
		ArrayList<AdoptionWriteDao> list = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				AdoptionWriteDao dto = new AdoptionWriteDao(
					rs.getInt("post_id"),
					rs.getString("user_id"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getString("type"),
					rs.getString("region"),
					rs.getString("animal_type_main"),
					rs.getString("animal_type_detail"),
					rs.getString("image_path"),
					rs.getTimestamp("created_at"),
					rs.getInt("views")
				
				);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return list;
}
	
}
	
	