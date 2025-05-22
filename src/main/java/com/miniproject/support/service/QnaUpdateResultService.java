package com.miniproject.support.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.QnaBoard;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaUpdateResultService implements CommandProcess{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//업데이트 폼에서 받은정보를 dao를 통해 db로 보내서 update를 적용시키고
		//리다이렉트로 qna 페이지로 이동시킴 
		
		
		String qnaNoStr = request.getParameter("no");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String status = request.getParameter("status"); 

        String defaultRedirect = "r:" + request.getContextPath() + "/support/qnaList";
        String detailRedirect = null;

        int qnaNo = 0;
        if (qnaNoStr == null || qnaNoStr.trim().isEmpty()) {
            return defaultRedirect;
        }
        try {
            qnaNo = Integer.parseInt(qnaNoStr);
            detailRedirect = "r:" + request.getContextPath() + "/support/qnaDetail?no=" + qnaNo;
        } catch (NumberFormatException e) {
            return defaultRedirect;
        }

        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            return detailRedirect != null ? detailRedirect : defaultRedirect; 
        }

        QnaDao qnaDao = new QnaDao();
        QnaBoard existingQnaBoard = qnaDao.getQnaDetail(qnaNo);

        if (existingQnaBoard == null) {
            return defaultRedirect;
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

        if (!isAdminUser && (loginUserId == null || !loginUserId.equals(existingQnaBoard.getWriter()))) {
            return detailRedirect; 
        }

        existingQnaBoard.setTitle(title);
        existingQnaBoard.setContent(content);
        
        if (status != null && isAdminUser) {
            existingQnaBoard.setStatus(status);
        }
        
        int updateResult = qnaDao.updateQnaBoard(existingQnaBoard);

        if (updateResult <= 0) {
            System.err.println("QnaUpdateResultService: 문의글 수정 실패 - qnaNo: " + qnaNo);
        }
        
        return detailRedirect;
	}

}
