// memberJoinForm.jsp에서 들어온 자료를 가공하는곳 

package com.miniproject.member.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate; // LocalDate를 사용할 경우 필요
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.miniproject.dao.MemberDao;
import com.miniproject.common.service.CommandProcess;
import com.miniproject.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberJoinResultService implements CommandProcess {

    @Override
    public String requestProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 요청 파라미터 받기
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender"); // gender 필드도 Member 객체에 설정 필요
        String emailId = request.getParameter("emailId");
        String emailDomain = request.getParameter("emailDomain");
        String mobile1 = request.getParameter("mobile1");
        String mobile2 = request.getParameter("mobile2");
        String mobile3 = request.getParameter("mobile3");
        String zipcode = request.getParameter("zipcode");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String introduction = request.getParameter("introduction"); // introduction 필드도 Member 객체에 설정 필요
        String birthDateString = request.getParameter("birthDate"); // 변수명 수정: birthDateString
        
        Timestamp birthTimestamp = null; // 변수명 변경: birthTimestamp (기존 birthDate String과 구분)

        if (birthDateString != null && !birthDateString.isEmpty()) {
            try {

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(birthDateString, dateFormatter);
                birthTimestamp = Timestamp.valueOf(localDate.atStartOfDay()); // 자정 기준으로 Timestamp 생성




            } catch (DateTimeParseException e) {
                // 날짜 형식이 잘못된 경우 예외 처리
                e.printStackTrace();
                // 오류 처리 로직 추가 
                
            }
        }

        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setGender(gender);
        member.setPassword(password);
        member.setEmail(emailId + "@" + emailDomain);
        member.setMobile(mobile1 + "-" + mobile2 + "-" + mobile3);
        member.setZipcode(zipcode);
        member.setAddress1(address1);
        member.setAddress2(address2);
        member.setIntroduction(introduction);

        // 파싱된 생년월일 설정
        if (birthTimestamp != null) {
            member.setBirthDate(birthTimestamp); // Member 클래스에 setBirthDate(Timestamp birthDate) 메소드가 있어야 함
        }

        MemberDao dao = new MemberDao();
        dao.joinMember(member);

        // 회원가입 성공 후 로그인 폼으로 리다이렉트
        return "r:loginForm"; // "r:" 접두사는 일반적으로 리다이렉트를 의미 (프레임워크에 따라 다를 수 있음)
    }
}