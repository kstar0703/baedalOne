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
			System.out.println("3. 뒤로 가기");
			
			/*
			 * String input = Main.SC.nextLine();
			 *  switch(input) {
			 *  case "1": selectMenu(매장번호); break;
			 *  case "2": break;
			 *  case "3": break;
			 *  default: System.out.println("다시 입력하세요."); break;
			 *  }
			 */
		} while(true/*input != 3*/);
	}

	/**
	 * 메뉴 선택하는 메소드
	 * 고려해야 될 사항
	 * 장바구니를 새로 생성하는가?
	 * 기존 장바구니에 물품이 있는데 다른 매장의 메뉴를 골랐는가?
	 */
	public void selectMenu(/*매장 번호*/) {
		try {
			CartVo myCart = purchaseService.getMyCart(/*회원번호*/);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*List<Menu> menuList = */ purchaseService.printMenu(/*매장번호*/);
		String menuNum = "";
		do {
			/*System.out.println("메뉴를 선택하세요");
			 * for(Menu m : MenuList) {
			 * 		System.out.println(m);
			 * }
			 * System.out.print("선택할 메뉴 번호: ");
			 * menuNum = Main.SC.nextLine();
			 * 
			 * if(Integer.parseInt(menuNum) >= menuList.size()) 
			 * 		System.out.println("다시 입력하세요")
			 * 
			 * 
			 */			
		} while(true/*Integer.parseInt(menuNum)-1 >= menuList.size()*/);
		
		/*
//		 * 다른 매장의 메뉴를 담을 경우
		 * if(매장번호 != myCart.getStoreNo()) { 
		 * do {
		 * 		System.out.println("다른 매장의 메뉴를 담으면 기존의 담아두었던 메뉴는 모두 삭제됩니다.");
		 * 		System.out.println("담으시겠습니까? (y/n)"); 
		 * 		String answer = Main.sc.nextLine();
		 * 		switch(answer) { 
		 * 		case "y": 
		 * 		case "Y": memberService.deleteCartList(회원번호); break;
		 * 		case "n": 
		 * 		case "N": System.out.println("뒤로 이동합니다");  return; 
		 * 		default: System.out.println("다시 입력하세요"); break; 
		 * 		} 
		 * } while(!answer.equals("y") || !answer.equals("Y")); 
		 * }
		 */
		System.out.print("메뉴 수량을 입력하세요: ");
		String quantity = /*Main.SC.nextLine()*/"";
	}
}
