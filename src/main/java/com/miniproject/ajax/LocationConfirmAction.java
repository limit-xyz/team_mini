package com.miniproject.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.mimproject.vo.AnimalHospital;
import com.miniproject.dao.BoardDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LocationConfirmAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String dataSelect = req.getParameter("dataSelect");
		// System.out.println(dataSelect);
		
		BoardDao dao = new BoardDao();
		List<AnimalHospital> animalHospitalList = dao.locationConfirm(dataSelect);
		
		Gson gson = new Gson();
		String result = gson.toJson(animalHospitalList);
		
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(result);
	}

	
}
