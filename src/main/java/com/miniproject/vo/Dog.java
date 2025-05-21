package com.miniproject.vo;

public class Dog extends Animal {
	String dogSize;
	String barkingLevel;
	String trainingDifficulty;
	String exercise;

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
	
	public void getAnimal(Animal animal){
		this.id = animal.getId();
		this.nameKor = animal.getNameKor();
	    this.nameKor = animal.getNameKor();
	    this.nameEng = animal.getNameEng();
	    this.origin = animal.getOrigin();
	    this.history = animal.getHistory();
	    this.lifespan = animal.getLifespan();
	    this.personality = animal.getPersonality();
	    this.weight = animal.getWeight();
	    this.height = animal.getHeight();
	    this.coat = animal.getCoat();
	    this.color = animal.getColor();
	    this.intelligence = animal.getIntelligence();
	    this.image = animal.getImage();
	    this.environment = animal.getEnvironment();
	    this.beginnerFriendly = animal.getBeginnerFriendly();
	    this.grooming = animal.getGrooming();
	    this.healthIssues = animal.getHealthIssues();
	    this.type = animal.getType();
	}
}
