package com.baedal.one.review.vo;

public class ReviewVo {

	public ReviewVo() {
		
	}

	public ReviewVo(String nickName, String writeDate, String content, String menuName, String storeNo,
			String totalQuantity) {
		super();
		this.writerName = nickName;
		this.writeDate = writeDate;
		this.content = content;
		this.menuName = menuName;
		StoreNo = storeNo;
		this.totalQuantity = totalQuantity;
	}

	String writerName;
	String writeDate;
	String content;
	String menuName;
	String StoreNo;
	String totalQuantity;
	
	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String orderNo) {
		this.totalQuantity = orderNo;
	}

	public String getStoreNo() {
		return StoreNo;
	}

	public void setStoreNo(String reviewNo) {
		StoreNo = reviewNo;
	}

	public String getNickName() {
		return writerName;
	}

	public void setNickName(String nickName) {
		this.writerName = nickName;
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
		return "ReviewVo [writerName=" + writerName + ", writeDate=" + writeDate + ", content=" + content
				+ ", menuName=" + menuName + ", StoreNo=" + StoreNo + ", totalQuantity=" + totalQuantity + "]";
	}

	

}
