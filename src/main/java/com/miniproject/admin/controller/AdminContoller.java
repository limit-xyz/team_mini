package com.miniproject.admin.controller;

import java.io.IOException;

import com.miniproject.admin.ajax.AjaxController;
import com.miniproject.admin.service.*;

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

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();

		String command = requestURI.substring(contextPath.length());
		String viewPage = null;

		CommandProcess service;

		/*
		 * 어드민인지 확인 절차 필요
		 */

		// ajax 요청 처리
		String[] splitTest1 = command.split("/");
		if (splitTest1.length > 0) {
			String str = splitTest1[splitTest1.length - 1];
			String[] splitTest2 = str.split("\\.");

			if (splitTest2.length > 1 && splitTest2[1].equals("ajax")) {
				AjaxController ajax = new AjaxController();
				ajax.doAjax(request, response, str);
				return;
			}
		}

		// 메인 화면, 관리 메뉴들이 존재
		if (command.equals("/admin/main") || command.equals("/admin/*")) {
			service = new AdminMainService();
			viewPage = service.requestProcess(request, response);
		}

		// 멤버 관리 화면
		else if (command.equals("/admin/member")) {
			service = new MemberListService();
			viewPage = service.requestProcess(request, response);
		}
		
		// 문의내역 관리 화면
		else if (command.equals("/admin/inquiry")) {
			service = new InquiryListService();
			viewPage = service.requestProcess(request, response);
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
