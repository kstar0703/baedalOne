package com.baedal.one.ownerfunction.dto;

public class OwnerOdersVo {

	private String orderNo;		//주문번호
	private String userNo;		//회원번호
	private String cartNo;		//장바구니 번호
	private String oderDate;	//주문 날짜
	private String totalPrice;	//총 가격
	private String menuName;	//메뉴이름 하나
	private String totalQuantity;	//총 개수

	public OwnerOdersVo() {
		super();
	}

	public OwnerOdersVo(String orderNo, String userNo, String cartNo, String oderDate, String totalPrice,
			String menuName, String totalQuantity) {
		super();
		this.orderNo = orderNo;
		this.userNo = userNo;
		this.cartNo = cartNo;
		this.oderDate = oderDate;
		this.totalPrice = totalPrice;
		this.menuName = menuName;
		this.totalQuantity = totalQuantity;
	}

	@Override
	public String toString() {
		return "OwnerOdersVo [orderNo=" + orderNo + ", userNo=" + userNo + ", cartNo=" + cartNo + ", oderDate="
				+ oderDate + ", totalPrice=" + totalPrice + ", menuName=" + menuName + ", totalQuantity="
				+ totalQuantity + "]";
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getCartNo() {
		return cartNo;
	}

	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}

	public String getOderDate() {
		return oderDate;
	}

	public void setOderDate(String oderDate) {
		this.oderDate = oderDate;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	

}
