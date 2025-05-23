package com.miniproject.community.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.miniproject.common.service.AjaxProcess;
import com.miniproject.dao.ComDao;
import com.miniproject.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReplyDeleteAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String replyNo = request.getParameter("replyNo");
		String boardNo = request.getParameter("boardNo");

		Reply reply = new Reply();
		reply.setNo(Integer.parseInt(replyNo));
		reply.setBbsNo(Integer.parseInt(boardNo));

		ComDao dao = new ComDao();
		dao.deleteReply(reply);

		ArrayList<Reply> replyList = dao.getReplyList(Integer.parseInt(boardNo));

		Gson gson = new Gson();
		String result = gson.toJson(replyList);

		response.setContentType("application/json; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
