package com.miniproject.sql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class KakaoHospitalInserter {
    public static void main(String[] args) {
        String csvFile = "kakao_hospital_result2.csv";
        String line;
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // DB 접속 정보
        String dbUser = "your_username";
        String dbPassword = "your_password";

        try (
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
        ) {
            // 첫 줄은 헤더이므로 skip
            br.readLine();

            String sql = "INSERT INTO animal_hospital "
                       + "(id, place_name, address, phone, longitude, latitude, place_url) "
                       + "VALUES (animal_hospital_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);  // CSV comma-safe split

                if (data.length < 6) continue;  // 잘못된 줄은 skip

                pstmt.setString(1, data[0]);                        // place_name
                pstmt.setString(2, data[1]);                        // address
                pstmt.setString(3, data[2]);                        // phone
                pstmt.setDouble(4, Double.parseDouble(data[3]));    // longitude
                pstmt.setDouble(5, Double.parseDouble(data[4]));    // latitude
                pstmt.setString(6, data[5]);                        // place_url

                pstmt.executeUpdate();
            }

            pstmt.close();
            System.out.println("모든 동물병원 정보가 성공적으로 삽입되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
