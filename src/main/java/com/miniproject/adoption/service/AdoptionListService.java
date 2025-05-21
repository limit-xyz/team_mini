package com.miniproject.adoption.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionWriteDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdoptionListService implements CommandProcess {

	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;
	
	
	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String pageNumParam = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");		
		String adoptionType = request.getParameter("adoptionType");
		String animalTypeMain = request.getParameter("animalTypeMain");
		
		boolean searchOption = false;
		if(type !=null && !type.isEmpty()) {
			searchOption = true;
		}
		
		if(keyword !=null && !keyword.isEmpty()) {
			searchOption = true;
		}
		
		if(adoptionType !=null && !adoptionType.isEmpty()) {
			searchOption = true;
		}
		
		if(animalTypeMain !=null && !animalTypeMain.isEmpty()) {
			searchOption = true;
		}
		
		
		
		int currentPage = 1;
		
		if(pageNumParam  != null) {
			try {
				currentPage = Integer.parseInt(pageNumParam);
			} catch (NumberFormatException e ) {
			}
		}
		
		 int startRow = (currentPage - 1) * PAGE_SIZE + 1;
	        int endRow = currentPage * PAGE_SIZE;
		
		AdoptionDao01 dao = new AdoptionDao01();
		ArrayList<AdoptionWriteDto> blist;
		int listCount = 0;

		// 상위에 있는 코드로 주석처리
//		// 검색 게시 글 리스트 - type, keyword 동시에 있으면
//		boolean searchOption = (type != null || !type.equals("")) 
//				|| (keyword != null || !keyword.equals("")) 				
//				|| (adoptionType != null || !adoptionType.equals("")) 
//				|| (animalTypeMain != null || !animalTypeMain.equals(""));
		
		try {
		if(!searchOption) { // 일반 게시 글 리스트			
			blist = dao.boardList(startRow, endRow);
			listCount = dao.getBoardCount();
		
		} else {
			blist = dao.searchList(type, keyword, adoptionType, animalTypeMain, startRow, endRow);
			listCount = dao.getSearchBoardCount(type, keyword, adoptionType, animalTypeMain);
		
		}
	} catch (SQLException e){
		e.printStackTrace();
		request.setAttribute("errorMessage", "데이터베이스 오류가 발생했습니다.");
		return "error/errorPage";
	}
			
		    int pageCount = (listCount + PAGE_SIZE - 1) / PAGE_SIZE;
		
		    int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
		            - (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		    int endPage = startPage + PAGE_GROUP - 1;
		
		    if (endPage > pageCount) {
		        endPage = pageCount;
		    }
		
		
		request.setAttribute("blist", blist);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageGroup", PAGE_GROUP);		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);		
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
			request.setAttribute("adoptionType", adoptionType);
			request.setAttribute("animalTypeMain", animalTypeMain);
		}
		
		return "adoptionboard/adopboardList";

	}

}
