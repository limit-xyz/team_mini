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

		String DictionaryDetailSql = "SELECT * FROM cat WHERE id=?";

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
				+ "SELECT id, name_ko, name_en, origin, height, weight, 'dog' \"type\" , image FROM dog "
				+ "UNION SELECT id, name_ko, name_en, origin, height, weight, 'cat' \"type\", image FROM cat ORDER BY name_ko) sub) "
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
					Animal animal = new Animal();
					animal.setId(rs.getInt("id"));
					animal.setNameKor(rs.getString("name_ko"));
					animal.setNameEng(rs.getString("name_en"));
					animal.setOrigin(rs.getString("origin"));
					animal.setHeight(rs.getString("height"));
					animal.setWeight(rs.getString("weight"));
					animal.setType(rs.getString("type"));
					
					if (animal.getType().equals("dog")){
						animal.setImage("images/dictionary/dog/" + rs.getString("image") + ".png");
					}
					else if (animal.getType().equals("cat"))  {
						animal.setImage("images/dictionary/cat/" + rs.getString("image") + ".png");
					}

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

		String DictionaryListSql = "";
		boolean isType = false;

		if (type.equals("type")) {
			isType = true;
			if (keyword.equals("dog") || keyword.equals("강아지")) {
				DictionaryListSql = "SELECT * FROM ( SELECT ROWNUM num, sub.* FROM ( "
						+ "SELECT name_ko, name_en, origin, height, weight, 'dog' \"type\", image FROM dog) "
						+ "sub) WHERE num BETWEEN ? AND ?";
			}

			else if (keyword.equals("cat") || keyword.equals("고양이")) {
				DictionaryListSql = "SELECT * FROM ( SELECT ROWNUM num, sub.* FROM ( "
						+ "SELECT name_ko, name_en, origin, height, weight, 'cat' \"type\", image FROM cat) "
						+ "sub) WHERE num BETWEEN ? AND ?";
			}
		}

		else {
			DictionaryListSql = "SELECT * FROM ( SELECT ROWNUM num, sub.* FROM ( "
					+ "SELECT name_ko, name_en, origin, height, weight, 'dog' \"type\" FROM dog "
					+ "UNION SELECT name_ko, name_en, origin, height, weight, 'cat' \"type\" FROM cat "
					+ "ORDER BY name_ko) sub WHERE " + type + " LIKE ?) WHERE num BETWEEN ? AND ?";
		}

		ArrayList<Animal> DictionaryList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(DictionaryListSql);

			if (isType) {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}

			else {
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {

				DictionaryList = new ArrayList<>();

				do {
					Animal animal = new Animal();
					animal.setNameKor(rs.getString("name_ko"));
					animal.setNameEng(rs.getString("name_en"));
					animal.setOrigin(rs.getString("origin"));
					animal.setHeight(rs.getString("height"));
					animal.setWeight(rs.getString("weight"));
					animal.setType(rs.getString("type"));
					
					if (animal.getType().equals("dog")){
						animal.setImage("images/dictionary/dog/" + rs.getString("image") + ".png");
					}
					else if (animal.getType().equals("cat"))  {
						animal.setImage("images/dictionary/cat/" + rs.getString("image") + ".png");
					}

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

	// 강아지 사전 쓰기
	public void insertDictionaryDog(Dog dog) {
		String insertDictionaryDogSql = "INSERT INTO dog VALUES (dog_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(insertDictionaryDogSql);
			pstmt.setString(1, checkValue(dog.getNameKor()));
			pstmt.setString(2, checkValue(dog.getNameEng()));
			pstmt.setString(3, checkValue(dog.getDogSize()));
			pstmt.setString(4, checkValue(dog.getOrigin()));
			pstmt.setString(5, checkValue(dog.getHistory()));
			pstmt.setString(6, checkValue(dog.getLifespan()));
			pstmt.setString(7, checkValue(dog.getPersonality()));
			pstmt.setString(8, checkValue(dog.getWeight()));
			pstmt.setString(9, checkValue(dog.getHeight()));
			pstmt.setString(10, checkValue(dog.getCoat()));
			pstmt.setString(11, checkValue(dog.getColor()));
			pstmt.setString(12, checkValue(dog.getBarkingLevel()));
			pstmt.setString(13, checkValue(dog.getIntelligence()));
			pstmt.setString(14, checkValue(dog.getTrainingDifficulty()));
			pstmt.setString(15, checkValue(dog.getExercise()));
			pstmt.setString(16, checkValue(dog.getHealthIssues()));
			pstmt.setString(17, checkValue(dog.getGrooming()));
			pstmt.setString(18, checkValue(dog.getBeginnerFriendly()));
			pstmt.setString(19, checkValue(dog.getEnvironment()));
			pstmt.setString(20, checkValue(dog.getImage()));
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 고양이 사전 쓰기
	public void insertDictionaryCat(Cat cat) {
		String insertDictionaryCatSql = "INSERT INTO cat VALUES (cat_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(insertDictionaryCatSql);
			pstmt.setString(1, checkValue(cat.getNameKor()));
			pstmt.setString(2, checkValue(cat.getNameEng()));
			pstmt.setString(3, checkValue(cat.getOrigin()));
			pstmt.setString(4, checkValue(cat.getHistory()));
			pstmt.setString(5, checkValue(cat.getLifespan()));
			pstmt.setString(6, checkValue(cat.getPersonality()));
			pstmt.setString(7, checkValue(cat.getWeight()));
			pstmt.setString(8, checkValue(cat.getHeight()));
			pstmt.setString(9, checkValue(cat.getCoat()));
			pstmt.setString(10, checkValue(cat.getColor()));
			pstmt.setString(11, checkValue(cat.getVocality()));
			pstmt.setString(12, checkValue(cat.getIntelligence()));
			pstmt.setString(13, checkValue(cat.getAffectionLevel()));
			pstmt.setString(14, checkValue(cat.getIndependence()));
			pstmt.setString(15, checkValue(cat.getActivityLevel()));
			pstmt.setString(16, checkValue(cat.getGrooming()));
			pstmt.setString(17, checkValue(cat.getHealthIssues()));
			pstmt.setString(18, checkValue(cat.getSheddingLevel()));
			pstmt.setString(19, checkValue(cat.getHypoallergenic()));
			pstmt.setString(20, checkValue(cat.getCompatibilityWithKids()));
			pstmt.setString(21, checkValue(cat.getBeginnerFriendly()));
			pstmt.setString(22, checkValue(cat.getEnvironment()));
			pstmt.setString(23, checkValue(cat.getBreedType()));
			pstmt.setString(24, checkValue(cat.getImage()));
			pstmt.setInt(25, cat.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 강아지 사전 수정
	public void updateDictionaryDog(Dog dog) {
		String updateDictionaryDogSql = "UPDATE dog SET name_ko=?, name_en=?, dog_size=?, origin=?, history=?, lifespan=?, personality=?, weight=?, "
				+ "height=?, coat=?, color=?, barking_level=?, intelligence=?, training_difficulty=?, exercise=?, health_issues=?, "
				+ "grooming=?, beginner_friendly=?, environment=?, image=? WHERE id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(updateDictionaryDogSql);
			pstmt.setString(1, checkValue(dog.getNameKor()));
			pstmt.setString(2, checkValue(dog.getNameEng()));
			pstmt.setString(3, checkValue(dog.getDogSize()));
			pstmt.setString(4, checkValue(dog.getOrigin()));
			pstmt.setString(5, checkValue(dog.getHistory()));
			pstmt.setString(6, checkValue(dog.getLifespan()));
			pstmt.setString(7, checkValue(dog.getPersonality()));
			pstmt.setString(8, checkValue(dog.getWeight()));
			pstmt.setString(9, checkValue(dog.getHeight()));
			pstmt.setString(10, checkValue(dog.getCoat()));
			pstmt.setString(11, checkValue(dog.getColor()));
			pstmt.setString(12, checkValue(dog.getBarkingLevel()));
			pstmt.setString(13, checkValue(dog.getIntelligence()));
			pstmt.setString(14, checkValue(dog.getTrainingDifficulty()));
			pstmt.setString(15, checkValue(dog.getExercise()));
			pstmt.setString(16, checkValue(dog.getHealthIssues()));
			pstmt.setString(17, checkValue(dog.getGrooming()));
			pstmt.setString(18, checkValue(dog.getBeginnerFriendly()));
			pstmt.setString(19, checkValue(dog.getEnvironment()));
			pstmt.setString(20, checkValue(dog.getImage()));
			pstmt.setInt(21, dog.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 고양이 사전 수정
	public void updateDictionaryCat(Cat cat) {
		String updateDictionaryCatSql = "UPDATE cat SET name_ko=?, name_en=?, origin=?, history=?, lifespan=?, personality=?, weight=?, "
				+ "height=?, coat=?, color=?, vocality=?, intelligence=?, affection_level=?, independence=?, activity_level=?, "
				+ "grooming=?, health_issues=?, shedding_level=?, hypoallergenic=?, compatibility_with_kids=?, "
				+ "beginner_friendly=?, environment=?, breed_type=?, image=? WHERE id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(updateDictionaryCatSql);
			pstmt.setString(1, checkValue(cat.getNameKor()));
			pstmt.setString(2, checkValue(cat.getNameEng()));
			pstmt.setString(3, checkValue(cat.getOrigin()));
			pstmt.setString(4, checkValue(cat.getHistory()));
			pstmt.setString(5, checkValue(cat.getLifespan()));
			pstmt.setString(6, checkValue(cat.getPersonality()));
			pstmt.setString(7, checkValue(cat.getWeight()));
			pstmt.setString(8, checkValue(cat.getHeight()));
			pstmt.setString(9, checkValue(cat.getCoat()));
			pstmt.setString(10, checkValue(cat.getColor()));
			pstmt.setString(11, checkValue(cat.getVocality()));
			pstmt.setString(12, checkValue(cat.getIntelligence()));
			pstmt.setString(13, checkValue(cat.getAffectionLevel()));
			pstmt.setString(14, checkValue(cat.getIndependence()));
			pstmt.setString(15, checkValue(cat.getActivityLevel()));
			pstmt.setString(16, checkValue(cat.getGrooming()));
			pstmt.setString(17, checkValue(cat.getHealthIssues()));
			pstmt.setString(18, checkValue(cat.getSheddingLevel()));
			pstmt.setString(19, checkValue(cat.getHypoallergenic()));
			pstmt.setString(20, checkValue(cat.getCompatibilityWithKids()));
			pstmt.setString(21, checkValue(cat.getBeginnerFriendly()));
			pstmt.setString(22, checkValue(cat.getEnvironment()));
			pstmt.setString(23, checkValue(cat.getBreedType()));
			pstmt.setString(24, checkValue(cat.getImage()));
			pstmt.setInt(25, cat.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 다이어리 삭제
	public void deleteDictionary(int id, String type) {

		String deleteDictionarySql = "DELETE FROM " + type + " WHERE id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(deleteDictionarySql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 전문가 혹은 어드민 계정인지 체크하는 메소드
	public boolean isExpert(String id) {
		String checkExpertSql = "SELECT role FROM member WHERE id=?";
		String role = "";

		if (id == null) {
			return false;
		}

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(checkExpertSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				role = rs.getString("role");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return (role.equals("expert") || role.equals("admin"));
	}

	// 임시값 넣는 메소드
	String checkValue(String str) {
		if (str == null || str.equals("")) {
			return "작성중";
		} else
			return str;
	}
}
