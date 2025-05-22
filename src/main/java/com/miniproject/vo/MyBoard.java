package com.miniproject.vo;

import java.sql.Timestamp;

public class MyBoard {
	int no, readCount;
	String type, title, writer;
	Timestamp regDate;

	public int getNo() {
		return no;
	}

	public int getReadCount() {
		return readCount;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getWriter() {
		return writer;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
}
