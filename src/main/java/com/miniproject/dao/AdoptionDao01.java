package com.miniproject.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
					dto.setType(rs.getString("type"));
					dto.setRegion(rs.getString("region"));
					dto.setAnimalTypeMain(rs.getString("animal_type_main"));
					dto.setAnimalTypeDetail(rs.getString("animal_type_detail"));
					dto.setImagePath(rs.getString("image_path"));
					dto.setCreatedAt(rs.getTimestamp("created_at"));
					dto.setViews(rs.getInt("views"));
					dto.setApprovalStatus(rs.getString("approval_status"));
			}
			DBManager.commit(conn);
				
		} catch (Exception e) {
			DBManager.rollback(conn);
			e.printStackTrace();
			
		} finally { 
			DBManager.close(conn, pstmt, rs);
		}
		
		return dto;
	
	}
	
	
	// 페이지 단위로 게시글 목록 조회
		public ArrayList<AdoptionWriteDto> boardList(int startRow, int endRow) {
			
			String sql = "SELECT * FROM (SELECT ROWNUM num, sub.* "
					+ "    FROM (SELECT * FROM adoption_post ORDER BY post_id DESC) sub) "
					+ " WHERE num >= ? AND num <= ?";
			ArrayList<AdoptionWriteDto> bList = null;
			
			try {		
				// 2. DB에 연결
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
							
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				
				// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
				rs = pstmt.executeQuery();
				
				// 여러 개의 Board 객체를 담을 ArrayList를 사용
				bList = new ArrayList<>();
				
				// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
				while(rs.next()) {
					AdoptionWriteDto b = new AdoptionWriteDto();
					b.setPostId(rs.getInt("post_id"));
					b.setTitle(rs.getString("title"));
					b.setUserId(rs.getString("user_id"));
					b.setCreatedAt(rs.getTimestamp("created_at"));
					b.setViews(rs.getInt("views"));
					// b.setPass(rs.getString("approval_status"));
					b.setImagePath(rs.getString("image_path"));
				
					bList.add(b);
				}
			} catch(SQLException e) {
				e.printStackTrace();
				
			} finally {
				// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
				DBManager.close(conn, pstmt, rs);
			}
			
			return bList;
		}
		
	
	
	//----------------------------------------------------------
	// 전체 게시글 수를 읽어오는 메서드 - paging 처리에 사용
		public int getBoardCount() {
			String sqlBoard = "SELECT COUNT(*) FROM adoption_post";
			int count = 0;
			
			try {
				conn = DBManager.getConnection();		
				pstmt = conn.prepareStatement(sqlBoard);			
				rs = pstmt.executeQuery();

				if(rs.next()) {
					count = rs.getInt(1);
				}			
			} catch(SQLException e) {
				//System.out.println("BoardDao - getBoardCount() : SQLException");
				e.printStackTrace();
				
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return count;		
		}		
	
	
	//------------------------------------------------------------------
	// 제목, 작성자, 내용에 검색어가 포함된 게시 글 리스트를 읽어와 반환하는 메서드
		public ArrayList<AdoptionWriteDto> searchList( //String approval_status
				String searchColumn,String keyword, String adoptiontype, String animalTypeMain, int startRow, int endRow) {
			
			List<String> allowedTypes = Arrays.asList("title", "user_id", "region");
			if(searchColumn == null || !allowedTypes.contains(searchColumn)) {
				throw new IllegalArgumentException("허용되지 않은 검색 컬럼 입니다." + searchColumn);
			}
			
			String sql = "SELECT * FROM ( "
					+ "    SELECT ROWNUM num, sub.* FROM "
					+ "        (SELECT * FROM adoption_post "
					+ " WHERE " + searchColumn + " LIKE ?"
					+ " and animal_type_main =? and type=? "
					+ " ORDER BY post_id DESC) sub) "
					+ " WHERE num >= ? AND num <= ?";
			
			ArrayList<AdoptionWriteDto> bList = new ArrayList<>();
			
			
			try {			
				// 2. DB에 연결
				conn = DBManager.getConnection();
				
				// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
				// PreparedStatement
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  "%" + keyword + "%");			//여기 주석처리 했음 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
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
					dto.setViews(rs.getInt("views"));
					// b.setApprovalStatus(rs.getString("approval_status"));
					dto.setImagePath(rs.getString("image_path"));
					dto.setType(rs.getString("type"));
					
					bList.add(dto);
				}
			} catch(SQLException e) {
				e.printStackTrace();
				
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			return bList;
		}
	
	//-----------------------------------------------------------------------
	// 검색어가 포함된 게시글 수를 읽어오는 메서드 - 검색 paging 처리에 사용
		public int getBoardCount(String type, String keyword) {
			// title, writer, content
			String sqlBoard = "SELECT COUNT(*) FROM adoption_post "
					+ " WHERE " + type + " LIKE '%' || ? || '%'";
			int count = 0;
			
			try {
				conn = DBManager.getConnection();		
				pstmt = conn.prepareStatement(sqlBoard);
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
				reply.setReplyWriter(rs.getString("reply_writer"));
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
		
		//---------------------------------------------------------
		// 게시글 전체 조회
		public ArrayList<AdoptionWriteDto> getAdopTionList(){
			String sql = "Select * From adoption_post Order by Post_Id Desc";
			ArrayList<AdoptionWriteDto> list = new ArrayList<>();
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					AdoptionWriteDto dto = new AdoptionWriteDto(
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
						rs.getInt("views"),
						rs.getString("approval_status")
					);
					list.add(dto);
				}
			}	 catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt,rs);
			}
			
			return list;
		} //getAdopTionList
		
		// 게시글 상세 조회
		public AdoptionWriteDto getAdopTion(int postId, boolean state){
			
			String sqlAdoptionList = "Select * From adoption_post WHERE Post_Id=?";
			String countSql = "UPDATE adoption_post SET views = views + 1 WHERE post_id=?";
			AdoptionWriteDto dto = null;
			PreparedStatement countStmt = null;
			PreparedStatement selectStmt = null;
			
			
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
						dto.setType(rs.getString("type"));
						dto.setRegion(rs.getString("region"));
						dto.setAnimalTypeMain(rs.getString("animal_type_main"));
						dto.setAnimalTypeDetail(rs.getString("animal_type_detail"));
						dto.setImagePath(rs.getString("image_path"));
						dto.setCreatedAt(rs.getTimestamp("created_at"));
						dto.setViews(rs.getInt("views"));
				}
				DBManager.commit(conn);
					
			} catch (Exception e) {
				DBManager.rollback(conn);
				e.printStackTrace();
				
			} finally { 
				DBManager.close(null, countStmt);
				DBManager.close(conn, selectStmt, rs);
			}
			
			return dto;
		
		}

			// 게시글 작성
		public int insertAdoptionPost(AdoptionWriteDto dto){
			int result = 0;
			String sql = "INSERT INTO adoption_post (post_id, user_id, title, content, type, region, animal_type_main, animal_type_detail, image_path, created_at, views) "
					   + "VALUES (adoption_post_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, 0)";
					
			try{
			 conn = DBManager.getConnection();
			 DBManager.setAutoCommit(conn, false);
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, dto.getUserId());
				 pstmt.setString(2, dto.getTitle());
				 pstmt.setString(3, dto.getContent());
				 pstmt.setString(4, dto.getType());
				 pstmt.setString(5, dto.getRegion());
				 pstmt.setString(6, dto.getAnimalTypeMain());
				 pstmt.setString(7, dto.getAnimalTypeDetail());
				 pstmt.setString(8, dto.getImagePath());
				 pstmt.setTimestamp(9, dto.getCreatedAt());
				 pstmt.setInt(10, dto.getViews());
			
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
		public AdoptionWriteDto getAdopTionById(int postId){
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
					rs.getString("type"),
					rs.getString("region"),
					rs.getString("animal_type_main"),
					rs.getString("animal_type_detail"),
					rs.getString("image_path"),
					rs.getTimestamp("created_at"),
					rs.getInt("views"),
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
			public void deleteAdoption(AdoptionWriteDto dto){
			String sql = "DELETE FROM adoption_post WHERE post_id = ?";
		
				try{
					 conn = DBManager.getConnection();
					 pstmt = conn.prepareStatement(sql);
					 pstmt.setInt(1, postId);
					 pstmt.executeUpdate();
											
				} catch (Exception e) {
					e.printStackTrace();
					
				} finally { 
					DBManager.close(conn, pstmt, rs);
				}
				
			
			}
			
			
			// 게시글 수정
			public int updateAdoption(AdoptionWriteDto dto){
				int result = 0;
				String sql = "Update adoption_post Set title = ?, content = ?, type = ?, region = ?, "
						+ "WHere post_id =?";
						
				try {
						conn=DBManager.getConnection();
						pstmt=conn.prepareStatement(sql){
		
				try{
					 conn = DBManager.getConnection();
					 pstmt = conn.prepareStatement(sql);
					 pstmt.setString(1, dto.getTitle());
					 pstmt.setString(2, dto.getContent());
					 pstmt.setString(3, dto.getType());
					 pstmt.setString(4, dto.getRegion());
					 pstmt.setString(5, dto.getAnimalTypeMain());
					 pstmt.setString(6, dto.getAnimalTypeDetail());
					 pstmt.setString(7, dto.getImagePath());
					 pstmt.setInt(8, dto.getPostId());
					 
					 
					 result = pstmt.executeUpdate();
								
				} catch (Exception e) {
							e.printStackTrace();
							
				} finally { 
						DBManager.close(conn, pstmt, rs);
				}
						
					return result;
					
			}
			
			
			
			// 조회수 1증가
		public void increaseViews(int postId){
				
				String sql = "Update adoption_post Set views = views + 1 Where post_id =?";
			
				
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

		
}


	
	