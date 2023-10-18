package com.baedal.one.review.vo;

public class ReviewVo {

	public ReviewVo() {
		
	}

	public ReviewVo(String writerName, String writeDate, String content, String menuName, String storeNo,
			int lastOrderNo, int allorderNo, String userNo) {
		super();
		this.writerName = writerName;
		this.writeDate = writeDate;
		this.content = content;
		this.menuName = menuName;
		StoreNo = storeNo;
		this.lastOrderNo = lastOrderNo;
		this.allorderNo = allorderNo;
		this.userNo = userNo;
	}

	String writerName;
	String writeDate;
	String content;
	String menuName;
	String StoreNo;
	int lastOrderNo;
	int allorderNo;
	String userNo;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getStoreNo() {
		return StoreNo;
	}

	public void setStoreNo(String reviewNo) {
		StoreNo = reviewNo;
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

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public int getLastOrderNo() {
		return lastOrderNo;
	}

	public void setLastOrderNo(int lastOrderNo) {
		this.lastOrderNo = lastOrderNo;
	}

	public int getAllorderNo() {
		return allorderNo;
	}

	public void setAllorderNo(int allorderNo) {
		this.allorderNo = allorderNo;
	}

	@Override
	public String toString() {
		return "ReviewVo [writerName=" + writerName + ", writeDate=" + writeDate + ", content=" + content
				+ ", menuName=" + menuName + ", StoreNo=" + StoreNo + ", lastOrderNo=" + lastOrderNo + ", allorderNo="
				+ allorderNo + ", userNo=" + userNo + "]";
	}

}
