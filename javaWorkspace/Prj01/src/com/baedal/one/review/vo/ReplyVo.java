package com.baedal.one.review.vo;

import java.util.Objects;

public class ReplyVo {
	
	

	public ReplyVo(String deleteYn, String replyWriteDate, String replyNo, String reviewNo, String content) {
		this.deleteYn = deleteYn;
		this.replyWriteDate = replyWriteDate;
		this.replyNo = replyNo;
		this.reviewNo = reviewNo;
		this.content = content;
	}

	public ReplyVo() {
		
	}

	String deleteYn;
	String replyWriteDate;
	String replyNo;
	String reviewNo;
	String content;
	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

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
		return "ReplyVo [deleteYn=" + deleteYn + ", replyWriteDate=" + replyWriteDate + ", replyNo=" + replyNo
				+ ", reviewNo=" + reviewNo + ", content=" + content + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, deleteYn, replyNo, replyWriteDate, reviewNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReplyVo other = (ReplyVo) obj;
		return Objects.equals(content, other.content) && Objects.equals(deleteYn, other.deleteYn)
				&& Objects.equals(replyNo, other.replyNo) && Objects.equals(replyWriteDate, other.replyWriteDate)
				&& Objects.equals(reviewNo, other.reviewNo);
	}
	
	
	
}
