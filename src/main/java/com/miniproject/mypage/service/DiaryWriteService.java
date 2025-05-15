package com.miniproject.mypage.service;

import java.io.IOException;

import com.miniproject.dao.DiaryDao;
import com.miniproject.vo.Diary;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DiaryWriteService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("memberId");
		
		Diary diary = new Diary();
				
		diary.setMemberId(id);
		diary.setPetName(request.getParameter("petName"));
		diary.setTitle(request.getParameter("title"));
		diary.setContent(request.getParameter("content"));
//		diary.setPhoto(request.getParameter("memberId"));

		DiaryDao dao = new DiaryDao();
		dao.insertDiary(diary);

		return "r:diaryList?id=" + id;
	}

}
