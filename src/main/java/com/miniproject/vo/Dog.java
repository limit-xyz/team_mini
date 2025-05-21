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

}
