package com.miniproject.mypage.ajax;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyPageAjaxController{

	public void doAjax(HttpServletRequest request, HttpServletResponse response, String command)
			throws ServletException, IOException {

		AjaxProcess ajaxAction = null;
		
//		// 멤버 역할 변경
//		if (command.equals("memberRole.ajax")) {
//			ajaxAction = new MemberRoleAction();
//			ajaxAction.ajaxProcess(request, response);
//		}



	}
}
