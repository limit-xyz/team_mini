package com.miniproject.mypage.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.miniproject.dao.DiaryDao;
import com.miniproject.vo.Diary;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DiaryListService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Diary> diaryList = new ArrayList<>();

		String id = (String) request.getSession().getAttribute("id");
		
		if (id == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('로그인이 필요합니다.');");
			sb.append("	location.href='" + request.getContextPath() + "/admin/main'");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}

		DiaryDao dao = new DiaryDao();
		diaryList = dao.getDiaryList(id);

		request.setAttribute("diaryList", diaryList);

		return "member/mypage/diaryList";
	}

}
