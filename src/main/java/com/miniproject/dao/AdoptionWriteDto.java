package com.miniproject.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AdoptionWriteDto {

	private int postId;
	private String userId;
	private String title;
	private String content;
	private String adoptionType; // 입양, 분양 중 선택
	private String region;
	private String animalTypeMain; // 강아지 고양이 등 대분류
	private String animalTypeDetail; // 소형견 대형견 장묘종 단묘종 등 소분류
	private String imagePath;
	private Timestamp createdAt;
	private int viewsCount;
	private String approvalStatus; // 입양 진행중 진행 완료 대기중 등 상태 표시용
	private String formattedCreatedAt;
	
	
	public AdoptionWriteDto() {};
	
	public AdoptionWriteDto(int postId, String userId, String title, String content, String adoptionType,
            String region, String animalTypeMain, String animalTypeDetail, String imagePath, 
            Timestamp createdAt, int viewsCount, String approvalStatus) {
		this.postId = postId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.adoptionType = adoptionType;
		this.region = region;
		this.animalTypeMain = animalTypeMain;
		this.animalTypeDetail = animalTypeDetail;
		this.imagePath = imagePath;
		this.createdAt = createdAt;
		this.viewsCount = viewsCount;
		this.approvalStatus = approvalStatus;
		this.formattedCreatedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(createdAt);		
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

	public String getAdoptionType() {
		return adoptionType;
	}
	
	public void setAdoptionType(String adoptionType) {
		this.adoptionType = adoptionType;
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
		this.formattedCreatedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(createdAt);
	}

	public int getViewsCount() {
		return viewsCount;
	}

	public void setViewsCount(int viewsCount) {
		this.viewsCount = viewsCount;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public String getFormattedCreatedAt() {
		return formattedCreatedAt;
	}
		
	public void setFormattedCreatedAt(String formattedCreatedAt) {
		this.formattedCreatedAt = formattedCreatedAt;
	}


	
	
}
