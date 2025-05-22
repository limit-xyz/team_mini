package com.miniproject.mypage.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.MyPageDao;
import com.miniproject.vo.MyBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyBoardListService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<MyBoard> myBoardList = new ArrayList<>();
		MyPageDao dao = new MyPageDao();

		String id = (String) request.getSession().getAttribute("id");
		String searchType = request.getParameter("searchBoardType");
		String searchKeyword = request.getParameter("searchBoardKeyword");

		boolean isSearch = false;
		if (searchType != null && searchKeyword != null && !searchType.equals("") && !searchKeyword.equals(""))
			isSearch = true;

		if (id == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('세션이 만료되었습니다.\n다시 로그인해주세요.');");
			sb.append("	location.href='" + request.getContextPath() + "/member/loginForm");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int PAGE_SIZE = (int) request.getServletContext().getAttribute("PAGE_SIZE");
		int PAGE_GROUP = (int) request.getServletContext().getAttribute("PAGE_GROUP");

		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		int endRow = startRow + PAGE_SIZE - 1;
		int listCount = 0;

		listCount = dao.getMyBoardCount(id, searchType, searchKeyword);

		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 - (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		int endPage = startPage + PAGE_GROUP - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		if (isSearch) {
			request.setAttribute("searchBoardOption", "1");
			request.setAttribute("searchBoardType", searchType);
			request.setAttribute("searchBoardKeyword", searchKeyword);
			myBoardList = dao.searchMyBoardList(id, searchType, searchKeyword, startRow, endRow);
		}

		else {
			myBoardList = dao.getMyBoardList(id, startRow, endRow);
		}
		request.setAttribute("boardList", myBoardList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		return "member/mypage/boardList";
	}

}
