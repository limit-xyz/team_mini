package com.miniproject.controller;

import java.io.File;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.community.service.DeleteService;
import com.miniproject.community.service.DetailService;
import com.miniproject.community.service.ListService;
import com.miniproject.community.service.UpdateFormService;
import com.miniproject.community.service.UpdateService;
import com.miniproject.community.service.WriteFormService;
import com.miniproject.community.service.WriteService;
import com.miniproject.community.ajax.ComAjaxController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 10 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 100)
@WebServlet(name = "FreeController", urlPatterns = "/free/*")
public class FreeController extends HttpServlet {

	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";

	@Override
	public void init() throws ServletException {

		// 웹 애플리케이션의 전역 정보에 접근하기 위한 객체
		ServletContext sc = getServletContext();

		// 애노테이션으로 선언한 서블릿 초기화 파라미터 읽음
		String uploadDir = sc.getInitParameter("uploadDir");

		/*
		 * 서블릿 초기화 파라미터로 읽어온 업로드 파일이 저장될 폴더의 로컬 경로를 구하여 그 경로와 파일명으로 File 객체를 생성한다.
		 **/
		String realPath = sc.getRealPath(uploadDir);
		File parentFile = new File(realPath);

		/*
		 * 파일 객체에 지정한 위치에 디렉토리가 존재하지 않거나 파일 객체가 디렉토리가 아니라면 디렉토리를 생성한다.
		 **/
		if (!(parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}

		/*
		 * ServletContext 객체의 속성으로 저장해 파일 업로드 디렉토리 정보를 다른 컴포넌트에서 사용할 수 있도록 하였다.
		 **/
		sc.setAttribute("uploadDir", uploadDir);
		sc.setAttribute("parentFile", parentFile);
		System.out.println("init - " + parentFile);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		// ajax 요청 처리
		String[] splitTest1 = command.split("/");
		if (splitTest1.length > 0) {
			String str = splitTest1[splitTest1.length - 1];
			String[] splitTest2 = str.split("\\.");

			if (splitTest2.length > 1 && splitTest2[1].equals("ajax")) {
				ComAjaxController ajax = new ComAjaxController();
				ajax.doAjax(request, response, str);
				return;
			}
		}
		
		// 뷰 페이지 정보 저장 변수
		String viewPage = null;
		CommandProcess service = null;

		// 서비스 클래스 실행
		// 어떤 서비스 클래스가 실행될지 결정
		if (command.equals("/free/*") || command.equals("/free/freeList")) {
			service = new ListService();
			viewPage = service.requestProcess(request, response);
		}

		// 게시 글 상세보기 요청을 처리하는 freeDetailService 클래스 실행
		else if (command.equals("/free/freeDetail")) {
			service = new DetailService();
			viewPage = service.requestProcess(request, response);

		}
		// 게시 글 쓰기 폼 요청을 처리하는 writeForm 클래스 실행
		else if (command.equals("/free/writeForm")) {
			service = new WriteFormService();
			viewPage = service.requestProcess(request, response);
		}

		// 게시 글 쓰기 요청을 처리하는 writeProcess 클래스 실행
		else if (command.equals("/free/writeProcess")) {
			service = new WriteService();
			viewPage = service.requestProcess(request, response);
		}

		// 게시 글 수정 폼 요청을 처리하는 updateForm 클래스 실행
		else if (command.equals("/free/updateForm")) {
			service = new UpdateFormService();
			viewPage = service.requestProcess(request, response);
		}

		// 게시 글 수정 요청을 처리하는 updateProcess 클래스 실행
		else if (command.equals("/free/updateProcess")) {
			service = new UpdateService();
			viewPage = service.requestProcess(request, response);
		}

		// 게시글 삭제 요청 처리하는 service 클래스 실행
		else if (command.equals("/free/deleteProcess")) {
			service = new DeleteService();
			viewPage = service.requestProcess(request, response);
		}
		
		if (viewPage != null) {
			String view = viewPage.split(":")[0];
			System.out.println("view : " + view);

			if (view.equals("r") || view.equals("redirect")) {
				response.sendRedirect(viewPage.split(":")[1]);

			} else if (view.equals("f") || view.equals("forward")) {
				RequestDispatcher rd = request.getRequestDispatcher(viewPage.split(":")[1]);
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(PREFIX + view + SUFFIX);
				rd.forward(request, response);
			}
		}

	}
}
