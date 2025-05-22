package com.miniproject.vo;

import java.util.Date;

public class QnaAnswer {
    private int answerNo;       // 답변 고유 번호 (PK)
    private int no;             // 원본 질문 글번호 (FK - QNA_BOARD.NO 참조)
    private String answerContent; // 답변 내용
    private String answerWriter;  // 답변 작성자
    private Date answerRegDate;   // 답변 등록일
	public int getAnswerNo() {
		return answerNo;
	}
	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getAnswerWriter() {
		return answerWriter;
	}
	public void setAnswerWriter(String answerWriter) {
		this.answerWriter = answerWriter;
	}
	public Date getAnswerRegDate() {
		return answerRegDate;
	}
	public void setAnswerRegDate(Date answerRegDate) {
		this.answerRegDate = answerRegDate;
	}
    
    
}
