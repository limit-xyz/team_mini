package com.miniproject.community.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
		String pageNum = request.getParameter("pageNum");
		
		ComDao dao = new ComDao();
		
		// no, pageNum 이 없으면 우리가 의도한 것이 아님
		// 직접 응답을 만들어서 (js로 응답)알림하고 게시글 리스트로 보냄
		if(sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("") ||
				dao.isOwner(Integer.parseInt(sNo), (String) request.getSession().getAttribute("id"))) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('잘못된 접근...')");
			out.println("	history.back();");
			out.println("</script>");
		}
				
		// 비밀번호 체크
		// 사용자가 입력한 비번과 DB 게시글의 비번이 맞는지 체크

		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 하나의 게시글 정보를 Community 객체로 담아서 전달
		Community board = new Community();
		board.setNo(Integer.parseInt(sNo));
		board.setTitle(title);
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
		
		String url = "freeList?pageNum=" + pageNum + "&type=" + type + "&keyword=" + keyword;
	
		return "r:" + url;			
				
	}

}
