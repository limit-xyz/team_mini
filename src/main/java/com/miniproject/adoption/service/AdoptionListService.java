package com.miniproject.adoption.service;

import java.io.IOException;
import java.util.ArrayList;

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
		
		String pageNum = request.getParameter("pageNum");
		String searchColumn = request.getParameter("serchColumn");
		String keyword = request.getParameter("keyword");		
		String adoptionType = request.getParameter("adoptionType");
		String animalTypeMain = request.getParameter("animalTypeMain");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = currentPage * PAGE_SIZE -(PAGE_SIZE -1);
		int endRow = startRow + PAGE_SIZE -1;
		
		AdoptionDao01 dao = new AdoptionDao01();
		ArrayList<AdoptionWriteDto> blist = dao.getAdopTionList();
		int listCount = 0;
		
		// 검색 게시 글 리스트 - type, keyword 동시에 있으면
		boolean searchOption = (searchColumn == null || searchColumn.equals("") 
				|| keyword == null || keyword.equals("") 				
				|| adoptionType == null || adoptionType.equals("") 
				|| animalTypeMain == null || animalTypeMain.equals("")) ? false : true;
		
		if(!searchOption) { // 일반 게시 글 리스트			
			blist = dao.boardList(startRow, endRow);
			listCount = dao.getBoardCount();
		
		} else {
			blist = dao.searchList(searchColumn, keyword, adoptionType, animalTypeMain, startRow, endRow);
			listCount = dao.getBoardCount(searchColumn, keyword);
			
		}
			
		int pageCount = listCount / PAGE_SIZE 
				+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
				- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		int endPage = startPage + PAGE_GROUP - 1;
		
		if(endPage > pageCount) {
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
			request.setAttribute("searchColumn", searchColumn);
			request.setAttribute("keyword", keyword);
			request.setAttribute("adoptionType", adoptionType);
			request.setAttribute("animalTypeMain", animalTypeMain);
		}
		
		return "adoption-board/adopboardList";
	}

}
