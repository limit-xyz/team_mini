package com.miniproject.controller;

import java.io.IOException;

import com.miniproject.adoption.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionUpdateFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String spostId = request.getParameter("postId");
		String userId = request.getParameter("userId");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(spostId == null || spostId.equals("") || userId == null || userId.equals("")
				|| pageNum == null || pageNum.equals(""));
		response.setContentType("text/html; charset=utf-8");
		
		
		
		
		return null;
	}

}
