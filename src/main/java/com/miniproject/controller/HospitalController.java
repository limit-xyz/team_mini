package com.miniproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="hospitalController", urlPatterns="/hospital/*")
public class HospitalController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setAttribute("menu", "hospital");
        
    	// 병원 정보 처리 후 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp?body=hospital/hospital.jsp");
        dispatcher.forward(request, response);
    }
}
