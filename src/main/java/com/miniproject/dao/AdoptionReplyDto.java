package com.miniproject.dao;

import java.sql.Timestamp;

public class AdoptionReplyDto {

	private int postId;
	private int replyId;
	private String userId;
	private String content;
	private String replyWriter;
	private Timestamp createdAt;
	private boolean isSecret;
	
	
	public AdoptionReplyDto() {}
	public AdoptionReplyDto(int replyId, String content, String replyWriter) {
		this.replyId = replyId;
		this.content = content;
		this.replyWriter = replyWriter;

	}
	public AdoptionReplyDto (int postId, int replyId, String userId, String content,
			String replyWriter, Timestamp createdAt, boolean isSecret) {
		this.postId = postId;
		this.replyId = replyId;
		this.userId = userId;
		this.content = content;
		this.replyWriter = replyWriter;
		this.createdAt = createdAt;
		this.isSecret = isSecret;
	
	}
	
	
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getReplyId() {		
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public boolean getIsSecret() {
		return isSecret;
	}
	public void setIsSecret(boolean isSecret) {
		this.isSecret = isSecret;
	}
	
	
	
}
