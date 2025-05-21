package com.miniproject.support.service;

import java.io.IOException;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.QnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaListService implements CommandProcess {

	// 한 페이지에 보여줄 게시 글 수
	private static final int PAGE_SIZE = 5;

	// 한 페이지에 보여줄 페이지 그룹의 수
	// [이전] 1 2 3 4 .... 10 [다음]
	private static final int PAGE_GROUP = 10;

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pageNum = request.getParameter("pageNum");

		// pageNem이 없으면 기본으로 1페이지를 보여줌
		if (pageNum == null) {
			pageNum = "1";
		}
		// 문자열이던 페이지번호를 숫자로 변환
		int currentPage = Integer.parseInt(pageNum);

		// 게시글 몇개를 가져올지 계산함
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		int endRow = startRow + PAGE_SIZE - 1;

		QnaDao dao = new QnaDao();
		ArrayList<QnaBoard> qnaList = null;
		int listCount = 0;

		qnaList = (ArrayList<QnaBoard>) dao.getQnaBoardList(startRow, endRow);
		listCount = dao.getQnaBoardTotalCount();

		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 - (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);

		// 페이지 네이션 마지막 페이지
		int endPage = startPage + PAGE_GROUP - 1;

		if (endPage > pageCount) {
			endPage = pageCount;
		}

		request.setAttribute("qnaList", qnaList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageGroup", PAGE_GROUP);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		// dao에서 db에 담긴 리스트 정보를 받아서
		// qnaList 뷰페이지로 이동
		return "support/qnaList";
	}

}
