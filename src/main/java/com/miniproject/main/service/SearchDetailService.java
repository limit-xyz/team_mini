package com.miniproject.main.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.ComDao;
import com.miniproject.dao.DictionaryDao;
import com.miniproject.dao.GlobalSearchDao;
import com.miniproject.vo.Cat;
import com.miniproject.vo.Community;
import com.miniproject.vo.Dog;
import com.miniproject.vo.GlobalSearch;
import com.miniproject.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchDetailService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		String type = request.getParameter("type");

//		adoption, dog, cat, faq, qna

		if (type.equals("free")) {
			ComDao dao = new ComDao();
			Community b = dao.getBoard(no);
			ArrayList<Reply> replyList = dao.getReplyList(b.getNo());

			request.setAttribute("board", b);
			request.setAttribute("replyList", replyList);

			return "free/boardDetail";
		}

		else if (type.equals("adoption")) {

			return null;
		}

		else if (type.equals("dog") || type.equals("cat")) {
			DictionaryDao dao = new DictionaryDao();
			boolean isExpert = dao.isExpert((String) request.getSession().getAttribute("id"));
			request.setAttribute("isExpert", isExpert);

			if (type.equals("dog")) {
				Dog dog = dao.getDogDictionary(no);
				String imagePath = "images/dictionary/" + dog.getImage();
				dog.setImage(imagePath);

				request.setAttribute("dog", dog);
				return "dictionary/dictionaryDetailDog";
			}

			else if (type.equals("cat")) {
				Cat cat = dao.getCatDictionary(no);
				String imagePath = "images/dictionary/" + cat.getImage();
				cat.setImage(imagePath);

				request.setAttribute("cat", cat);
				return "dictionary/dictionaryDetailCat";
			}
		}

		else if (type.equals("faq")) {
			return null;
		}

		else if (type.equals("qna")) {
			return null;
		}

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
