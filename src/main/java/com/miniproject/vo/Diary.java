package com.miniproject.vo;

import java.sql.Timestamp;

public class Diary {

	int no;
	String memberId;
	String petName;
	String title;
	String content;
	Timestamp regDate;
	String photo;

	public Diary() {

	}

	public int getNo() {
		return no;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getPetName() {
		return petName;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
