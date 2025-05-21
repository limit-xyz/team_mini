package com.miniproject.community.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.ComDao;
import com.miniproject.vo.Community;
import com.miniproject.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 게시글 상세보기 요청을 처리하는 서비스 클래스
public class DetailService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 몇번 게시글을 볼것인지 파악
		String no = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		// no, pageNum이 없으면 우리가 의도한것이 아님
		// 직접 응답을 만들어서 (js로 응답) 알림하고 게시글 리스트로 보냄
		if(no == null || no.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			//out.println("	history.back();");
			out.println("	location.href='boardList'");
			out.println("</script>");
			
			return null;
		}
		
		// 검색 게시글 리스트 - type, keyword 동시에 있으면
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		// 게시글을 DB에서 가져와서 뷰에 출력
		ComDao dao = new ComDao();
		
		// DB에 가서 no에 해당하는 게시글을 읽어옴
		Community b = dao.getBoard(Integer.parseInt(no), false);
		
		// 현재 게시글에 해당하는 댓글 리스트
		ArrayList <Reply> replyList = dao.getReplyList(b.getNo());
		
		// 뷰에 가서 출력할 결과 데이터
		request.setAttribute("board", b);
		request.setAttribute("replyList", replyList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		
		// 최종적으로 여기서 만들 모델(데이터)를 어디에 출력할지 결정해서 그 뷰의 이름을 반환
		return "free/boardDetail";

	}

}
