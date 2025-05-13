package com.miniproject.adoption.dto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		} catch(ClassNotFoundException e) {
		  e.printStackTrace();		
		}
	}
	
	
	// 게시글 하나만 조회
	public ArrayList<AdoptionWriteDto> getAdopTionList(){
		
		String sql = "Select * From adoption_post Order by Post_Id Desc";
		ArrayList<AdoptionWriteDto> AList = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			AList = new ArrayList<>();
			
			while (rs.next()) {
				AdoptionWriteDto dto = new AdoptionWriteDto();
					dto.setPostId(rs.getInt("post_id"));
					dto.setTitle(rs.getString(2));
					dto.setUserId(rs.getString("user_id"));
					dto.setCreatedAt(rs.getTimestamp("reg_date"));
					dto.setViews(rs.getInt("read_count"));
					
				
			
				AList.add(dto);
			};
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	return AList;
} //getAdopTionList
	
}
	
	