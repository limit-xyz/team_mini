package com.miniproject.adoption.dto;

import java.sql.*;
import javax.naming.*;


import org.apache.tomcat.jdbc.pool.DataSource;

public class DBManager {

	private static DataSource DS = null;
	
	
	private DBManager(){}
		
		static {
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DS = (DataSource) envContext.lookup("jdbc/adoptDBPool");
				
			} catch(NamingException e) {
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection() {
			try {
				return DS.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public static void close(Connection conn, PreparedStatement pstmt) {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		
		public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
			}
		}
		
		public static void setAutoCommit(Connection conn, boolean isAutoCommit) {
			try {
				 if(conn != null) conn.setAutoCommit(isAutoCommit);
				} catch(SQLException e) {
					e.printStackTrace();
			}
		}
		public static void commit(Connection conn) {
			try {
				 if(conn != null) conn.commit();
				} catch(SQLException e) {
					e.printStackTrace();
			}
		}
		public static void rollback(Connection conn) {
			try {
				 if(conn != null) conn.rollback();
				} catch(SQLException e) {
					e.printStackTrace();
			}
		}
	}
