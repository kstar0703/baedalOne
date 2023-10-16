package com.baedal.one.purchase.controller;

import com.baedal.one.purchase.service.PurchaseService;
import com.baedal.one.purchase.vo.CartVo;

public class PurchaseController {

	private final PurchaseService purchaseService;

	public PurchaseController() {
		purchaseService = new PurchaseService();
	}

	public void selectOption(/*매장 번호*/) {
		do {
			 
			System.out.println("원하는 작업을 선택하세요");
			System.out.println("1. 메뉴한개 선택하기");
			System.out.println("2. 결제하기");
			
			/*
			 * String input = Main.SC.nextLine();
			 *  switch(input) {
			 *  case "1": selectMenu(/*매장번호); break;
			 *  case "2": break;
			 *  default: System.out.println("다시 입력하세요."); break;
			 *  }
			 */
		} while(true/*input != 1 || input != 2*/);
	}

	public void selectMenu(/*매장 번호*/) {
		CartVo myCart = purchaseService.getMyCart();
		/*List<Menu> menuList = */ purchaseService.printMenu(/*매장번호*/);
		String menuNum = "";
		do {
			/*System.out.println("메뉴를 선택하세요");
			 * for(Menu m : MenuList) {
			 * 		System.out.println(m);
			 * 
			 * System.out.print("선택할 메뉴 번호: ");
			 * menuNum = Main.SC.nextLine();
			 * 
			 * if(Integer.parseInt(menuNum) >= menuList.size()) 
			 * 		System.out.println("다시 입력하세요")
			 */			
		} while(true/*Integer.parseInt(menuNum)-1 >= menuList.size()*/);
		System.out.print("메뉴 수량을 입력하세요: ");
		String quantity = /*Main.SC.nextLine()*/"";
	}
}
