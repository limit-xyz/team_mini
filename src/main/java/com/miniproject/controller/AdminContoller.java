package com.miniproject.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "adminController", urlPatterns = "/admin/*")
public class AdminContoller extends HttpServlet {

	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);

	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String viewPage = null;

		if (true)
			viewPage = "admin/main";

		if (viewPage != null) {
			String view = viewPage.split(":")[0];

			if (view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);
			}

			else if (view.equals("f") || view.equals("forward")) {
				RequestDispatcher rd = request.getRequestDispatcher(viewPage.split(":")[1]);
				rd.forward(request, response);
			}

			else {
				RequestDispatcher rd = request.getRequestDispatcher(PREFIX + view + SUFFIX);
				rd.forward(request, response);
			}
		}
	}

}
