package com.miniproject.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.miniproject.dao.BoardDao;
import com.miniproject.vo.AnimalHospital;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LocationConfirmAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String dataSelect = req.getParameter("dataSelect");
		String searchOption = req.getParameter("searchOption");
		
		List<AnimalHospital> animalHospitalList = null;
		BoardDao dao = new BoardDao();
		
		if(searchOption.equals("")||searchOption==null) {
			 animalHospitalList = dao.locationConfirm(dataSelect);
		} else {
			 animalHospitalList = dao.locationConfirm(dataSelect,searchOption);
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(animalHospitalList);
		
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(result);
	}

	
}
