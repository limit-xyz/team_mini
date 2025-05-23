package com.miniproject.admin.service;

import java.io.IOException;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
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

		String searchId = request.getParameter("searchMemberId");
		
		if (searchId == null) {
			boolean isBanSort = false;
			if (request.getParameter("banSort") != null) {
				request.setAttribute("searchOption", "0");
				request.setAttribute("isBanSort", "1");
				isBanSort = true;
			}
			memberList = dao.getMemberList(isBanSort);
		}

		else {
			memberList = dao.searchMemberList(searchId);
		}

		request.setAttribute("memberList", memberList);

		return "admin/memberList";
	}

}
