package com.miniproject.mypage.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.DiaryDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DiaryDeleteService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		String id = (String) request.getSession().getAttribute("id");
		String pageNum = request.getParameter("pageNum");

		String searchType = request.getParameter("searchDiaryType");
		String searchKeyword = request.getParameter("searchDiaryKeyword");
		boolean isSearch = false;
		if (searchType != null && searchKeyword != null && !searchType.equals("") && !searchKeyword.equals(""))
			isSearch = true;

		DiaryDao dao = new DiaryDao();
		boolean isDiaryOwner = dao.isDiaryOwner(id, no);

		if (isDiaryOwner) {
			String url = "r:diaryList?id=" + id + "&pageNum=" + pageNum;
			if (isSearch) {
				url += String.format("&searchDiaryType=%s&searchDiaryContent=%s", searchType, searchKeyword);
			}

			dao.deleteDiary(no);
			return url;
		}

		else {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('잘못된 요청입니다.');");
			sb.append("	history.back();");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}
	}
}
