package com.baedal.one.pay.controller;

import java.util.List;
import java.util.Scanner;

import com.baedal.one.pay.service.PayService;
import com.baedal.one.pay.vo.PayVo;

/**
 * 페이 컨트롤러 -로그인 한 사용자의 잔액 확인 -페이 충전 -입출금 내역 확인
 */
public class PayController {
	// 서비스 사용
	private final PayService service;

	// 임시 스캐너
	Scanner sc = new Scanner(System.in);

	public PayController() {
		service = new PayService();
	}

	// 페이기능에서 사용할 기능 입력
	public void selectPayMenu(String userNo) {
		while (true) {

			// 페이 페이지 출력
			System.out.println("──────────────────내 돈 관리───────────────────");
			System.out.println("1. 페이 충전하기");
			System.out.println("2. 페이 출금 내역 확인");
			System.out.println("3. 뒤로가기");

			// 사용자에게 어떤 기능을 사용 할 것인지 입력 받기
			String inputNum = sc.nextLine();

			// 입력한 수에 따라 기능 실행
			switch (inputNum) {
			case "1":
				selectChargePay(userNo);
				break;
			case "2":
				DepositWithdrawalDetails(userNo);
				break;
			case "3":
				return;
			}

		}
	}

	// 현재 잔액 불러오기
	private int findBalance(String userNo) {
		int nowMoney = 0;// 이 변수에 담아서 리턴
		try {

			// 잔액을 불러와서 저장
			nowMoney = service.findBalance(userNo);

			// 잔액이 음수일경우 오류 발생
			if (nowMoney < 0) {
				throw new Exception();
			}
			// 현재 잔액 출력
			System.out.println("현재 잔액 : " + nowMoney);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 현재 잔액 리턴
		return nowMoney;
	}

	// 페이충전 가기 전 잔액을 출력하고 충전 할지 뒤로 갈지 선택
	private void selectChargePay(String userNo) {
		while (true) {

			System.out.println("─────────────────페이 충전─────────────────────");

			// 현재 잔액 출력
			findBalance(userNo);

			// 사용자에게 입력받기
			System.out.print("페이를 충전 하시겠습니까? (1. 충전하기 /2. 뒤로가기)");
			String chargeYn = sc.nextLine();

			// 1이면 충전 페이지, 2면 돌아가기
			switch (chargeYn) {
			case "1":
				chargePay(userNo);
				return;
			case "2":

				return;
			}

		}
	}

	// 페이 충전
	private void chargePay(String userNo) {
		// 충전 금액에 정수형을 넣지 않았을 때 예외 처리
		try {
			System.out.println();
			int nowMoney = findBalance(userNo);// 현재 금액 보여주고 nowMoney에 현재 금액 저장

			System.out.println("─────────────────충전하기──────────────────────");

			// 충전 금액 입력 받기
			System.out.println("충전 할 금액을 입력하세요 : ");
			String chargeAmount = sc.nextLine();

			// 잔액을 계산하기 위해 문자열 -> 정수형으로 변환
			int intChargeAmount = Integer.parseInt(chargeAmount);

			// 충전 후 금액 구하고 DB에 넣기 쉽게 문자열로 변환
			String afterMoney = Integer.toString(nowMoney + intChargeAmount);

			// pay테이블에 추가 할 내용 뭉치기(충전한 금액, 잔액)
			PayVo vo = new PayVo();
			vo.setUserNo(userNo);
			vo.setPay(chargeAmount);
			vo.setBalance(afterMoney);

			// 서비스 실행
			int result = service.chargePay(userNo, afterMoney, vo);

			// 정상적으로 내역이 추가 될 경우
			if (result == 1) {
				System.out.println("충전이 완료 되었습니다.");
				// 잔액 출력
				findBalance(userNo);
			} else {
				// 내역이 추가 되지 않을경우 오류발생
				throw new Exception();
			}

		} catch (NumberFormatException e) {
			// 충전 금액에 정수를 입력 하지 않았을 경우
			System.out.println("충전 금액을 정수형(예 : 20000)으로 입력 해 주세요.");
			System.out.println("이전 화면으로 돌아갑니다");
			System.out.println();
			return;
		} catch (Exception e) {
			// 페이 충전 오류
			System.out.println("페이 충전 실패 ");
			return;
		}

	}

	// 입출금 내역
	private void DepositWithdrawalDetails(String userNo) {
		try {
			System.out.println("─────────────────입출금 내역확인─────────────────");

			// 데이터
			List<PayVo> voList = service.payList(userNo);
			// 결과
			for (PayVo vo : voList) {
				// 출금의 출처가 '충전'일때는 파랑 글씨로 출력
				if (vo.getSource().equals("충전")) {
					System.out.println("\u001B[34m날짜 : " + vo.getPayDate());
//					System.out.println("\t\t\t\t      " +  vo.getSource());// ""(쌍따움표)안의 역할 : 출력문 파랑색으로 바꾸기
//					System.out.println("\t\t\t\t     " + vo.getPay());
					System.out.print("\t\t\t       " +  vo.getSource());// ""(쌍따움표)안의 역할 : 출력문 파랑색으로 바꾸기
					System.out.println(" : " + vo.getPay()+ "원");
					System.out.println("\t\t\t       잔액 : " + vo.getBalance() + "원\u001B[0m");// ""(쌍따움표)안의 역할 : 출력문 원래 색으로 바꾸기
					System.out.println("────────────────────────────────────────────");
				} else {
					System.out.println("\u001B[31m날짜 : " + vo.getPayDate());
//					System.out.println("\t\t\t\t      " +  vo.getSource());// ""(쌍따움표)안의 역할 : 출력문 파랑색으로 바꾸기
//					System.out.println("\t\t\t\t     " + vo.getPay());
					System.out.print("\t\t\t    " +  vo.getSource());// ""(쌍따움표)안의 역할 : 출력문 파랑색으로 바꾸기
					System.out.println(" : " + vo.getPay()+ "원");
					System.out.println("\t\t\t       잔액 : " + vo.getBalance() + "원\u001B[0m");// ""(쌍따움표)안의 역할 : 출력문 원래 색으로 바꾸기
					System.out.println("────────────────────────────────────────────");
					
				}
			}
		} catch (Exception e) {
			System.out.println("입출금 내역 불러오기 실패");
			e.printStackTrace();
			return;
		}

	}

}
