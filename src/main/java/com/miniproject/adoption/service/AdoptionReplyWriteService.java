package com.miniproject.adoption.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionReplyDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdoptionReplyWriteService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		if(userId == null ) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('로그인 전용 서비스 입니다.');");
			out.println("	history.back();"); 
			out.println("</script>");
			out.close();
			return null;
		}

		 request.setCharacterEncoding("utf-8");
		 
		 String postIdParam = request.getParameter("postId");
		 String content = request.getParameter("content");
		 String isSecretParam = request.getParameter("isSecret");
		 boolean isSecret = "true".equalsIgnoreCase(isSecretParam);
		 
		 if(postIdParam == null || postIdParam.equals("") 
				 || content == null || content.equals("")){ 
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("	alert('게시글 id 와 댓글 내용을 입력해주세요.');");
				out.println("	history.back();"); 
				out.println("</script>");	
				out.close();
				return null;
			}
		 
		 int postId = Integer.parseInt(postIdParam);
		 
		 AdoptionReplyDto replyDto = new AdoptionReplyDto();
		 replyDto.setPostId(postId);
		 replyDto.setUserId(userId);
		 replyDto.setContent(content);
		 replyDto.setIsSecret(isSecret);
		
		AdoptionDao01 adoptionDao = new AdoptionDao01();
		int result = adoptionDao.insertReply(replyDto);
		
		if(result > 0) {
			// 댓글 작성 성공시 해당 게시글 상세 페이지로 리다이렉트
			return "redirect:/AdoptionDetail.mvc?postId=" + postId + "&increaseViewCount=false";
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('댓글 작성에 실패하였습니다.');");
			out.println("	history.back();"); 
			out.println("</script>");				
			out.close();
			return null;
		}
	}
}
