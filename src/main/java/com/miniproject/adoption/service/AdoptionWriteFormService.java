package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.common.service.CommandProcess;

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
				true : false;
		
		if(!isLogin) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('로그인 전용 게시글 입니다.')");
			out.println("	location.href='"+ request.getContextPath() + "/member/loginForm'");  //-- 로그인 메뉴창 생성시 연동 필요
			out.println("</script>");
			out.close();
			return null;
		}
		
		return "adoptionboard/adoptionWrite";
	}

}
