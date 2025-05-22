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
		
		String searchKeyword = "'%" + keyword + "%'";

		String globalSearchList = "SELECT no, title, content, 'free' type, writer FROM cm01 WHERE NVL(title, '') || NVL(content, '') || NVL(writer, '') LIKE " + searchKeyword + "  UNION ALL "
				+ "SELECT post_id no, title, content, 'adoption' type, user_id writer FROM adoption_post WHERE NVL(title, '') || NVL(content, '') || NVL(user_id, '') LIKE " + searchKeyword + "  UNION ALL "
				+ "SELECT id no, name_ko title, history content, 'dog' type, 'Expert' writer FROM dog WHERE NVL(name_ko, '') || NVL(name_en, '') || NVL(dog_size, '') || NVL(origin, '') || NVL(history, '') "
				+ "|| NVL(personality, '') || NVL(coat, '') || NVL(color, '') || NVL(exercise, '') || NVL(health_issues, '') || NVL(environment, '') LIKE " + searchKeyword + "  UNION ALL "
				+ "SELECT id no, name_ko title, history content, 'cat' type, 'Expert' writer FROM cat WHERE NVL(name_ko, '') || NVL(name_en, '')  || NVL(breed_type, '') || NVL(origin, '') || NVL(history, '') || "
				+ "NVL(personality, '') || NVL(coat, '') || NVL(color, '') || NVL(health_issues, '') || NVL(environment, '') LIKE " + searchKeyword + "  UNION ALL "
				+ "SELECT no, title, DBMS_LOB.SUBSTR(content), 'qna' type, writer FROM qna_board WHERE NVL(title, '') ||  NVL(content, '') ||  NVL(writer, '') LIKE " + searchKeyword + "   UNION ALL "
				+ "SELECT faq_no no, faq_title title, DBMS_LOB.SUBSTR(faq_content) content, 'faq' type, faq_author writer FROM faq WHERE NVL(faq_title, '') ||  NVL(faq_content, '') ||  NVL(faq_author, '') LIKE " + searchKeyword + "  ORDER BY TITLE";

		ArrayList<GlobalSearch> searchList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(globalSearchList);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				searchList = new ArrayList<>();

				do {
					GlobalSearch search = new GlobalSearch();
					search.setNo(rs.getInt("no"));
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
