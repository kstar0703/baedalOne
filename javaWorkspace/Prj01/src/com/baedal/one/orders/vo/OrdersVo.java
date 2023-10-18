package com.baedal.one.orders.vo;

/**
 * 주문 번호, 유저 번호, 장바구니 번호, 주문일자(yyyy-mm-dd hh24:mm), 대표 메뉴 이름, 수량
 */
public class OrdersVo {

	private String orderNo;
	private String userNo;
	private String cartNo;
	private String orderDate;
	private int totalPrice;
	private String menuName;
	private int totalQuantity;
	
	public OrdersVo(String userNo, String cartNo, int totalPrice, String menuName, int totalQuantity) {
		super();
		this.userNo = userNo;
		this.cartNo = cartNo;
		this.totalPrice = totalPrice;
		this.menuName = menuName;
		this.totalQuantity = totalQuantity;
	}

	public OrdersVo(String orderNo, String userNo, String cartNo, String orderDate, int totalPrice, String menuName,
			int totalQuantity) {
		this.userNo = userNo;
		this.cartNo = cartNo;
		this.totalPrice = totalPrice;
		this.menuName = menuName;
		this.totalQuantity = totalQuantity;
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

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	@Override
	public String toString() {
		return "OrdersVo [orderNo=" + orderNo + ", userNo=" + userNo + ", cartNo=" + cartNo + ", orderDate=" + orderDate
				+ ", totalPrice=" + totalPrice + ", menuName=" + menuName + ", totalQuantity=" + totalQuantity + "]";
	}
}
