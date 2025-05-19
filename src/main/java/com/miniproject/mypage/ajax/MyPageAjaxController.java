package com.miniproject.mypage.ajax;

import java.io.IOException;

import com.miniproject.common.service.AjaxProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyPageAjaxController{

	public void doAjax(HttpServletRequest request, HttpServletResponse response, String command)
			throws ServletException, IOException {

		AjaxProcess ajaxAction = null;
		
		// 파일 경로 요청
		if (command.equals("filePath.ajax")) {
			ajaxAction = new FilePathAction();
			ajaxAction.ajaxProcess(request, response);
		}



	}
}
