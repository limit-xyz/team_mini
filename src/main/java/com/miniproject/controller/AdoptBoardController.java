package com.miniproject.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import com.miniproject.adoption.service.*;
import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@MultipartConfig(fileSizeThreshold = 10 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 100)
@WebServlet(name = "adoptionController", urlPatterns = "/adoption/*")
public class AdoptBoardController extends HttpServlet {

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

		sc.setAttribute("uploadDir", uploadDir);
		sc.setAttribute("parentFile", parentFile);
		System.out.println("init - " + parentFile);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
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

		System.out.println("command : " + command);

		if (command.equals("/adoption/AdoptionWriteForm") || command.equals("/adoption/AdoptionWrite")
				|| command.equals("/adoption/AdoptionReplyWrite") || command.equals("/adoption/AdoptionReplyDelete")
				|| command.equals("/adoption/AdoptionDelete") || command.equals("/adoption/AdoptionupdateForm")) {

			HttpSession session = request.getSession();

			if (session.getAttribute("id") == null) {

				String message = URLEncoder.encode("로그인이 필요한 서비스 입니다.", "UTF-8");
				response.sendRedirect(request.getContextPath() + "/member/loginForm?message=" + message);
				return;
			}
		}

		String viewPage = null;
		CommandProcess service = null;

		if (command.equals("/adoption/AdoptionList") || command.equals("/adoption/*")) {
			service = new AdoptionListService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionWriteForm")) {
			service = new AdoptionWriteFormService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionWrite")) {
			service = new AdoptionWriteService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionDetail")) {
			service = new AdoptionDetailService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionView")) {
			service = new AdoptionViewService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionDelete")) {
			service = new AdoptionDeleteService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionUpdateForm")) {
			service = new AdoptionUpdateFormService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionUpdate")) {
			service = new AdoptionUpdateService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionDownload")) {
			service = new AdoptionDownloadService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionReplyWrite")) {
			service = new AdoptionReplyWriteService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionReplyUpdate")) {
			service = new AdoptionReplyUpdateService();
			viewPage = service.requestProcess(request, response);

		} else if (command.equals("/adoption/AdoptionReplyDelete")) {
			service = new AdoptionReplyDeleteService();
			viewPage = service.requestProcess(request, response);
			
		} else if (command.equals("/adoption/AdoptionReplyReport")) {
			service = new AdoptionReplyReportService();
			viewPage = service.requestProcess(request, response);

		}

		RequestDispatcher rd = null;

		if (viewPage != null) {
			request.setAttribute("menu", "adopt");
			
			String view = viewPage.split(":")[0];
			if (view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);
				return;
			} else if (view.equals("f") || view.equals("forward")) {
				rd = request.getRequestDispatcher(viewPage.split(":")[1]);

			} else {
				rd = request.getRequestDispatcher(PREFIX + view + SUFFIX);
			}
			if (rd != null) {
				rd.forward(request, response);

			}
		}
		System.out.println("viewPage = " + viewPage);
	}
}
