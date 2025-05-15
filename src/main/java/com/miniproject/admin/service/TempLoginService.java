package com.miniproject.admin.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TempLoginService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("tempId");
		request.getSession().setAttribute("id", id);

		return "admin/main";
	}

}
