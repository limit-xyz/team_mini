package com.miniproject.vo;

public class Member {

	int no;
	String name;

	public Member() {
		
	}
	
	public Member(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
