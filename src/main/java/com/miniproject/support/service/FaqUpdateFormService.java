package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.FaqDao;
import com.miniproject.dao.MemberDao;
import com.miniproject.vo.Faq;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FaqUpdateFormService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String faqNoStr = request.getParameter("no");
        HttpSession session = request.getSession(false);
        String loginUserId = null;
        Faq faq = null; 

        // 로그인 ID 먼저 가져오기
        if (session != null && session.getAttribute("id") != null) {
            loginUserId = (String) session.getAttribute("id");
        }

        // 필수 값 체크 글 번호 없으면 진행 불가
  
        if (faqNoStr == null || faqNoStr.isEmpty()) {
            System.out.println("FaqUpdateFormService: 수정할 FAQ 번호가 없습니다.");
            request.setAttribute("errorMsg", "수정할 FAQ를 선택해주세요.");
            return "support/faq"; 
        }

        MemberDao memberDao = new MemberDao();
        
        if (memberDao.isAdmin(loginUserId)) { 
            try {
                int faqNo = Integer.parseInt(faqNoStr);
                FaqDao faqDao = new FaqDao();
                faq = faqDao.getFaq(faqNo); 

                if (faq == null) { 
                	System.out.println("에러");
                    return "support/faq";
                }

            } catch (NumberFormatException e) {
                System.out.println("에러");
                return "support/faq"; 
            }
        } else {
            // 관리자가 아님
            System.out.println("수정 권한이 없습니다.");
            return "support/faq"; // 목록 페이지로 포워드
        }

        request.setAttribute("faq", faq); 

		return "support/faqUpdateForm"; 
	}
}