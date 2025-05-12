package com.miniproject.adoption.dto;

import java.sql.Timestamp;

public class AdoptionWriteDao {

	private int PostId;
	private String userId;
	private String title;
	private String content;
	private String type;
	private String region;
	private String animalTypeMain;
	private String animalTypeDetail;
	private String imagePath;
	private Timestamp createdAt;
	private int views;
	
	
	public AdoptionWriteDao() {};
	
	public AdoptionWriteDao(int postId, String userId, String title, String content, String type,
            String region, String animalTypeMain, String animalTypeDetail, String imagePath, 
            Timestamp createdAt, int views) {
		this.PostId = postId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.type = type;
		this.region = region;
		this.animalTypeMain = animalTypeMain;
		this.animalTypeDetail = animalTypeDetail;
		this.imagePath = imagePath;
		this.createdAt = createdAt;
		this.views = views;
	}

	public int getPostId() {
		return PostId;
	}

	public void setPostId(int postId) {
		PostId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAnimalTypeMain() {
		return animalTypeMain;
	}

	public void setAnimalTypeMain(String animalTypeMain) {
		this.animalTypeMain = animalTypeMain;
	}

	public String getAnimalTypeDetail() {
		return animalTypeDetail;
	}

	public void setAnimalTypeDetail(String animalTypeDetail) {
		this.animalTypeDetail = animalTypeDetail;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
	
	
}
