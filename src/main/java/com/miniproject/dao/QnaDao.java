package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miniproject.vo.QnaAnswer;
import com.miniproject.vo.QnaBoard;

public class QnaDao {
	
	
	public QnaDao() {
		
	}
	
	//기본 db매니저 연결 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	
	//C기능 
	
	//QNA에 글 하나를 올리는 기능 
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
	// QNA에 답변을 하나 넣는 기능 
    
    /**
     * QNA 게시글에 대한 답변을 등록합니다.
     * @param qnaAnswer 등록할 답변 정보를 담은 객체
     * @return 삽입 성공 시 1, 실패 시 0 또는 예외 발생
     */
    public int insertQnaAnswer(QnaAnswer qnaAnswer) {
        // ANSWER_NO는 시퀀스로, ANSWER_REG_DATE는 DB 디폴트(SYSDATE)로 처리
        String sql = "INSERT INTO QNA_ANSWER (ANSWER_NO, NO, ANSWER_CONTENT, ANSWER_WRITER) VALUES (QNA_ANSWER_SEQ.NEXTVAL, ?, ?, ?)";
        int result = 0;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, qnaAnswer.getNo());             // QNA_BOARD의 NO (원본 질문 번호)
            pstmt.setString(2, qnaAnswer.getAnswerContent());
            pstmt.setString(3, qnaAnswer.getAnswerWriter());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            DBManager.close(conn, pstmt);
        }
        return result;
    }

    
    
	//R기능 
    
    // QNA의 리스트를 가져오는 기능 
    
    /**
     * QNA 게시글 목록을 페이지네이션하여 가져옵니다. (최신글 순)
     * @param startRow 시작 행 번호
     * @param endRow   종료 행 번호
     * @return QnaBoard 객체 리스트
     */
    public List<QnaBoard> getQnaBoardList(int startRow, int endRow) {
        // Oracle DB 페이지네이션 쿼리
        String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, sub.* "
                   + "    FROM (SELECT * FROM QNA_BOARD ORDER BY NO DESC) sub) "
                   + " WHERE rnum >= ? AND rnum <= ?";

        List<QnaBoard> qnaBoardList = new ArrayList<>(); // 바로 초기화

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                QnaBoard qb = new QnaBoard();
                qb.setNo(rs.getInt("NO"));
                qb.setStatus(rs.getString("STATUS"));
                qb.setWriter(rs.getString("WRITER"));
                qb.setTitle(rs.getString("TITLE"));
                qb.setContent(rs.getString("CONTENT")); // 목록에서는 제목만 보여주고 내용은 상세에서 보여줄 경우, 이 부분은 제외하거나 VO에 따라 조정 가능
                qb.setRegDate(rs.getDate("REG_DATE"));
                
                qnaBoardList.add(qb);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return qnaBoardList;
    }

    
    
    // QNA의 디테일을 가져오는 기능 - 수정폼에 사용 
    
    /**
     * 특정 QNA 게시글의 상세 정보를 가져옵니다. (주로 수정폼 채우기용)
     * @param no 조회할 게시글 번호
     * @return QnaBoard 객체 (해당 번호의 글이 없으면 null 반환)
     */
    public QnaBoard getQnaDetail(int no) {
        String sql = "SELECT NO, STATUS, WRITER, TITLE, CONTENT, REG_DATE FROM QNA_BOARD WHERE NO = ?";
        QnaBoard qb = null; // QnaBoard 객체를 담을 변수

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();

            if (rs.next()) { // 해당 번호의 게시글이 존재하면
                qb = new QnaBoard();
                qb.setNo(rs.getInt("NO"));
                qb.setStatus(rs.getString("STATUS"));
                qb.setWriter(rs.getString("WRITER"));
                qb.setTitle(rs.getString("TITLE"));
                qb.setContent(rs.getString("CONTENT"));
                qb.setRegDate(rs.getDate("REG_DATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return qb; // QnaBoard 객체 또는 null 반환
    }
    
    
    // QNA의 리스트 전체 수를 가져오는 기능 - 페이지네이션에 사용 
    
    /**
     * QNA 게시판의 전체 게시글 수를 반환합니다. (페이지네이션에 사용)
     * @return 전체 게시글 수
     */
    
    public int getQnaBoardTotalCount() {
        String sql = "SELECT COUNT(*) FROM QNA_BOARD";
        int totalCount = 0; // 총 게시글 수를 담을 변수

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt(1); // COUNT(*) 결과는 첫 번째 컬럼에 있음
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return totalCount;
    }
	
	//U기능 
    
    // QNA의 디테일을 수정하는 기능 
    
    /**
     * QNA 게시글의 내용을 수정합니다.
     * @param qnaBoard 수정할 내용을 담고 있는 QnaBoard 객체 (NO 필드 필수)
     * @return 업데이트 성공 시 1, 실패 또는 대상 없음 시 0, 오류 시 음수 또는 예외 발생 가능
     */
    public int updateQnaBoard(QnaBoard qnaBoard) {
        // 일반적으로 제목, 내용, 상태 등을 수정할 수 있습니다.
        // 작성자(WRITER)나 작성일(REG_DATE)은 보통 수정하지 않습니다.
        String sql = "UPDATE QNA_BOARD SET TITLE = ?, CONTENT = ?, STATUS = ? WHERE NO = ?";
        int result = 0; // 업데이트된 행의 수를 담을 변수

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            // SQL 쿼리의 ? 순서에 맞게 값 설정
            pstmt.setString(1, qnaBoard.getTitle());
            pstmt.setString(2, qnaBoard.getContent());
            pstmt.setString(3, qnaBoard.getStatus());
            pstmt.setInt(4, qnaBoard.getNo()); 

            result = pstmt.executeUpdate(); // UPDATE 실행

        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            DBManager.close(conn, pstmt); 
        }
        return result;
    }
    
    
    
    // QNA의 답변을 수정하는 기능 
    
    /**
     * QNA 답변의 내용을 수정합니다. 답변 수정일도 현재 시간으로 업데이트됩니다.
     * @param qnaAnswer 수정할 내용을 담고 있는 QnaAnswer 객체 (answerNo 필드 필수)
     * @return 업데이트 성공 시 1, 실패 또는 대상 없음 시 0, 오류 시 음수 또는 예외 발생 가능
     */
    public int updateQnaAnswer(QnaAnswer qnaAnswer) {
        
        String sql = "UPDATE QNA_ANSWER SET ANSWER_CONTENT = ?, ANSWER_WRITER = ?, ANSWER_MOD_DATE = SYSDATE WHERE ANSWER_NO = ?";
        int result = 0; // 업데이트된 행의 수를 담을 변수

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            
            pstmt.setString(1, qnaAnswer.getAnswerContent());
            pstmt.setString(2, qnaAnswer.getAnswerWriter()); // 관리자가 답변 작성자를 변경할 경우 등을 대비
            pstmt.setInt(3, qnaAnswer.getAnswerNo());    

            result = pstmt.executeUpdate(); // UPDATE 실행

        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            DBManager.close(conn, pstmt); 
        }
        return result;
    }
	
	//D기능 
    
    // QNA게시글 하나를 삭제하는 기능 리스트에서도 사라져야함 달려있는 답변도 삭제 
    
    /**
     * QNA 게시글을 삭제합니다.
     * ON DELETE CASCADE 설정으로 인해 관련된 답변도 함께 삭제됩니다.
     * @param no 삭제할 QNA 게시글의 번호
     * @return 삭제 성공 시 1, 실패 또는 대상 없음 시 0, 오류 시 음수 또는 예외 발생 가능
     */
    public int deleteQnaBoard(int no) {
        String sql = "DELETE FROM QNA_BOARD WHERE NO = ?";
        int result = 0; // 삭제된 행의 수를 담을 변수

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, no); 

            result = pstmt.executeUpdate(); // DELETE 실행

        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            DBManager.close(conn, pstmt); 
        }
        return result;
    }
    
    // 답변 하나를 삭제 하는 기능 
    
    /**
     * QNA 답변 하나를 삭제합니다.
     * @param answerNo 삭제할 답변의 고유 번호 (QNA_ANSWER 테이블의 ANSWER_NO)
     * @return 삭제 성공 시 1, 실패 또는 대상 없음 시 0, 오류 시 음수 또는 예외 발생 가능
     */
    public int deleteQnaAnswer(int answerNo) {
        String sql = "DELETE FROM QNA_ANSWER WHERE ANSWER_NO = ?";
        int result = 0; // 삭제된 행의 수를 담을 변수

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, answerNo); 

            result = pstmt.executeUpdate(); // DELETE 실행

        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            DBManager.close(conn, pstmt); 
        }
        return result;
    }
	
	

}
