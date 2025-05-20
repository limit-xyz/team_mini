package com.miniproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.miniproject.vo.Animal;
import com.miniproject.vo.Dog;
import com.miniproject.vo.Cat;

public class DictionaryDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DictionaryDao() {
	}

	// 사전 갯수 가져오기
	public int getDictionaryCount(String type, String keyword) {

		String getCountSql = "";
		boolean isSearch = false;

		// 검색일 경우
		if (!(type == null || keyword == null || type.equals("") || keyword.equals(""))) {
			// 검색 타입이 종류 이고, 검색 내용이 강아지 일 경우
			if (type.equals("type") || keyword.equals("강아지")) {
				getCountSql = "SELECT count(*) count FROM dog";
			}

			// 검색 타입이 종류 이고, 검색 내용이 고양이 일 경우
			else if (type.equals("type") || keyword.equals("고양이")) {
				getCountSql = "SELECT count(*) count FROM cat";
			}

			// 검색 타입이 종류가 아닌 경우
			else {
				getCountSql = "SELECT count(*) count FROM (SELECT name_ko, name_en FROM dog "
						+ "UNION SELECT name_ko, name_en FROM cat) WHERE " + type + " LIKE ?";
				isSearch = true;
			}
		}

		// 검색이 아닌 일반 요청일 경우
		else {
			getCountSql = "SELECT count(*) count FROM (SELECT name_ko FROM dog UNION SELECT name_ko FROM cat)";
		}

		int count = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(getCountSql);
			if (isSearch) {
				pstmt.setString(1, "%" + keyword + "%");
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return count;
	}

	// 사전 상세 가져오기 - 강아지
	public Dog getDogDictionary(int id) {

		String DictionaryDetailSql = "SELECT * FROM dog WHERE id=?";

		Dog dog = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(DictionaryDetailSql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dog = new Dog();

				dog.setId(rs.getInt("id"));
				dog.setNameKor(rs.getString("name_ko"));
				dog.setNameEng(rs.getString("name_en"));
				dog.setOrigin(rs.getString("origin"));
				dog.setHistory(rs.getString("history"));
				dog.setLifespan(rs.getString("lifespan"));
				dog.setPersonality(rs.getString("personality"));
				dog.setWeight(rs.getString("weight"));
				dog.setHeight(rs.getString("height"));
				dog.setCoat(rs.getString("coat"));
				dog.setColor(rs.getString("color"));
				dog.setIntelligence(rs.getString("intelligence"));
				dog.setImage(rs.getString("image"));
				dog.setEnvironment(rs.getString("environment"));
				dog.setBeginnerFriendly(rs.getString("beginner_friendly"));
				dog.setGrooming(rs.getString("grooming"));
				dog.setHealthIssues(rs.getString("health_issues"));

				dog.setType("dog");
				dog.setDogSize(rs.getString("dog_size"));
				dog.setBarkingLevel(rs.getString("barking_level"));
				dog.setTrainingDifficulty(rs.getString("training_difficulty"));
				dog.setExercise(rs.getString("exercise"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return dog;
	}

	// 사전 상세 가져오기 - 고양이
	public Cat getCatDictionary(int id) {

		String DictionaryDetailSql = "SELECT * FROM dog WHERE id=?";

		Cat cat = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(DictionaryDetailSql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cat = new Cat();

				cat.setId(rs.getInt("id"));
				cat.setNameKor(rs.getString("name_ko"));
				cat.setNameEng(rs.getString("name_en"));
				cat.setOrigin(rs.getString("origin"));
				cat.setHistory(rs.getString("history"));
				cat.setLifespan(rs.getString("lifespan"));
				cat.setPersonality(rs.getString("personality"));
				cat.setWeight(rs.getString("weight"));
				cat.setHeight(rs.getString("height"));
				cat.setCoat(rs.getString("coat"));
				cat.setColor(rs.getString("color"));
				cat.setIntelligence(rs.getString("intelligence"));
				cat.setImage(rs.getString("image"));
				cat.setEnvironment(rs.getString("environment"));
				cat.setBeginnerFriendly(rs.getString("beginner_friendly"));
				cat.setGrooming(rs.getString("grooming"));
				cat.setHealthIssues(rs.getString("health_issues"));

				cat.setType("cat");
				cat.setVocality(rs.getString("vocality"));
				cat.setAffectionLevel(rs.getString("affection_level"));
				cat.setIndependence(rs.getString("independence"));
				cat.setActivityLevel(rs.getString("activity_level"));
				cat.setSheddingLevel(rs.getString("shedding_level"));
				cat.setHypoallergenic(rs.getString("hypoallergenic"));
				cat.setCompatibilityWithKids(rs.getString("compatibility_with_kids"));
				cat.setBreedType(rs.getString("breed_type"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return cat;
	}

	// 사전 목록 가져오기
	public ArrayList<Animal> getDictionaryList(int startRow, int endRow) {

		String DictionaryListSql = "SELECT * FROM ( " + "SELECT ROWNUM num, sub.* FROM( "
				+ "SELECT name_ko, name_en, origin, height, weight, 'dog' \"type\" FROM dog "
				+ "UNION SELECT name_ko, name_en, origin, height, weight, 'cat' \"type\" FROM cat ORDER BY name_ko) sub) "
				+ "WHERE num BETWEEN ? AND ?";

		ArrayList<Animal> DictionaryList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(DictionaryListSql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				DictionaryList = new ArrayList<>();

				do {
					Animal animal = new Dog();
					animal.setNameKor(rs.getString("name_ko"));
					animal.setNameEng(rs.getString("name_en"));
					animal.setOrigin(rs.getString("origin"));
					animal.setHeight(rs.getString("height"));
					animal.setWeight(rs.getString("weight"));
					animal.setType(rs.getString("type"));

					DictionaryList.add(animal);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return DictionaryList;
	}

	// 검색한 다이어리 목록 가져오기
	public ArrayList<Animal> searchDictionaryList(String type, String keyword, int startRow, int endRow) {

		String DictionaryListSql = "SELECT * FROM ( " + "SELECT ROWNUM num, sub.* FROM( "
				+ "SELECT name_ko, name_en, origin, height, weight, 'dog' \"type\" FROM dog "
				+ "UNION SELECT name_ko, name_en, origin, height, weight, 'cat' \"type\" FROM cat "
				+ "ORDER BY name_ko) sub WHERE " + type + "LIKE ?) WHERE num BETWEEN ? AND ?";

		ArrayList<Animal> DictionaryList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(DictionaryListSql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				DictionaryList = new ArrayList<>();

				do {
					Animal animal = new Dog();
					animal.setNameKor(rs.getString("name_ko"));
					animal.setNameEng(rs.getString("name_en"));
					animal.setOrigin(rs.getString("origin"));
					animal.setHeight(rs.getString("height"));
					animal.setWeight(rs.getString("weight"));
					animal.setType(rs.getString("type"));

					DictionaryList.add(animal);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return DictionaryList;
	}

	/*
	// 다이어리 쓰기
	public void insertDictionary(Dictionary Dictionary) {
		String insertDictionarylSql = "INSERT INTO Dictionary (Dictionary_no, member_id, pet_name, title, content, photo) "
				+ "VALUES (Dictionary_seq.NEXTVAL, ?, ?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(insertDictionarylSql);
			pstmt.setString(1, Dictionary.getMemberId());
			pstmt.setString(2, Dictionary.getPetName());
			pstmt.setString(3, Dictionary.getTitle());
			pstmt.setString(4, Dictionary.getContent());
			pstmt.setString(5, Dictionary.getPhoto());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 다이어리 수정
	public void updateDictionary(Dictionary Dictionary, int no) {
		String updateDictionarylSql = "UPDATE Dictionary SET pet_name=?, title=?, content=?, photo=? WHERE Dictionary_no=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(updateDictionarylSql);
			pstmt.setString(1, Dictionary.getPetName());
			pstmt.setString(2, Dictionary.getTitle());
			pstmt.setString(3, Dictionary.getContent());
			pstmt.setString(4, Dictionary.getPhoto());
			pstmt.setInt(5, Dictionary.getNo());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 다이어리 삭제
	public void deleteDictionary(int no) {

		String deleteDictionarylSql = "DELETE FROM Dictionary WHERE Dictionary_no=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(deleteDictionarylSql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}*/
}
