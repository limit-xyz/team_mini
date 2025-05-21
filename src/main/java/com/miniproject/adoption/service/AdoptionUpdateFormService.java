package com.miniproject.adoption.service;

import java.io.IOException;
import java.sql.SQLException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionUpdateFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String spostId = request.getParameter("postId");
		String userIdParam = (String) request.getSession().getAttribute("id");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(spostId == null || spostId.equals("") || userIdParam  == null || userIdParam.equals("")
				) {
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().println("<script>alert('잘못된 접근입니다.'); history.back();</script>");
		return null;
	}
	int postId = Integer.parseInt(spostId);
	
	AdoptionDao01 dao = new AdoptionDao01();
	AdoptionWriteDto adoptionDto = null;
	
	try {
		adoptionDto = dao.getAdoption(postId, false);
		if (adoptionDto == null || !adoptionDto.getUserId().equals(userIdParam)) {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<script>alert('수정 권한이 없거나 해당 게시글이 존재하지 않습니다.'); history.back();</script>");
			return null;
		}
		request.setAttribute("adoptionDto", adoptionDto);
		request.setAttribute("type", type);
		request.setAttribute("keyword", keyword);
		
		return "adoptionboard/adoptionUpdateForm";
		
	} catch(SQLException e){
		e.printStackTrace();
		request.setAttribute("errorMessage", "데이터베이스 오류가 발생했습니다.");
		return "error/errorPage"; // 오류 페이지로 이동
		}
	}
}