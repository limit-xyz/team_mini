package com.miniproject.vo;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Member {

	String id;
	String name;
	String password;
	String gender;
	String mobile;
	String address;
	String email;
	Timestamp regDate;
	String introduction;
	Timestamp birthDate;
	Timestamp banDate;
	String banReason;
	String role;
	boolean ban;

	public Member() {

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public String getIntroduction() {
		return introduction;
	}

	public Timestamp getBirthDate() {
		return birthDate;
	}

	public Timestamp getBanDate() {
		return banDate;
	}

	public String getBanReason() {
		return banReason;
	}

	public String getRole() {
		return role;
	}

	public boolean isBan() {
		return ban;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public void setBanDate(Timestamp banDate) {
		this.banDate = banDate;
	}

	public void setBanReason(String banReason) {
		this.banReason = banReason;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setBan(Timestamp ts) {
		LocalDate today = LocalDate.now();
		LocalDate banDate = ts.toLocalDateTime().toLocalDate();

		this.ban = today.isBefore(banDate);
	}

	public void setBan(Boolean ban) {
		this.ban = ban;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", gender=" + gender + ", mobile="
				+ mobile + ", address=" + address + ", email=" + email + ", regDate=" + regDate + ", introduction="
				+ introduction + ", birthDate=" + birthDate + ", banDate=" + banDate + ", banReason=" + banReason
				+ ", role=" + role + ", ban=" + ban + "]";
	}

	
}
