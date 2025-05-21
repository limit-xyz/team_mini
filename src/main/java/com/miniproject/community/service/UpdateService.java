package com.miniproject.community.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.UUID;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.ComDao;
import com.miniproject.vo.Community;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class UpdateService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		
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
				
		// 비밀번호 체크
		// 사용자가 입력한 비번과 DB 게시글의 비번이 맞는지 체크
		ComDao dao = new ComDao();
		boolean isPassCheck = dao.isPassCheck(Integer.parseInt(sNo), pass);
		
		// 틀리면 경고창 띄우고 종료
		if(!isPassCheck) {
			// 경고창 띄우기 위해서 자바스크립트로 응답
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('비밀번호가 틀립니다.');");
			out.println("	history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// 하나의 게시글 정보를 Community 객체로 담아서 전달
		Community board = new Community();
		board.setNo(Integer.parseInt(sNo));
		board.setTitle(title);
		board.setWriter(writer);
		board.setPass(pass);
		board.setContent(content);
		
		// 파일 처리
		Part part = request.getPart("file1");
		if(part.getSize() > 0) { // 파일이 업로드 된 경우
			UUID uid = UUID.randomUUID();
			String saveName = uid.toString() + "_" + part.getSubmittedFileName();
			File parentFile = (File) request.getServletContext().getAttribute("parentFile");
			String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
		
			part.write(savePath);
			part.delete();
			board.setFile1(saveName);
		
		} else {
			System.out.println("파일이 업로드 되지 않음");
		}
		
		// DB에 저장한 후 게시글을 수저하고 리다이렉트 시킨다
		dao.updateBoard(board);
		
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		// 검색 게시글 리스트 - type, keyword 동시에 있으면
		boolean searchOption =(type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		String url = "free/freeList?pageNum=" + pageNum;
		
		if(searchOption) {
			keyword = URLEncoder.encode(keyword, "UTF-8");
			url += "&type=" + type + "&keyword=" + keyword;
		}
		System.out.println(url);
		
		return "r:" + request.getContextPath()+ url;			
				
	}

}
