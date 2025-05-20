package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaUpdateFormService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//디테일 페이지에서 수정 버튼을 누르면 번호를 기준으로 db에 상세페이지 내용 하나를 받아서 
		//qnaUpdateForm 뷰 페이지로 페이지 이동하고 뿌려줌 
		return "support/qnaUpdateForm";
	}

}
