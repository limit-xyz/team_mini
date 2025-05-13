package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mimproject.vo.AnimalHospital;

import jakarta.servlet.http.HttpServlet;

public class BoardDao extends HttpServlet {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ArrayList<AnimalHospital> locationConfirm(String dataSelect) {
		
		String getAnimalBuildingLocationSql = "";
		
		if(dataSelect.equals("hospital")) {
			getAnimalBuildingLocationSql = "select * from animal_hospital";
		} else if (dataSelect.equals("beauty")) {
			getAnimalBuildingLocationSql = "select * from animal_beauty";
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
}
