package com.miniproject.vo;

public class Cat extends Animal {
	String vocality;
	String affectionLevel;
	String independence;
	String activityLevel;
	String sheddingLevel;
	String hypoallergenic;
	String compatibilityWithKids;
	String breedType;

	public Cat() {
		super();
	}

	public String getVocality() {
		return vocality;
	}

	public String getAffectionLevel() {
		return affectionLevel;
	}

	public String getIndependence() {
		return independence;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public String getSheddingLevel() {
		return sheddingLevel;
	}

	public String getHypoallergenic() {
		return hypoallergenic;
	}

	public String getCompatibilityWithKids() {
		return compatibilityWithKids;
	}

	public String getBreedType() {
		return breedType;
	}

	public void setVocality(String vocality) {
		this.vocality = vocality;
	}

	public void setAffectionLevel(String affectionLevel) {
		this.affectionLevel = affectionLevel;
	}

	public void setIndependence(String independence) {
		this.independence = independence;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public void setSheddingLevel(String sheddingLevel) {
		this.sheddingLevel = sheddingLevel;
	}

	public void setHypoallergenic(String hypoallergenic) {
		this.hypoallergenic = hypoallergenic;
	}

	public void setCompatibilityWithKids(String compatibilityWithKids) {
		this.compatibilityWithKids = compatibilityWithKids;
	}

	public void setBreedType(String breedType) {
		this.breedType = breedType;
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
