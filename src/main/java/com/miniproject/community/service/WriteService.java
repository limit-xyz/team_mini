package com.miniproject.community.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.ComDao;
import com.miniproject.vo.Community;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

// 게시글쓰기 요청을 처리하는 서비스 클래스
public class WriteService implements CommandProcess {

	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		Community board = new Community();
		
		// 파일이 업로드 되는 경우, 그렇지 않은 경우
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();
		
		if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			// 요청 본문으로 들어오는 데이터를 Pary 리스트로 받음
			Collection<Part> parts = request.getParts();
			
			for(Part part : parts) {
				String partHeader = part.getHeader("Content-Disposition");
				
				// 파일 데이터라면 - file 컨트롤에서 전송된 데이터
				if(partHeader.contains("filename=")) { // 현재 part 파일 데이터라면 
					if(part.getSize() > 0) {
						// 파일 이름 중복 방지
						UUID uid = UUID.randomUUID();
						String saveName = uid.toString() + "_" + part.getSubmittedFileName();
						
						File parentFile = (File) request.getServletContext().getAttribute("parentFile");
						
						String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
						
						part.write(savePath);
						board.setFile1(saveName);
						part.delete();
					
					} else {
						System.out.println("파일이 업로드 되지 않음");
					}
					
				} else { // 현재 part가 일반 폼 컨트롤에 입력된 데이터
					String paramName = part.getName();
					String paramValue = request.getParameter(paramName);
					
					if(paramName.equals("title")) {
						board.setTitle(paramValue);
					
					} else if(paramName.equals("writer") ) {
						board.setWriter(paramValue);
						
					} else if(paramName.equals("content")) {
						board.setContent(paramValue);
						
					} else if(paramName.equals("pass")) {
						board.setPass(paramValue);
					}
				}
			}
		
		} else {
			System.out.println("전송된 데이터가 multipart/form-data가 아님");
		} 
		
		ComDao dao = new ComDao();
		dao.insertBoard(board);
		
		return "r:" + request.getContextPath() +"/free/freeList"; 
	}

}
