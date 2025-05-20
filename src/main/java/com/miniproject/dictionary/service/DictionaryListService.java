package com.miniproject.dictionary.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.DiaryDao;
import com.miniproject.dao.DictionaryDao;
import com.miniproject.vo.Animal;
import com.miniproject.vo.Diary;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DictionaryListService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Animal> dictionaryList = new ArrayList<>();
		DictionaryDao dao = new DictionaryDao();

		String searchType = request.getParameter("searchDictionaryType");
		String searchKeyword = request.getParameter("searchDictionaryKeyword");

		boolean isSearch = false;
		if (searchType != null && searchKeyword != null && !searchType.equals("") && !searchKeyword.equals(""))
			isSearch = true;

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

		listCount = dao.getDictionaryCount(searchType, searchKeyword);

		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);

		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 - (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		int endPage = startPage + PAGE_GROUP - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		if (isSearch) {
			request.setAttribute("searchDictionaryOption", "1");
			request.setAttribute("searchDictionaryType", searchType);
			request.setAttribute("searchDictionaryKeyword", searchKeyword);
			dictionaryList = dao.searchDictionaryList(searchType, searchKeyword, startRow, endRow);
		}

		else {
			dictionaryList = dao.getDictionaryList(startRow, endRow);
		}
		request.setAttribute("dictionaryList", dictionaryList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		return "dictionary/dictionaryList";
	}

}
