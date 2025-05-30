package com.miniproject.support.service;

import java.io.IOException;
import java.util.ArrayList;


import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.FaqDao;
import com.miniproject.vo.Faq;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FaqService implements CommandProcess{
	
	// 한 페이지에 보여줄 게시 글 수 
		private static final int PAGE_SIZE = 5;
		
		
		// 한 페이지에 보여줄 페이지 그룹의 수
		// [이전] 1 2 3 4 .... 10 [다음]
		private static final int PAGE_GROUP = 10;

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false); // 현재 세션을 가져옵니다. 없으면 null 반환.

		if (session != null) {
		    System.out.println("=== 현재 세션 속성 값 확인 ===");
		    java.util.Enumeration<String> attributeNames = session.getAttributeNames();
		    if (!attributeNames.hasMoreElements()) {
		        System.out.println("세션에 저장된 속성이 없습니다.");
		    }
		    while (attributeNames.hasMoreElements()) {
		        String name = attributeNames.nextElement();
		        Object value = session.getAttribute(name);
		        System.out.println("키(Key): " + name + ", 값(Value): " + value + " (타입: " + (value != null ? value.getClass().getName() : "null") + ")");
		    }
		    System.out.println("==============================");
		} else {
		    System.out.println("현재 활성화된 세션이 없습니다.");
		}
		
		
		String pageNum = request.getParameter("pageNum");
		String keyword = request.getParameter("keyword");
		
		//pageNem이 없으면 기본으로 1페이지를 보여줌 
		if(pageNum == null) {
			pageNum = "1";
		}
		
		//문자열이던 페이지번호를 숫자로 변환 
		int currentPage = Integer.parseInt(pageNum);
		
		//게시글 몇개를 가져올지 계산함 
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		int endRow = startRow + PAGE_SIZE - 1;
		
		FaqDao dao = new FaqDao();
		ArrayList<Faq> faqList = null;
		int listCount = 0;
		
		boolean searchOption = keyword == null? false : true;
		
		//검색인지 아닌지 판단하는 if문 
		if(!searchOption) { // 일반 게시 글 리스트			
			faqList = dao.getFaqList(startRow, endRow);
			listCount = dao.getFaqCount();
			
		} else { // 검색 게시 글 리스트
			faqList = dao.getFaqList( keyword, startRow, endRow);
			listCount = dao.getFaqCount(keyword);
		}
		
		int pageCount = listCount / PAGE_SIZE 
				+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
				- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		// 페이지 네이션 마지막 페이지
		int endPage = startPage + PAGE_GROUP - 1;
		
		// 전체 페이지가 17페이지 = endPage ?
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		
		
		
		
		
		
		request.setAttribute("faqList", faqList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageGroup", PAGE_GROUP);		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);		
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("keyword", keyword);			
		}
		
		return "support/faq";
	}

}