package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.FaqDao;
import com.miniproject.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FaqDeleteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        String faqNoStr = request.getParameter("no"); 
        HttpSession session = request.getSession(false);
        String loginUserId = null;

        // 로그인 여부 확인
        if (session != null && session.getAttribute("id") != null) {
            loginUserId = (String) session.getAttribute("id");
        }



        // 관리자 권한 확인
        MemberDao memberDao = new MemberDao();
        if (memberDao.isAdmin(loginUserId)) 
            try {
                int faqNo = Integer.parseInt(faqNoStr);
                FaqDao faqDao = new FaqDao();
                faqDao.deleteFaq(faqNo);
            } catch (NumberFormatException e) {
                System.out.println("FaqDeleteService: 실패함");
                return "support/faqList"; 
            
        } else {
            // 관리자가 아님
        	request.setAttribute("errorMsg", "삭제 권한이 없습니다.");
            return "support/faq"; // 또는 에러 페이지
        }

        // 모든 작업 후 FAQ 목록으로 리다이렉트
        return "r:/team_mini/support/faq"; 
	}

}
