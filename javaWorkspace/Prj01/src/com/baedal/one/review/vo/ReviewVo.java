package com.baedal.one.review.vo;

public class ReviewVo {

	public ReviewVo() {
		
	}
	
	public ReviewVo(String reviewNo, String storeNo, String orderNo, String content, String writeDate,
			String deleteYn) {
		super();
		this.reviewNo = reviewNo;
		this.storeNo = storeNo;
		this.orderNo = orderNo;
		this.content = content;
		this.writeDate = writeDate;
		this.deleteYn = deleteYn;
		this.title = title;
	}

	private String title;
	private String reviewNo;
	private String storeNo;
	private String orderNo;
	private String content;
	private String writeDate;
	private String deleteYn;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(String reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	@Override
	public String toString() {
		return "ReviewVo [reviewNo=" + reviewNo + ", storeNo=" + storeNo + ", orderNo=" + orderNo + ", content="
				+ content + ", writeDate=" + writeDate + ", deleteYn=" + deleteYn + "]";
	}
	
}
