package com.baedal.one.orders.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.baedal.one.Main;
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
			cartList = orderService.getCartList(Main.loginMember.getMemberNo());
			
			if(cartList.size() == 0) {
				System.out.println("장바구니에 담은 상품이 없습니다");
				return;
			} else {
				//내 장바구니 리스트 보여주기
				int total = 0;
				System.out.println();
				System.out.println("--------------------------------");
				System.out.println("\t   🛒 장바구니\t");
				System.out.println("--------------------------------");
				for(int length = 0; length < cartList.size(); length++) {					
					System.out.println("번호 : "+ (length+1));
					System.out.println("매장 : "+ cartList.get(length).getStoreName());
					System.out.println("메뉴 : "+ cartList.get(length).getMenuName());
					System.out.println("수량 : "+ cartList.get(length).getQuantity()+"개");
					System.out.println("가격 : "+ cartList.get(length).getPrice()+"원   "+"\u001B[32m"+"| 총합 : " + cartList.get(length).getSubTotal()+"원"+"\u001B[0m");
					System.out.println("--------------------------------");
					total += cartList.get(length).getSubTotal();
				}
				System.out.println("\u001B[36m"+"총 가격 : " + total + "원"+"\u001B[0m");
			}
		} catch (Exception e) {
			System.err.println("장바구니 리스트 출력 실패");
		}
	}
	
	/**
	 * 결제하기 화면
	 */
	private void selectOption() {
		while(true) {
			//내 장바구니 리스트 보여주기
			getCartList();
			System.out.println("--------------------------------");
			if(cartList.size() != 0) {
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
			} else {
				System.out.println();
				return;
			}
		}
	}
	
	/**
	 * 상품 삭제하는 메소드
	 */
	private void deleteCartList() {
		String num = "";
		while(true) {
			try {
				System.out.print("삭제하려는 상품의 번호를 입력하세요: ");
				num = Main.SC.nextLine();			
				if(Integer.parseInt(num) > 0)  break;
				else throw new Exception("1 이상의 수만 입력하세요.");
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.out.println("잘못 입력하셨습니다. 다시입력하세요");
			}						
		}
	
		String cartListNo = cartList.get(Integer.parseInt(num)-1).getCartListNo();
		
		try {
			int result = orderService.deleteCartList(cartListNo);
			
			if(result == 1) System.out.println("상품 삭제 성공");
			else throw new Exception();
		} catch (Exception e) {
			System.err.println("상품 삭제 실패");
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
				
				System.out.print("메뉴 수량을 입력하세요(뒤로 가려면 x키를 누르세요): ");
				quantity = Main.SC.nextLine();
				if(quantity.equals("x")) return;
				if(Integer.parseInt(quantity) > 0) break;
				else throw new Exception("1 이상의 수만 입력하세요.");
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.out.println("잘못 입력하셨습니다. 다시입력하세요");
			}						
		}

		String cartListNo = cartList.get(Integer.parseInt(num)-1).getCartListNo();
		try {
			int result = orderService.updateQuantity(cartListNo, quantity);
			
			if(result == 1) System.out.println("상품 수량 수정 성공");
			else throw new Exception();
		} catch (Exception e) {
			System.err.println("상품 수량 수정 실패");
		}
	}

	/**
	 * 결제하기 전 화면 
	 */
	private void printPayInfo() {
		
		if(isOpen(cartList.get(0).getOpenTime(), cartList.get(0).getCloseTime())) {
			System.out.println("------------결제 하기------------");
			try {
				//페이 가져오기
				int money = orderService.getMoneyById(Main.loginMember.getMemberNo());
				
				int totalPrice = 0;
				int totalQuantity = 0;
				for(CartListDto c : cartList) {
					totalPrice += c.getSubTotal();
					totalQuantity += c.getQuantity();
				}
				
				System.out.println("총 수량 = " + totalQuantity + "개");
				System.out.println("보유 잔액 = " + money + "원");
				System.out.println("\u001B[31m"+"총 가격 = " + totalPrice + "원"+"\u001B[0m");
				System.out.println("\u001B[32m"+"결제 후 잔액 = " + (money-totalPrice) + "원"+"\u001B[0m");
				
				String select = "";
				if(money-totalPrice >= 0) {
					do {
						System.out.print("결제 하시겠습니까?(y/n)");
						select = Main.SC.nextLine().toLowerCase();
						
						switch(select) {
						case"y": 
							OrdersVo newOrder = new OrdersVo(Main.loginMember.getMemberNo(), cartList.get(0).getCartNo(), totalPrice, cartList.get(0).getMenuName(), totalQuantity);
							pay(newOrder, money); 
							break;
						case"n": System.out.println("뒤로 이동합니다"); return;
						default: System.out.println("y와 n중에서 입력해주세요"); break;
						}					
					} while(!select.equals("y"));
				} else 
					throw new Exception("잔액이 부족합니다.");
			} catch (Exception e) {
				System.err.println("결제 실패");
				System.err.println(e.getMessage()); 
			}
		} else {
			System.out.println("매장 영업시간이 아닙니다.");
		}
	}

	/**
	 * 결제 기능
	 * @param newOrder
	 * @param money
	 */
	private void pay(OrdersVo newOrder, int money) {
		 try {
			System.out.print("결제 비밀번호를 입력하세요: ");
			String amountPwd = Main.SC.nextLine();
			
			String findAmountPwd = orderService.getAmountPwd(Main.loginMember.getMemberNo());
			OrdersVo recentOrder = null;
			if(amountPwd.equals(findAmountPwd)) {
				recentOrder = orderService.pay(newOrder, money);				
			} else {
				throw new Exception("비밀번호가 틀립니다");
			}
			
			if(recentOrder == null) {
				throw new Exception("결제 실패");
			}
			
			System.out.println("구매 성공하셨습니다");
			System.out.println();
			printRecentOrder(recentOrder);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * 결제한 내역 영수증 형태로 보여주는 화면
	 * @param recentOrder
	 */
	private void printRecentOrder(OrdersVo recentOrder) {
		System.out.println("----------------------------------");
		System.out.println("\t    결제 내역");
		System.out.println("----------------------------------");
		System.out.println("주문 번호: " + recentOrder.getOrderNo());
		System.out.print("주문한 음식: ");
		if(recentOrder.getTotalQuantity() == 0) {
			System.out.println(recentOrder.getMenuName());			
		} else {
			System.out.println(recentOrder.getMenuName()+" 외 "+recentOrder.getTotalQuantity() +"개" );
		}
		System.out.println("주문 일자: " + recentOrder.getOrderDate());
		System.out.println("주문 가격: " + recentOrder.getTotalPrice()+"원");
		System.out.println("----------------------------------");
		System.out.println();
	}

	/**
	 * 매장 영업중인지 체크하는 메소드
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
