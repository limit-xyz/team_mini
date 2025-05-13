package com.miniproject.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hospital")
public class controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 병원 정보 처리 후 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/location/location.jsp");
        dispatcher.forward(request, response);
    }
}
