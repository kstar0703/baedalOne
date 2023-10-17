package com.baedal.one.cart.controller;

import java.util.List;

import com.baedal.one.Main;
import com.baedal.one.cart.TestMain;
import com.baedal.one.cart.dto.MenuInfoDto;
import com.baedal.one.cart.service.CartService;
import com.baedal.one.cart.vo.CartListVo;
import com.baedal.one.cart.vo.CartVo;
import com.baedal.one.purchase.controller.PurchaseController;

public class CartController {

	private final CartService cartService;
	private final PurchaseController purchaseController; 
	
	public CartController() {
		cartService = new CartService();
		purchaseController = new PurchaseController();
	}

	/**
	 * 장바구니에 담기 or or 결제기능
	 * @param storeNo
	 */
	public void selectOption(String storeNo) {
		String input = "";
		do {
			System.out.println("원하는 작업을 선택하세요");
			System.out.println("1. 메뉴 한 개 장바구니에 담기");
			System.out.println("2. 결제하러 이동하기");
			System.out.println("3. 뒤로 가기");
			
			 
			 input = Main.SC.nextLine(); 
			 switch(input) { 
			 case "1": selectMenu(storeNo); break; 
			 case "2": purchaseController.pay(); break; 
			 case "3": return;
			 default: System.out.println("다시 입력하세요."); break; 
			 }
		} while (true);
	}

	/**
	 * 장바구니 기능
	 * 고려해야 될 사항
	 * 장바구니를 새로 생성하는가?
	 * 기존 장바구니에 물품이 있는데 다른 매장의 메뉴를 골랐는가? - V
	 * @param storeNo 매장 번호
	 * @param memberNo 일반 사용자 번호
	 */
	public void selectMenu(String storeNo) {
		
		//메뉴 리스트
		List<MenuInfoDto> menuInfoList = null;
		//내 장바구니
		CartVo myCart= null;
		//담을 메뉴 번호
		String menuNum = "";
		//기존에 담겨있는 장바구니가 삭제되었는 지 체크하는 변수
		boolean isDeleted = false;
		//수량
		String quantity = "";
		int result = 0;
		
		try {
			try {
				myCart = cartService.getMyCart(TestMain.memberNo);
				if(myCart == null) throw new NullPointerException("장바구니를 새로 생성합니다");
			} catch (NullPointerException e) {
				myCart = createNewCart(storeNo);
			}
			System.out.println("myCart.getCartNo() = "+myCart.getCartNo());
			menuInfoList = cartService.getMenuInfo(storeNo);
			
			do {
				System.out.println("메뉴를 선택하세요");
				System.out.println("----------------------");
				for(MenuInfoDto m : menuInfoList) {
					System.out.println(m);
				}
				
				System.out.print("선택할 메뉴 번호: ");
				menuNum = Main.SC.nextLine();
				 
				if(Integer.parseInt(menuNum) > menuInfoList.size()) {
					System.out.println("다시 입력하세요");					
				}
			} while(Integer.parseInt(menuNum)-1 >= menuInfoList.size());
			
//			 다른 매장의 메뉴를 담을 경우
			if (!storeNo.equals(myCart.getStoreNo())) {
				isDeleted = deleteCartList(myCart.getCartNo());			
			}
			//같은 매장의 메뉴를 담을 경우
			else if(storeNo.equals(myCart.getStoreNo())) {
				System.out.print("메뉴 수량을 입력하세요: ");
				quantity = Main.SC.nextLine();
				
				CartListVo newCartList = new CartListVo(myCart.getCartNo(), menuInfoList.get(Integer.parseInt(menuNum)-1).getMenuNo(), quantity);
				result = cartService.addMenu(newCartList);
			}
			
			if(isDeleted) {
				myCart = createNewCart(storeNo);
				System.out.print("메뉴 수량을 입력하세요: ");
				quantity = Main.SC.nextLine();
				
				CartListVo newCartList = new CartListVo(myCart.getCartNo(), menuInfoList.get(Integer.parseInt(menuNum)-1).getMenuNo(), quantity);
				result = cartService.addMenu(newCartList);
			}
			
			if(result == 1) 
				System.out.println("장바구니 담기 성공");
			else 
				System.out.println("장바구니 담기 실패");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public CartVo createNewCart(String storeNo) throws Exception {
		CartVo newCart = new CartVo(TestMain.memberNo, storeNo);
		CartVo findCart = null;
		int result = cartService.createNewCart(newCart);	
		if(result == 1) {
			findCart = cartService.getMyCart(TestMain.memberNo);					 
		} else 
			throw new Exception("장바구니 신규 생성 실패");
		return findCart;
	}
	
	/**
	 * 기존의 장바구니 삭제 기능
	 * @param cartNo
	 */
	public boolean deleteCartList(String cartNo) {
		boolean isDelete = false;
		String answer = "";
		int result = 0;
		do {
			System.out.println("다른 매장의 메뉴를 담으면 기존의 담아두었던 메뉴는 모두 삭제됩니다.");
			System.out.println("담으시겠습니까? (y/n)");
			answer = Main.SC.nextLine();
			switch (answer) {
			case "y", "Y":
				try {
					result = cartService.deleteCartList(cartNo);
					if(result == 1) {
						System.out.println("삭제 성공");
						isDelete = true;
						return isDelete;
					} else throw new Exception();
				} catch (Exception e) {
					System.out.println("장바구니 리스트 삭제 실패 ");
					e.printStackTrace();
				}
				break;
			case "n", "N":
				System.out.println("뒤로 이동합니다");
				return isDelete;
			default:
				System.out.println("다시 입력하세요");
				break;
			}
		} while (!answer.equals("y") || !answer.equals("Y"));
		return isDelete;
	}
}
