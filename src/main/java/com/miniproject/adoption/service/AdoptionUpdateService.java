package com.miniproject.adoption.service;

import java.io.IOException;

import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionUpdateService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String region = request.getParameter("region");
		
		AdoptionWriteDto dto = new AdoptionWriteDto();
		dto.setPostId(postId);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setRegion(region);
		
		AdoptionDao01 dao = new AdoptionDao01();
		dao.updateAdoption(dto);
		
		return null;
	}

}
