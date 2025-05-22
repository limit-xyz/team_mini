package com.miniproject.mypage.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.MyPageDao;
import com.miniproject.vo.Diary;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class DiaryUpdateService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		String pageNum = request.getParameter("pageNum");

		String contentType = request.getContentType();

		Diary diary = new Diary();
		diary.setNo(no);

		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {

			Collection<Part> parts = request.getParts();

			for (Part part : parts) {

				String partHeader = part.getHeader("Content-Disposition");

				if (partHeader.contains("filename=")) {
					if (part.getSize() > 0) {

						File parentFile = (File) request.getServletContext().getAttribute("parentFile");
						UUID uid = UUID.randomUUID();

						String saveName = uid.toString() + "_" + part.getSubmittedFileName();
						String savePath = parentFile.getAbsolutePath() + File.separator + saveName;

						part.write(savePath);
						part.delete();
						diary.setPhoto(saveName);

					} else {
						System.out.println("파일이 업로드 되지 않음");
					}

				} else {

					String paramName = part.getName();
					String paramValue = request.getParameter(paramName);

					if (paramName.equals("memberId")) {
						diary.setMemberId(paramValue);

					} else if (paramName.equals("petName")) {
						diary.setPetName(paramValue);
					}

					else if (paramName.equals("title")) {
						diary.setTitle(paramValue);

					} else if (paramName.equals("content")) {
						diary.setContent((paramValue).trim());
					}
				}
			}

		} else {
			System.out.println("폼에서 전송된 요청이 mutipart/form-data가 아님");
		}

		MyPageDao dao = new MyPageDao();
		dao.updateDiary(diary, no);

		return "r:diaryList?id=" + diary.getMemberId() + "&pageNum=" + pageNum;
	}

}
