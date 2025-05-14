package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.miniproject.vo.Diary;

public class DiaryDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DiaryDao() {
	}

	// 다이어리 목록 가져오기
	public ArrayList<Diary> getDiaryList(String id) {

		String diaryListSql = "SELECT * FROM diary WHERE member_id=? ORDER BY diary_no DESC";

		ArrayList<Diary> diaryList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(diaryListSql);
			pstmt.setString(1, id);
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

}
