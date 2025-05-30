package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.QnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaUpdateFormService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//디테일 페이지에서 수정 버튼을 누르면 번호를 기준으로 db에 상세페이지 내용 하나를 받아서 
		//qnaUpdateForm 뷰 페이지로 페이지 이동하고 뿌려줌 
		String qnaNoStr = request.getParameter("no");
        String errorRedirectPath = "r:" + request.getContextPath() + "/support/qnaList";
        String detailRedirectPath = null;


        if (qnaNoStr == null || qnaNoStr.trim().isEmpty()) {
            return errorRedirectPath;
        }

        int qnaNo = 0;
        try {
            qnaNo = Integer.parseInt(qnaNoStr);
            detailRedirectPath = "r:" + request.getContextPath() + "/support/qnaDetail?no=" + qnaNo;
        } catch (NumberFormatException e) {
            return errorRedirectPath;
        }

        QnaDao qnaDao = new QnaDao();
        QnaBoard qnaBoard = qnaDao.getQnaDetail(qnaNo);

        if (qnaBoard == null) {
            return errorRedirectPath;
        }

        HttpSession session = request.getSession(false);
        String loginUserId = null;
        boolean isAdminUser = false;

        if (session != null) {
            loginUserId = (String) session.getAttribute("id");
            Object isAdminAttr = session.getAttribute("isAdmin");
            if (Boolean.TRUE.equals(isAdminAttr)) {
                isAdminUser = true;
            }
        }

        if (!isAdminUser && (loginUserId == null || !loginUserId.equals(qnaBoard.getWriter()))) {
            return detailRedirectPath; 
        }

        request.setAttribute("qnaBoard", qnaBoard);
        
        return "support/qnaUpdateForm"; 
	}

}
