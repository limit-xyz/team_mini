package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.miniproject.vo.Community;
import com.miniproject.vo.Reply;

public class ComDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 제목, 작성자, 내용에 검색어가 포함된 게시글 리스트를 읽어와 반환하는 메서드
	public ArrayList<Community> searchList(String type, String keyword, int startRow, int endRow) {

		String sqlBoardList = "SELECT * FROM ( " + "    SELECT ROWNUM num, sub.* FROM "
				+ "        (SELECT * FROM cm01 WHERE " + type + "			LIKE ? ORDER BY no DESC) sub) "
				+ " WHERE num >= ? AND num <= ?";

		ArrayList<Community> bList = null;

		try {

			// DB 연결
			conn = DBManager.getConnection();

			// DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구함
			pstmt = conn.prepareStatement(sqlBoardList);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			// 쿼리를 발행해 SELECT한 결과를 ResultSet 객체로 받음
			rs = pstmt.executeQuery();

			// 여러개의 Community 객체를 담을 ArrayList 사용
			bList = new ArrayList<>();

			// While로 실행반복하며 Community 객체에 담고 List 담는다
			while (rs.next()) {
				Community b = new Community();
				b.setNo(rs.getInt("no"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));
				b.setPass(rs.getString("pass"));
				b.setFile1(rs.getString("file1"));

				bList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return bList;

	}

	// 전체 게시글 수를 읽어오는 메서드 - paging 처리에 사용
	public int getBoardCount() {

		String sqlBoard = "SELECT COUNT(*) FROM cm01";
		int count = 0;

		try {

			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlBoard);
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

	// 검색어가 포함된 게시글 수를 읽어오는 메서드 - 검색 paging 처리에 사용
	public int getBoardCount(String type, String keyword) {
		// 제목, 작성자, 내용
		String sqlBoard = " SELECT COUNT(*) FROM cm01 " + " WHERE " + type + " LIKE '%' || ? || '%'";
		int count = 0;

		try {

			// DB연결
			conn = DBManager.getConnection();

			// 쿼리를 발행하는 객체 생성
			pstmt = conn.prepareStatement(sqlBoard);
			pstmt.setString(1, keyword);
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

	// 한 페이지에 보여질 게시글리스트 요청
	// 요청한 페이지에 해당하는 게시글리스트를 DB에서 읽어와 반환
	public ArrayList<Community> boardList(int startRow, int endRow) {

		String sqlBoardList = "SELECT * FROM (SELECT ROWNUM num, sub.* "
				+ " FROM (SELECT * FROM cm01 ORDER BY no DESC) sub) " + "WHERE num >= ? AND num <= ?";

		ArrayList<Community> bList = null;

		try {

			// DB연결
			conn = DBManager.getConnection();

			// 쿼리를 발행하는 객체 생성
			pstmt = conn.prepareStatement(sqlBoardList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			// 쿼리를 발행
			rs = pstmt.executeQuery();

			// 여러개의 Community 객체를 담을 ArrayList 사용
			bList = new ArrayList<>();

			// 쿼리의 실행 결과를 while 반복하면서 Community 객체에 담고 List 담는다
			while (rs.next()) {

				Community b = new Community();
				b.setNo(rs.getInt("no"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));
				b.setPass(rs.getString("pass"));
				b.setFile1(rs.getString("file1"));
				
				bList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bList;

	}

	// no에 해당하는 게시글을 DB에서 삭제하는 메서드
	public void deleteBoard(int no) {

		String deleteBoard = "DELETE FROM cm01 WHERE no=?";

		try {

			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리를 발행하는 객체 생성
			pstmt = conn.prepareStatement(deleteBoard);
			pstmt.setInt(1, no);

			// 쿼리 발행 후 SELECT한 결과를 Resultset 객체로 받음
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 게시글 상세보기
	// 게시글 번호에 해당하는 하나의 게시글을 DB에서 읽어와 반환하는 메서드
	public Community getBoard(int no) {

		String sqlBoardList = "SELECT * FROM cm01 WHERE no=?";
		String countSql = "UPDATE cm01 SET read_count = read_count + 1 WHERE no=?";
		Community b = null;

		try {

			// DB 연결
			conn = DBManager.getConnection();

			// 트랜잭션 시작
			DBManager.setAutoCommit(conn, false);

			// 게시글 조회 요청일 때 게시글 조회 수를 1 증가 시킨다.
			// countSql은 UPDATE 이기 때문에 executeUpdate를 사용
			pstmt = conn.prepareStatement(countSql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

			// DB에 SQL 쿼리를 발행하는 객체를 활생화된 커넥션으로부터 구한다.
			pstmt = conn.prepareStatement(sqlBoardList);
			pstmt.setInt(1, no);

			// countSql은 SELECT 이기 때문에 executeQuery를 사용
			// pstmt.executeQuery는 거의 사용 x, 결과값은 rs = pstmt.executeQuery(); 사용
			rs = pstmt.executeQuery();

			if (rs.next()) {
				b = new Community();
				b.setNo(rs.getInt("no"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setContent(rs.getString("content"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));
				b.setPass(rs.getString("pass"));
				b.setFile1(rs.getString("file1"));
			}

			DBManager.commit(conn);

		} catch (SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return b;
	}
	
	public int getReplyCount(int no) {

		String getReplyCountSql = "SELECT count(*) count FROM cm01_reply WHERE board_no = ?";
		int count = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(getReplyCountSql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return count;
	}

	public ArrayList<Reply> getReplyList(int bbsNo) {

		String replyListSql = "SELECT * FROM cm01_reply WHERE board_no=? " + " ORDER BY no DESC ";
		ArrayList<Reply> replyList = null;

		try {

			// DB연결
			conn = DBManager.getConnection();

			// 쿼리를 발행하는 객체 생성
			pstmt = conn.prepareStatement(replyListSql);
			pstmt.setInt(1, bbsNo);

			rs = pstmt.executeQuery();

			replyList = new ArrayList<>();

			while (rs.next()) {
				Reply reply = new Reply();
				reply.setNo(rs.getInt("no"));
				reply.setBbsNo(rs.getInt("board_no"));
				reply.setReplyContent(rs.getString("reply_content"));
				reply.setReplyWriter(rs.getString("reply_writer"));
				reply.setRegDate(rs.getTimestamp("reg_date"));
				replyList.add(reply);
			}

		} catch (SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return replyList;
	}

	// 게시글 하나를 받아서 DB에 저장하는 메서드
	public void insertBoard(Community b) {

		String insertBoard = "INSERT INTO cm01 (no, title, writer, content, reg_date, read_count, file1, recommend, thank) "
				+ "VALUES(cm01_seq.NEXTVAL, ?, ?, ?, SYSDATE, 0, ?, 0, 0)";

		try {

			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(insertBoard);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getWriter());
			pstmt.setString(3, b.getContent());
			pstmt.setString(4, b.getFile1());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}

	}

	// 게시글의 내용을 수정하는 정보를 받아서 DB에서 수정하는 메서드
	public void updateBoard(Community b) {

		// 파일이 업로드 되었을때 - 업로드된 파일로 치환
		String fileUpload = b.getFile1() != null ? ", file1=?" : "";

		// 파일이 업로드 되지 않았을때 기존값을 유지 - 바꿀 필요가 없음
		String updateBoard = " UPDATE cm01 SET title=?, content=?" + fileUpload + " WHERE no=? ";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(updateBoard);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());

			if (b.getFile1() != null) {
				pstmt.setString(3, b.getFile1());
				pstmt.setInt(4, b.getNo());

			} else {
				pstmt.setInt(3, b.getNo());
			}
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 게시글 번호에 해당하는 추천/땡큐를 증가, 갱신하고 다시 읽어와 반환하는 메서드
	public HashMap<String, Integer> getRecommend(int no, String strThank) {

		// 어떤 때는 추천/땡큐 갱신
		String addRecommendSql = " UPDATE cm01 set recommend = recommend + 1 " + " WHERE no =? ";

		String addThankSql = " UPDATE cm01 set thank = thank + 1 " + " WHERE no = ? ";

		// 갱신된 추천/땡큐를 다시 읽어옴
		String selectResultSql = " SELECT recommend, thank FROM cm01 " + "WHERE no = ? ";

		HashMap<String, Integer> map = null;

		try {
			conn = DBManager.getConnection();

			DBManager.setAutoCommit(conn, false);

			if (strThank.equals("commend")) { // 추천 요청이면
				pstmt = conn.prepareStatement(addRecommendSql);

			} else if (strThank.equals("thank")) { // 땡큐 요청이면
				pstmt = conn.prepareStatement(addThankSql);
			}

			pstmt.setInt(1, no);

			// 쿼리를 발행해서 추천/땡큐를 갱신
			pstmt.executeUpdate();

			// 새로 갱신된 추천/땡큐를 읽어옴
			pstmt = conn.prepareStatement(selectResultSql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				map = new HashMap<>();
				map.put("recommend", rs.getInt(1));
				map.put("thank", rs.getInt(2));
			}

			// 작업이 완료되면 트랜잭션 종료
			DBManager.commit(conn);

		} catch (SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return map;
	}

	// 댓글 하나의 데이터를 받아서 DB에 저장하는 메서드
	public void insertReply(Reply reply) {

		String insertReply = "INSERT INTO cm01_reply VALUES(reply_seq.NEXTVAL, ?, ?, ?, SYSDATE)";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(insertReply);
			pstmt.setInt(1, reply.getBbsNo());
			pstmt.setString(2, reply.getReplyContent());
			pstmt.setString(3, reply.getReplyWriter());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 댓글 번호에 해당하는 댓글을 DB에서 수정하는 메서드
	public void updateReply(Reply reply) {

		String updateReply = "UPDATE cm01_reply SET reply_content=? WHERE no=?";

		try {

			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(updateReply);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 댓글 번호에 해당하는 댓글을 DB에서 삭제하는 메서드
	public void deleteReply(Reply reply) {

		String deleteReply = "DELETE FROM cm01_reply WHERE no=? ";

		try {

			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(deleteReply);
			pstmt.setInt(1, reply.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public boolean isOwner(int no, String id) {

		String isOwnerSql = "SELECT writer FROM cm01 WHERE no=?";
		String writer = "";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(isOwnerSql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				writer = rs.getString("writer");
			}

			DBManager.commit(conn);

		} catch (SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return id.equals(writer);
	}

}
