package com.miniproject.admin.service;

import java.io.IOException;
import java.util.ArrayList;

import com.miniproject.dao.MemberDao;
import com.miniproject.vo.Member;

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
	   
		request.setAttribute("memberList", memberList);
		
		return "admin/memberList";
	}

}
