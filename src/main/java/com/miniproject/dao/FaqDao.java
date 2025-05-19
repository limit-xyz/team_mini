package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.miniproject.dao.DBManager;
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
	public Faq getFaq(int faqNo) {
		
		String sqlFaq = "SELECT faq_no, faq_title, faq_content, faq_author FROM FAQ WHERE faq_no = ?";
		Faq faq = null;
		
		
		try {
			
			conn = DBManager.getConnection();		
			pstmt = conn.prepareStatement(sqlFaq);		
			pstmt.setInt(1, faqNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				faq =new Faq();
				faq.setFaqNo(rs.getInt("faq_no"));
				faq.setFaqTitle(rs.getString("faq_title"));
				faq.setFaqContent(rs.getString("faq_content"));
				faq.setFaqAuthor(rs.getString("faq_author"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
				
		return faq;
	}
	
	
	
	// U기능 FAQ에 글을 수정하는 기능 
	public void updateFaq(Faq faq) {
		
		String sqlFaq = "UPDATE faq SET faq_title = ?, faq_content = ? WHERE faq_no = ?";
		try {
			
			conn = DBManager.getConnection();		
			pstmt = conn.prepareStatement(sqlFaq);
			pstmt.setString(1, faq.getFaqTitle());
			pstmt.setString(2, faq.getFaqContent());
			pstmt.setInt(3, faq.getFaqNo());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

		return;
	}
	
	
	// C기능 FAQ에 글 하나를 등록하는 기능 
	// 관리자만 가능하게 처리함 
	public void insertFaq(Faq faq) {
		String sqlFaq = "INSERT INTO FAQ (faq_no, faq_title, faq_content, faq_author) VALUES (SEQ_FAQ_NO.NEXTVAL, ?, ?, ?)";
		try {
			
			conn = DBManager.getConnection();		
			pstmt = conn.prepareStatement(sqlFaq);	
			
			
			pstmt.setString(1, faq.getFaqTitle());
			pstmt.setString(2, faq.getFaqContent());
			pstmt.setString(3, faq.getFaqAuthor());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {			
			DBManager.close(conn, pstmt);
		}

		return ;
	}
	

	
	// D기능 FAQ에 글 하나를 지우는 기능 faqNo의 값으로 게시글 번호를 찾음
	public void deleteFaq(int faqNo) {
		String sqlFaq = "DELETE FROM FAQ WHERE faq_no = ?"; 
		
		try {
			
			conn = DBManager.getConnection();		
			pstmt = conn.prepareStatement(sqlFaq);			
			pstmt.setInt(1,faqNo);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
		return;
	}
	
	
	
	
	//faq의 전체 갯수를 읽어오는 기능 페이징에 씀 
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
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;		
	}		
	
	
	// 검색어가 포함된 faq 게시글 수를 읽어오는 메서드 - 검색 paging 처리에 사용
	public int getFaqCount(String keyword) {
		// title, writer, content
		String faqSql = "SELECT COUNT(*) FROM faq "
				+ " WHERE LIKE ?";
		int count = 0;
		
		try {
			conn = DBManager.getConnection();		
			pstmt = conn.prepareStatement(faqSql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt(1);
			}			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;		
	}	
	
	
	
	
	//L기능 faqlist 전체를 받아오는 기능 페이징 데이터를 받음
	public ArrayList<Faq> getFaqList(int startRow, int endRow) {
		
		
		String faqSql = "SELECT * FROM (SELECT ROWNUM num, sub.* "
				+ "    FROM (SELECT * FROM faq ORDER BY faq_no DESC) sub) "
				+ " WHERE num >= ? AND num <= ?";
		
		ArrayList<Faq> faqList = null;
		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(faqSql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();


				faqList = new ArrayList<Faq>();

				// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
				while(rs.next()) {
					Faq f = new Faq();
					f.setFaqNo(rs.getInt("faq_no"));
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
	
	
	//L기능 검색된 faqlist 전체를 받아오는 기능 페이징 데이터를 받음
		public ArrayList<Faq> getFaqList(String keyword,int startRow, int endRow) {
			
			
			String faqSql = "SELECT * FROM (SELECT ROWNUM num, sub.* "
					+ "    FROM (SELECT * FROM faq WHERE LIKE ? ORDER BY faq_no DESC) sub) "
					+ " WHERE num >= ? AND num <= ?";
			
			ArrayList<Faq> faqList = null;
			
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(faqSql);
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();


					faqList = new ArrayList<Faq>();

					// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
					while(rs.next()) {
						Faq f = new Faq();
						f.setFaqNo(rs.getInt("faq_no"));
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
