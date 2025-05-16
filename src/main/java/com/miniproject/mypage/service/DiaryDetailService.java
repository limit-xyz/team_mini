package com.miniproject.mypage.service;

import java.io.IOException;

import com.miniproject.dao.DiaryDao;
import com.miniproject.vo.Diary;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DiaryDetailService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Diary diary = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		DiaryDao dao = new DiaryDao();
		diary = dao.getDiary(no);
	   
		request.setAttribute("diary", diary);
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		
		return "member/mypage/diaryDetail";
	}

}
