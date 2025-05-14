package com.miniproject.helper.service;

import java.io.IOException;

import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionWriterService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//파라미터 수신
		String userId = request.getParameter("userId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String region = request.getParameter("region");
		String animalTypeMain = request.getParameter("animal_type_main");
		String animalTypeDetail = request.getParameter("animal_type_detail");
		String imagePath = request.getParameter("image_path");
		
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
		
		//Dao 호출
		AdoptionBoardDao dao = new AdoptionBoardDao();
		dao.insertPost(dto);
		
		return "redirect:/adoptionList.mvc";
		
	}

}
