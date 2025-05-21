package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.FaqDao;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.Faq;
import com.miniproject.vo.QnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaWriteResultService implements CommandProcess{
	   @Override
	    public String requestProcess(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        request.setCharacterEncoding("UTF-8");

	        String title = request.getParameter("title");
	        String content = request.getParameter("content");
	        String status = "답변대기"; 

	        HttpSession session = request.getSession(false);
	        String writer = null;
	        
	        if (session != null && session.getAttribute("id") != null) { // 세션의 사용자 ID 속성명이 "id"라고 가정
	            writer = (String) session.getAttribute("id");
	        } else {
	            
	            return "false"; 
	        }
	        
	        QnaBoard qnaBoard = new QnaBoard();
	        qnaBoard.setTitle(title);
	        qnaBoard.setContent(content);
	        qnaBoard.setWriter(writer);
	        qnaBoard.setStatus(status); // 상태 설정 추가
	        
	        // 4. DAO를 통해 데이터베이스에 삽입
	        QnaDao dao = new QnaDao(); 
	        int result = dao.insertQnaBoard(qnaBoard);
	        
	        if (result > 0) { // 성공
	            return "r:/team_mini/support/qnaList"; // QNA 목록 경로로 변경
	        } else {
	            return "/WEB-INF/views/support/qnaWriteForm.jsp"; // 글쓰기 폼으로 다시 이동
	        }
	    }
	}