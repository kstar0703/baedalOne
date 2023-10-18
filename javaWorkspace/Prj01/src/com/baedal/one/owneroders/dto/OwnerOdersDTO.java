package com.baedal.one.owneroders.dto;

public class OwnerOdersDTO {

	private String oderNo;
	private String menuName;
	private String menuCount;
	private String oderDate;
	private String oderPrice;
	@Override
	public String toString() {
		return "OwnerOdersDTO [oderNo=" + oderNo + ", menuName=" + menuName + ", menuCount=" + menuCount + ", oderDate="
				+ oderDate + ", oderPrice=" + oderPrice + "]";
	}
	public String getOderNo() {
		return oderNo;
	}
	public void setOderNo(String oderNo) {
		this.oderNo = oderNo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuCount() {
		return menuCount;
	}
	public void setMenuCount(String menuCount) {
		this.menuCount = menuCount;
	}
	public String getOderDate() {
		return oderDate;
	}
	public void setOderDate(String oderDate) {
		this.oderDate = oderDate;
	}
	public String getOderPrice() {
		return oderPrice;
	}
	public void setOderPrice(String oderPrice) {
		this.oderPrice = oderPrice;
	}
	public OwnerOdersDTO(String oderNo, String menuName, String menuCount, String oderDate, String oderPrice) {
		super();
		this.oderNo = oderNo;
		this.menuName = menuName;
		this.menuCount = menuCount;
		this.oderDate = oderDate;
		this.oderPrice = oderPrice;
	}
	public OwnerOdersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
