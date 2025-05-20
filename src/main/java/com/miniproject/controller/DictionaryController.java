package com.miniproject.controller;

import java.io.File;
import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dictionary.service.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 10, // 10KB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 10 * 10) // 100MB

@WebServlet(name = "dictionaryController", urlPatterns = "/dictionary/*")
public class DictionaryController extends HttpServlet {

	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";

	@Override
	public void init() throws ServletException {

		ServletContext sc = getServletContext();
		String uploadDir = sc.getInitParameter("uploadDir");
		String realPath = sc.getRealPath(uploadDir);

		File parentFile = new File(realPath);
		if (!(parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}
		getServletContext().setAttribute("uploadDir", uploadDir);
		getServletContext().setAttribute("parentFile", parentFile);
		System.out.println("init - " + parentFile);
	}

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

		ServletContext app = request.getServletContext();
		if (app.getAttribute("PAGE_SIZE") == null) {
			request.getServletContext().setAttribute("PAGE_SIZE", 10);
			request.getServletContext().setAttribute("PAGE_GROUP", 10);
		}

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();

		String command = requestURI.substring(contextPath.length());
		String viewPage = null;

		CommandProcess service;

		// 반려동물 다이어리 리스트
		if (command.equals("/dictionary/*") || command.equals("/dictionary/dictionaryList")) {
			service = new DictionaryListService();
			viewPage = service.requestProcess(request, response);
		}

		// 반려동물 다이어리 상세
		else if (command.equals("/dictionary/dictionayDetail")) {
//			service = new DiaryDetailService();
//			viewPage = service.requestProcess(request, response);
		}

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
