package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.miniproject.vo.Diary;
import com.miniproject.vo.MyBoard;
import com.miniproject.vo.GlobalSearch;

public class GlobalSearchDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public GlobalSearchDao() {
	}

	// 통합 검색 목록 가져오기
	public ArrayList<GlobalSearch> getGlobalSearchList(String keyword) {

		String globalSearchList = "SELECT * FROM ( SELECT ROWNUM num, sub.* FROM "
				+ "(SELECT * FROM diary WHERE member_id=? ORDER BY diary_no DESC) sub)" + "WHERE num BETWEEN ? AND ?";

		ArrayList<GlobalSearch> searchList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(globalSearchList);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				searchList = new ArrayList<>();

				do {
					GlobalSearch search = new GlobalSearch();
					search.setTitle(rs.getString("title"));
					search.setContent(rs.getString("content"));
					search.setType(rs.getString("type"));
					search.setWriter(rs.getString("writer"));

					searchList.add(search);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return searchList;
	}

}
