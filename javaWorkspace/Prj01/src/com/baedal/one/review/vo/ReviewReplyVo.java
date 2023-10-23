package com.baedal.one.review.vo;

public class ReviewReplyVo {
	
	public ReviewReplyVo(ReplyVo replyVo, ReviewVo reviewVo) {
		this.replyVo = replyVo;
		this.reviewVo = reviewVo;
	}
	
	public ReviewReplyVo() {
		
	}
	
	private ReplyVo replyVo;
	private ReviewVo reviewVo;
	
	public ReplyVo getReplyVo() {
		return replyVo;
	}

	public void setReplyVo(ReplyVo replyVo) {
		this.replyVo = replyVo;
	}

	public ReviewVo getReviewVo() {
		return reviewVo;
	}

	public void setReviewVo(ReviewVo reviewVo) {
		this.reviewVo = reviewVo;
	}

	@Override
	public String toString() {
		return "VoList [replyVo=" + replyVo + ", reviewVo=" + reviewVo + "]";
	}
	
}
