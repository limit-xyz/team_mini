package com.miniproject.admin.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.miniproject.admin.dao.MemberDao;
import com.miniproject.admin.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberDeleteAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));

		MemberDao dao = new MemberDao();
		dao.deleteMember(no);
		
		ArrayList<Member> memberList = dao.getMemberList();
		
		Gson gson = new Gson();
		String result = gson.toJson(memberList);

		response.setContentType("application/json; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
