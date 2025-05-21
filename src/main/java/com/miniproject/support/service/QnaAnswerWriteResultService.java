package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.QnaAnswer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaAnswerWriteResultService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");

		 String qnaNoStr = request.getParameter("qnaNo");
	        String answerContent = request.getParameter("answerContent");
	        
	        HttpSession session = request.getSession(false);
	        String answerWriter = null;

	        if (session != null && session.getAttribute("id") != null) {
	            answerWriter = (String) session.getAttribute("id");
	        } else {
	            return "r:" + request.getContextPath() + "/support/qnaList"; 
	        }

	        if (qnaNoStr == null || qnaNoStr.trim().isEmpty() ||
	            answerContent == null || answerContent.trim().isEmpty()) {
	             if(qnaNoStr != null && !qnaNoStr.trim().isEmpty()) {
	                 return "r:" + request.getContextPath() + "/support/qnaDetail?no=" + qnaNoStr;
	             }
	            return "r:" + request.getContextPath() + "/support/qnaList";
	        }

	        int qnaNo = 0;
	        try {
	            qnaNo = Integer.parseInt(qnaNoStr);
	        } catch (NumberFormatException e) {
	            return "r:" + request.getContextPath() + "/support/qnaList";
	        }
	        
	        QnaAnswer qnaAnswer = new QnaAnswer();
	        qnaAnswer.setNo(qnaNo); 
	        qnaAnswer.setAnswerContent(answerContent);
	        qnaAnswer.setAnswerWriter(answerWriter);

	        QnaDao qnaDao = new QnaDao();
	        int insertResult = qnaDao.insertQnaAnswer(qnaAnswer);
	        
	        if (insertResult > 0) {
	            qnaDao.updateQnaBoardStatus(qnaNo, "답변완료");
	        } else {
	            // 답변 등록 실패 시
	            System.err.println("QnaAnswerWriteResultService: 답변 등록 실패");
	        }
	        
	        return "r:" + request.getContextPath() + "/support/qnaDetail?no=" + qnaNo;
	}

}
