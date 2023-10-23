package com.baedal.one.ownerfunction.dto;

public class OwnerCartListDetailDTO {
	private String storeName;
	private String orderDate;
	private String menuName;
	private String quantity;
	private String price;
	private String totalPrice;
	
	public OwnerCartListDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OwnerCartListDetailDTO(String storeName, String orderDate, String menuName, String quantity, String price,
			String totalPrice) {
		super();
		this.storeName = storeName;
		this.orderDate = orderDate;
		this.menuName = menuName;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "CartListDTO [storeName=" + storeName + ", orderDate=" + orderDate + ", menuName=" + menuName
				+ ", quantity=" + quantity + ", price=" + price + ", totalPrice=" + totalPrice + "]";
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	}
