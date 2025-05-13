package com.miniproject.admin.ajax;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjaxController {

	public void doAjax(HttpServletRequest request, HttpServletResponse response, String command)
			throws ServletException, IOException {

		AjaxProcess ajaxAction = null;
		
		// 멤버 역할 변경
		if (command.equals("memberRole.ajax")) {
			ajaxAction = new MemberRoleAction();
			ajaxAction.ajaxProcess(request, response);
		}

		// 멤버 차단
		else if (command.equals("memberBan.ajax")) {
			ajaxAction = new MemberBanAction();
			ajaxAction.ajaxProcess(request, response);
		}
		
		// 멤버 차단 해제
		else if (command.equals("memberRelease.ajax")) {
			ajaxAction = new MemberReleaseAction();
			ajaxAction.ajaxProcess(request, response);
		}
		
		// 멤버 삭제
		else if (command.equals("memberDelete.ajax")) {
			ajaxAction = new MemberDeleteAction();
			ajaxAction.ajaxProcess(request, response);
		}

	}
}
