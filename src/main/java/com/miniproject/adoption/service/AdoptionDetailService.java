package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionReplyDto;
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
		boolean isLogin = session.getAttribute("isLogin") != null 
				&& (Boolean) session.getAttribute("isLogin");
		
		
		if(! isLogin) {//로그인이 아닐경우
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('로그인 전용 서비스 입니다.')");
			out.println("	location.href='loginForm.member'"); //--로그인 메뉴창 생성시 연동 필요
			out.println("</script>");
			
			return null;
			
		}
		
		String postIdParam = request.getParameter("postId");
		String pageNum = request.getParameter("pageNum");
		String searchColumn = request.getParameter("searchColumn");
		String adoptionType = request.getParameter("adoptionType");
		String animalTypeMain = request.getParameter("animalTypeMain");
		String keyword = request.getParameter("keyword");
	
		if(postIdParam == null || postIdParam.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근 입니다.')");
			out.println("	location.href='adoptionList.mvc'"); 
			out.println("</script>");
		
			return null;
		}
		int postId = Integer.parseInt(postIdParam);
		

		boolean searchOption = false;
		if (searchColumn != null && !searchColumn.isEmpty()) {
			searchOption = true;
		}
		if (keyword != null && !keyword.isEmpty()) {
			searchOption = true;
		}
		if (adoptionType != null && !adoptionType.isEmpty()) {
			searchOption = true;
		}
		if (animalTypeMain != null && !animalTypeMain.isEmpty()) {
			searchOption = true;
		}
		
		AdoptionDao01 dao = new AdoptionDao01();
		
		AdoptionWriteDto adoptionDetail = dao.getAdopTion(postId, true);
		
		List<AdoptionReplyDto> replyList = dao.getReplyList(postId);
		int replyCount = dao.getReplyCount(postId); // 댓글수 조회
		
		request.setAttribute("AdoptionWriteDto", adoptionDetail);
		request.setAttribute("replyList", replyList);
		request.setAttribute("replyCount", replyCount);	
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("searchColumn", searchColumn);
			request.setAttribute("adoptionType", adoptionType);			
			request.setAttribute("animalTypeMain", animalTypeMain);			
			request.setAttribute("keyword", keyword);			
		}		

		return "forward:./adoption/adoptionDetail.jsp";
	}

}
