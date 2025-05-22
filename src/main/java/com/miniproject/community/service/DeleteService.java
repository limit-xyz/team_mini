package com.miniproject.community.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.ComDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 게시글 삭제 요청을 처리하는 서비스 클래스
public class DeleteService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sNo = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		ComDao dao = new ComDao();

		if (sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("")
				|| dao.isOwner(Integer.parseInt(sNo), (String) request.getSession().getAttribute("id"))) {

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			out.println(" history.back();");
			out.println("</script>");
		}

		// ComDao 객체를 이용해 게시글 삭제
		dao.deleteBoard(Integer.parseInt(sNo));

		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");

		// 리다이렉트시 해당하는 게시글 리스트의 페이지 번호를 파라미터로 넘김
		String url = "freeList?pageNum=" + pageNum + "&type=" + type + "&keyword=" + keyword;

		return "r:" + url;

	}

}
