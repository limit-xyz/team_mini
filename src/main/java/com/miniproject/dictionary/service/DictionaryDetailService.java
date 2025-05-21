package com.miniproject.dictionary.service;

import java.io.IOException;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.DictionaryDao;
import com.miniproject.vo.Dog;
import com.miniproject.vo.Cat;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DictionaryDetailService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int animalId = Integer.parseInt(request.getParameter("animalId"));
		String type = request.getParameter("type");

		String searchType = request.getParameter("searchDictionaryType");
		String searchKeyword = request.getParameter("searchDictionaryKeyword");

		boolean isSearch = false;
		if (searchType != null && searchKeyword != null && !searchType.equals("") && !searchKeyword.equals("")) {
			isSearch = true;
		}

		request.setAttribute("pageNum", request.getParameter("pageNum"));
		if (isSearch) {
			request.setAttribute("searchDictionaryOption", "1");
			request.setAttribute("searchDictionaryType", searchType);
			request.setAttribute("searchDictionaryKeyword", searchKeyword);
		}

		DictionaryDao dao = new DictionaryDao();
		boolean isExpert = dao.isExpert((String) request.getSession().getAttribute("id"));
		request.setAttribute("isExpert", isExpert);

		if (type.equals("dog")) {
			Dog dog = dao.getDogDictionary(animalId);
			String imagePath = "images/dictionary/dog/" + dog.getImage() + ".png";
			dog.setImage(imagePath);

			request.setAttribute("dog", dog);
			return "dictionary/dictionaryDetailDog";
		}

		else if (type.equals("cat")) {
			Cat cat = dao.getCatDictionary(animalId);
			String imagePath = "images/dictionary/cat/" + cat.getImage() + ".png";
			cat.setImage(imagePath);

			request.setAttribute("cat", cat);
			return "dictionary/dictionaryDetailCat";
		}

		else {
			return null;
		}
	}
}
