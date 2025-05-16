package com.miniproject.mypage.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.dao.DiaryDao;
import com.miniproject.vo.Diary;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DiaryUpdateFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		String id = (String) request.getSession().getAttribute("id");

		DiaryDao dao = new DiaryDao();
		boolean isDiaryOwner = dao.isDiaryOwner(id, no);
		Diary diary = dao.getDiary(no);

		if (isDiaryOwner) {
			request.setAttribute("diary", diary);
			request.setAttribute("pageNum", request.getParameter("pageNum"));
			return "member/mypage/diaryUpdateForm";
		}

		else {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('세션이 만료되었거나 잘못된 요청입니다.');");
			sb.append("	history.back();");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}
	}

}
