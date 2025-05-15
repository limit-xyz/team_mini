package com.miniproject.adoption.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class AdoptionWriteService implements CommandProcess{

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		AdoptionWriteDto dto = new AdoptionWriteDto();
		System.out.println(System.getProperty("java.io.tmpdir"));
		
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();
		
		if(contentType != null
				&& contentType.toLowerCase().startsWith("multiplart/")) {
			
			Collection<Part> parts = request.getParts();
			
			for(Part part : parts) {
				String PartHeader = part.getHeader("Content-Disposition");
				System.out.println(PartHeader);
				System.out.printf("파라미터 : %s, contentType : %s, size : %dByte, \n",
						part.getName(), part.getContentType(), part.getSize());
				if(PartHeader.contains("image_path=")) {
					if(part.getSize() > 0) {
						UUID uid = UUID.randomUUID();
						String saveName = uid.toString() + "_" + part.getSubmittedFileName();
							File parentFile = (File) request.getServletContext().getAttribute("parentFile");
						String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
						
						part.write(savePath);
						dto.setImagePath(saveName);
						part.delete();
						
					} else {
						System.out.println("파일이 업로드 되지 않음");
					}
				} else {
			}
		}
		
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
		dto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		dto.setViews(0);
		
		//Dao 호출
		AdoptionDao01 dao = new AdoptionDao01();
		dao.insertAdoptionPost(dto);
		
		return "redirect:/adoptionList.mvc";
		
	}

}
