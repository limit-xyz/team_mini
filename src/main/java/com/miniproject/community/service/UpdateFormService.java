package com.miniproject.community.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.ComDao;
import com.miniproject.vo.Community;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("id") == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('세션이 만료되었습니다.\n다시 로그인해주세요.');");
			sb.append("	location.href='" + request.getContextPath() + "/member/loginForm'");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}

		// 1. 수정 권한 체크 -> 비번 - no, pass 받기
		String sNo = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		// no, pageNum 이 없으면 우리가 의도한 것이 아님
		// 직접 응답을 만들어서 (js로 응답)알림하고 게시글 리스트로 보냄
		if(sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			out.println("	history.back();");
			out.println("</script>");
		}
		
		// 검색 게시글 리스트 - type, keyword 동시에 있으면
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		// 사용자가 입력한 비번과 DB 게시글의 비번이 맞는지 체크
		ComDao dao = new ComDao();
		int no = Integer.parseInt(sNo);
		
		// 비번 맞으면 no에 해당하는 게시글 정보를 DB에서 읽어와 폼에 출력
		Community b = dao.getBoard(no);
		
		// 뷰에가서 출력할 결과 데이터 => 모델
		request.setAttribute("board", b);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		
		return "free/updateForm";
	}

}
