package com.miniproject.vo;

public class AnimalHospital {
	private int id;
	private String place_name, address, phone, place_url;
	private double longitude, latitude;
	
	public AnimalHospital() {}

	public AnimalHospital(int id, String place_name, String address, String phone, String place_url, double longitude,
			double latitude) {
		super();
		this.id = id;
		this.place_name = place_name;
		this.address = address;
		this.phone = phone;
		this.place_url = place_url;
		this.longitude = longitude;
		this.latitude = latitude;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlace_url() {
		return place_url;
	}

	public void setPlace_url(String place_url) {
		this.place_url = place_url;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
}
