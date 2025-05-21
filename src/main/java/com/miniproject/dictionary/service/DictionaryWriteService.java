package com.miniproject.dictionary.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import com.miniproject.common.service.CommandProcess;
import com.miniproject.dao.DiaryDao;
import com.miniproject.dao.DictionaryDao;
import com.miniproject.vo.Animal;
import com.miniproject.vo.Cat;
import com.miniproject.vo.Diary;
import com.miniproject.vo.Dog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class DictionaryWriteService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");

		String contentType = request.getContentType();

		Animal animal = new Animal();
		Dog dog = new Dog();
		Cat cat = new Cat();

		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {

			Collection<Part> parts = request.getParts();

			for (Part part : parts) {

				String partHeader = part.getHeader("Content-Disposition");

				if (partHeader.contains("filename=")) {
					if (part.getSize() > 0) {

						File parentFile = (File) request.getServletContext().getAttribute("parentFile");
						UUID uid = UUID.randomUUID();

						String saveName = uid.toString() + "_" + part.getSubmittedFileName();
						String savePath = parentFile.getAbsolutePath() + File.separator + saveName;

						part.write(savePath);
						part.delete();
						animal.setImage(saveName);

					} else {
						System.out.println("파일이 업로드 되지 않음");
					}

				} else {

					String paramName = part.getName();
					String paramValue = request.getParameter(paramName);

					switch (paramName) {
					case "nameKor" -> animal.setNameKor(paramValue);
					case "nameEng" -> animal.setNameEng(paramValue);
					case "origin" -> animal.setOrigin(paramValue);
					case "history" -> animal.setHistory(paramValue);
					case "lifespan" -> animal.setLifespan(paramValue);
					case "personality" -> animal.setPersonality(paramValue);
					case "weight" -> animal.setWeight(paramValue);
					case "height" -> animal.setHeight(paramValue);
					case "coat" -> animal.setCoat(paramValue);
					case "color" -> animal.setColor(paramValue);
					case "intelligence" -> animal.setIntelligence(paramValue);
					case "environment" -> animal.setEnvironment(paramValue);
					case "beginnerFriendly" -> animal.setBeginnerFriendly(paramValue);
					case "grooming" -> animal.setGrooming(paramValue);
					case "healthIssues" -> animal.setHealthIssues(paramValue);
					case "type" -> animal.setType(paramValue);

					case "dogSize" -> dog.setDogSize(paramValue);
					case "barkingLevel" -> dog.setBarkingLevel(paramValue);
					case "trainingDifficulty" -> dog.setTrainingDifficulty(paramValue);
					case "exercise" -> dog.setExercise(paramValue);

					case "vocality" -> cat.setVocality(paramValue);
					case "affectionLevel" -> cat.setAffectionLevel(paramValue);
					case "independence" -> cat.setIndependence(paramValue);
					case "activityLevel" -> cat.setActivityLevel(paramValue);
					case "sheddingLevel" -> cat.setSheddingLevel(paramValue);
					case "hypoallergenic" -> cat.setHypoallergenic(paramValue);
					case "compatibilityWithKids" -> cat.setCompatibilityWithKids(paramValue);
					case "breedType" -> cat.setBreedType(paramValue);
					}
					;

				}
			}

		} else {
			System.out.println("폼에서 전송된 요청이 mutipart/form-data가 아님");
		}

		DictionaryDao dao = new DictionaryDao();

		if (type.equals("dog")) {
			dog.getAnimal(animal);
			dao.insertDictionaryDog(dog);
		}

		else if (type.equals("cat")) {
			cat.getAnimal(animal);
			dao.insertDictionaryCat(cat);
		}

		return "r:dictionaryList?pageNum=" + pageNum;
	}

}
