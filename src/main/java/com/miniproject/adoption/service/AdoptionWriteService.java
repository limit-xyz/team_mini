package com.miniproject.adoption.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class AdoptionWriteService implements CommandProcess{

	private static final String UPLOAD_DIR_PARAM = "uploadDir";
	private static final String ALLOWED_IMAGE_TYPES = "image/jpeg, image/png, image/gif";
	
		@Override
		public String requestProcess(
				HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			
			if(userId == null) { //로그인 확인
			  response.setContentType("text/html; charset=utf-8");
			  response.getWriter().println("<script> alert('로그인후 게시글을 작성할수 있습니다.'); "
			  		+ "history.back();</script>");
			  return null;
			}
			
			AdoptionWriteDto dto = new AdoptionWriteDto();
			System.out.println(System.getProperty("java.io.tmpdir"));
			dto.setUserId(userId); //세션에서 사용자 ID 설정
			
			String contentType = request.getContentType();
			
			if(contentType != null
					&& contentType.toLowerCase().startsWith("multiplart/")) {
				
				Collection<Part> parts = request.getParts();
				
				for(Part part : parts) {
					String PartHeader = part.getHeader("Content-Disposition");
					String paramName = part.getName();
					
					System.out.println(PartHeader);
					System.out.printf("파라미터 : %s, contentType : %s, size : %dByte, \n",
					part.getName(), part.getContentType(), part.getSize());
					
					if(PartHeader.contains("image_path=")) {
						if(part.getSize() > 0) {
							String mimeType = part.getContentType();
							if (mimeType != null && ALLOWED_IMAGE_TYPES.contains(mimeType.toLowerCase())) { // 추가: 파일 형식 검증
							
							UUID uid = UUID.randomUUID();
							String saveName = uid.toString() + "_" + part.getSubmittedFileName();
								
							File parentFile = (File) request.getServletContext().getAttribute("parentFile");
							String uploadDir = request.getServletContext().getInitParameter("UPLOAD_DIR_PARAM");
							String savePath = parentFile.getAbsolutePath() + File.separator + saveName;
							
							try {
							part.write(savePath);
							dto.setImagePath(uploadDir + "/" + saveName); // DB에 저장할 상대경로
							} catch(IOException e){
							e.printStackTrace();
							response.setContentType("text/html; charset=utf-8");
							response.getWriter().println("<script>alert('파일 업로드에 실패했습니다.'); history.back();</script>");
							return null;
							}
						} else {
							System.out.println("파일이 업로드 되지 않음");
						}
					} }else {
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
			} else {
				System.out.println("전송된 데이터가 multipart/form-data 가 아닙니다.");
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().println("<script>alert('잘못된 형식의 요청입니다.'); history.back(); </script>");
				return null;
			}
			// 데이터 유효성 검사
			if ( dto.getTitle() == null || dto.getTitle().trim().isEmpty()||dto.getContent() == null 
					|| dto.getContent().trim().isEmpty()) {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().println("<script> alert('제목과 내용은 필수 입니다.'); history.back(); </script>");
				return null;
			}
			AdoptionDao01 dao = new AdoptionDao01();
			int result = 0;
			  try {
		            result = dao.insertAdoptionPost(dto);
		        } catch (Exception e) {
		            e.printStackTrace();
		            response.setContentType("text/html; charest=utf-8");
		            response.getWriter()
		                    .println("<script>alert('데이터베이스 오류로 게시글 작성에 실패하였습니다.'); history.back(); </script>");
		            return null;
		        }
			  
			if(result > 0) {
				return "redirect:/adoptionList.mvc";
			} else {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().println("<script> alert('게시글 작성에 실패하였습니다.); history.back(); </script>");
				return null;
		}
	}
}
