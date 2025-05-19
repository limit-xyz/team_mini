package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FaqUpdateFormService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return "support/faqUpdateForm";
	}

}
