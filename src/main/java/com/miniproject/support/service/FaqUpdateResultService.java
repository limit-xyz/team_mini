package com.miniproject.support.service;

import java.io.IOException;
// import java.lang.reflect.Member; // 1. 여전히 살아있는 불필요한 Member! 삭제!
import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.FaqDao;
import com.miniproject.dao.MemberDao;
import com.miniproject.vo.Faq;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FaqUpdateResultService implements CommandProcess{ // 클래스 이름 좋아!

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
        String faqNo1 = request.getParameter("faqNo"); // JSP 폼의 hidden 필드 이름이 "faqNo"라고 가정!
		String faq_title = request.getParameter("title");
		String faq_content = request.getParameter("content");

        HttpSession session = request.getSession(false);
        String loginUserId = null;
        Faq faq = new Faq(); 

        // 로그인 여부 확인
        if (session != null && session.getAttribute("id") != null) {
            loginUserId = (String) session.getAttribute("id");
        }

        
        if (loginUserId == null) {
            System.out.println("FaqUpdateResultService: 로그인이 필요합니다.");
            return "r:" + request.getContextPath() + "/login.member"; 
        }
        if (faqNo1 == null || faqNo1.isEmpty()) {
            System.out.println("FaqUpdateResultService: 수정할 FAQ 번호가 없습니다.");
            return "support/faq"; 
        }

        if (faq_title == null || faq_title.trim().isEmpty() || faq_content == null || faq_content.trim().isEmpty()) {
            System.out.println("FaqUpdateResultService: 제목 또는 내용이 비어있습니다.");
            request.setAttribute("errorMsg", "제목과 내용을 모두 입력해주세요.");
           
            return "support/faqUpdateForm"; 
        }


        // 4. 관리자 권한 확인
        MemberDao memberDao = new MemberDao();
        
        if (memberDao.isAdmin(loginUserId)) { 
            try {
               
                int faqNo = Integer.parseInt(faqNo1); 
                faq.setFaqTitle(faq_title);
                faq.setFaqContent(faq_content);
                faq.setFaqNo(faqNo); 

                FaqDao faqDao = new FaqDao(); 
                faqDao.updateFaq(faq); 

            } catch (NumberFormatException e) {
           
                return "support/faq"; // 목록으로
            }
        } else {
            // 관리자가 아님
            System.out.println("관리자가 아닙니다");
            return "support/faqList"; // 목록으로
        }

	
        return "r:/team_mini/support/faq"; 

	}
}