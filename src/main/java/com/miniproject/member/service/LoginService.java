package com.miniproject.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// 요청 객체
				String id = request.getParameter("id");
				String pass = request.getParameter("password");
				
				// Dao를 이용해서  로그인 처리?  
				// -1 아이디 없음, 0 비밀번호 틀림, 1 로그인 성공
				MemberDao dao = new MemberDao();
				int result = dao.checkMember(id, pass);
				boolean admin = dao.isAdmin(id);
				
				if(result == -1) { // 아이디 없음
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("	alert('아이디가 존재하지 않음')");
					out.println("	history.back();");			
					out.println("</script>");			
					return null;
					
				} else if(result == 0) { // 비번 틀림
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("	alert('비밀번호가 틀립니다.')");
					out.println("	history.back();");			
					out.println("</script>");
					return null;
				} 
				
				
				 if(dao.isBan(id) != null) { // 밴 당한 계정일 경우
						response.setContentType("text/html; charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("	alert('차단당한 계정입니다.\\n차단 해제 일자 : " + dao.getBanDate(id) + "\\n차단 사유 : " + dao.isBan(id) + "');");
						out.println("	history.back();");			
						out.println("</script>");
						return null;
					} 
				HttpSession session = request.getSession();
				
				
				if(admin==true) {
					session.setAttribute("isAdmin", true);
				}
				// 로그인 성공에 대한 처리 - 세션에 데이터 저장
				// 포워드, 리다이렉트
				
				session.setAttribute("id", id);
				session.setAttribute("isLogin", true);
				return "r:" + request.getContextPath() +"/main/main";
	}

}
