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

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int PAGE_SIZE = (int) request.getServletContext().getAttribute("PAGE_SIZE");
		int PAGE_GROUP = (int) request.getServletContext().getAttribute("PAGE_GROUP");

		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		int endRow = startRow + PAGE_SIZE - 1;
		int listCount = 0;
		
		String searchId = request.getParameter("searchMemberId");
		listCount = dao.getMemberCount(searchId);

		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 - (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		int endPage = startPage + PAGE_GROUP - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		if (searchId == null) {
			boolean isBanSort = false;
			if (request.getParameter("banSort") != null) {
				request.setAttribute("searchOption", "0");
				request.setAttribute("isBanSort", "1");
				isBanSort = true;
			}

			memberList = dao.getMemberList(startRow, endRow, isBanSort);
		}

		else {
			request.setAttribute("searchId", searchId);
			request.setAttribute("searchOption", "1");
			memberList = dao.searchMemberList(searchId, startRow, endRow);
		}

		request.setAttribute("memberList", memberList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		return "admin/memberList";
	}

}
