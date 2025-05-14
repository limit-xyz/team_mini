package com.miniproject.mypage.service;

import java.io.IOException;
import java.util.ArrayList;

import com.miniproject.dao.DiaryDao;
import com.miniproject.dao.MemberDao;
import com.miniproject.vo.Diary;
import com.miniproject.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DiaryListService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Diary> diaryList = new ArrayList<>();
		
		String id = (String) request.getSession().getAttribute("id");
		
		// 임시 사용
		if (id == null) {	
			id = "user02";
		}
		
		DiaryDao dao = new DiaryDao();
		diaryList = dao.getDiaryList(id);
	   
		request.setAttribute("diaryList", diaryList);
		
		return "member/mypage/diaryList";
	}

}
