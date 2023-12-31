package com.baedal.one.cart.dto;


/**
 * 메뉴 정보 가져오는 Dto
 * 메뉴번호, 매장 이름, 메뉴이름, 가격 오픈 타임, 마감 타임
 */
public class MenuInfoDto {

	private String menuNo;
	private String storeName;
	private String menuName;
	private String price;
	// 08:00 -> 8 //23:00 -> 23으로 가져오기
	//	받는 건 String으로 반환하는 건 int로
	private String openTime;
	private String closeTime;
	
	public MenuInfoDto(String menuNo, String storeName, String menuName, String price, String openTime,
			String closeTime) {
		super();
		this.menuNo = menuNo;
		this.storeName = storeName;
		this.menuName = menuName;
		this.price = price;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public int getOpenTime() {
		return Integer.parseInt(openTime);
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public int getCloseTime() {
		return Integer.parseInt(closeTime);
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	@Override
	public String toString() {
		return "\u001B[35m메뉴 이름\u001B[0m : " + menuName + "   |   \u001B[35m가격\u001B[0m : " + price + "원";
	}
	
}
