package com.baedal.one.cart.vo;

public class CartVo {

	private String cartNo;
	private String userNo;
	private String storeNo;
	
	public CartVo(String cartNo, String userNo, String storeNo) {
		this.cartNo = cartNo;
		this.userNo = userNo;
		this.storeNo = storeNo;
	}
	
	public CartVo(String userNo, String storeNo) {
		this.userNo = userNo;
		this.storeNo = storeNo;
	}

	public String getCartNo() {
		return cartNo;
	}

	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	@Override
	public String toString() {
		return "CartVo [cartNo=" + cartNo + ", userNo=" + userNo + ", storeNo=" + storeNo + "]";
	}
	
}
