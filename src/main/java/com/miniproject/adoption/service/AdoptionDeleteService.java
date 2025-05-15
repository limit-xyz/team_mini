package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.miniproject.dao.AdoptionDao01;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionDeleteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String postId1 = request.getParameter("postId");
		String userId = request.getParameter("userId");
		String pageNum = request.getParameter("pageNum");
		
		if(postId1 == null || postId1.equals("") 
				|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			out.println("	location.href='boardList'");
			out.println("</script>");
		}			
		
		AdoptionDao01 dao = new AdoptionDao01();
		int postId = Integer.parseInt(request.getParameter("postId1"));
		boolean isUserIdcheck = dao.isUserIdCheck(postId, userId);
		
		if(!isUserIdcheck) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('해당글 작성자가 아니라서 권한이 없습니다.')");
			out.println("	history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		dao.deleteAdoption(postId);
		
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		String url = "AdoptionListService?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "UTF-8");
			url+="&type=" + type + "&keyword=" + keyword; 
		}
			System.out.println(url);
		return "redirect:/adoptionList.mvc";
	}

}
