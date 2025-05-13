package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {
	
	private static DataSource DS = null;
	private static Connection CONN = null;
	
	// 인스턴스 생성 막음 -> 전부 static
	private DBManager() {}
	
	// 생성자 보다 빨리 로딩
	static {
		// META-INF 에 context.xml 붙여넣기
		//1. DBCP 자원을 찾는다.
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DS = (DataSource) envContext.lookup("jdbc/bbsDBPool");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	// DB Connection 객체 반환 메서드
	public static Connection getConnection() {
		try {
			CONN = DS.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CONN;
	}
	
	// DB 작업에 사용된 객체 해제하는 메서드 1
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DB 작업에 사용된 객체 해제하는 메서드 2
	public static void close(
			Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Transaction 시작
	public static void setAutoCommit(Connection conn, boolean isAutoCommit) {
		try {
				if(conn != null) conn.setAutoCommit(isAutoCommit);
		} catch (SQLException e) {
				
				e.printStackTrace();
		}
	}
	
	// Transaction 종료
	public static void commit(Connection conn) {
		try {
			if(conn != null) conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Transaction 되돌리기
	public static void rollback(Connection conn) {
		try {
				if(conn != null) conn.rollback();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
