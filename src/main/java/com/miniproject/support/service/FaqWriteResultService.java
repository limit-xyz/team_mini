package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.FaqDao;
import com.miniproject.vo.Faq;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FaqWriteResultService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Faq faq = new Faq();
		String faq_title = request.getParameter("title");
		String faq_content = request.getParameter("content");
		
		HttpSession session = request.getSession(false); // 현재 세션을 가져오되, 없으면 새로 만들지는 마!
        String faq_author = null;
        
        
        if (session != null && session.getAttribute("id") != null) {
        	faq_author = (String) session.getAttribute("id");
        	
        }else {
			return null;
		}
        
		
		
		faq.setFaqTitle(faq_title);
		faq.setFaqContent(faq_content);
		faq.setFaqAuthor(faq_author);
		
		
		FaqDao dao = new FaqDao();
		dao.insertFaq(faq);
		
		
		return "r:/team_mini/support/faq";
	}

}
