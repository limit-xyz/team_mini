package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.miniproject.vo.Diary;
import com.miniproject.vo.MyBoard;

public class MyPageDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MyPageDao() {
	}

	// 다이어리 갯수 가져오기
	public int getDiaryCount(String id, String type, String keyword) {

		String getDiaryCountSql = "";
		boolean isSearch = false;

		if (type == null || keyword == null || type.equals("") || keyword.equals("")) {
			getDiaryCountSql = "SELECT count(*) count FROM diary WHERE member_id=?";
		} else {
			getDiaryCountSql = "SELECT count(*) count FROM diary WHERE member_id=? AND " + type + " LIKE ?";
			isSearch = true;
		}

		int count = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(getDiaryCountSql);
			if (isSearch) {
				pstmt.setString(1, id);
				pstmt.setString(2, "%" + keyword + "%");
			} else {
				pstmt.setString(1, id);
			}
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

	// 다이어리 상세 가져오기
	public Diary getDiary(int no) {

		String diaryDetailSql = "SELECT * FROM diary WHERE diary_no=?";

		Diary diary = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(diaryDetailSql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				diary = new Diary();

				diary.setNo(rs.getInt("diary_no"));
				diary.setMemberId(rs.getString("member_id"));
				diary.setPetName(rs.getString("pet_name"));
				diary.setTitle(rs.getString("title"));
				diary.setContent(rs.getString("content"));
				diary.setRegDate(rs.getTimestamp("reg_date"));
				diary.setPhoto(rs.getString("photo"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return diary;
	}

	// 다이어리 목록 가져오기
	public ArrayList<Diary> getDiaryList(String id, int startRow, int endRow) {

		String diaryListSql = "SELECT * FROM ( SELECT ROWNUM num, sub.* FROM "
				+ "(SELECT * FROM diary WHERE member_id=? ORDER BY diary_no DESC) sub)" + "WHERE num BETWEEN ? AND ?";

		ArrayList<Diary> diaryList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(diaryListSql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				diaryList = new ArrayList<Diary>();

				do {
					Diary diary = new Diary();
					diary.setNo(rs.getInt("diary_no"));
					diary.setMemberId(rs.getString("member_id"));
					diary.setPetName(rs.getString("pet_name"));
					diary.setTitle(rs.getString("title"));
					diary.setContent(rs.getString("content"));
					diary.setRegDate(rs.getTimestamp("reg_date"));
					diary.setPhoto(rs.getString("photo"));

					diaryList.add(diary);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return diaryList;
	}

	// 검색한 다이어리 목록 가져오기
	public ArrayList<Diary> searchDiaryList(String id, String type, String keyword, int startRow, int endRow) {

		String diaryListSql = "SELECT * FROM ( SELECT ROWNUM num, sub.* FROM "
				+ "(SELECT * FROM diary WHERE member_id=? AND " + type
				+ " LIKE ? ORDER BY diary_no DESC) sub) WHERE num BETWEEN ? AND ?";

		ArrayList<Diary> diaryList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(diaryListSql);
			pstmt.setString(1, id);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				diaryList = new ArrayList<Diary>();

				do {
					Diary diary = new Diary();
					diary.setNo(rs.getInt("diary_no"));
					diary.setMemberId(rs.getString("member_id"));
					diary.setPetName(rs.getString("pet_name"));
					diary.setTitle(rs.getString("title"));
					diary.setContent(rs.getString("content"));
					diary.setRegDate(rs.getTimestamp("reg_date"));
					diary.setPhoto(rs.getString("photo"));

					diaryList.add(diary);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return diaryList;
	}

	// 다이어리 쓰기
	public void insertDiary(Diary diary) {
		String insertDiarylSql = "INSERT INTO diary (diary_no, member_id, pet_name, title, content, photo) "
				+ "VALUES (diary_seq.NEXTVAL, ?, ?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(insertDiarylSql);
			pstmt.setString(1, diary.getMemberId());
			pstmt.setString(2, diary.getPetName());
			pstmt.setString(3, diary.getTitle());
			pstmt.setString(4, diary.getContent());
			pstmt.setString(5, diary.getPhoto());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 다이어리 수정
	public void updateDiary(Diary diary, int no) {
		String updateDiarylSql = "UPDATE diary SET pet_name=?, title=?, content=?, photo=? WHERE diary_no=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(updateDiarylSql);
			pstmt.setString(1, diary.getPetName());
			pstmt.setString(2, diary.getTitle());
			pstmt.setString(3, diary.getContent());
			pstmt.setString(4, diary.getPhoto());
			pstmt.setInt(5, diary.getNo());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 다이어리 삭제
	public void deleteDiary(int no) {

		String deleteDiarylSql = "DELETE FROM diary WHERE diary_no=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(deleteDiarylSql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 본인의 다이어리인지 체크
	public boolean isDiaryOwner(String id, int no) {

		if (id == null)
			return false;

		String isOwnerSql = "SELECT member_id FROM diary WHERE diary_no=?";

		boolean isDiaryOwner = false;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(isOwnerSql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				isDiaryOwner = id.equals(rs.getString("member_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return isDiaryOwner;
	}

	// 자신이 쓴 글 목록 가져오기
	public ArrayList<MyBoard> getMyBoardList(String id, int startRow, int endRow) {
		
		String myBoardListSql = "SELECT * FROM (SELECT ROWNUM num, sub.* FROM (SELECT no, '자유 게시판' type, title, writer, "
				+ "reg_date, read_count FROM cm01 WHERE writer=? UNION ALL SELECT post_id no, '입양/분양 게시판' type, "
				+ "title, user_id, created_at reg_date, views_count FROM adoption_post WHERE user_id=? ORDER BY reg_date DESC) sub) "
				+ "WHERE num BETWEEN ? AND ?";

		ArrayList<MyBoard> myBoardList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(myBoardListSql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				myBoardList = new ArrayList<>();

				do {
					MyBoard myBoard = new MyBoard();
					myBoard.setNo(rs.getInt("no"));
					myBoard.setType(rs.getString("type"));
					myBoard.setTitle(rs.getString("title"));
					myBoard.setWriter(rs.getString("writer"));
					myBoard.setRegDate(rs.getTimestamp("reg_date"));
					myBoard.setReadCount(rs.getInt("read_count"));

					myBoardList.add(myBoard);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return myBoardList;
	}

	// 검색한 자신이 쓴 글 목록 가져오기
	public ArrayList<MyBoard> searchMyBoardList(String id, String saerchType, String keyword, int startRow,
			int endRow) {

		String myBoardListSql = "SELECT * FROM (SELECT ROWNUM num, sub.* FROM (SELECT no, '자유 게시판' type, title, writer, "
				+ "reg_date, read_count FROM cm01 WHERE writer=? AND " + saerchType + " LIKE ? UNION ALL SELECT post_id no, "
				+ "'입양/분양 게시판' type, title, user_id, created_at reg_date, views_count FROM adoption_post WHERE user_id=? "
				+ "AND " + saerchType + " LIKE ? ORDER BY reg_date DESC) sub) WHERE num BETWEEN ? AND ?";

		ArrayList<MyBoard> myBoardList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(myBoardListSql);
			pstmt.setString(1, id);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, id);
			pstmt.setString(4, "%" + keyword + "%");
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				myBoardList = new ArrayList<>();

				do {
					MyBoard myBoard = new MyBoard();
					myBoard.setNo(rs.getInt("no"));
					myBoard.setType(rs.getString("type"));
					myBoard.setTitle(rs.getString("title"));
					myBoard.setWriter(rs.getString("writer"));
					myBoard.setRegDate(rs.getTimestamp("reg_date"));
					myBoard.setReadCount(rs.getInt("read_count"));

					myBoardList.add(myBoard);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return myBoardList;
	}

	// 자신이 쓴 글 목록의 갯수 가져오기
	public int getMyBoardCount(String id, String type, String keyword) {

		String getMyBoardCountSql = "";
		boolean isSearch = false;

		if (type == null || keyword == null || type.equals("") || keyword.equals("")) {
			getMyBoardCountSql = "SELECT count(*) count FROM (SELECT title FROM cm01 WHERE writer=? "
					+ "UNION SELECT title FROM adoption_post WHERE user_id=?)";
		} else {
			getMyBoardCountSql = "SELECT count(*) count FROM (SELECT title FROM cm01 WHERE writer=? AND " + type + " LIKE ? "
					+ "UNION SELECT title FROM adoption_post WHERE user_id=? AND " + type + " LIKE ?)";
			isSearch = true;
		}

		int count = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(getMyBoardCountSql);
			if (isSearch) {
				pstmt.setString(1, id);
				pstmt.setString(2, "%" + keyword + "%");
				pstmt.setString(3, id);
				pstmt.setString(4, "%" + keyword + "%");
			} else {
				pstmt.setString(1, id);
				pstmt.setString(2, id);
			}
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
}
