package com.miniproject.dictionary.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.DictionaryDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DictionaryDeleteService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int animalId = Integer.parseInt(request.getParameter("animalId"));
		String type = request.getParameter("type");
		String pageNum = request.getParameter("pageNum");

		String searchType = request.getParameter("searchDiaryType");
		String searchKeyword = request.getParameter("searchDiaryKeyword");
		boolean isSearch = false;
		if (searchType != null && searchKeyword != null && !searchType.equals("") && !searchKeyword.equals(""))
			isSearch = true;

		DictionaryDao dao = new DictionaryDao();

		String url = "r:dictionaryList?pageNum=" + pageNum;
		if (isSearch) {
			url += String.format("&searchDiaryType=%s&searchDiaryContent=%s", searchType, searchKeyword);
		}

		dao.deleteDictionary(animalId, type);
		return url;
	}

}
