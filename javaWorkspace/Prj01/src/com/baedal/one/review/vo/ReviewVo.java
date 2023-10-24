package com.baedal.one.review.vo;

import java.util.Objects;

/**
 * 
 */
public class ReviewVo {

	public ReviewVo() {

	}

	public ReviewVo(String rating, String userNo, String reviewNo, String nickName, String writeDate, String content,
			String menuName, String storeNo, String orderNo) {
		super();
		this.rating = rating;
		this.userNo = userNo;
		this.reviewNo = reviewNo;
		this.nickName = nickName;
		this.writeDate = writeDate;
		this.content = content;
		this.menuName = menuName;
		this.StoreNo = storeNo;
		this.orderNo = orderNo;
	}

	String rating;
	String userNo;
	String reviewNo;
	String nickName; // dto
	String writeDate;
	String content;
	String menuName; // dto
	String StoreNo;
	String orderNo;

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
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

	@Override
	public int hashCode() {
		return Objects.hash(StoreNo, content, nickName, orderNo, rating, reviewNo, userNo, writeDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewVo other = (ReviewVo) obj;
		return Objects.equals(StoreNo, other.StoreNo) && Objects.equals(content, other.content)
				&& Objects.equals(nickName, other.nickName) && Objects.equals(rating, other.rating)
				&& Objects.equals(reviewNo, other.reviewNo) && Objects.equals(userNo, other.userNo)
				&& Objects.equals(writeDate, other.writeDate);
	}

}
