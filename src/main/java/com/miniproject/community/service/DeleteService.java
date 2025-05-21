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
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		
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
		
		// 사용자가 입력한 비번과 DB 게시글의 비번이 맞는지 체크
		ComDao dao = new ComDao();
		int no = Integer.parseInt(sNo);
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		// 틀리면 경고창 띄우고 종료
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
		
		// ComDao 객체를 이용해 게시글 삭제
		dao.deleteBoard(no);
		
		/* 요청 파라미터에서 type이나 keyword가 비어 있으면 일반
		* 게시글 리스트에서 넘어온 요청으로 간주하여 false 값을 갖게 한다.
		* 이 정보는 게시글 리스트와 검색 리스트로 구분해 돌려보내기 위해 필요하다.
		**/
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		// 검색 게시글리스트 - type, keyword가 동시에 있으면
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		// 리다이렉트시 해당하는 게시글 리스트의 페이지 번호를 파라미터로 넘김
		String url = "free/freeList?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "UTF-8");
			url += "&type=" + type + "&keyword=" + keyword;
		}
		
		return "r:" + url;
		
	}

}
