package com.miniproject.vo;

public class Dog extends Animal {
	String dogSize;
	String barkingLevel;
	String trainingDifficulty;
	String exercise;

	public Dog(String nameKor, String nameEng, String origin, String history, String lifespan, String personality,
			String weight, String height, String coat, String color, String intelligence, String image,
			String environment, String beginnerFriendly, String grooming, String healthIssues, String dogSize,
			String barkingLevel, String trainingDifficulty, String exercise) {
		super(nameKor, nameEng, origin, history, lifespan, personality, weight, height, coat, color, intelligence,
				image, environment, beginnerFriendly, grooming, healthIssues);
		this.dogSize = dogSize;
		this.barkingLevel = barkingLevel;
		this.trainingDifficulty = trainingDifficulty;
		this.exercise = exercise;
	}
	
	public Dog() {
		super();
	}

	public String getDogSize() {
		return dogSize;
	}

	public String getBarkingLevel() {
		return barkingLevel;
	}

	public String getTrainingDifficulty() {
		return trainingDifficulty;
	}

	public String getExercise() {
		return exercise;
	}

	public void setDogSize(String dogSize) {
		this.dogSize = dogSize;
	}

	public void setBarkingLevel(String barkingLevel) {
		this.barkingLevel = barkingLevel;
	}

	public void setTrainingDifficulty(String trainingDifficulty) {
		this.trainingDifficulty = trainingDifficulty;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

}
