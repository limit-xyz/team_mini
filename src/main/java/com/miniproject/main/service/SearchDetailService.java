package com.miniproject.main.service;

import java.io.IOException;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.GlobalSearchDao;
import com.miniproject.vo.GlobalSearch;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchDetailService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		String type = request.getParameter("type");

		if (type.equals("free")) {
			
		}
			
		return "";
	}

}
