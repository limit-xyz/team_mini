package com.miniproject.adoption.service;

import java.io.IOException;

import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionViewService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		AdoptionDao01 dao = new AdoptionDao01();
		AdoptionWriteDto post = dao.getPostById(postId);
		
		request.setAttribute("post", post);
		return "forward:/adoption/adoptionView.jsp";
	}

}
