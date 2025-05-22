package com.miniproject.dictionary.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DictionaryWriteFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = (String) request.getSession().getAttribute("id");
		String type = request.getParameter("type");
		String searchType = request.getParameter("searchDiaryType");
		String searchKeyword = request.getParameter("searchDiaryKeyword");

		boolean isSearch = false;
		if (searchType != null && searchKeyword != null && !searchType.equals("") && !searchKeyword.equals(""))
			isSearch = true;

		if (id == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('세션이 만료되었습니다.\n다시 로그인해주세요.');");
			sb.append("	location.href='" + request.getContextPath() + "/member/loginForm'");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}

		if (isSearch) {
			request.setAttribute("searchDiaryOption", "1");
			request.setAttribute("searchDiaryType", searchType);
			request.setAttribute("searchDiaryKeyword", searchKeyword);
		}

		request.setAttribute("pageNum", request.getParameter("pageNum"));

		if (type.equals("dog")) {
			return "dictionary/dictionaryDogWriteForm";
		}

		else if (type.equals("cat")) {
			return "dictionary/dictionaryCatWriteForm";
		}

		else {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('잘못된 요청입니다.');");
			sb.append("	history.back()");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}
	}

}
