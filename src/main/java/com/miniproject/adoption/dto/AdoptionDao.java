package com.miniproject.adoption.dto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdoptionDao {


	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// 게시글 하나만 조회
	public AdoptionWriteDao getAdopTionById(int postId){
		AdoptionWriteDao = null;
		String sql = "Select * From adoption_post WHERE Post_Id=?";
		
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASS);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			
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
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally { 
			close();
		}
		
	return dto;
}
	
}
	
	