package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.miniproject.vo.Faq;


// 전체 게시글 수를 읽어오는 메서드 - paging 처리에 사용


public class FaqDao {
	
	
	public FaqDao() {
	}
	
	
	//기본 db매니저 연결 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	
	
	
	//CLUD U기능 FAQ의 수정폼에 불러올 내용 
	//특정 FAQ 게시글중 하나의 모든 정보를 가져옴
	public faq getFaqDetail(int faqNo) {
		
		String sqlFaq = SELECT faq_no, faq_title, faq_content, faq_author FROM FAQ WHERE faq_no = ?;
				
		return faq;
	}
	
	// U기능 FAQ에 글을 수정하는 기능 
	public int updateFaq(Faq faq) {
		int result=0;
		return result;
	}
	
	// C기능 FAQ에 글 하나를 등록하는 기능 
	// 관리자만 가능하게 처리함 
	public int insertFaq(Faq faq) {
		String sqlFaq = INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author) VALUES (SEQ_FAQ_NO.NEXTVAL, ?, ?, ?);
		return result;
	}
	

	
	// D기능 FAQ에 글 하나를 지우는 기능 faqNo의 값으로 게시글 번호를 찾음
	public int deleteFaq(int faqNo) {
		String sqlFaq = DELETE FROM FAQ WHERE faq_no = ?; 
		return result;
	}
	
	
	
	
	//faq의 수를 읽어오는 기능 페이징에 씀 
	public int getFaqCount() {
		String sqlFaq = "SELECT COUNT(*) FROM faq";
		int count = 0;
		
		try {
			conn = DBManager.getConnection();		
			pstmt = conn.prepareStatement(sqlFaq);			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt(1);
			}			
		} catch(SQLException e) {
			System.out.println("dao getFaqCount메서드 : SQLException");
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;		
	}		
	
	
	
	
	
	//L기능 faqlist 전체를 받아오는 기능 
	public ArrayList<Faq> getFaqList() {
		
		
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
