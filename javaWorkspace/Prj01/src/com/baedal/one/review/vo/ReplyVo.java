package com.baedal.one.review.vo;

public class ReplyVo {
	
	public ReplyVo(String replyWriteDate, String replyNo, String reviewNo, String content) {
		this.replyWriteDate = replyWriteDate;
		this.replyNo = replyNo;
		this.reviewNo = reviewNo;
		this.content = content;
	}

	public ReplyVo() {
		
	}

	String replyWriteDate;
	String replyNo;
	String reviewNo;
	String content;
	
	public String getReplyWriteDate() {
		return replyWriteDate;
	}

	public void setReplyWriteDate(String replyWriteDate) {
		this.replyWriteDate = replyWriteDate;
	}

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
		return "ReplyVo [replyWriteDate=" + replyWriteDate + ", replyNo=" + replyNo + ", reviewNo=" + reviewNo
				+ ", content=" + content + "]";
	}
	
}
