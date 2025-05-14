package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdoptionDetailService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		boolean isLogin = session.getAttribute("isLogin") != null?
				(Boolean) session.getAttribute("isLogin") : false;
		
		if(! isLogin) {//로그인이 아닐경우
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('로그인 전용 서비스 입니다.')");
			out.println("	location.href='로그인 화면'"); --로그인 메뉴창 생성시 연동 필요
			out.println("</script>");
			
			return null;
			
		}
		
		String postId = request.getParameter("postId");
		String pageNum = request.getParameter("pageNum");
		String searchColumn = request.getParameter("searchColumn");
		String adoptionType = request.getParameter("adoptiontype");
		String animalTypeMain = request.getParameter("animalTypeMain");
		String keyword = request.getParameter("keyword");
	
		if(postId == null || postId.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근 입니다.')");
			out.println("	location.href='adoptionBoardList.mvc'"); 
			out.println("</script>");
		
			return null;
		}
		
		boolean searchOption = (searchColumn == null || searchColumn.equals("")
				|| adoptionType == null || adoptionType.equals("") 
				|| keyword == null || keyword.equals("")
				|| animalTypeMain == null || animalTypeMain.equals("")) ? false : true;
		
		AdoptionDao01 dao = new AdoptionDao01();
		
		AdoptionDao01 b = dao.getAdopTion(Integer.parseInt(postId), false);
		
		ArrayList<댓글 생성후 추가> replyList = dao.gatReplyList(b.getpostId());
		
		
		
		
		return null;
	}

}
