package com.baedal.one.purchase.dto;

/**
 * 장바구니 조회 Dto
 * 물품 번호, 매장 이름, 메뉴 이름, 가격, 수량, 소계(메뉴*수량)
 */
public class CartListDto {

	private String cartListNo;
	private String storeName;
	private String menuName;
	private int price;
	private int quantity;
	private int subTotal;
	
	public CartListDto(String cartListNo, String storeName, String menuName, int price, int quantity, int subTotal) {
		this.cartListNo = cartListNo;
		this.storeName = storeName;
		this.menuName = menuName;
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
		return "CartListDto [cartListNo=" + cartListNo + ", storeName=" + storeName + ", menuName=" + menuName
				+ ", price=" + price + ", quantity=" + quantity + ", subTotal=" + subTotal + "]";
	}

}
