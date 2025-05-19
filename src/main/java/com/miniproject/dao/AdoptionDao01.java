package com.miniproject.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.miniproject.dao.DBManager;


public class AdoptionDao01 {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	
	public AdoptionWriteDto getPostById(int postId){

		
		String sql = "Select * From adoption_post WHERE Post_Id = ?";
		AdoptionWriteDto dto = null;
		
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, postId);
					
			rs= pstmt.executeQuery();
			
			if (rs.next()) {
				 dto = new AdoptionWriteDto();
					dto.setPostId(rs.getInt("post_id"));
					dto.setUserId(rs.getString("user_id"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setAdoptionType(rs.getString("adoption_type"));
					dto.setRegion(rs.getString("region"));
					dto.setAnimalTypeMain(rs.getString("animal_type_main"));
					dto.setAnimalTypeDetail(rs.getString("animal_type_detail"));
					dto.setImagePath(rs.getString("image_path"));
					dto.setCreatedAt(rs.getTimestamp("created_at"));
					dto.setViewsCount(rs.getInt("views_count"));
					dto.setApprovalStatus(rs.getString("approval_status"));
			}				
		} catch (Exception e) {
					e.printStackTrace();
			
		} finally { 
			DBManager.close(conn, pstmt, rs);
		}
		
		return dto;
	
	}
	
	
	// 페이지 단위로 게시글 목록 조회
		public ArrayList<AdoptionWriteDto> boardList(int startRow, int endRow) throws SQLException {
			
			String sql = "SELECT * FROM (SELECT ROWNUM num, sub.* "
					+ "    FROM (SELECT * FROM adoption_post ORDER BY post_id DESC) sub) "
					+ " WHERE num >= ? AND num <= ?";
			ArrayList<AdoptionWriteDto> blist = new ArrayList<>();
			
			try {		
				// 2. DB에 연결
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
							
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				
				// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
				rs = pstmt.executeQuery();
				
			
				
				// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
				while(rs.next()) {
					AdoptionWriteDto b = new AdoptionWriteDto();
					b.setPostId(rs.getInt("post_id"));
					b.setTitle(rs.getString("title"));
					b.setUserId(rs.getString("user_id"));
					b.setCreatedAt(rs.getTimestamp("created_at"));
					b.setViewsCount(rs.getInt("views_count"));
					b.setApprovalStatus(rs.getString("approval_status"));
					b.setImagePath(rs.getString("image_path"));
					b.setContent(rs.getString("content"));
					b.setAdoptionType(rs.getString("adoption_type"));
					b.setRegion(rs.getString("region"));
					b.setAnimalTypeMain(rs.getString("animal_type_main"));
					b.setAnimalTypeDetail(rs.getString("animal_type_detail"));
					b.setImagePath(rs.getString("image_path"));
					
					blist.add(b);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
				
			} finally {
				// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
				DBManager.close(conn, pstmt, rs);
			}
			
			return blist;
		}
		
	
	
	//----------------------------------------------------------
	// 전체 게시글 수를 읽어오는 메서드 - paging 처리에 사용
		public int getSearchBoardCount(String type,
				String keyword, String adoptionType, 
				String animalTypeMain) throws SQLException{
			String sql = "SELECT COUNT(*) FROM adoption_post"
					+ " WHERE " + type + " LIKE ? "
					+ " And adoption_type = ? " + " AND animal_type_main = ?";
			int count = 0;
			
			try {
				conn = DBManager.getConnection();		
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" +  keyword + "%");
				pstmt.setString(2, adoptionType);
				pstmt.setString(3, animalTypeMain);
				rs = pstmt.executeQuery();

				if(rs.next()) {
					count = rs.getInt(1);
				}			
			} catch(SQLException e) {
				System.out.println("BoardDao - getBoardCount() : SQLException");
				e.printStackTrace();
				
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return count;		
		}		
	
	
	//------------------------------------------------------------------
	// 제목, 작성자, 내용에 검색어가 포함된 게시 글 리스트를 읽어와 반환하는 메서드
		public ArrayList<AdoptionWriteDto> searchList ( //String approval_status
				String type,String keyword, String adoptiontype, String animalTypeMain, int startRow, int endRow) throws SQLException{
			
			List<String> allowedTypes = Arrays.asList("title", "user_id", "region", "content");
			if(type == null || !allowedTypes.contains(type)) {
				
				throw new IllegalArgumentException("허용되지 않은 검색 컬럼 입니다." + type);
			}
			
			String sql = "SELECT * FROM ( "
					+ "    SELECT ROWNUM num, sub.* FROM "
					+ "        (SELECT * FROM adoption_post "
					+ " WHERE " + type + " LIKE ? "
					+ " and adoption_type=? and animal_type_main =? "
					+ " ORDER BY post_id DESC) sub) "
					+ " WHERE num >= ? AND num <= ?";
			
			ArrayList<AdoptionWriteDto> blist = new ArrayList<>();
			
			
			try {			
				// 2. DB에 연결
				conn = DBManager.getConnection();
				
				// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
				// PreparedStatement
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, adoptiontype);
				pstmt.setString(3, animalTypeMain);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
				
				// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
				rs = pstmt.executeQuery();
				
				
				
				// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
				while(rs.next()) {
					AdoptionWriteDto dto = new AdoptionWriteDto();
					dto.setPostId(rs.getInt("post_id"));
					dto.setTitle(rs.getString("title"));
					dto.setUserId(rs.getString("user_id"));
					dto.setCreatedAt(rs.getTimestamp("created_at"));
					dto.setViewsCount(rs.getInt("views_count"));
					dto.setApprovalStatus(rs.getString("approval_status"));
					dto.setImagePath(rs.getString("image_path"));
					dto.setAdoptionType(rs.getString("adoption_type"));
					
					blist.add(dto);
				}
			} catch(SQLException e) {
				e.printStackTrace();
				
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			return blist;
		}
	
	//-----------------------------------------------------------------------
	// 검색어가 포함된 게시글 수를 읽어오는 메서드 - 검색 paging 처리에 사용
		public int getBoardCount() throws SQLException{
			// title, writer, content
			String sql = "SELECT COUNT(*) FROM adoption_post ";
			int count = 0;
			
			try {
				conn = DBManager.getConnection();		
				pstmt = conn.prepareStatement(sql);			
				rs = pstmt.executeQuery();

				if(rs.next()) {
					count = rs.getInt(1);
				}			
			} catch(SQLException e) {
				System.out.println("BoardDao - getBoardCount() : SQLException");
				e.printStackTrace();
				
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return count;		
		}	
		
		// 검색어가 포함된 게시글 수를 읽어오는 메서드 - 검색 paging 처리에 사용
				public int getBoardCount(String adoption_type, String keyword) {
					// title, writer, content
					String sql = "SELECT COUNT(*) FROM adoption_post "
							+ " WHERE " + adoption_type + " LIKE  ? ";
					int count = 0;
					
					try {
						conn = DBManager.getConnection();		
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, keyword);
						rs = pstmt.executeQuery();

						if(rs.next()) {
							count = rs.getInt(1);
						}			
					} catch(SQLException e) {
						System.out.println("BoardDao - getBoardCount() : SQLException");
						e.printStackTrace();
						
					} finally {
						DBManager.close(conn, pstmt, rs);
					}
					return count;		
				}	
						
		
		
		
//---------------------------------------------------------------------
	/*// 게시 글 번호에 해당하는 댓글 리스트를 DB 테이블에서 읽어와 반환하는 메서드
	public ArrayList<Reply> getReplyList(int bbsNo) {
		
		String replyListSql = "SELECT * FROM reply WHERE bbs_no=? "
							+ " ORDER BY no DESC";		
		ArrayList<Reply> replyList = null;
		
		
		try {			
			// 2. DB에 연결
			conn = DBManager.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(replyListSql);
			pstmt.setInt(1, bbsNo);			
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			rs = pstmt.executeQuery();
			
			replyList = new ArrayList<>();
			
			// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setNo(rs.getInt("no"));
				reply.setBbsNo(rs.getInt("bbs_no"));				
				reply.setReplyContent(rs.getString("reply_content"));
				reply.setReplyWriter(rs.getString("user_id"));
				reply.setRegDate(rs.getTimestamp("reg_date"));
				replyList.add(reply);
			}
			
		} catch(SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			DBManager.close(conn, pstmt, rs);
		}
		
		*/
		// 게시 글 수정, 삭제시 게시 글 비밀번호가 맞는지 체크하는 메서드
		public boolean isUserIdCheck(int postId, String userId) {
			
			String sql = "SELECT user_id FROM adoption_post WHERE post_id=?";
			boolean isUserId = false;
			
			try {			
				// 2. DB에 연결
				conn = DBManager.getConnection();
				
				// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
				// PreparedStatement
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, postId);			
				
				// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
				rs = pstmt.executeQuery();
				
				// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
				if(rs.next()) {
					isUserId = rs.getString(1).equals(userId);
				}			
				
			} catch(SQLException e) {
				e.printStackTrace();
				
			} finally {
				// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
				DBManager.close(conn, pstmt, rs);
			}
			
			return isUserId;
		} // end isPassCheck()	
		
		
		// 게시글 상세 조회
		public AdoptionWriteDto getAdoption(int postId, boolean state) throws SQLException{
			
			String sqlAdoptionList = "Select * From adoption_post WHERE Post_Id=?";
			String countSql = "UPDATE adoption_post SET views_count = views_count + 1 WHERE post_id=?";
			AdoptionWriteDto dto = null;
			PreparedStatement countStmt = null;
			PreparedStatement selectStmt = null;
			ResultSet rs = null;
			Connection conn=null;
		
			
			try{
				conn = DBManager.getConnection();
				DBManager.setAutoCommit(conn, false);
				
				if(state) {
					countStmt = conn.prepareStatement(countSql);
					countStmt.setInt(1,postId);
					countStmt.executeUpdate();
				}
				selectStmt = conn.prepareStatement(sqlAdoptionList);
				selectStmt.setInt(1, postId);
				
				rs= selectStmt.executeQuery();
				
				if (rs.next()) {
					 dto = new AdoptionWriteDto();
						dto.setPostId(rs.getInt("post_id"));
						dto.setUserId(rs.getString("user_id"));
						dto.setTitle(rs.getString("title"));
						dto.setContent(rs.getString("content"));
						dto.setAdoptionType(rs.getString("adoption_type"));
						dto.setRegion(rs.getString("region"));
						dto.setAnimalTypeMain(rs.getString("animal_type_main"));
						dto.setAnimalTypeDetail(rs.getString("animal_type_detail"));
						dto.setImagePath(rs.getString("image_path"));
						dto.setCreatedAt(rs.getTimestamp("created_at"));
						dto.setViewsCount(rs.getInt("views_count"));
						dto.setApprovalStatus(rs.getString("approval_status"));
				}
				DBManager.commit(conn);
					
			} catch (SQLException e) {
				DBManager.rollback(conn);
				e.printStackTrace();
				
			} finally { 
				if (countStmt != null) DBManager.close(null, countStmt);
				DBManager.close(conn, selectStmt, rs);
			}
			
			return dto;
		
		}

			// 게시글 작성
		public int insertAdoptionPost(AdoptionWriteDto dto){
			int result = 0;
			String sql = "INSERT INTO adoption_post (post_id, user_id, title, content, adoption_type, region, animal_type_main, animal_type_detail, image_path, created_at, views_count) "
					   + "VALUES (adoption_post_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, 0)";
					
			try{
			 conn = DBManager.getConnection();
			 DBManager.setAutoCommit(conn, false);
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, dto.getUserId());
				 pstmt.setString(2, dto.getTitle());
				 pstmt.setString(3, dto.getContent());
				 pstmt.setString(4, dto.getAdoptionType());
				 pstmt.setString(5, dto.getRegion());
				 pstmt.setString(6, dto.getAnimalTypeMain());
				 pstmt.setString(7, dto.getAnimalTypeDetail());
				 pstmt.setString(8, dto.getImagePath());
					
				 result = pstmt.executeUpdate();
				 DBManager.commit(conn);
			} catch (Exception e) {
				DBManager.rollback(conn);
				e.printStackTrace();
				
			} finally { 
				DBManager.close(conn, pstmt);
			}
		
			return result;
		
		}
		
