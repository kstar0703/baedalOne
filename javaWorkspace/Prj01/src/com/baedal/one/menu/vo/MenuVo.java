package com.baedal.one.menu.vo;

public class MenuVo {
	
	private String menuNo;
	private String storeNo;
	private String menuName;
	private String price;
	private String deleteYn;
	private String sellYn;
	
	public MenuVo(String menuNo, String storeNo, String menuName, String price, String deleteYn, String sellYn) {
		super();
		this.menuNo = menuNo;
		this.storeNo = storeNo;
		this.menuName = menuName;
		this.price = price;
		this.deleteYn = deleteYn;
		this.sellYn = sellYn;
	}
	
	public MenuVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getSellYn() {
		return sellYn;
	}
	public void setSellYn(String sellYn) {
		this.sellYn = sellYn;
	}
	
	@Override
	public String toString() {
		return "MenuVo [menuNo=" + menuNo + ", storeNo=" + storeNo + ", menuName=" + menuName + ", price=" + price
				+ ", deleteYn=" + deleteYn + ", sellYn=" + sellYn + "]";
	}
	
	
	
	
	
	

}
