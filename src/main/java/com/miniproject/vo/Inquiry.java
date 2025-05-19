package com.miniproject.vo;

import java.sql.Timestamp;

public class Inquiry {

	int no;
	String userId;
	String category;
	String title;
	String content;
	String status;
	Timestamp regDate;
	String ansContent;
	Timestamp ansDate;

	public Inquiry() {

	}

	public int getNo() {
		return no;
	}

	public String getUserId() {
		return userId;
	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getStatus() {
		return status;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public String getAnsContent() {
		return ansContent;
	}

	public Timestamp getAnsDate() {
		return ansDate;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public void setAnsContent(String ansContent) {
		this.ansContent = ansContent;
	}

	public void setAnsDate(Timestamp ansDate) {
		this.ansDate = ansDate;
	}

}
