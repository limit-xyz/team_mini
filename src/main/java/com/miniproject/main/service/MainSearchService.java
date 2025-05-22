package com.miniproject.main.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainSearchService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String keyword =  request.getParameter("keyword");
		
		request.setAttribute("keyword", keyword);

		return "main/search";
	}

}
