package com.miniproject.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 게시 글 리스트 요청을 처리하는 서비스 클래스
public class MainService implements CommandProcess {

	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		
		
		return "main/index";
	}
	
}
