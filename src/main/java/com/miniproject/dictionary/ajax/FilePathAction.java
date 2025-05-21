package com.miniproject.dictionary.ajax;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.miniproject.common.service.AjaxProcess;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilePathAction implements AjaxProcess {

	@Override
	public void ajaxProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ServletContext sc = request.getServletContext();

		String fileName = request.getParameter("fileName");
		String downPath = sc.getRealPath(fileName);
		
		File file = new File(downPath);

		String mimeType = sc.getMimeType(downPath);

		if (mimeType == null) {
			mimeType = "application.octet-stream";
		}

		response.setContentType(mimeType);

		FileInputStream in = new FileInputStream(file);
		ServletOutputStream downStream = response.getOutputStream();

		byte[] b = new byte[1 * 1024 * 1024];
		int readByte = 0;

		while ((readByte = in.read(b, 0, b.length)) != -1) {
			downStream.write(b, 0, readByte);
		}

		downStream.flush();
		downStream.close();
		in.close();

	}

}
