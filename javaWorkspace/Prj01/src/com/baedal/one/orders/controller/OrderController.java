package com.baedal.one.orders.controller;

import java.util.List;

import com.baedal.one.cart.TestMain;
import com.baedal.one.orders.dto.CartListDto;
import com.baedal.one.orders.service.OrderService;

public class OrderController {

	private final OrderService purchaseService;
	
	public OrderController() {
		purchaseService = new OrderService();
	}
	
	/**
	 * 결제하기
	 */
	public void printMyCart() {
		//내 장바구니 리스트 보여주기
		
		//원하는 작업 선택 -> 결제/장바구니 물품 취소/뒤로 가기
	}
	
	private List<CartListDto> getCartList() {
		 List<CartListDto> cartList = purchaseService.getCartList(TestMain.memberNo);
		return cartList;
	}

}
