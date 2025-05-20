package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaUpdateResultService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//업데이트 폼에서 받은정보를 dao를 통해 db로 보내서 update를 적용시키고
		//리다이렉트로 qna 페이지로 이동시킴 
		return null;
	}

}
