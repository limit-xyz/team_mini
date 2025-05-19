package com.miniproject.member.service;

import java.io.IOException;

import com.miniproject.dao.MemberDao;
import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IdCheckService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// id 중복 확인 요청을 처리
		String id = request.getParameter("id");
		
		MemberDao dao = new MemberDao();
		boolean overlap = dao.overlapIdCheck(id);
		
		request.setAttribute("id", id);
		request.setAttribute("overlap", overlap);
		
		// member/idCheck
		return "f:/WEB-INF/member/idCheck.jsp";
		
	}

}
