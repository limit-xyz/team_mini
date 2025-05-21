package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionReplyDto;
import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

public class AdoptionDetailService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		boolean isLogin = session.getAttribute("isLogin") != null && (Boolean) session.getAttribute("isLogin");

		
		String postIdParam = request.getParameter("postId");
		String pageNum = request.getParameter("pageNum");
		String searchColumn = request.getParameter("searchColumn");
		String adoptionType = request.getParameter("adoptionType");
		String animalTypeMain = request.getParameter("animalTypeMain");
		String keyword = request.getParameter("keyword");

		if (postIdParam == null || postIdParam.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근 입니다.')");
			out.println("	location.href='/team_mini/adoption/AdoptionList'");
			out.println("</script>");
			out.close();
		
			return null;
		}
		if(pageNum == null || pageNum.equals("")) {
			pageNum ="1";
		}
		int postId = Integer.parseInt(postIdParam);
		System.out.println("AdoptionDetailService - postIdParam: " + postIdParam);
		System.out.println("AdoptionDetailService - postId: " + postId);

			@SuppressWarnings("unchecked")
	    java.util.Set<Integer> viewedPostIds = (java.util.Set<Integer>) session.getAttribute("viewedPostIds");
	    if (viewedPostIds == null) {
	        viewedPostIds = new java.util.HashSet<>();
	    }
	    boolean increaseViewCount = false;
		
	    if(!viewedPostIds.contains(postId)) {
	    	increaseViewCount = true;
	    	viewedPostIds.add(postId);
	        session.setAttribute("viewedPostIds", viewedPostIds);
	    }
	    
	    
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

		AdoptionWriteDto adoptionDetail;

		try {
			adoptionDetail = dao.getAdoption(postId, increaseViewCount);
			 System.out.println("AdoptionDetailService - 게시글 조회 결과: " + (adoptionDetail == null ? "null" : "not null"));
		} catch (SQLException e) {

			e.printStackTrace(); // 오류를 로그에 기록합니다.
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('데이터베이스 오류가 발생했습니다. 잠시 후 다시 시도해주세요.')");
			out.println("	history.back();"); // 또는 오류 페이지로 리디렉션합니다.
			out.println("</script>");
			out.close();
			return null;
		}

		List<AdoptionReplyDto> replyList = dao.getReplyList(postId);
		int replyCount = dao.getReplyCount(postId); // 댓글수 조회

		request.setAttribute("adopboard", adoptionDetail);
		request.setAttribute("adopreplyList", replyList);
		request.setAttribute("replyCount", replyCount);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);

		if (searchOption) {
			request.setAttribute("searchColumn", searchColumn);
			request.setAttribute("adoptionType", adoptionType);
			request.setAttribute("animalTypeMain", animalTypeMain);
			request.setAttribute("keyword", keyword);
		}

		return "adoptionboard/adoptionDetail";
	}

}
