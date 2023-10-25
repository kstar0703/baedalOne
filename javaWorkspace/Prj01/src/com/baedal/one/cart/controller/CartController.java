package com.baedal.one.cart.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.baedal.one.Main;
import com.baedal.one.cart.dto.MenuInfoDto;
import com.baedal.one.cart.service.CartService;
import com.baedal.one.cart.vo.CartListVo;
import com.baedal.one.cart.vo.CartVo;
import com.baedal.one.orders.controller.OrderController;

public class CartController {

	private final CartService cartService;
	private final OrderController orderController; 
	
	public CartController() {
		cartService = new CartService();
		orderController = new OrderController();
	}

	/**
	 * 장바구니에 담기 or 결제기능 선택 화면
	 * @param storeNo 매장 번호 필요
	 */
	public void selectOption(String storeNo) {
		String input = "";
		do {
			System.out.println("-------------------------");
			System.out.println("\u001B[36m   장바구니에 담고 결제하세요! \u001B[0m");
			System.out.println("-------------------------");
			System.out.println("1. 메뉴 한 개 장바구니에 담기");
			System.out.println("2. 내 장바구니 조회하기");
			System.out.println("3. 뒤로 가기");
			System.out.println("-------------------------");
			System.out.print("\u001B[36m원하는 작업을 선택하세요: \u001B[0m"); 
			
			 
			 input = Main.SC.nextLine(); 
			 switch(input) { 
			 case "1": selectMenu(storeNo); break; 
			 case "2": orderController.printMyCartList(); break; 
			 case "3": return;
			 default: System.out.println("다시 입력하세요."); break; 
			 }
		} while (true);
	}

	/**
	 * 장바구니에 담기
	 * @param storeNo 매장 번호
	 * @param memberNo 일반 사용자 번호
	 */
	public void selectMenu(String storeNo) {
		
		List<MenuInfoDto> menuInfoList = null;	//메뉴 리스트
		CartVo myCart= null;	//내 장바구니
		String menuNum = ""; //담을 메뉴 번호
		boolean isDeleted = false;	//기존에 담겨있는 장바구니가 삭제되었는 지 체크하는 변수
		String quantity = "";	//수량
		int result = 0;
		
		//장바구니가 없을 경우 새로 생성
		try {
			try {
				myCart = cartService.getMyCart(Main.loginMember.getMemberNo());
				if(myCart == null) throw new NullPointerException("장바구니를 새로 생성합니다");
			} catch (NullPointerException e) {
				System.err.println(e.getMessage());
				myCart = createNewCart(null);
			}
			
			menuInfoList = cartService.getMenuInfo(storeNo);
			
			do {
				System.out.println("메뉴를 선택하세요");
				System.out.println("--------------------------------------------");
				System.out.println("\t\t   MENU");
				System.out.println("--------------------------------------------");
				for(int length = 0; length < menuInfoList.size(); length++) {
					System.out.println("\u001B[35m번호\u001B[0m : "+(length+1)+" | "+menuInfoList.get(length));
					System.out.println("--------------------------------------------");
				}
				System.out.print("선택할 메뉴 번호: ");
				menuNum = Main.SC.nextLine();
				 
				if(Integer.parseInt(menuNum) > menuInfoList.size()) {
					System.out.println("다시 입력하세요");					
				}
			} while(Integer.parseInt(menuNum)-1 >= menuInfoList.size());
			
			
			int openTime = menuInfoList.get(Integer.parseInt(menuNum)-1).getOpenTime(); //오픈 시간
			int closeTime = menuInfoList.get(Integer.parseInt(menuNum)-1).getCloseTime(); //마감 시간
			
//			 다른 매장의 메뉴를 담을 경우
			if (!storeNo.equals(myCart.getStoreNo()) && !myCart.getStoreNo().equals("0")) {
				isDeleted = deleteCartList(myCart.getCartNo());
				myCart = createNewCart(null);
			}
			//같은 매장의 메뉴를 담을 경우 or 새로 담는 경우
			else if(storeNo.equals(myCart.getStoreNo()) || myCart.getStoreNo().equals("0")) {
				System.out.print("메뉴 수량을 입력하세요(뒤로 가려면 x키를 누르세요): ");
				quantity = Main.SC.nextLine();
				if(quantity.equals("x")) return;
				if(myCart.getStoreNo().equals("0")) {
					cartService.updateStoreNo(myCart.getCartNo(), storeNo);
				}
				
				//운영시간일때만 장바구니에 담을 수 있게 조건 설정하기
				if(isOpen(openTime, closeTime)) {
					CartListVo newCartList = new CartListVo(myCart.getCartNo(), menuInfoList.get(Integer.parseInt(menuNum)-1).getMenuNo(), Integer.parseInt(quantity));
					result = cartService.addMenu(newCartList);					
				} else {
					System.out.println("운영시간이 아닙니다");
				}
			}
			
			//삭제 후 새로 담는 경우
			if(isDeleted) {
				System.out.print("메뉴 수량을 입력하세요(뒤로 가려면 x키를 누르세요): ");
				quantity = Main.SC.nextLine();
				if(quantity.equals("x")) return;
				
				if(myCart.getStoreNo().equals("0")) {
					cartService.updateStoreNo(myCart.getCartNo(), storeNo);
				}
				
				//운영시간일때만 장바구니에 담을 수 있게 조건 설정하기
				if(isOpen(openTime,closeTime)) {
					CartListVo newCartList = new CartListVo(myCart.getCartNo(), menuInfoList.get(Integer.parseInt(menuNum)-1).getMenuNo(), Integer.parseInt(quantity));
					result = cartService.addMenu(newCartList);					
				} else {
					System.out.println("운영시간이 아닙니다");
				}
			}
			
			if(result == 1) 
				System.out.println("장바구니 담기 성공");
			else 
				throw new Exception();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("장바구니 담기 실패");
		} 
	}
	
	/**
	 * 신규 장바구니 생성 기능
	 * @param storeNo
	 * @return
	 * @throws Exception
	 */
	public CartVo createNewCart(String storeNo) throws Exception {
		CartVo newCart = new CartVo(Main.loginMember.getMemberNo(), storeNo);
		CartVo findCart = null;
		int result = cartService.createNewCart(newCart);	
		if(result == 1) {
			findCart = cartService.getMyCart(Main.loginMember.getMemberNo());					 
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
			answer = Main.SC.nextLine().toLowerCase();
			switch (answer) {
			case "y":
				try {
					result = cartService.deleteCartList(cartNo);
					if(result == 1) {
						System.out.println("삭제 성공");
						isDelete = true;
						return isDelete;
					} else throw new Exception();
				} catch (Exception e) {
					System.out.println("장바구니 리스트 삭제 실패 ");
				}
				break;
			case "n":
				System.out.println("뒤로 이동합니다");
				return isDelete;
			default:
				System.out.println("다시 입력하세요");
				break;
			}
		} while (!answer.equals("y"));
		return isDelete;
	}
	
	/**
	 * 매장이 영업중인지 검사하는 매소드
	 * @param openTime
	 * @param closeTime
	 * @return
	 */
	private boolean isOpen(int openTime, int closeTime) {
		int currentHour = LocalDateTime.now().getHour();
		if(openTime == closeTime) return true;
		if(openTime > closeTime) {
			if(closeTime >= currentHour) {
				currentHour += 24;
			}
			closeTime += 24;	
		}
		return currentHour >= openTime && currentHour < closeTime ? true : false;
	}
}
