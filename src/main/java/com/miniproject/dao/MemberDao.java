package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.miniproject.vo.Member;
import com.miniproject.vo.Member1;

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

	// 멤버 역할 변경
	public String roleMember(String id, String role, boolean isChange) {
		String changeRoleSql = "UPDATE member SET role=? WHERE id=?";
		String getRoleSql = "SELECT role FROM member WHERE id=?";

		try {
			conn = DBManager.getConnection();
			if (isChange) {
				pstmt = conn.prepareStatement(changeRoleSql);
				pstmt.setString(1, role);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
			} else {
				pstmt = conn.prepareStatement(getRoleSql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					role = rs.getString("role");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return role;
	}

	// 멤버 차단하고, 갱신된 차단일자 저장
	public Member banMember(String id, String reason, int banDate) {
		String banMemberSql = "UPDATE member SET ban_date = SYSDATE + ?, ban_reason=? WHERE id=?";
		String getMemberSql = "SELECT * FROM member WHERE id=?";

		Member member = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(banMemberSql);
			pstmt.setInt(1, banDate);
			pstmt.setString(2, reason);
			pstmt.setString(3, id);
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

	public void updateMember(Member member) {

		String updateSql = "UPDATE member SET id=?, name=?, password=?, gender=?, email=?, mobile=?, address=?, reg_date=SYSDATE, "
				+ "introduction=?, birth_date=? WHERE id=?";

		try {
			// DBCP로부터 Connection을 대여한다.
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(updateSql);

			// loginSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getMobile());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getIntroduction());
			pstmt.setTimestamp(9, member.getBirthDate());
			pstmt.setString(10, member.getId());

			// DB에 쿼리를 발행하여 회원 정보를 저장한다.
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// id에 해당하는 회원 정보를 DB에서 읽어와 반환하는 메서드
	public Member getMember(String id) {
		Member member = null;
		String selectMember = "SELECT * FROM member WHERE id = ?";

		try {
			// DBCP로부터 Connection을 대여한다.
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(selectMember);

			// loginSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setString(1, id);

			// DB에 쿼리를 전송하여 결과를 ResultSet으로 받는다.
			rs = pstmt.executeQuery();

			// if 안으로 들어가면? 못들어가면 ?
			if (rs.next()) {
				// 한 명의 회원 정보를 Member 객체에 저장

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return member;
	}

//	member 객체
//	String id;
//	String name;
//	String password;
//	String gender;
//	String mobile;
//	String address;
//	String email;
//	Timestamp regDate;
//	String introduction;	
//	Timestamp birthDate;	
//	Timestamp banDate;		차단 남은 날짜 ,설정 안해도 됨
//	String banReason;		차단 사유, 설정 안해도 됨
//	String role;			기본값 user, 설정 안해도 됨
//	boolean ban;			member.setBan(member.getBanDate()); 하면 설정됨

// 	회원 정보를 DB에 등록하는 메서드   (아직 수정 안함) ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	public void joinMember(Member member) {
/*
		String joinSql = "INSERT INTO member " + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE , 0)";

		try {
			// DBCP로부터 Connection을 대여한다.
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(joinSql);

			// loginSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPass());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getMobile());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getZipcode());
			pstmt.setString(8, member.getAddress1());
			pstmt.setString(9, member.getAddress2());
			pstmt.setBoolean(10, member.isEmailGet());

			// DB에 쿼리를 발행하여 회원 정보를 저장한다.
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}*/
	}

// 회원 아이디가 중복되는지 확인해 주는 메서드  ( 아직 수정 안함)
	public boolean overlapIdCheck(String id) {
		boolean result = false;
		String overlapSql = "SELECT id FROM member WHERE id = ?";

		try {
			// DBCP로부터 Connection을 대여한다.
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(overlapSql);

			// loginSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setString(1, id);

			// DB에 쿼리를 전송하여 결과를 ResultSet으로 받는다.
			rs = pstmt.executeQuery();

			// if 안으로 들어가면? 못들어가면 ?
			if (rs.next()) {
				// 사용자가 입력한 id가 조회되었다는 건 중복
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

// 회원 로그인을 처리하는 메소드  (아직 수정 안함)
	public int checkMember(String id, String pass) {

		String loginSql = "SELECT password FROM member WHERE id = ?";
		// -1 아이디 없음, 0 비밀번호 틀림, 1 로그인 성공
		// 1, 2, 3
		int result = -1;
		String password = "";

		try {
			// DBCP로부터 Connection을 대여한다.
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(loginSql);

			// loginSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.
			pstmt.setString(1, id);

			// DB에 쿼리를 전송하여 결과를 ResultSet으로 받는다.
			rs = pstmt.executeQuery();

			/*
			 * ResultSet에 데이터가 존재하지 않으면 가입된 회원이 아니므로 -1을 반환 회원 id는 Primary Key로 중복되지 않기 때문에
			 * 회원 테이블에서 SELECT한 결과가 단일 행으로 반환 되므로 if문을 사용해 rs.next()를 호출했다.
			 **/
			if (rs.next()) {
				// ResultSet에 데이터가 존재하면 ID에 대한 비밀번호를 읽어 온다.
				password = rs.getString("pass");
			} else {
				return result;
			}

			/*
			 * 로그인 요청시 입력한 비밀번호와 회원 테이블에서 SELECT한 결과로 읽어온 비밀번호가 일치하면 1을 반환 하고 일치하지 않으면 0을 반환
			 * 한다.
			 **/
			if (password.equals(pass)) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

	// admin 인지 체크하는 메소드, 아직 틀만 잡아놓고 사용은 안하는중
	public boolean isAdmin(String id) {
		String CheckAdminSql = "SELECT role FROM member WHERE id=?";
		String role = "";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(CheckAdminSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				role = rs.getString("role");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return role.equals("admin");
	}
}
