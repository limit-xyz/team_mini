package com.miniproject.admin.service;

import java.io.IOException;
import java.util.ArrayList;

import com.miniproject.dao.InquiryDao;
import com.miniproject.vo.Inquiry;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InquiryListService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Inquiry> inquiryList = new ArrayList<>();
		
		InquiryDao dao = new InquiryDao();
		inquiryList = dao.getInquiryList();
		
		request.setAttribute("inquiryList", inquiryList);
		
		return "admin/inquiryList";
	}

}
