package com.miniproject.adoption.service;

import java.io.IOException;
import java.sql.Timestamp;

import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionWriteDto;
import com.miniproject.helper.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionWriteService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//파라미터 수신
		String userId = request.getParameter("userId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String region = request.getParameter("region");
		String animalTypeMain = request.getParameter("animalTypeMain");
		String animalTypeDetail = request.getParameter("animalTypeDetail");
		String imagePath = request.getParameter("imagePath");
		
		//DTO 생성
		AdoptionWriteDto dto = new AdoptionWriteDto();
		dto.setUserId(userId);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setType(type);
		dto.setRegion(region);
		dto.setAnimalTypeMain(animalTypeMain);
		dto.setAnimalTypeDetail(animalTypeDetail);
		dto.setImagePath(imagePath);
		dto.setCreatedAt(new Timestamp(System.currentTimeMillis());
		dto.setViews(0);
		
		//Dao 호출
		AdoptionDao01 dao = new AdoptionDao01();
		dao.insertAdoptionPost(dto);
		
		return "redirect:/adoptionList.mvc";
		
	}

}
