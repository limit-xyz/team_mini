package com.miniproject.helper.service;

import java.io.IOException;

import com.miniproject.dao.MemberDao;
import com.miniproject.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberJoinResultService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//
//		String name = request.getParameter("name");
//		String id = request.getParameter("id");
//		String pass = request.getParameter("pass1");
//		String emailId = request.getParameter("emailId");
//		String emailDomain = request.getParameter("emailDomain");
//		String mobile1 = request.getParameter("mobile1");
//		String mobile2 = request.getParameter("mobile2");
//		String mobile3 = request.getParameter("mobile3");
//		String zipcode = request.getParameter("zipcode");
//		String address1 = request.getParameter("address1");
//		String address2 = request.getParameter("address2");
//		String phone1 = request.getParameter("phone1");
//		String phone2 = request.getParameter("phone2");
//		String phone3 = request.getParameter("phone3");
//		String emailGet = request.getParameter("emailGet");
//		
//		Member1 member = new Member1();
//		member.setId(id);
//		member.setName(name);
//		member.setPass(pass);
//		member.setEmail(emailId + "@" + emailDomain);
//		member.setMobile(mobile1 + "-" + mobile2 + "-" + mobile3);
//		member.setZipcode(zipcode);
//		member.setAddress1(address1);
//		member.setAddress2(address2);
//		if(phone1.equals("") || phone2.equals("") || phone3.equals("")) {
//			member.setPhone("");
//		} else {
//			member.setPhone(phone1 + "-" + phone2 + "-" + phone3);
//		}
//		member.setEmailGet(Boolean.valueOf(emailGet));
//		
//		MemberDao dao = new MemberDao();
//		dao.joinMember(member);
//		
		return "r:loginForm.member";
		
		
	}

}
