package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.QnaDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaAnswerDeleteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String answerNoStr = request.getParameter("answerNo");
	        String qnaNoStr = request.getParameter("qnaNo"); 
	        
	        HttpSession session = request.getSession(false);
	        boolean isAdminUser = false;

	        if (session != null) {
	            Object isAdminAttr = session.getAttribute("isAdmin");
	            if (Boolean.TRUE.equals(isAdminAttr)) { // 세션의 "isAdmin" 속성이 Boolean true인지 확인
	                isAdminUser = true;
	            }
	        }

	        if (!isAdminUser) {
	            // 관리자가 아니면 QNA 목록으로 리다이렉트 (또는 에러 페이지/로그인 페이지)
	            return "r:" + request.getContextPath() + "/support/qnaList"; 
	        }

	        if (answerNoStr == null || answerNoStr.trim().isEmpty() ||
	            qnaNoStr == null || qnaNoStr.trim().isEmpty()) {
	            if(qnaNoStr != null && !qnaNoStr.trim().isEmpty()){
	                 return "r:" + request.getContextPath() + "/support/qnaDetail?no=" + qnaNoStr;
	            }
	            return "r:" + request.getContextPath() + "/support/qnaList";
	        }

	        int answerNo = 0;
	        int qnaNo = 0;
	        
	            answerNo = Integer.parseInt(answerNoStr);
	            qnaNo = Integer.parseInt(qnaNoStr);
	     
	        QnaDao qnaDao = new QnaDao();
	        int deleteResult = qnaDao.deleteQnaAnswer(answerNo);

	        if (deleteResult > 0) {
	            qnaDao.updateQnaBoardStatus(qnaNo, "답변대기");
	        } else {
	            System.err.println("QnaAnswerDeleteService: 답변 삭제 실패 - answerNo: " + answerNo);
	        }
	        
	        return "r:" + request.getContextPath() + "/support/qnaDetail?no=" + qnaNo;
	}

}
