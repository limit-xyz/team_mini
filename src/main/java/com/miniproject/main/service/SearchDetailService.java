package com.miniproject.main.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.AdoptionDao01;
import com.miniproject.dao.AdoptionReplyDto;
import com.miniproject.dao.AdoptionWriteDto;
import com.miniproject.dao.ComDao;
import com.miniproject.dao.DictionaryDao;
import com.miniproject.dao.FaqDao;
import com.miniproject.dao.QnaDao;
import com.miniproject.vo.Cat;
import com.miniproject.vo.Community;
import com.miniproject.vo.Dog;
import com.miniproject.vo.Faq;
import com.miniproject.vo.QnaAnswer;
import com.miniproject.vo.QnaBoard;
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

		request.setAttribute("pageNum", 1);

		if (type.equals("free")) {
			ComDao dao = new ComDao();
			Community b = dao.getBoard(no);
			ArrayList<Reply> replyList = dao.getReplyList(b.getNo());

			request.setAttribute("board", b);
			request.setAttribute("replyList", replyList);

			return "free/boardDetail";
		}

		else if (type.equals("adoption")) {

			AdoptionDao01 dao = new AdoptionDao01();
			AdoptionWriteDto adoptionDetail;

			adoptionDetail = dao.getAdoption(no, true);
			List<AdoptionReplyDto> replyList = dao.getReplyList(no);
			int replyCount = dao.getReplyCount(no);

			request.setAttribute("adopboard", adoptionDetail);
			request.setAttribute("adopreplyList", replyList);
			request.setAttribute("replyCount", replyCount);

			return "adoptionboard/adoptionDetail";
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
			FaqDao dao = new FaqDao();
			ArrayList<Faq> faqList = new ArrayList<>();
			faqList.add(dao.getFaq(no));
			request.setAttribute("faqList", faqList);
			return "support/faq";
		}

		else if (type.equals("qna")) {
			QnaDao qnaDao = new QnaDao();
			QnaBoard qnaBoard = qnaDao.getQnaDetail(no);
			QnaAnswer qnaAnswer = qnaDao.getAnswerByQnaNo(no);

			if (qnaAnswer != null) {
				request.setAttribute("qnaAnswer", qnaAnswer);
			}
			request.setAttribute("qnaBoard", qnaBoard);

			return "support/qnaDetail";
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
