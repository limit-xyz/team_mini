package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.QnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaDeleteService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//qna글을 디테일에서 삭제하고 리다이렉트로 리스트 페이지로 보내는 기능 
		//작성자가 맞는지 확인하는 기능이 필요함 
		String qnaNoStr = request.getParameter("no");
        String qnaListRedirect = "r:" + request.getContextPath() + "/support/qnaList";

        if (qnaNoStr == null || qnaNoStr.trim().isEmpty()) {
            return qnaListRedirect;
        }

        int qnaNo = 0;
        try {
            qnaNo = Integer.parseInt(qnaNoStr);
        } catch (NumberFormatException e) {
            return qnaListRedirect;
        }

        QnaDao qnaDao = new QnaDao();
        QnaBoard qnaBoardToDelete = qnaDao.getQnaDetail(qnaNo);

        if (qnaBoardToDelete == null) {
            return qnaListRedirect;
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

        if (!isAdminUser && (loginUserId == null || !loginUserId.equals(qnaBoardToDelete.getWriter()))) {
            return "r:" + request.getContextPath() + "/support/qnaDetail?no=" + qnaNo;
        }
        
        int deleteResult = qnaDao.deleteQnaBoard(qnaNo);

        if (deleteResult <= 0) {
            System.err.println("QnaDeleteService: 문의글 삭제 실패 - qnaNo: " + qnaNo);
            // 실패 시에도 목록으로 이동하거나, 특정 에러 처리를 할 수 있습니다.
            // 여기서는 성공/실패 여부와 관계없이 목록으로 갑니다.
        }
        
        return qnaListRedirect;
	}

}
