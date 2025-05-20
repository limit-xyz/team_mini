package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaDeleteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//qna글을 디테일에서 삭제하고 리다이렉트로 리스트 페이지로 보내는 기능 
		//작성자가 맞는지 확인하는 기능이 필요함 
		return null;
	}

}
