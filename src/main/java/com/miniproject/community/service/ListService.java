package com.miniproject.community.service;

import java.io.IOException;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.ComDao;
import com.miniproject.vo.Community;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 게시글 리스트를 보여주기 위해서 필요한 데이터를 만드는 서비스
public class ListService implements CommandProcess {

	
	// 한 페이지에 보여줄 게시글 수
	private static final int PAGE_SIZE = 10;
	
	// 한 페이지에 보여줄 페이지 그룹 수
	private static final int PAGE_GROUP =10;
	
	// 게시 글 리스트 요청을 처리하는 메서드
	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		// 몇 페이지 원해?
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		// 만약 pageNum이 null이면 1로 설정 = 최신 게시글의 첫 번째 페이지에 해당하는 게시글 리스트를 화면에 출력
		if(pageNum == null) {
			pageNum = "1";
		}
		
		// 요청 파라미터의 pageNum을 int 형으로 변환하여 현재 페이지로 설정
		int currentPage = Integer.parseInt(pageNum);
		
		// startRow가 11 = 2페이지 * 10 - (10 - 1);
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		
		// 20 = 11 + 10 - 1;
		int endRow = startRow + PAGE_SIZE - 1;
		
		// BoardDao 객체를 생성하고 전체 게시글의 수를 얻음
		ComDao dao = new ComDao();
		
		ArrayList<Community> bList = null;
		int listCount = 0;
		
		// 게시글 검색시 - type, keyword가 동시에 있으면
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
		
		if(!searchOption) { // 일반 게시글 리스트
			bList = dao.boardList(startRow, endRow);
			listCount = dao.getBoardCount();
			
		} else { // 검색 게시글 리스트
			bList = dao.searchList(type, keyword, startRow, endRow);
			listCount = dao.getBoardCount(type, keyword);
		}
		
		for (Community b : bList) {
			String title = String.format("%s [%s]",b.getTitle() ,dao.getReplyCount(b.getNo()));
			b.setTitle(title);
		}
		
		// 전체 페이지 수
		// 153 / 10 = 15 + 1(어느때 해야하는지)
		int pageCount = listCount / PAGE_SIZE
				+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		// 페이지 네이션 시작 페이지
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
				- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		// 페이지 네이션 마지막 페이지
		int endPage = startPage + PAGE_GROUP -1;
		
		// 전체페이지가 17페이지 = endPage ?
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		// DB에 가서 게시글 리스트(모델)를 읽음
		// 뷰에가서 출력할 모델을 담음
		request.setAttribute("bList", bList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageGroup", PAGE_GROUP);		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);		
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("listCount", listCount);
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		
		
		// 최종적으로 여기서 만들 모델(데이터)를 어디에 출력할지 결정해서 그 뷰의 이름을 반환
		return "free/boardList";

	}

}
