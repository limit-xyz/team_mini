package com.miniproject.community.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.miniproject.common.service.AjaxProcess;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.QnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReplyReportAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String freeTitle = request.getParameter("title");
		String replyContent = request.getParameter("replyContent");
		String writer = (String) request.getSession().getAttribute("id");
		String status = "답변대기";

		if (writer == null) {
			return;
		}

		String title = "자유게시판에서 댓글이 신고되었습니다.";
		String content = String.format("신고가 발생한 글 : %s\n신고 댓글 내용 : %s", freeTitle, replyContent);

		QnaBoard qnaBoard = new QnaBoard();
		qnaBoard.setTitle(title);
		qnaBoard.setContent(content);
		qnaBoard.setWriter(writer);
		qnaBoard.setStatus(status);
		System.out.println(String.format("제목 : %s, 작성자 : %s, 내용 : %s", title, writer, content));

		QnaDao dao = new QnaDao();
		dao.insertQnaBoard(qnaBoard);

		Gson gson = new Gson();
		String result = gson.toJson("성공");

		response.setContentType("application/json; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
