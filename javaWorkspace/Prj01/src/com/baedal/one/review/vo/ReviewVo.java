package com.baedal.one.review.vo;

public class ReviewVo {

	public ReviewVo() {
		
	}

	public ReviewVo(String nickName, String writeDate, String content, String menuName, String storeNo,
			String orderNo) {
		super();
		this.nickName = nickName;
		this.writeDate = writeDate;
		this.content = content;
		this.menuName = menuName;
		StoreNo = storeNo;
		this.orderNo = orderNo;
	}

	String nickName;
	String writeDate;
	String content;
	String menuName;
	String StoreNo;
	String orderNo;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStoreNo() {
		return StoreNo;
	}

	public void setStoreNo(String reviewNo) {
		StoreNo = reviewNo;
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

	@Override
	public String toString() {
		return "ReviewVo [nickName=" + nickName + ", writeDate=" + writeDate + ", content=" + content + ", menuName="
				+ menuName + ", StoreNo=" + StoreNo + ", orderNo=" + orderNo + "]";
	}

}
