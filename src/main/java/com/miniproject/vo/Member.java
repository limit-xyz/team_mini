package com.miniproject.vo;

import java.sql.Timestamp;

public class Member {

	int no;
	String name;
	Timestamp regDate;
	Timestamp ignoreDate;
	boolean ignore;

	public Member() {

	}

	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getIgnoreDate() {
		return ignoreDate;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public void setIgnoreDate(Timestamp ignoreDate) {
		this.ignoreDate = ignoreDate;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", name=" + name + ", regDate=" + regDate + ", ignoreDate=" + ignoreDate
				+ ", ignore=" + ignore + "]";
	}

}
