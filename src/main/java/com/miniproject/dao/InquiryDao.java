package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.miniproject.vo.Inquiry;

public class InquiryDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public InquiryDao() {
	}

	// 멤버 목록 가져오기
	public ArrayList<Inquiry> getInquiryList() {

		String inquiryListSql = "SELECT * FROM inquiry";

		ArrayList<Inquiry> inquiryList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(inquiryListSql);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				inquiryList = new ArrayList<Inquiry>();

				do {
					Inquiry inquiry = new Inquiry();
					inquiry.setNo(rs.getInt("no"));
					inquiry.setUserId(rs.getString("user_id"));
					inquiry.setCategory(rs.getString("category"));
					inquiry.setTitle(rs.getString("title"));
					inquiry.setContent(rs.getString("content"));
					inquiry.setStatus(rs.getString("status"));
					inquiry.setRegDate(rs.getTimestamp("reg_date"));

					inquiryList.add(inquiry);

				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return inquiryList;
	}

}
