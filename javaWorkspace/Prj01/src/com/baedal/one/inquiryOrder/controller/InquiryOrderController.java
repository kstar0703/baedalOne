package com.baedal.one.inquiryOrder.controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

import com.baedal.one.Main;
import com.baedal.one.ownerfunction.dto.CartListDetailDTO;
import com.baedal.one.ownerfunction.dto.OwnerOdersVo;
import com.baedal.one.ownerfunction.service.OwnerOdersService;
import com.baedal.one.review.controller.ReviewController;

public class InquiryOrderController {

	private final OwnerOdersService service;

	public CartListDetailDTO detailDto;// 상세 주문 내역을 담을 객체

	public InquiryOrderController() {
		detailDto = new CartListDetailDTO();
		service = new OwnerOdersService();
	}

	public void showOder(String userNo) {
		try {
			while (true) {
				System.out.println("──────────────────간편내역 조회──────────────────────────────");

				// 간편내역을 담을 리스트를 만들어서 튜플을 담음
				List<OwnerOdersVo> voList = service.userOderList(userNo);

				// HashMap<출력될 번호 , 장바구니 번호> map = new HashMap<출력될 번호 , 장바구니 번호>();
				// 장바구니 번호는 별도로 저장되어 있는 번호기 때문에 그냥 오름차순으로 출력(ex: 2,5,6,7,9)되는 것을 1부터 차례대로 출력 되게
				// 함(ex:1,2,3,4,5)
				HashMap<Integer, String> map = new HashMap<Integer, String>();
				int i = 1;

				// 간편내역보기 정보를 담은 리스트를 차례대로 출력
				for (OwnerOdersVo vo : voList) {

					// 장바구니 번호가 가장 작은것이 1번 키값으로 들어가고, 그 다음 작은 수가 2번 키값에 들어감 << 반복
					map.put(i, vo.getCartNo());
					System.out.println("<" + findStoreName(vo.getCartNo()) + ">");
					System.out.print(i + ". ");
//					System.out.print(vo.getCartNo() + " ");
					System.out.print(vo.getMenuName() + " ");

					// 메뉴 하나의 이름과 '그 외의 개수'를 계산하기 위해 문자열을 정수로 변환
					int otherQuantity = Integer.parseInt(vo.getTotalQuantity()) - 1;

					// 2개 이상일 경우 에는 '외 n개' 라고 출력한다.
					if (otherQuantity >= 1) {
						System.out.print("외 ");
						System.out.print(otherQuantity);
						System.out.print("개 ");
					} else {
						// 메뉴를 하나만 시킨 주문일 경우 그냥 공백을 두고 출력
						System.out.print("\t");
					}
					System.out.println("\n\t\t\t결제 금액  :  \t\t" +vo.getTotalPrice()+"원");
					System.out.println("\t\t\t주문 일시  :  " +vo.getOderDate());
					System.out.println("---------------------------------------------------------");
					i++;
				}

				// 간편보기 내역중 상세히 확인하고픈 주문 입력

				System.out.print("상세목록을 보고싶은 내역을 입력하세요(이전 화면으로 돌아가려면 'x'입력) : ");
				String stringInput = Main.SC.nextLine();
				int intInput = Integer.parseInt(stringInput);

				// 확인하고픈 주문의 '장바구니번호'를 구함
				String nowCartNo = map.get(intInput);
				// 상세 내역 객체에 매장 이름 저장
//				detailDto.setStoreName(findStoreName(voList.get(input-1).getCartNo()));
//				System.out.println(detailDto.getStoreName());

				// 상세내역 객체에 일자, 총 가격 저장
				detailDto.setOrderDate(voList.get(intInput - 1).getTotalPrice());
				detailDto.setTotalPrice(voList.get(intInput - 1).getOderDate());

				String orderNo = voList.get(intInput-1).getOrderNo();
				// 장바구니 번호와 상세 내역 객체를 넘겨서 메소드 실행
				oderDetails(nowCartNo, detailDto,userNo,orderNo);

			}
		} catch (NullPointerException e) {
			// 간편내역조회를 할때 주문 내역이 없을시
			System.out.println("주문한 내역이 없습니다. ");
			return;
		} catch (NumberFormatException e) {
			return;
		} catch (IndexOutOfBoundsException e) {
			// 다른 수를 입력해도 종료
			return;
		} catch (Exception e) {
			// DB문제가 있을때
			System.out.println("오류: 주문한 내역이 없습니다.");
			return;
		}

	}

	// 매장 이름 찾기
	public String findStoreName(String cartNo) {
		// 매장 이름을 넣을 변수
		String storeName = null;
		try {
			// 서비스
			storeName = service.nowStoreName(cartNo);

			// 매장 이름을 못 불러오면
			if (storeName == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("매장 이름 가져오기 실패");
		}

		return storeName;
	}

	// 매장 번호 찾기
	public String findStoreNo(String cartNo) {
		// 매장 이름을 넣을 변수
		String storeName = null;
		try {
			// 서비스
			storeName = service.nowStoreNo(cartNo);

			// 매장 이름을 못 불러오면
			if (storeName == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("매장 번호 가져오기 실패");
		}

		return storeName;
	}

	// 상세내역 보기
	private void oderDetails(String nowCartNo, CartListDetailDTO dto, String userNo, String orderNo) {
		System.out.println("\n──────────────────" + findStoreName(nowCartNo) + "<주문 상세 내역>────────────────────");
		
		try {
			// 메뉴이름, 가격, 개수를 담은 객체를 리스트에 저장
			List<CartListDetailDTO> dtoList = service.oderDetails(nowCartNo);

			// 메뉴이름, 가격, 개수를 담은 객체를 출력
			for (CartListDetailDTO ocDto : dtoList) {
				System.out.print(ocDto.getMenuName());
				System.out.print(" ");
				System.out.print(ocDto.getQuantity());
				System.out.print("개 ");

				// 메뉴1개의 가격과 개수를 곱해 가격의 저장
				int price = Integer.parseInt(ocDto.getQuantity()) * Integer.parseInt(ocDto.getPrice());
				System.out.print(price);
				System.out.println("원 ");
			}
			// 결제일시, 총 결제금액 출력
			System.out.println("---------------------------------------------------------");
			System.out.println("\t\t\t주문 일시  :  " + dto.getTotalPrice());
			System.out.println("---------------------------------------------------------");
			System.out.println("\t\t\t결제 금액  :  \t\t" + dto.getOrderDate() + "원");


			System.out.println("─────────────────────────────────────────────────────────");

			System.out.println("1. 리뷰 작성 ");
			System.out.println("2. 뒤로가기 ");
			System.out.print("입력 : ");
			String input = Main.SC.nextLine();
			ReviewController reviewController = new ReviewController();

			if (input.equals("1")) {
				reviewController.writeReview(findStoreNo(nowCartNo),userNo,orderNo);
			} else {
			}
		} catch (Exception e) {
			System.out.println("주문 상세 내역 불러오기 실패");
			return;
		}

	}
}

//	//멤버 이름 찾기
//	public String findUserName(String userNo) {
//		// 매장 이름을 넣을 변수
//		String userName = null;
//		try {
//			// 서비스
//			userName = service.findUserName(userNo);
//
//			// 매장 이름을 못 불러오면
//			if (userName == null) {
//				throw new Exception();
//			}
//		} catch (Exception e) {
//			System.out.println("유저 이름 가져오기 실패");
//		}
//
//		return userName;
//	}