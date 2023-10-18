package com.baedal.one.orders.controller;

import java.util.List;

import com.baedal.one.Main;
import com.baedal.one.cart.TestMain;
import com.baedal.one.orders.dto.CartListDto;
import com.baedal.one.orders.service.OrderService;
import com.baedal.one.orders.vo.OrdersVo;

public class OrderController {

	private final OrderService orderService;
	private List<CartListDto> cartList;
	
	public OrderController() {
		orderService = new OrderService();

	}
	
	/**
	 * 결제 전 화면
	 */
	public void printMyCartList() {
		//원하는 작업 선택 -> 결제/장바구니 물품 취소/뒤로 가기
		selectOption();
	}
	
	/**
	 * 내 장바구니 반환하는 실질적인 코드
	 * @return
	 */
	private void getCartList() {
		try {
			cartList = orderService.getCartList(TestMain.memberNo);
			
			if(cartList.size() == 0) {
				System.out.println("장바구니에 담은 상품이 없습니다");
				return;
			} else {
				//내 장바구니 리스트 보여주기
				System.out.println("------------------------------");
				for(int length = 0; length < cartList.size(); length++) {
					System.out.println((length+1)+". "+cartList.get(length));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectOption() {
		while(true) {
			getCartList();
			if(cartList.size() != 0) {
				//내 장바구니 리스트 보여주기
				System.out.println("--------------------------------");
				System.out.println("원하는 작업을 선택하세요.");
				System.out.println("1. 결제하기");
				System.out.println("2. 수량 수정하기");
				System.out.println("3. 삭제하기");
				System.out.println("4. 뒤로 가기");
				System.out.print("선택: ");
				
				String select = Main.SC.nextLine();
				
				switch(select) {
				case"1": printPayInfo(); break;
				case"2": updateQuantity(); break;
				case"3": deleteCartList(); break;
				case"4": return;
				default: System.out.println("잘못입력하셨습니다. 다시 입력하세요");		
				}
			} 
		}
	}
	private void deleteCartList() {
		String num = "";
		while(true) {
			try {
				System.out.print("삭제하려는 상품의 번호를 입력하세요: ");
				num = Main.SC.nextLine();			
				if(Integer.parseInt(num) > 0)  break;
				else throw new Exception("1 이상의 수만 입력하세요.");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("잘못 입력하셨습니다. 다시입력하세요");
			}						
		}
	
		String cartListNo = cartList.get(Integer.parseInt(num)-1).getCartListNo();
		
		try {
			int result = orderService.deleteCartList(cartListNo);
			
			if(result == 1) System.out.println("상품 삭제 성공");
			else throw new Exception();
		} catch (Exception e) {
			System.out.println("상품 삭제 실패");
			e.printStackTrace();
		}

	}

	/**
	 * 상품 수정하는 메서드
	 */
	private void updateQuantity() {
		String num = "";
		String quantity = "";
		while(true) {
			try {
				System.out.print("수정하려는 상품의 번호를 입력하세요: ");
				num = Main.SC.nextLine();			
				if(Integer.parseInt(num) <= 0)  throw new Exception("1 이상의 수만 입력하세요.");
				
				System.out.print("수정할 상품의 수량을 입력하세요: ");
				quantity = Main.SC.nextLine();
				if(Integer.parseInt(quantity) > 0) break;
				else throw new Exception("1 이상의 수만 입력하세요.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("잘못 입력하셨습니다. 다시입력하세요");
			}						
		}

		String cartListNo = cartList.get(Integer.parseInt(num)-1).getCartListNo();
		try {
			int result = orderService.updateQuantity(cartListNo, quantity);
			
			if(result == 1) System.out.println("상품 수량 수정 성공");
			else throw new Exception();
		} catch (Exception e) {
			System.out.println("상품 수량 수정 실패");
			e.printStackTrace();
		}
	}

	/**
	 * 결제하기
	 */
	private void printPayInfo() {
		System.out.println("------------결제 하기------------");
		try {
			//페이 가져오기
			int money = orderService.getMoneyById(TestMain.memberNo);
			
			int totalPrice = 0;
			int totalQuantity = 0;
			for(CartListDto c : cartList) {
				totalPrice += c.getSubTotal();
				totalQuantity += c.getQuantity();
			}
			
			System.out.println("총 수량 = " + totalQuantity + "개");
			System.out.println("총 가격 = " + totalPrice + "원");
			System.out.println("보유 잔액 = " + money + "원");
			System.out.println("결제 후 잔액 = " + (money-totalPrice) + "원");
			
			String select = "";
			if(money-totalPrice >= 0) {
				do {
					System.out.print("결제 하시겠습니까?(y/n)");
					select = Main.SC.nextLine().toLowerCase();
					
					switch(select) {
					case"y": 
						OrdersVo newOrder = new OrdersVo(TestMain.memberNo, cartList.get(0).getCartNo(), totalPrice, cartList.get(0).getMenuName(), totalQuantity);
						pay(newOrder, money); break;
					case"n": return;
					default: System.out.println("y와 n중에서 입력해주세요"); break;
					}					
				} while(!select.equals("y"));
			} else 
				throw new Exception("잔액이 부족합니다.");
		} catch (Exception e) {
			System.out.println("결제 실패");
			System.out.println(e.getMessage()); 
		}
	}

	private void pay(OrdersVo newOrder, int money) {
		 try {
			int result = orderService.pay(newOrder, money);
			
			if(result == 1) {
				System.out.println("구매 성공하셨습니다");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
