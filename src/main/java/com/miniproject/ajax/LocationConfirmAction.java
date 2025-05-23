package com.miniproject.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.miniproject.common.service.AjaxProcess;
import com.miniproject.dao.BoardDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.mimproject.vo.*;

public class LocationConfirmAction implements AjaxProcess{

	@Override
	public void ajaxProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String dataSelect = req.getParameter("dataSelect");
		String searchOption = req.getParameter("searchOption");
		String nearby = req.getParameter("nearby");
		String firstOption = req.getParameter("firstOption");

		System.out.println("LocationConfirmAction 실행됨");
		System.out.println(dataSelect);
		System.out.println(searchOption);
		System.out.println(nearby);
		
		List<AnimalHospital> animalHospitalList = null;
		BoardDao dao = new BoardDao();
		
		if(firstOption != null) {
			animalHospitalList = dao.newLocationConfirm(dataSelect);
		}
		else {
			Double lat = Double.parseDouble(req.getParameter("lat"));
			Double lng = Double.parseDouble(req.getParameter("lng"));
		
			if(nearby == "1" || nearby.equals("1")) {
				if(searchOption.equals("")||searchOption==null) {
					 animalHospitalList = dao.locationConfirmNearby(dataSelect, lat, lng);
				} else {
					 animalHospitalList = dao.locationConfirmNearby(dataSelect, lat, lng, searchOption);
				} 
			}
			
			else if(searchOption.equals("")||searchOption==null) {
				 animalHospitalList = dao.locationConfirm(dataSelect);
			} else {
				 animalHospitalList = dao.locationConfirm(dataSelect,searchOption);
			} 
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(animalHospitalList);
		
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(result);
	}
}