		// post_id로 게시글 하나 조회
		public AdoptionWriteDto getAdoptionById(int postId){
			AdoptionWriteDto dto = null;
			String sql = "SELECT * FROM adoption_post WHERE post_id = ?";
		
						
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, postId);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					dto = new AdoptionWriteDto(
					rs.getInt("post_id"),
					rs.getString("user_id"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getString("adoption_type"),
					rs.getString("region"),
					rs.getString("animal_type_main"),
					rs.getString("animal_type_detail"),
					rs.getString("image_path"),
					rs.getTimestamp("created_at"),
					rs.getInt("views_count"),
					rs.getString("approvalStatus")
					);
			
			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt,rs);
			}
		
			return dto;
		} //getAdopTionList
		
		// 게시글 삭제
			public void deleteAdoption(int postId){
			String deleteAdoption = "DELETE FROM adoption_post WHERE post_id = ?";
		
			
				try{
					 conn = DBManager.getConnection();
					 pstmt = conn.prepareStatement(deleteAdoption);
					 pstmt.setInt(1, postId);
					 pstmt.executeUpdate();
											
				} catch (Exception e) {
					e.printStackTrace();
					
				} finally { 
					DBManager.close(conn, pstmt);
				}
				
			
			
			}
			
			
			// 게시글 수정
			public int updateAdoption(AdoptionWriteDto dto){
				int result = 0;
				String sql = "Update adoption_post Set title = ?, content = ?, adoptionType = ?, region = ?, "
						+ " animal_type_main = ?, animal_type_detail =?, image_path = ?, "
						+ " WHere post_id = ?";
						
			    Connection conn = null;
			    PreparedStatement pstmt = null;
		
			    
				try{
					 conn = DBManager.getConnection();
					 pstmt = conn.prepareStatement(sql);
					 pstmt.setString(1, dto.getTitle());
					 pstmt.setString(2, dto.getContent());
					 pstmt.setString(3, dto.getAdoptionType());
					 pstmt.setString(4, dto.getRegion());
					 pstmt.setString(5, dto.getAnimalTypeMain());
					 pstmt.setString(6, dto.getAnimalTypeDetail());
					 pstmt.setString(7, dto.getImagePath());
					 pstmt.setInt(8, dto.getPostId());
					 
					 
					 result = pstmt.executeUpdate();
								
				} catch (SQLException e) {
							e.printStackTrace();
							
				} finally { 
						DBManager.close(conn, pstmt, null);
				}
						
					return result;
						
				
			}

			
			// 조회수 1증가
		public void increaseViews_count(int postId){
				
				String sql = "Update adoption_post Set views_count = views_count + 1 Where post_id =?";
			
				
				try{
					conn = DBManager.getConnection();
					DBManager.setAutoCommit(conn, false);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, postId);
					pstmt.executeUpdate();
					DBManager.commit(conn);
					
				} catch (Exception e) {
					DBManager.rollback(conn);
					e.printStackTrace();
				} finally {
					DBManager.close(conn, pstmt);
					
			}
		}

		// 댓글 작성
		public int insertReply(AdoptionReplyDto replyDto) {
		    int result = 0;
		    String sql = "INSERT INTO adoption_reply (reply_id, post_id, user_id, content, is_secret) "
		                 + "VALUES (adoption_reply_seq.NEXTVAL, ?, ?, ?, ?)";

		    try {
		        conn = DBManager.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, replyDto.getPostId());
		        pstmt.setString(2, replyDto.getUserId());
		        pstmt.setString(3, replyDto.getContent());
		        pstmt.setString(4, replyDto.getIsSecret() ? "Y" : "N");

		        result = pstmt.executeUpdate();

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DBManager.close(conn, pstmt);
		    }
		    return result;
		}
		
		// 특정 게시글의 댓글 목록 조회
		public List<AdoptionReplyDto> getReplyList(int postId) {
		    List<AdoptionReplyDto> replyList = new ArrayList<>();
		    String sql = "SELECT reply_id, post_id, user_id, content, user_id, "
		    		+ " created_at, is_secret FROM adoption_reply"
		    		+ " WHERE post_id = ? ";

		    try {
		        conn = DBManager.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, postId);
		        rs = pstmt.executeQuery();
		        
		     while(rs.next()) {
		    	 AdoptionReplyDto reply = new AdoptionReplyDto();
		    	 reply.setReplyId(rs.getInt("reply_id"));
		    	 reply.setPostId(rs.getInt("post_id"));
		    	 reply.setUserId(rs.getString("user_id"));
		    	 reply.setContent(rs.getString("content"));
		    	 reply.setReplyWriter(rs.getString("user_id"));
		    	 reply.setCreatedAt(rs.getTimestamp("created_at"));
		    	 reply.setIsSecret(rs.getBoolean("is_secret"));
		    	 replyList.add(reply);
		     }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DBManager.close(conn, pstmt, rs);
		    }
		    return replyList;
		}
		
