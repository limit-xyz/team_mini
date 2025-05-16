package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.miniproject.vo.Faq;


public class FaqDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public FaqDao() {
	}
	
	public ArrayList<Faq> getFaq() {
		
		
		String faqSql = "SELECT * FROM FAQ";
		
		ArrayList<Faq> faqList = null;
		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(faqSql);
			rs = pstmt.executeQuery();


				faqList = new ArrayList<Faq>();

				// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
				while(rs.next()) {
					Faq f = new Faq();
					f.setFaqNo(rs.getString("faq_no"));
					f.setFaqTitle(rs.getString("faq_title"));
					f.setFaqContent(rs.getString("faq_content"));
					f.setFaqAuthor(rs.getString("faq_author"));
					
					faqList.add(f);
				}
			} catch(SQLException e) {
				e.printStackTrace();
				
			} finally {
				// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
				DBManager.close(conn, pstmt, rs);
			}
			
		return faqList;
		
		
	}

}
