package com.miniproject.support.service;

import java.io.IOException;
import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.QnaAnswer;
import com.miniproject.vo.QnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaDetailService implements CommandProcess {

    @Override
    public String requestProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String qnaNoStr = request.getParameter("no");
        System.out.println("QnaDetailService: 요청된 게시글 번호 (String): " + qnaNoStr); 

        if (qnaNoStr == null || qnaNoStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "잘못된 접근입니다. 게시글 번호가 없습니다.");
            System.err.println("QnaDetailService: 'no' 파라미터 없음 또는 비어 있음."); 
            return "support/errorPage"; 
        }

        int qnaNo = 0;
        try {
            qnaNo = Integer.parseInt(qnaNoStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "유효하지 않은 게시글 번호입니다.");
            System.err.println("QnaDetailService: 'no' 파라미터 숫자 변환 실패 - " + qnaNoStr); 
            return "support/errorPage"; 
        }
        
 

        QnaDao qnaDao = new QnaDao(); 
        QnaBoard qnaBoard = qnaDao.getQnaDetail(qnaNo); 
        QnaAnswer qnaAnswer = qnaDao.getAnswerByQnaNo(qnaNo); 
        
        if (qnaAnswer != null) {
            request.setAttribute("qnaAnswer", qnaAnswer);
        }

        request.setAttribute("qnaBoard", qnaBoard);
        
       

        return "support/qnaDetail"; 
    }
}