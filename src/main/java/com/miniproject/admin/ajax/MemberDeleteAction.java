package com.miniproject.admin.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.miniproject.common.service.AjaxProcess;
import com.miniproject.dao.MemberDao;
import com.miniproject.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberDeleteAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemberDao dao = new MemberDao();
		String id = request.getParameter("id");
		dao.deleteMember(id);

		String searchId = request.getParameter("serachMemberId");

		ArrayList<Member> memberList;
		if (searchId == null)
			memberList = dao.getMemberList(false);
		else
			memberList = dao.searchMemberList(searchId);

		Gson gson = new Gson();
		String result = gson.toJson(memberList);

		response.setContentType("application/json; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
