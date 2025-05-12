package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.miniproject.vo.Member;

public class MemberDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MemberDao() {
	}

	// 멤버 목록 가져오기
	public ArrayList<Member> getMemberList() {

		String memberListSql = "SELECT * FROM project_member";

		ArrayList<Member> memberList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(memberListSql);
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

					boolean isIgnore = today.isBefore(ignoreDate);
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

	// 멤버 차단하고, 갱신된 차단일자 저장
	public Member ignoreMember(int no, int ignoreDate) {
		String ignoreMemberSql = "UPDATE project_member SET ignore_date = SYSDATE + ? WHERE no = ?";
		String getDateSql = "SELECT * FROM project_member WHERE no=?";

		Member member = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(ignoreMemberSql);
			pstmt.setInt(1, ignoreDate);
			pstmt.setInt(2, no);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(getDateSql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();

				member.setNo(rs.getInt("no"));
				member.setName(rs.getString("name"));
				member.setRegDate(rs.getTimestamp("reg_date"));
				member.setIgnoreDate(rs.getTimestamp("ignore_date"));
				member.setIgnore(true);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return member;
	}

	// 멤버 차단 해제
	public void releaseMember(int no) {
		String ignoreMemberSql = "UPDATE project_member SET ignore_date = SYSDATE WHERE no = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(ignoreMemberSql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 멤버 삭제
	public void deleteMember(int no) {
		String deleteMemberSql = "DELETE FROM project_member WHERE no=?";

		Member member = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(deleteMemberSql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
}
