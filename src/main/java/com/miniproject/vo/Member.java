package com.miniproject.vo;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Member {

	String id;
	String name;
	String password;
	String gender;
	String mobile;
	String zipcode;
	String address1;
	String address2;
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

	public String getZipcode() {
		return zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
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

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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
				+ mobile + ", zipcode=" + zipcode + ", address1=" + address1 + ", address2=" + address2 + ", email="
				+ email + ", regDate=" + regDate + ", introduction=" + introduction + ", birthDate=" + birthDate
				+ ", banDate=" + banDate + ", banReason=" + banReason + ", role=" + role + ", ban=" + ban + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getPassword()=" + getPassword() + ", getGender()="
				+ getGender() + ", getMobile()=" + getMobile() + ", getZipcode()=" + getZipcode() + ", getAddress1()="
				+ getAddress1() + ", getAddress2()=" + getAddress2() + ", getEmail()=" + getEmail() + ", getRegDate()="
				+ getRegDate() + ", getIntroduction()=" + getIntroduction() + ", getBirthDate()=" + getBirthDate()
				+ ", getBanDate()=" + getBanDate() + ", getBanReason()=" + getBanReason() + ", getRole()=" + getRole()
				+ ", isBan()=" + isBan() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
