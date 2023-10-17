package com.baedal.one.cart.vo;

//일단 setter 만들어두고 안쓰는 메서드 삭제하기 
public class CartListVo {

	private String cartListNo;
	private String cartNo;
	private String menuNo;
	private String quantity;
	
	public CartListVo(String cartNo, String menuNo, String quantity) {
		this.cartNo = cartNo;
		this.menuNo = menuNo;
		this.quantity = quantity;
	}
	
	public CartListVo(String cartListNo, String cartNo, String menuNo, String quantity) {
		this.cartListNo = cartListNo;
		this.cartNo = cartNo;
		this.menuNo = menuNo;
		this.quantity = quantity;
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


	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartListVo [cartListNo=" + cartListNo + ", cartNo=" + cartNo + ", menuNo=" + menuNo + ", quantity="
				+ quantity + "]";
	}
	
}
