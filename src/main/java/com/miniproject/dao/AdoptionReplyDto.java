package com.miniproject.dao;

import java.sql.Timestamp;

public class AdoptionReplyDto {

	private int postId;
	private int replyId;
	private String userId;
	private String replyContent;
	private String replyWriter;
	private Timestamp createdAt;
	private boolean isSecret;
	
	
	public AdoptionReplyDto() {}
	public AdoptionReplyDto(int replyId, String replyContent, String replyWriter) {
		this.replyId = replyId;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;

	}
	public AdoptionReplyDto (int postId, int replyId, String userId, String replyContent,
			String replyWriter, Timestamp createdAt, boolean isSecret) {
		this.postId = postId;
		this.replyId = replyId;
		this.userId = userId;
		this.replyContent = replyContent;
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
	public String getreplyContent() {
		return replyContent;
	}
	public void setreplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isSecret() {
		return isSecret;
	}
	public void setSecret(boolean isSecret) {
		this.isSecret = isSecret;
	}
	
	
	
}
