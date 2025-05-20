package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.AdoptionDao01;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdoptionDeleteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String postIdStr = request.getParameter("postId");
		String pageNum = request.getParameter("pageNum");
		
		if(postIdStr == null || postIdStr.equals("") 
				|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근 입니다.')");
			out.println("	location.href='${pageContext.request.contextPath}/adoptionBoard/AdoptionList'");
			out.println("</script>");
			out.close();
			return null;
		}			
		
		HttpSession session = request.getSession();
		String loggedInUserId = (String) session.getAttribute("userId");  // 세션에서 userId 가져오기

		if(loggedInUserId == null) { //로그인 확인
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('로그인 후 삭제할 수 있습니다.')");
			out.println("	history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		AdoptionDao01 dao = new AdoptionDao01();
		int postId = Integer.parseInt(postIdStr);
		boolean isUserIdCheck = dao.isUserIdCheck(postId,  loggedInUserId); // 세션 userId로 권한 확인
		
		if(!isUserIdCheck) { //로그인 확인
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('해당 글의 작성자만 삭제할 수 있습니다.')");
			out.println("	history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		
		dao.deleteAdoption(postId);
		
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		boolean searchOption = (type != null || !type.equals("")
				|| keyword != null || !keyword.equals("")); 
		
		String url = "${pageContext.request.contextPath}/adoptionBoard/AdoptionList?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "UTF-8");
			url += "&searchColumn=" + type + "&keyword=" + keyword; 
		}
			System.out.println(url);
		return "r:" + url;
	}

}
