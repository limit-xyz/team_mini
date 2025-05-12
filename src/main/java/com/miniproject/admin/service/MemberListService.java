package com.miniproject.admin.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.miniproject.admin.dao.MemberDao;
import com.miniproject.admin.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberListService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Member> memberList = new ArrayList<>();
		
		MemberDao dao = new MemberDao();
		memberList = dao.getMemberList();
		
		LocalDate today = LocalDate.now();
	   
		request.setAttribute("memberList", memberList);
		request.setAttribute("today", today);
		
		return "admin/memberList";
	}

}
