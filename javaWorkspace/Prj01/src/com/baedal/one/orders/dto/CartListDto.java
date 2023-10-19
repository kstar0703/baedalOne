package com.baedal.one.orders.dto;

/**
 * 장바구니 조회 Dto
 * 물품 번호, 매장 이름, 오픈 시간 마감 시간, 메뉴 이름, 가격, 수량, 소계(메뉴*수량)
 */
public class CartListDto {

	private String cartListNo;
	private String cartNo;
	private String storeName;
	private String menuName;
	private String openTime;
	private String closeTime;
	private int price;
	private int quantity;
	private int subTotal;
	

	public CartListDto(String cartListNo, String cartNo, String storeName, String menuName, String openTime, String closeTime,
			int price, int quantity, int subTotal) {
		this.cartListNo = cartListNo;
		this.cartNo = cartNo;
		this.storeName = storeName;
		this.menuName = menuName;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.price = price;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}

	public String getCartListNo() {
		return cartListNo;
	}

	public void setCartListNo(String cartListNo) {
		this.cartListNo = cartListNo;
	}

	public String getCartNo() {
		return cartNo;
	}

	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return " [매장 이름 = " + storeName + ", 메뉴 이름 = " + menuName + ", 가격 = " + price + ", 수량 = "
				+ quantity + ", 소계= " + subTotal + "]";
	}

}
