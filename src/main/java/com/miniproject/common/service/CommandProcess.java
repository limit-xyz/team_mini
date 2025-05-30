package com.miniproject.common.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CommandProcess {
	public abstract String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException;
}
