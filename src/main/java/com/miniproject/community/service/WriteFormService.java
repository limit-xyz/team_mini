package com.miniproject.community.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = (String) request.getSession().getAttribute("id");
		if (id == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('로그인이 필요합니다.');");
			sb.append("	location.href='" + request.getContextPath() + "/member/loginForm'");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}

		String searchType = request.getParameter("type");
		String searchKeyword = request.getParameter("keyword");

		boolean isSearch = false;
		if (searchType != null && searchKeyword != null && !searchType.equals("") && !searchKeyword.equals(""))
			isSearch = true;

		if (isSearch) {
			request.setAttribute("type", searchType);
			request.setAttribute("keyword", searchKeyword);
		}
		request.setAttribute("pageNum", request.getParameter("pageNum"));

		return "free/writeForm";
	}

}
