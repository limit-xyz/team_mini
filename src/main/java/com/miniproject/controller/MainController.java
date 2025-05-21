package com.miniproject.controller;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.main.service.MainService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="mainController", urlPatterns="/main/*")
public class MainController extends HttpServlet {
	
	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";


	@Override
	protected void doGet(
			HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(
			HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doProcess(req, resp);
	}

	// GET, POST 방식의 모든 요청 처리를 이 메서드가 함
	protected void doProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		// /JSPClassMvcBBS01/boardList.mvc		
		String requestURI = request.getRequestURI();
		
		// /JSPClassMvcBBS01
		String contextPath = request.getContextPath();
		System.out.println("URI : " + requestURI + ", ctxPath : " + contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);
		
		String viewPage = null;
		CommandProcess service = null;
		
		// 명령을 처리 - Service 클래스 이용 - dao이용
		// 어떤 서비스 클래스가 실행될지 결정
		if(command.equals("/main")) {
			// 게시 글 리스트 요청을 처리하는 BoardListService 클래스 실행
			service = new MainService();
			viewPage = service.requestProcess(request, response);
			System.out.println(viewPage);
		}
		
		else {
			viewPage = "r:" + request.getContextPath() + "/main";
		}
		
		if(viewPage != null) {
			// "boardList", "r:boardList.mvc", "redirect:boardList.mvc"
			String view = viewPage.split(":")[0];
			System.out.println("view : " + view);
	 
			if(view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);
				
			} else if(view.equals("f") || view.equals("forward")) {
				RequestDispatcher rd = 
						request.getRequestDispatcher(viewPage.split(":")[1]);
					rd.forward(request, response);
				
			} else {
				// "/WEB-INF/index.jsp?body=" + "board/boardList" + ".jsp"
				RequestDispatcher rd = 
						request.getRequestDispatcher(PREFIX + view + SUFFIX);
					rd.forward(request, response);
			}
		}
		
	}
}
