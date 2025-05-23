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
		
		HttpSession session = request.getSession();		
		String postIdStr = request.getParameter("postId");
		String pageNum = request.getParameter("pageNum");
		
		
		if(postIdStr == null || postIdStr.equals("") 
				|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근 입니다.')");
			out.println("	location.href='/team_mini/adoption/AdoptionList'");
			out.println("</script>");
			out.close();
			return null;
		}			
		
		
		String loggedInUserId = (String) session.getAttribute("id");  // 세션에서 userId 가져오기
		Object isAdminObj = session.getAttribute("isAdmin");
		boolean isAdmin = false;
		 if (isAdminObj instanceof Boolean) {
	            isAdmin = (Boolean) isAdminObj;
	        } else if (isAdminObj instanceof String) {
	            isAdmin = "true".equalsIgnoreCase((String) isAdminObj);
	        }
		
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
		boolean isUserIdCheck = true;
		if(!isAdmin) {
				dao.isUserIdCheck(postId,  loggedInUserId); // 세션 userId로 권한 확인
		}
		if(!isAdmin) {
			isUserIdCheck = dao.isUserIdCheck(postId, loggedInUserId);
		}
		
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
		boolean searchOption = !(type == null || type.equals("")
				|| keyword == null || keyword.equals("")); 
		
		String url = request.getContextPath() +"/adoption/AdoptionList?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "UTF-8");
			url += "&searchColumn=" + type + "&keyword=" + keyword; 
		}
			System.out.println(url);
		return "r:" + url;
	}

}
