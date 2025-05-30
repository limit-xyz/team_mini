package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.miniproject.vo.AnimalHospital;

public class BoardDao  {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ArrayList<AnimalHospital> newLocationConfirm(String dataSelect) {
		String getAnimalBuildingLocationSql = "";
		
		if(dataSelect.equals("hospital")) {
			getAnimalBuildingLocationSql = "SELECT * FROM animal_hospital "
					+ "WHERE id = (SELECT MAX(id) FROM animal_hospital)";
		} else if (dataSelect.equals("beauty")) {
			getAnimalBuildingLocationSql =  "SELECT * FROM animal_beauty "
					+ "WHERE id = (SELECT MAX(id) FROM animal_beauty)";
		}
		
		System.out.println(getAnimalBuildingLocationSql);
		ArrayList<AnimalHospital> animalHospitalList = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(getAnimalBuildingLocationSql);
			rs = pstmt.executeQuery();
			
			animalHospitalList = new ArrayList<AnimalHospital>();
			if (rs.next()) {
				System.out.println("yes");
				
				AnimalHospital animalHospital = new AnimalHospital();
				animalHospital.setId(rs.getInt("id"));
				animalHospital.setPlace_name(rs.getString("place_name"));
				animalHospital.setAddress(rs.getString("address"));
				animalHospital.setPhone(rs.getString("phone"));
				animalHospital.setLongitude(rs.getDouble("longitude"));
				animalHospital.setLatitude(rs.getDouble("latitude"));
				animalHospital.setPlace_url(rs.getString("place_url"));
			
				animalHospitalList.add(animalHospital);
			}
			
		} catch(SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return animalHospitalList;
	}
	
	public ArrayList<AnimalHospital> locationConfirm(String dataSelect) {
		
		String getAnimalBuildingLocationSql = "";
		
		if(dataSelect.equals("hospital")) {
			getAnimalBuildingLocationSql = "select * from animal_hospital order by id desc";
		} else if (dataSelect.equals("beauty")) {
			getAnimalBuildingLocationSql = "select * from animal_beauty order by id desc";
		}
		ArrayList<AnimalHospital> animalHospitalList = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(getAnimalBuildingLocationSql);
			rs = pstmt.executeQuery();
			
			animalHospitalList = new ArrayList<AnimalHospital>();
			
			while(rs.next()) {
				
				AnimalHospital animalHospital = new AnimalHospital();
				animalHospital.setId(rs.getInt("id"));
				animalHospital.setPlace_name(rs.getString("place_name"));
				animalHospital.setAddress(rs.getString("address"));
				animalHospital.setPhone(rs.getString("phone"));
				animalHospital.setLongitude(rs.getDouble("longitude"));
				animalHospital.setLatitude(rs.getDouble("latitude"));
				animalHospital.setPlace_url(rs.getString("place_url"));
				
				animalHospitalList.add(animalHospital);
			}
			
		} catch(SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return animalHospitalList;
	}
	
	public ArrayList<AnimalHospital> locationConfirm(String dataSelect, String searchOption) {
		
		String getAnimalBuildingLocationSql = "";
		
	
			
		if(dataSelect.equals("hospital")) {
			getAnimalBuildingLocationSql = "select * from animal_hospital where place_name LIKE '%' || ? || '%'  ";
		} else if (dataSelect.equals("beauty")) {
			getAnimalBuildingLocationSql = "select * from animal_beauty where place_name LIKE '%' || ? || '%' ";
		} 
		ArrayList<AnimalHospital> animalHospitalList = new ArrayList<AnimalHospital>();

		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(getAnimalBuildingLocationSql);
			pstmt.setString(1, searchOption);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				AnimalHospital animalHospital = new AnimalHospital();
				animalHospital.setId(rs.getInt("id"));
				animalHospital.setPlace_name(rs.getString("place_name"));
				animalHospital.setAddress(rs.getString("address"));
				animalHospital.setPhone(rs.getString("phone"));
				animalHospital.setLongitude(rs.getDouble("longitude"));
				animalHospital.setLatitude(rs.getDouble("latitude"));
				animalHospital.setPlace_url(rs.getString("place_url"));
				
				animalHospitalList.add(animalHospital);
			}
			
		} catch(SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return animalHospitalList;
	}
	
	public ArrayList<AnimalHospital> locationConfirmNearby(String dataSelect, Double lat, Double lng) {
		
		String getAnimalBuildingLocationSql = "";
		
		if(dataSelect.equals("hospital")) {
			getAnimalBuildingLocationSql = "select * from animal_hospital where power((longitude - ?),2) + power((latitude - ?),2) <= power(0.03,2) order by id desc";
		} else if (dataSelect.equals("beauty")) {
			getAnimalBuildingLocationSql = "select * from animal_beauty where power((longitude - ?),2) + power((latitude - ?),2) <= power(0.03,2) order by id desc";
		}
		ArrayList<AnimalHospital> animalHospitalList = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(getAnimalBuildingLocationSql);
			pstmt.setDouble(1, lng);
			pstmt.setDouble(2, lat);
			rs = pstmt.executeQuery();
			
			animalHospitalList = new ArrayList<AnimalHospital>();
			
			while(rs.next()) {
				
				AnimalHospital animalHospital = new AnimalHospital();
				animalHospital.setId(rs.getInt("id"));
				animalHospital.setPlace_name(rs.getString("place_name"));
				animalHospital.setAddress(rs.getString("address"));
				animalHospital.setPhone(rs.getString("phone"));
				animalHospital.setLongitude(rs.getDouble("longitude"));
				animalHospital.setLatitude(rs.getDouble("latitude"));
				animalHospital.setPlace_url(rs.getString("place_url"));
				
				animalHospitalList.add(animalHospital);
			}
			
		} catch(SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return animalHospitalList;
	}
	
	public ArrayList<AnimalHospital> locationConfirmNearby(String dataSelect, Double lat, Double lng, String searchOption) {
		
		String getAnimalBuildingLocationSql = "";
		
	
			
		if(dataSelect.equals("hospital")) {
			getAnimalBuildingLocationSql = "select * from animal_hospital where place_name LIKE '%' || ? || '%' and power((longitude - ?),2) + power((latitude - ?),2) <= power(0.03,2)";
		} else if (dataSelect.equals("beauty")) {
			getAnimalBuildingLocationSql = "select * from animal_beauty where place_name LIKE '%' || ? || '%' and power((longitude - ?),2) + power((latitude - ?),2) <= power(0.03,2)";
		} 
		ArrayList<AnimalHospital> animalHospitalList = new ArrayList<AnimalHospital>();

		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(getAnimalBuildingLocationSql);
			pstmt.setString(1, searchOption);
			pstmt.setDouble(2, lng);
			pstmt.setDouble(3, lat);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				AnimalHospital animalHospital = new AnimalHospital();
				animalHospital.setId(rs.getInt("id"));
				animalHospital.setPlace_name(rs.getString("place_name"));
				animalHospital.setAddress(rs.getString("address"));
				animalHospital.setPhone(rs.getString("phone"));
				animalHospital.setLongitude(rs.getDouble("longitude"));
				animalHospital.setLatitude(rs.getDouble("latitude"));
				animalHospital.setPlace_url(rs.getString("place_url"));
				
				animalHospitalList.add(animalHospital);
			}
			
		} catch(SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return animalHospitalList;
	}
}
