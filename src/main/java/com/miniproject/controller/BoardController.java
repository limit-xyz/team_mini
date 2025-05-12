package com.miniproject.controller;

import java.io.File;
import java.io.IOException;

import com.jspstudy.ch06.service.BoardDetailService;
import com.jspstudy.ch06.service.BoardListService;
import com.jspstudy.ch06.service.BoardWriteFormService;
import com.jspstudy.ch06.service.BoardWriteService;
import com.jspstudy.ch06.service.CommandProcess;
import com.jspstudy.ch06.service.DeleteService;
import com.jspstudy.ch06.service.FileDownloadService;
import com.jspstudy.ch06.service.JoinFormService;
import com.jspstudy.ch06.service.JoinResultService;
import com.jspstudy.ch06.service.LoginFormService;
import com.jspstudy.ch06.service.LoginService;
import com.jspstudy.ch06.service.LogoutService;
import com.jspstudy.ch06.service.MemberUpdateFormService;
import com.jspstudy.ch06.service.MemberUpdateResultService;
import com.jspstudy.ch06.service.OverlapIdCheckService;
import com.jspstudy.ch06.service.UpdateFormService;
import com.jspstudy.ch06.service.UpdateService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 10 * 1024,
	maxFileSize = 1024 * 1024 * 10,
	maxRequestSize = 1024 * 1024 * 100)
@WebServlet(name="boardController", urlPatterns="*.mvc")
public class BoardController extends HttpServlet {
	
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
		if(command.equals("/*.mvc") 
				|| command.equals("/boardList.mvc")) {
			// 게시 글 리스트 요청을 처리하는 BoardListService 클래스 실행
			service = new MainService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/boardDetail.mvc")) {
			// 게시 글 상세보기 요청을 처리하는 BoardDetailService 클래스 실행
			service = new BoardDetailService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/writeForm.mvc")) {
			service = new BoardWriteFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/writeProcess.mvc")) {
			service = new BoardWriteService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/updateForm.mvc")) {
			service = new UpdateFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/updateProcess.mvc")) {
			service = new UpdateService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/deleteProcess.mvc")) {
			service = new DeleteService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/loginForm.mvc")) {
			service = new LoginFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/login.mvc")) {
			service = new LoginService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/logout.mvc")) {
			service = new LogoutService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/joinForm.mvc")) {
			service = new JoinFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/overlapIdCheck.mvc")) {
			service = new OverlapIdCheckService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/joinResult.mvc")) {
			service = new JoinResultService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/memberUpdateForm.mvc")) {
			service = new MemberUpdateFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/memberUpdateResult.mvc")) {
			service = new MemberUpdateResultService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/fileDownload.mvc")) {
			service = new FileDownloadService();
			viewPage = service.requestProcess(request, response);
			
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
