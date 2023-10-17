package com.baedal.one.cart;

import com.baedal.one.cart.controller.CartController;

public class TestMain {

	public static String memberNo = "1";
	public static void main(String[] args) {
		
		CartController purchaseController = new CartController();
		purchaseController.selectOption("1");
	}
}
