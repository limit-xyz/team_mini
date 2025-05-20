package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.miniproject.dao.DBManager;
import com.miniproject.vo.QnaBoard;

public class QnaDao {
	
	
	public QnaDao() {
		
	}
	
	//기본 db매니저 연결 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	
	//C기능 
    public int insertQnaBoard(QnaBoard qnaBoard) {
        String sql = "INSERT INTO QNA_BOARD (NO, STATUS, WRITER, TITLE, CONTENT) VALUES (QNA_BOARD_SEQ.NEXTVAL, ?, ?, ?, ?)";
        int result = 0;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, qnaBoard.getStatus());
            pstmt.setString(2, qnaBoard.getWriter());
            pstmt.setString(3, qnaBoard.getTitle());
            pstmt.setString(4, qnaBoard.getContent());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return result;
    }
	
	//R기능 
	
	//U기능 
	
	//D기능 
	
	
	public Faq getQaq(int faqNo) {
	}

}
