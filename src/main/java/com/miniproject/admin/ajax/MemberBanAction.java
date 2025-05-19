package com.miniproject.admin.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.miniproject.common.service.AjaxProcess;
import com.miniproject.dao.MemberDao;
import com.miniproject.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberBanAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String reason = request.getParameter("reason");
		int banDate = Integer.parseInt(request.getParameter("date"));

		MemberDao dao = new MemberDao();
		Member member = dao.banMember(id, reason, banDate);
		
		Gson gson = new Gson();
		String result = gson.toJson(member);

		response.setContentType("application/json; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
