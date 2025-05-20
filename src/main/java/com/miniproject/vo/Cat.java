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

	public Cat(String nameKor, String nameEng, String origin, String history, String lifespan, String personality,
			String weight, String height, String coat, String color, String intelligence, String image,
			String environment, String beginnerFriendly, String grooming, String healthIssues, String vocality,
			String affectionLevel, String independence, String activityLevel, String sheddingLevel,
			String hypoallergenic, String compatibilityWithKids, String breedType) {
		super(nameKor, nameEng, origin, history, lifespan, personality, weight, height, coat, color, intelligence,
				image, environment, beginnerFriendly, grooming, healthIssues);
		this.vocality = vocality;
		this.affectionLevel = affectionLevel;
		this.independence = independence;
		this.activityLevel = activityLevel;
		this.sheddingLevel = sheddingLevel;
		this.hypoallergenic = hypoallergenic;
		this.compatibilityWithKids = compatibilityWithKids;
		this.breedType = breedType;
	}

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

}
