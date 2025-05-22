package com.miniproject.community.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.google.gson.Gson;
import com.miniproject.common.service.AjaxProcess;
import com.miniproject.dao.ComDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecommandAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String com = request.getParameter("recommend");
		int no = Integer.parseInt(request.getParameter("no"));

		ComDao dao = new ComDao();
		HashMap<String, Integer> map = dao.getRecommend(no, com);
		System.out.printf("no : %s , com : %s", no, com);

		Gson gson = new Gson();
		String result = gson.toJson(map);

		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
