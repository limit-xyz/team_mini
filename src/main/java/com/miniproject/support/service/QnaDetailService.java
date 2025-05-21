package com.miniproject.support.service;

import java.io.IOException;
import java.sql.SQLException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.QnaAnswer;
import com.miniproject.vo.QnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaDetailService implements CommandProcess {

    @Override
    public String requestProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String qnaNoStr = request.getParameter("no");
        // 🎯 점검 1: "no" 파라미터가 제대로 전달되고 있는지 확인
        System.out.println("QnaDetailService: 요청된 게시글 번호 (String): " + qnaNoStr); 

        if (qnaNoStr == null || qnaNoStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "잘못된 접근입니다. 게시글 번호가 없습니다.");
            System.err.println("QnaDetailService: 'no' 파라미터 없음 또는 비어 있음."); // 🐞 디버깅 로그
            return "support/errorPage"; 
        }

        int qnaNo = 0;
        try {
            qnaNo = Integer.parseInt(qnaNoStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "유효하지 않은 게시글 번호입니다.");
            System.err.println("QnaDetailService: 'no' 파라미터 숫자 변환 실패 - " + qnaNoStr); // 🐞 디버깅 로그
            return "support/errorPage"; 
        }
        
        // 🎯 점검 2: qnaNo 값이 올바르게 변환되었는지 확인
        System.out.println("QnaDetailService: 변환된 게시글 번호 (int): " + qnaNo);

        QnaDao qnaDao = new QnaDao(); 
        QnaBoard qnaBoard = qnaDao.getQnaDetail(qnaNo); 

        // 🎯 점검 3: DAO에서 qnaBoard 객체를 제대로 가져오는지 확인
        if (qnaBoard == null) {
            request.setAttribute("errorMessage", "해당 번호의 게시글을 찾을 수 없습니다. (번호: " + qnaNo + ")");
            System.err.println("QnaDetailService: DAO에서 게시글 조회 실패 - qnaBoard is null for no: " + qnaNo); // 🐞 디버깅 로그
            return "support/errorPage"; 
        }
        System.out.println("QnaDetailService: 조회된 qnaBoard 제목: " + qnaBoard.getTitle()); // 🐞 디버깅 로그
        
        QnaAnswer qnaAnswer = qnaDao.getAnswerByQnaNo(qnaNo); 
        // 🎯 점검 4: 답변 조회 결과 확인 (null일 수 있음)
        if (qnaAnswer != null) {
            request.setAttribute("qnaAnswer", qnaAnswer);
            System.out.println("QnaDetailService: 조회된 qnaAnswer 내용: " + qnaAnswer.getAnswerContent()); // 🐞 디버깅 로그
        } else {
            System.out.println("QnaDetailService: 해당 질문에 대한 답변 없음 (번호: " + qnaNo + ")"); // 🐞 디버깅 로그
        }

        request.setAttribute("qnaBoard", qnaBoard);
        
       
        System.out.println("QnaDetailService: support/qnaDetail 뷰로 포워딩"); // 🐞 디버깅 로그

        return "support/qnaDetail"; 
    }
}