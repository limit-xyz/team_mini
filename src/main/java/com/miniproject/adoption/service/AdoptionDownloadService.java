package com.miniproject.adoption.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import com.miniproject.common.service.CommandProcess;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionDownloadService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		String uploadDir = (String) sc.getAttribute("uploadDir");

		String fileName = request.getParameter("fileName");
		if (fileName == null || fileName.trim().isEmpty()) { // 파일 이름 유효성 검사 추가
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid file name.");
			return null;
		}
		String downPath = sc.getRealPath(uploadDir + File.separator + fileName);
		File file = new File(downPath);
		System.out.println("downPath : " + downPath);
		System.out.println("filePath : " + file.getPath() + ", " + file.getName());

		if (!file.exists()) { // 파일 존재 여부 확인 추가
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
			return null;
		}

		String mimeType = sc.getMimeType(downPath);
		System.out.println("mimeType : " + mimeType);

		if (mimeType == null) {
			mimeType = "appliction.octec-stream";

		}

		response.setContentType(mimeType);
		String fileNameEncoding = URLEncoder.encode(file.getName(), "utf-8").replaceAll("\\+", "%20");
		;
		response.setHeader("content-Disposition", "attachment; filename=" + fileNameEncoding);
		response.setContentLengthLong(file.length()); // 파일 크기 설정

		try (FileInputStream in = new FileInputStream(file);
				ServletOutputStream downStream = response.getOutputStream()) {
			byte[] b = new byte[1 * 1024 * 1024];

			while ((in.read(b, 0, b.length)) != -1) {
				downStream.write(b, 0, b.length);
			}
			downStream.flush();
			downStream.close();
			in.close();

			return null;
		}
	}
}
