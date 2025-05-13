package com.miniproject.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.miniproject.admin.vo.Member;

public class MemberDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MemberDao() {
	}

	// 멤버 목록 가져오기
	public ArrayList<Member> getMemberList() {

		String memberListSql = "SELECT * FROM member";

		ArrayList<Member> memberList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(memberListSql);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				memberList = new ArrayList<Member>();

				do {
					Member member = new Member();
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setPassword(rs.getString("password"));
					member.setGender(rs.getString("gender"));
					member.setMobile(rs.getString("mobile"));
					member.setAddress(rs.getString("address"));
					member.setEmail(rs.getString("email"));
					member.setRegDate(rs.getTimestamp("reg_date"));
					member.setIntroduction(rs.getString("introduction"));
					member.setBirthDate(rs.getTimestamp("birth_date"));
					member.setBanDate(rs.getTimestamp("ban_date"));
					member.setBanReason(rs.getString("ban_reason"));
					member.setRole(rs.getString("role"));

					member.setBan(member.getBanDate());

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
	public Member banMember(String id, int banDate) {
		String banMemberSql = "UPDATE member SET ban_date = SYSDATE + ? WHERE id=?";
		String getMemberSql = "SELECT * FROM member WHERE id=?";

		Member member = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(banMemberSql);
			pstmt.setInt(1, banDate);
			pstmt.setString(2, id);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(getMemberSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new Member();

				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setGender(rs.getString("gender"));
				member.setMobile(rs.getString("mobile"));
				member.setAddress(rs.getString("address"));
				member.setEmail(rs.getString("email"));
				member.setRegDate(rs.getTimestamp("reg_date"));
				member.setIntroduction(rs.getString("introduction"));
				member.setBirthDate(rs.getTimestamp("birth_date"));
				member.setBanDate(rs.getTimestamp("ban_date"));
				member.setBanReason(rs.getString("ban_reason"));
				member.setRole(rs.getString("role"));

				member.setBan(member.getBanDate());
				
				member.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return member;
	}

	// 멤버 차단 해제
	public void releaseMember(String id) {
		String releaseMemberSql = "UPDATE member SET ban_date = SYSDATE-1 WHERE id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(releaseMemberSql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 멤버 삭제
	public void deleteMember(String id) {
		String deleteMemberSql = "DELETE FROM member WHERE id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(deleteMemberSql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
}
