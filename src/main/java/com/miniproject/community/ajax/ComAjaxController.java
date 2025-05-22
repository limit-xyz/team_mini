package com.miniproject.community.ajax;

import java.io.IOException;

import com.miniproject.common.service.AjaxProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ComAjaxController {

	public void doAjax(HttpServletRequest request, HttpServletResponse response, String command)
			throws ServletException, IOException {

		AjaxProcess ajaxAction = null;

		if (command.equals("recommend.ajax")) {
			ajaxAction = new RecommandAction();
			ajaxAction.ajaxProcess(request, response);
		}

		else if (command.equals("replyWrite.ajax")) {
			ajaxAction = new ReplyWriteAction();
			ajaxAction.ajaxProcess(request, response);
		}

		else if (command.equals("replyUpdate.ajax")) {
			ajaxAction = new ReplyUpdateAction();
			ajaxAction.ajaxProcess(request, response);
		}

		else if (command.equals("replyDelete.ajax")) {
			ajaxAction = new ReplyDeleteAction();
			ajaxAction.ajaxProcess(request, response);
		}
		
		else if (command.equals("replyDelete.ajax")) {
			ajaxAction = new ReplyDeleteAction();
			ajaxAction.ajaxProcess(request, response);
		}
		
		else if (command.equals("replyReport.ajax")) {
			ajaxAction = new ReplyReportAction();
			ajaxAction.ajaxProcess(request, response);
		}
	}
}
