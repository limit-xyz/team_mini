package com.miniproject.adoption.service;

import java.io.IOException;
import java.util.ArrayList;

import com.miniproject.adoption.dto.AdoptionDao01;
import com.miniproject.adoption.dto.AdoptionWriteDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionListService implements CommandProcess {

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdoptionDao01 dao = new AdoptionDao01();
		ArrayList<AdoptionWriteDao> list = dao.getAdopTionList();
		request.setAttribute("adoptionList", list);
		
		
		return "adoption/adiptionList.mvc";
	}

}
