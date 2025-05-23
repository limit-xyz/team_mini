package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.QnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdoptionReplyReportService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String freeTitle = request.getParameter("title");
		String replyContent = request.getParameter("replyContent");
		String writer = request.getParameter("userId");
		String postId = request.getParameter("postId");
		String pageNum = request.getParameter("pageNum");
		String status = "답변대기";

		String title = "입양/분양 게시판에서 댓글이 신고되었습니다.";
		String content = String.format("신고가 발생한 글 : %s\n신고 댓글 내용 : %s", freeTitle, replyContent);

		QnaBoard qnaBoard = new QnaBoard();
		qnaBoard.setTitle(title);
		qnaBoard.setContent(content);
		qnaBoard.setWriter(writer);
		qnaBoard.setStatus(status);

		QnaDao dao = new QnaDao();
		dao.insertQnaBoard(qnaBoard);

		return "r:" + request.getContextPath() + "/adoption/AdoptionDetail?postId=" + postId + "&pageNum=" + pageNum;
	}
}
