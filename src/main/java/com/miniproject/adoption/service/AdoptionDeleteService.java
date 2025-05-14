package com.miniproject.adoption.service;

import java.io.IOException;

import com.miniproject.dao.AdoptionDao01;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionDeleteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		AdoptionDao01 dao = new AdoptionDao01();
		dao.deleteAdoption(postId);
		
		return "redirec:/adoptionList.mvc";
	}

}
