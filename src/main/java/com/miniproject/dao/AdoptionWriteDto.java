package com.miniproject.dao;

import java.sql.Timestamp;

public class AdoptionWriteDto {

	private int postId;
	private String userId;
	private String title;
	private String content;
	private String type; // 입양, 분양 중 선택
	private String region;
	private String animalTypeMain; // 강아지 고양이 등 대분류
	private String animalTypeDetail; // 소형견 대형견 장묘종 단묘종 등 소분류
	private String imagePath;
	private Timestamp createdAt;
	private int views;
	private String approvalStatus; // 입양 진행중 진행 완료 대기중 등 상태 표시용
	
	
	public AdoptionWriteDto() {};
	
	public AdoptionWriteDto(int postId, String userId, String title, String content, String type,
            String region, String animalTypeMain, String animalTypeDetail, String imagePath, 
            Timestamp createdAt, int views, String approvalStatus) {
		this.postId = postId;
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
		this.approvalStatus = approvalStatus;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
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

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	
	
}
