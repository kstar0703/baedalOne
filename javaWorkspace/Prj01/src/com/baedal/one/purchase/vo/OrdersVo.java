package com.baedal.one.purchase.vo;

public class OrdersVo {

	private String orderNo;
	private String userNo;
	private String cartNo;
	private String oderDate;
	private String totalPrice;
	
	public OrdersVo(String orderNo, String userNo, String cartNo, String oderDate, String totalPrice) {
		this.orderNo = orderNo;
		this.userNo = userNo;
		this.cartNo = cartNo;
		this.oderDate = oderDate;
		this.totalPrice = totalPrice;
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
	
	@Override
	public String toString() {
		return "OrdersVo [orderNo=" + orderNo + ", userNo=" + userNo + ", cartNo=" + cartNo + ", oderDate=" + oderDate
				+ ", totalPrice=" + totalPrice + "]";
	}
}
