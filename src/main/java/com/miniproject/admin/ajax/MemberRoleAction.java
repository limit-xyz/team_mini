package com.miniproject.admin.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.miniproject.common.service.AjaxProcess;
import com.miniproject.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberRoleAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String role = request.getParameter("role");
		boolean isChange = Boolean.parseBoolean(request.getParameter("change"));

		MemberDao dao = new MemberDao();
		role = dao.roleMember(id, role, isChange);

		Gson gson = new Gson();
		String result = gson.toJson(role);

		response.setContentType("application/json; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