// 댓글 삭제 (본인 또는 관리자 권한 필요)
		public int deleteReply (int replyId, String userId ) {// 사용자 권한 id 로만 삭제
			int result = 0;
			String sql = "DELETE FROM adoption_reply WHERE reply_id =? AND user_id = ?";

		    try {
		        conn = DBManager.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, replyId);
		        pstmt.setString(2, userId);
		        result = pstmt.executeUpdate();
		    
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DBManager.close(conn, pstmt, rs);
		    }
		    return result;
		}
		
				
		// 특정 게시글의 댓글수 조회
		public int getReplyCount (int postId) {
			int count = 0;
			String sql = "SELECT COUNT(*) FROM adoption_reply WHERE post_id = ?";

		    try {
		        conn = DBManager.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, postId);
		        rs = pstmt.executeQuery();
		    
		        if (rs.next()) {
		        	count = rs.getInt(1);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        DBManager.close(conn, pstmt, rs);
		    }
		    return count;
		}

		public int updateApprovalStatus (int postId, String approvalStatus) {
			int result = 0;
			String sql = "UPDATE adoption_post SET approval_status = ? WHERE post_id =? ";
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, approvalStatus);
				pstmt.setInt(2, postId);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn,  pstmt);
			}
			
			return result;
		}
		
		public boolean isReplyWriter (int replyId, String userId) {
			boolean isWriter = false;
			String sql = "SELECT user_id FROM adoption_reply WHERE reply_id = ?";
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, replyId);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String writerId = rs.getString("user_id");
					if(writerId != null && writerId.equals(userId)) {
						isWriter = true;
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			return isWriter;
		}
		
				
				
}


	
	