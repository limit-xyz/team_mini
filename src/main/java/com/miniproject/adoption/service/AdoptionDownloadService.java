package com.miniproject.adoption.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionDownloadService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		String downDir = (String) sc. getAttribute("uploadDir");
		
		String fileName = request.getParameter("fileName");
		String downPath = sc.getRealPath(downDir + "\\" + fileName);
		File file = new File(downPath);
		System.out.println("downPath : " + downPath);
		System.out.println("filePath : " + file.getPath() + ", " + file.getName());
		
		
		String mimeType = sc.getMimeType(downPath);
		System.out.println("mimeType : " + mimeType);

		if(mimeType == null) {
			mimeType = "appliction.octec-stream";
			
		}
		
		response.setContentType(mimeType);
		String fileNameEncoding = URLEncoder.encode(file.getName(),"utf-8");
		fileName = fileNameEncoding.replaceAll("\\+", "%20");
		response.setHeader("content-Disposition",  "attachment; filename=" + fileName);
		
		FileInputStream in = new FileInputStream(file);
		ServletOutputStream downStream = response.getOutputStream();
		byte[] b = new byte[1*1024*1024];
		int readByte=0;
		while((readByte = in.read(b,0,b.length)) !=-1) {
			downStream.write(b, 0, b.length);
		}
		downStream.flush();
		downStream.close();
		in.close();
		
		return null;
	}

}
