package com.miniproject.controller;

import java.io.File;
import java.io.IOException;

import com.miniproject.mypage.service.*;
import com.miniproject.mypage.ajax.MyPageAjaxController;

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

@WebServlet(name = "myPageController", urlPatterns = "/member/mypage/*")
public class MyPageContoller extends HttpServlet {

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

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();

		String command = requestURI.substring(contextPath.length());
		String viewPage = null;

		CommandProcess service;

//		// 로그인 여부 검증
//		String requestId = (String) request.getSession().getAttribute("id");
//		MemberDao dao = new MemberDao();
//		boolean isLogin = dao.isLogin(requestId);
//		if (!isAdmin) {
//			System.out.println("콰과광");
//			return;
//		}

		// ajax 요청 처리
		String[] splitTest1 = command.split("/");
		if (splitTest1.length > 0) {
			String str = splitTest1[splitTest1.length - 1];
			String[] splitTest2 = str.split("\\.");

			if (splitTest2.length > 1 && splitTest2[1].equals("ajax")) {
				MyPageAjaxController ajax = new MyPageAjaxController();
				ajax.doAjax(request, response, str);
				return;
			}
		}

		// 예약 목록 확인
		if (command.equals("/member/mypage/reservation")) {
//			service = new AdminMainService();
//			viewPage = service.requestProcess(request, response);
		}

		// 자신의 게시글 목록
		else if (command.equals("/member/mypage/boards")) {
//			service = new InquiryListService();
//			viewPage = service.requestProcess(request, response);
		}

		// 반려동물 다이어리 리스트
		else if (command.equals("/member/mypage/diaryList")) {
			service = new DiaryListService();
			viewPage = service.requestProcess(request, response);
		}

		// 반려동물 다이어리 상세
		else if (command.equals("/member/mypage/diaryDetail")) {
			service = new DiaryDetailService();
			viewPage = service.requestProcess(request, response);
		}

		// 반려동물 다이어리 수정 폼 요청
		else if (command.equals("/member/mypage/diaryUpdateForm")) {
			service = new DiaryUpdateFormService();
			viewPage = service.requestProcess(request, response);
		}

		// 반려동물 다이어리 수정
		else if (command.equals("/member/mypage/diaryUpdateProcess")) {
			service = new DiaryUpdateService();
			viewPage = service.requestProcess(request, response);
		}

		// 반려동물 다이어리 삭제
		else if (command.equals("/member/mypage/diaryDelete")) {
			service = new DiaryDeleteService();
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
