package com.miniproject.admin.ajax;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjaxController {

	public void doAjax(HttpServletRequest request, HttpServletResponse response, String command)
			throws ServletException, IOException {

		AjaxProcess ajaxAction = null;

		if (command.equals("memberIgnore.ajax")) {
			ajaxAction = new MemberIgnoreAction();
			ajaxAction.ajaxProcess(request, response);
		}
		
		else if (command.equals("memberRelease.ajax")) {
			ajaxAction = new MemberReleaseAction();
			ajaxAction.ajaxProcess(request, response);
		}
		
		else if (command.equals("memberDelete.ajax")) {
			ajaxAction = new MemberDeleteAction();
			ajaxAction.ajaxProcess(request, response);
		}

	}
}
