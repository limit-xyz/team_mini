package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdoptionWriteFormService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		boolean isLogin = session.getAttribute("isLogin") != null?
				(Boolean) session.getAttribute("isLogin") : false;
		
		if(! isLogin) {
			response.setContentType("text/html; Charser=utf-8);");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('로그인 전용 게시글 입니다.')");
			out.println("	location.href=' 로그인 전용창'");  -- 로그인 메뉴창 생성시 연동 필요
			out.println("</script>");
			
		}
		
		return "adoption-board/adoptionWrite";
	}

}
