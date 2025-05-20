package com.miniproject.vo;

import java.util.Date;

public class Qna_board {
	 private int no;          // 글번호
	    private String status;   // 상태
	    private String writer;   // 작성자
	    private String title;    // 제목
	    private String content;  // 내용
	    private Date regDate;    // 작성일
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getWriter() {
			return writer;
		}
		public void setWriter(String writer) {
			this.writer = writer;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Date getRegDate() {
			return regDate;
		}
		public void setRegDate(Date regDate) {
			this.regDate = regDate;
		}
	    
	    
}
