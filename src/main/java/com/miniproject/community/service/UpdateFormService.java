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

		// 1. 수정 권한 체크 -> 비번 - no, pass 받기
		String sNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		// no, pageNum 이 없으면 우리가 의도한 것이 아님
		// 직접 응답을 만들어서 (js로 응답)알림하고 게시글 리스트로 보냄
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("") 
				|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			//out.println("	history.back();");
			out.println("	location.href='boardList'");
			out.println("</script>");
		}		
		
		// 검색 게시글 리스트 - type, keyword 동시에 있으면
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		// 사용자가 입력한 비번과 DB 게시글의 비번이 맞는지 체크
		ComDao dao = new ComDao();
		int no = Integer.parseInt(sNo);
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		// 틀리면 - 경고창 띄우고 종료
		if(!isPassCheck) {
			// 경고창을 띄우기위해 자바스크립트로 응답
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비밀번호가 틀립니다.');");
			out.println("	history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		// 비번 맞으면 no에 해당하는 게시글 정보를 DB에서 읽어와 폼에 출력
		Community b = dao.getBoard(no, false);
		
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
