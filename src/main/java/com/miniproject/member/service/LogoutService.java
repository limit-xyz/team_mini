package com.miniproject.member.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutService implements CommandProcess {

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		// 로그아웃 처리 
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 리다이렉트 		
		return "r:/team_mini/main/main";
	}

}
