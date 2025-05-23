package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.AdoptionDao01;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdoptionReplyDeleteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("id");
		String userRole = (String) session.getAttribute("userRole"); // 세션에서 사용자 권한 획득

		System.out.println("세션 ID: " + userId + " / 역할(ROLE): " + userRole);
		
		if(userId == null) { //로그인 확인
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('로그인 후 댓글을 삭제할수 있습니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}		
		
		String replyIdparam = request.getParameter("replyId");
		String postIdParam = request.getParameter("postId");
		String pageNum = request.getParameter("pageNum");
		
		if (replyIdparam == null || replyIdparam.equals("") 
				|| postIdParam == null || postIdParam.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('댓글 ID 또는 게시글 ID 가 없습니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}

		int replyId = Integer.parseInt(replyIdparam);
		int postId = Integer.parseInt(postIdParam);
		
		AdoptionDao01 adoptionDao = new AdoptionDao01();
		boolean isWriter = adoptionDao.isReplyWriter(replyId, userId);
		boolean isAdmin = "admin".equals(userRole); // 관리자 권한 확인 코드
		
		
		//댓글 작성자 확인
		if(!isWriter && !isAdmin) { // 작성자도 아니고 관리자도 아니면 권한 없음
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('댓글 삭제 권한이 없거나 작성자가 아닙니다..');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
		int result = adoptionDao.deleteReply(replyId, userId);
		
		if (result > 0) {
			//댓글 삭제 성공시 해당 게시글 상세 페이지로
			String url = request.getContextPath() + "/adoption/AdoptionDetail?postId=" + postId + "&increaseViewCount=false&pageNum=" + pageNum;
			return "r:" + url;
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('댓글 삭제에 실패하였습니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
	}
}
