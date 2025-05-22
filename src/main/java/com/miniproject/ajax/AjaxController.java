package com.miniproject.ajax;

import java.io.IOException;

import com.miniproject.common.service.AjaxProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ajaxController", urlPatterns="/ajax/*")
public class AjaxController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		doAjax(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		doAjax(req,resp);
	}

	protected void doAjax(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	
		String reqestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = reqestURI.substring(contextPath.length());
		System.out.println("AjaxController - command : " + command);
		AjaxProcess ajaxAction = null;
		
		if(command.equals("/ajax/locationConfirm.ajax")) {
			// 게시 글 리스트 요청을 처리하는 BoardListService 클래스 실행
			ajaxAction = new LocationConfirmAction();
			ajaxAction.ajaxProcess(req, resp);
			
		}
		// 지금 페이지
		
	}
}
