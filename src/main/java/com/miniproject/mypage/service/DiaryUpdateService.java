package com.miniproject.mypage.service;

import java.io.IOException;

import com.miniproject.dao.DiaryDao;
import com.miniproject.vo.Diary;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DiaryUpdateService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		String id = request.getParameter("memberId");

		Diary diary = new Diary();

		diary.setNo(no);
		diary.setPetName(request.getParameter("petName"));
		diary.setTitle(request.getParameter("title"));
		diary.setContent(request.getParameter("content"));
//		diary.setPhoto(request.getParameter("memberId"));

		DiaryDao dao = new DiaryDao();
		dao.updateDiary(diary, no);

		return "r:diaryList?id=" + id;
	}

}
