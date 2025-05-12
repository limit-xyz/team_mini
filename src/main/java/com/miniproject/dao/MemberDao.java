package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import com.miniproject.vo.Member;

public class MemberDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MemberDao() {
	}


	// 멤버 목록 가져오기
	public ArrayList<Member> getMemberList() {

		String sqlBoardList = "SELECT * FROM project_member";

		ArrayList<Member> memberList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlBoardList);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				memberList = new ArrayList<Member>();

				do {
					Member member = new Member();
					member.setNo(rs.getInt("no"));
					member.setName(rs.getString("name"));
					member.setRegDate(rs.getTimestamp("reg_date"));
					member.setIgnoreDate(rs.getTimestamp("ignore_date"));
					
					LocalDate today = LocalDate.now();
					LocalDate ignoreDate = member.getIgnoreDate().toLocalDateTime().toLocalDate();
					
					boolean isIgnore = ignoreDate.isBefore(today);
					member.setIgnore(isIgnore);

					memberList.add(member);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return memberList;
	}

}
