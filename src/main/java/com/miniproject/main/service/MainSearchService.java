package com.miniproject.main.service;

import java.io.IOException;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.GlobalSearchDao;
import com.miniproject.vo.GlobalSearch;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainSearchService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String keyword =  request.getParameter("searchKeyword");
		request.setAttribute("keyword", keyword);
		
		GlobalSearchDao dao = new GlobalSearchDao();
		ArrayList<GlobalSearch> globalSearchList = dao.getGlobalSearchList(keyword);
		request.setAttribute("searchList", globalSearchList);

		return "main/search";
	}

}
