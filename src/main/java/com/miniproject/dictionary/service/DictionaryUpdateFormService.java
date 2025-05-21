package com.miniproject.dictionary.service;

import java.io.IOException;
import java.io.PrintWriter;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.DictionaryDao;
import com.miniproject.vo.Cat;
import com.miniproject.vo.Dog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DictionaryUpdateFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int animalId = Integer.parseInt(request.getParameter("animalId"));
		String type = request.getParameter("type");
		String searchType = request.getParameter("searchDiaryType");
		String searchKeyword = request.getParameter("searchDiaryKeyword");

		boolean isSearch = false;
		if (searchType != null && searchKeyword != null && !searchType.equals("") && !searchKeyword.equals(""))
			isSearch = true;

		DictionaryDao dao = new DictionaryDao();
		if (isSearch) {
			request.setAttribute("searchDiaryOption", "1");
			request.setAttribute("searchDiaryType", searchType);
			request.setAttribute("searchDiaryKeyword", searchKeyword);
		}

		if (type.equals("dog")) {
			Dog dog = dao.getDogDictionary(animalId);
			request.setAttribute("dog", dog);
			return "dictionary/dictionaryDogUpdateForm";
		}
		
		else if (type.equals("cat")) {
			Cat cat = dao.getCatDictionary(animalId);
			request.setAttribute("cat", cat);
			return "dictionary/dictionaryCatUpdateForm";	
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
