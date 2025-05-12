package com.miniproject.adoption.controller;

import java.io.IOException;

import com.miniproject.adoption.service.CommandProcess;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="adoptionController", urlPatterns="*.mvc")
public class AdoptBoardController extends HttpServlet{

	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";
	

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		doProcess(request, response);		
	}
			
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);		
	}
		
	protected void doProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		String reuqestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = reuqestURI.substring(contextPath.length());
		
		System.out.println("command : " + command);
		
		String viewPage = null;
		CommandProcess service = null;
	
		if(command.equals("/adoptionList.mvc")) {
			service = new AdoptionListService();
			viewPage = service.requestProcess(request, response);
		}
		
		if (viewPage !=null) {
			String view = viewPage.split(":")[0]; 
			if (view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);
			} else if (view.equals("f") || view.equals("forward")) {
				RequestDispatcher rd =request.getRequestDispatcher(viewPage.split(":")[1]);
				rd.forward(request,response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(PREFIX + view + SUFFIX);
			} 	rd.forward(request, response);
				
			}
		}
	}
	