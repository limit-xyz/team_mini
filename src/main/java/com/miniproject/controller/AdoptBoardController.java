package com.miniproject.controller;

import java.io.File;
import java.io.IOException;

import com.miniproject.adoption.service.*;
import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 10 * 1024,
			maxFileSize = 1024 * 1024 * 10,
			maxRequestSize = 1024 * 1024 * 100)
@WebServlet(name="adoptionController", urlPatterns="*.mvc")
public class AdoptBoardController extends HttpServlet{

	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";
	
	@Override
	public void init() throws ServletException {		
		ServletContext sc = getServletContext();
		String uploadDir = sc.getInitParameter("uploadDir");
		String realPath = sc.getRealPath(uploadDir);
				
		File parentFile = new File(realPath);
		if(! (parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}
		
		sc.setAttribute("uploadDir", uploadDir);
		sc.setAttribute("parentFile", parentFile);
		System.out.println("init - " + parentFile);		
	}
	
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
	
		if(command.equals("/AdoptionList.mvc") || command.equals("/*.mvc")) {
			service = new AdoptionListService();					
			viewPage = service.requestProcess(request, response);
	
		} else if (command.equals("/AdoptionWriteForm.mvc")) {
			service = new AdoptionWriteFormService();						 
			viewPage = service.requestProcess(request, response);
	
		} else if (command.equals("/AdoptionWrite.mvc")) {
			service = new AdoptionWriteService();						 
			viewPage = service.requestProcess(request, response);
		
		}	else if (command.equals("/AdoptionDetail.mvc")) {
			service = new AdoptionDetailService();						 
			viewPage = service.requestProcess(request, response);
	
		}	else if (command.equals("/AdoptionView.mvc")) {
			service = new AdoptionViewService();						 
			viewPage = service.requestProcess(request, response);
	
		}	else if (command.equals("/AdoptionDelete.mvc")) {
			service = new AdoptionDeleteService();						 
			viewPage = service.requestProcess(request, response);
	
		}	else if (command.equals("/AdoptionupdateForm.mvc")) {
			service = new AdoptionUpdateFormService();						 
			viewPage = service.requestProcess(request, response);
		
		} else if (command.equals("/AdoptionDownload.mvc")) { 
            service = new AdoptionDownloadService();
            viewPage = service.requestProcess(request, response);

        } else if (command.equals("/AdoptionReplyWrite.mvc")) { 
            service = new AdoptionReplyWriteService();
            viewPage = service.requestProcess(request, response);

        } else if (command.equals("/AdoptionReplyDelete.mvc")) { 
            service = new AdoptionReplyDeleteService();
            viewPage = service.requestProcess(request, response);

        }
		
		RequestDispatcher rd = null;
		
		if (viewPage !=null) {
			String view = viewPage.split(":")[0]; 
			if (view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);
				return;
			} else if (view.equals("f") || view.equals("forward")) {
				rd =request.getRequestDispatcher(viewPage.split(":")[1]);
				
			} else {
				rd = request.getRequestDispatcher(PREFIX + view + SUFFIX);
			} 	
				if(rd != null) {
					rd.forward(request, response);
				}
			}
		}
	}
	