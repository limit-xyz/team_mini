package com.miniproject.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.miniproject.dao.MemberDao;
import com.miniproject.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberIgnoreAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		int ignoreDate = Integer.parseInt(request.getParameter("date"));

		MemberDao dao = new MemberDao();
		Member member = dao.ignoreMember(no, ignoreDate);
		
		Gson gson = new Gson();
		String result = gson.toJson(member);

		response.setContentType("application/json; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
