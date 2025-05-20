package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaDetailService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//리스트에 있는 글을 클릭하면 페이지 넘버를 받아와서 dao랑 연결해서 
		//상세페이지의 내용을 담아서 뷰 디테일로 넘긴다 
		return "support/qnaDetail";
	}

}
