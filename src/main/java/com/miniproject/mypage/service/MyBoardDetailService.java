package com.miniproject.mypage.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.adoption.service.AdoptionDetailService;
import com.miniproject.community.service.DetailService;
import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionReplyDto;
import com.miniproject.dao.AdoptionWriteDto;
import com.miniproject.dao.ComDao;
import com.miniproject.vo.Community;
import com.miniproject.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyBoardDetailService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = (String) request.getSession().getAttribute("id");
		if (id == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('세션이 만료되었습니다.\n다시 로그인해주세요.');");
			sb.append("	location.href='" + request.getContextPath() + "/member/loginForm");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}

		String boardType = request.getParameter("boardType");
		request.setAttribute("boardType", boardType);

		if (boardType.equals("자유 게시판")) {
			String no = request.getParameter("no");
			String pageNum = request.getParameter("pageNum");
			String type = request.getParameter("type");
			String keyword = request.getParameter("keyword");

			boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false
					: true;

			ComDao dao = new ComDao();

			Community b = dao.getBoard(Integer.parseInt(no));

			ArrayList<Reply> replyList = dao.getReplyList(b.getNo());

			request.setAttribute("board", b);
			request.setAttribute("replyList", replyList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("searchOption", searchOption);

			if (searchOption) {
				request.setAttribute("type", type);
				request.setAttribute("keyword", keyword);
			}

			return "free/boardDetail";

		} else if (boardType.equals("입양/분양 게시판")) {
			String postIdParam = request.getParameter("no");
			String pageNum = request.getParameter("pageNum");
			String searchColumn = request.getParameter("type");
			String adoptionType = request.getParameter("adoptionType");
			String animalTypeMain = request.getParameter("animalTypeMain");
			String keyword = request.getParameter("keyword");

			int postId = Integer.parseInt(postIdParam);

			boolean searchOption = false;
			if (searchColumn != null && !searchColumn.isEmpty()) {
				searchOption = true;
			}
			if (keyword != null && !keyword.isEmpty()) {
				searchOption = true;
			}
			if (adoptionType != null && !adoptionType.isEmpty()) {
				searchOption = true;
			}
			if (animalTypeMain != null && !animalTypeMain.isEmpty()) {
				searchOption = true;
			}

			AdoptionDao01 dao = new AdoptionDao01();

			AdoptionWriteDto adoptionDetail;

			try {
				adoptionDetail = dao.getAdoption(postId, true);
			} catch (SQLException e) {

				e.printStackTrace(); // 오류를 로그에 기록합니다.
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("	alert('데이터베이스 오류가 발생했습니다. 잠시 후 다시 시도해주세요.')");
				out.println("	history.back();"); // 또는 오류 페이지로 리디렉션합니다.
				out.println("</script>");
				out.close();
				return null;
			}

			List<AdoptionReplyDto> replyList = dao.getReplyList(postId);
			int replyCount = dao.getReplyCount(postId); // 댓글수 조회

			request.setAttribute("adopboard", adoptionDetail);
			request.setAttribute("adopreplyList", replyList);
			request.setAttribute("replyCount", replyCount);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("searchOption", searchOption);

			if (searchOption) {
				request.setAttribute("searchColumn", searchColumn);
				request.setAttribute("adoptionType", adoptionType);
				request.setAttribute("animalTypeMain", animalTypeMain);
				request.setAttribute("keyword", keyword);
			}

			return "adoptionboard/adoptionDetail";
		}

		else {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("	alert('잘못된 요청입니다.');");
			sb.append("	history.back()");
			sb.append("</script>");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return null;
		}

	}

}
