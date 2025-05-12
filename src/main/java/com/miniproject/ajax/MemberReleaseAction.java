package com.miniproject.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberReleaseAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));

		MemberDao dao = new MemberDao();
		dao.releaseMember(no);

		response.setContentType("application/json; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print("ok");
	}
}
