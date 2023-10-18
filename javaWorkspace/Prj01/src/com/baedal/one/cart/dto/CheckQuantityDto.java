package com.baedal.one.cart.dto;

/**
 * 기존 장바구니에 물품이 담겨있는지 체크하는 Dto
 */
public class CheckQuantityDto {

	private int count;
	private int quantity;
	
	public CheckQuantityDto(int count, int quantity) {
		this.count = count;
		this.quantity = quantity;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CheckQuantityDto [count=" + count + ", quantity=" + quantity + "]";
	}
	
	
}
