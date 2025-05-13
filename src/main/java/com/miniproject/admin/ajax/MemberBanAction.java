package com.miniproject.admin.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.miniproject.dao.MemberDaoDoyoung;
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

		MemberDaoDoyoung dao = new MemberDaoDoyoung();
		Member member = dao.banMember(id, reason, banDate);
		
		Gson gson = new Gson();
		String result = gson.toJson(member);

		response.setContentType("application/json; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
