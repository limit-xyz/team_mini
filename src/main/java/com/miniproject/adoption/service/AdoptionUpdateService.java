package com.miniproject.adoption.service;

import java.io.*;
import java.util.*;

import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class AdoptionUpdateService implements CommandProcess{

	private static final String UPLOAD_DIR_PARAM = "uploadDir";
    private static final String ALLOWED_IMAGE_TYPES = "image/jpeg,image/png,image/gif";

    private String uploadDir; // 멤버 변수로 선언

	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		
		AdoptionDao01 adoptionDao = new AdoptionDao01();
		
		   // uploadDir 초기화 (request 객체를 통해 ServletContext에 접근해야 함)
        if (uploadDir == null) {
            uploadDir = request.getServletContext().getInitParameter(UPLOAD_DIR_PARAM);
            if (uploadDir == null) {
                System.err.println("UPLOAD_DIR 파라미터가 web.xml에 설정되지 않았습니다.");
                request.setAttribute("errorMessage", "서버 설정 오류가 발생했습니다.");
                return "error/errorPage";
            }
        }
		
		// 수정 권한 확인
		if(!adoptionDao.isUserIdCheck(postId, userId)) {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<script>alert('수정 권한이 없습니다.'); history.back();</script>");
			return null;
		}
		
		AdoptionWriteDto dto = new AdoptionWriteDto();
		dto.setPostId(postId);
		dto.setUserId(userId); //수정시에도 userId 사용
		
		String contentType = request.getContentType();
		
		if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			Collection<Part> parts = request.getParts();
			
			for(Part part : parts) {
				String partHeader = part.getHeader("Content-Disposition");
				String paramName = part.getName();
				
				if (partHeader.contains("image_path=")) {
					if(part.getSize() > 0) {
						UUID uid = UUID.randomUUID();
						String saveName = uid.toString() + "_" + part.getSubmittedFileName();
						
						File parentFile = (File) request.getServletContext().getAttribute("parentFile");
						String uploadDir = request.getServletContext().getInitParameter("uploadDir");
						String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
						
						try {
							part.write(savePath);
							dto.setImagePath(uploadDir + "/" + saveName); //DB에 저장할 상대경로
						} catch(IOException e) {
							e.printStackTrace();
							 response.setContentType("text/html; charset=utf-8");
	                         response.getWriter().println("<script>alert('파일 업로드에 실패했습니다.'); history.back();</script>");
	                         return null;
							}
						} else {
							// 기존 이미지 유지
							AdoptionWriteDto existingPost = adoptionDao.getAdoptionById(postId);
							if(existingPost != null) {
								dto.setImagePath(existingPost.getImagePath());
							}
						}
					} else {
						String paramValue = request.getParameter(paramName);
						if(paramName.equals("title")) {
							dto.setTitle(paramValue);
						} else if(paramName.equals("content")) {
							dto.setContent(paramValue);
						} else if(paramName.equals("adoptionType")) {
							dto.setAdoptionType(paramValue);
						} else if(paramName.equals("region")) {
							dto.setRegion(paramValue);
						} else if(paramName.equals("animalTypeMain")) {
							dto.setAnimalTypeMain(paramValue);
						} else if(paramName.equals("animalTypeDetail")) {
						dto.setAnimalTypeDetail(paramValue);
					}
				}
			}
		}
			else {
	            // multipart/form-data가 아닌 경우 처리
	            dto.setTitle(request.getParameter("title"));
	            dto.setContent(request.getParameter("content"));
	            dto.setAdoptionType(request.getParameter("adoptionType"));
	            dto.setRegion(request.getParameter("region"));
	            dto.setAnimalTypeMain(request.getParameter("animalTypeMain"));
	            dto.setAnimalTypeDetail(request.getParameter("animalTypeDetail"));
	        		
		} 
		// 데이터 유효성 검사
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty() || dto.getContent() == null
                || dto.getContent().trim().isEmpty()) {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().println("<script>alert('제목과 내용은 필수 입니다.'); history.back(); </script>");
            return null;
        }
        
        
        int result = adoptionDao.updateAdoption(dto);
		
        if(result > 0) {
        	return "redirect:adoptionDetail.mvc?postId=" + postId + "&increaseViewCount=false";
        	//수정된 게시글 상세 페이지로 리다이렉트
        } else {
        	response.setContentType("text/html; charset=utf-8");
            response.getWriter().println("<script>alert('게시글 수정에 실패하였습니다.'); history.back(); </script>");
            return null;
        }
	}
}
