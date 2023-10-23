package com.baedal.one.review.vo;

public class ReplyVo {
	
	public ReplyVo(String replyNo, String reviewNo, String content) {
		this.replyNo = replyNo;
		this.reviewNo = reviewNo;
		this.content = content;
	}
	
	public ReplyVo() {

	}
	
	String replyNo;
	String reviewNo;
	String content;
	
	public String getReplyNo() {
		return replyNo;
	}
	
	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}
	
	public String getReviewNo() {
		return reviewNo;
	}
	
	public void setReviewNo(String reviewNo) {
		this.reviewNo = reviewNo;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "replyVo [replyNo=" + replyNo + ", reviewNo=" + reviewNo + ", content=" + content + "]";
	}

}
