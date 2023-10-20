package com.baedal.one.review.vo;

public class ReviewVo {

	public ReviewVo() {
		
	}

	public ReviewVo(int rating, String userNo, String reviewNo, String nickName, String writeDate, String content,
			String menuName, String storeNo, String orderNo) {
		super();
		this.rating = rating;
		this.userNo = userNo;
		this.reviewNo = reviewNo;
		this.nickName = nickName;
		this.writeDate = writeDate;
		this.content = content;
		this.menuName = menuName;
		StoreNo = storeNo;
		this.orderNo = orderNo;
	}

	int rating;
	String userNo;
	String reviewNo;
	String nickName; // dto
	String writeDate;
	String content;
	String menuName; // dto 
	String StoreNo;
	String orderNo;
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(String reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getStoreNo() {
		return StoreNo;
	}

	public void setStoreNo(String storeNo) {
		StoreNo = storeNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "ReviewVo [rating=" + rating + ", userNo=" + userNo + ", reviewNo=" + reviewNo + ", nickName=" + nickName
				+ ", writeDate=" + writeDate + ", content=" + content + ", menuName=" + menuName + ", StoreNo="
				+ StoreNo + ", orderNo=" + orderNo + "]";
	}

}
