package com.baedal.one.pay.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.baedal.one.pay.PayTestMain;
import com.baedal.one.pay.service.PayService;
import com.baedal.one.pay.vo.PayVo;

public class PayController {

	private final PayService service;
	private static final String USERNO = "1"; // 임시 사용자 넘버

	Scanner sc = new Scanner(System.in);

	public PayController() {
		service = new PayService();
	}

	public void selectPayMenu() {
		selectChargePay();

//		// 페이 페이지 출력
//		System.out.println("───────내 돈 관리────────");
//		System.out.println("1. 페이 충전하기");
//		System.out.println("2. 페이 출금 내역 확인");
//		System.out.println("3. 뒤로가기");
//		
//		// 사용자에게 어떤 기능을 사용 할 것인지 입력 받기
//		String inputNum = Main.SC.nextLine();
//		
//		// 엽력한 수에 따라 기능 실행
//		switch(inputNum) {
//		case "1" : chargePay(); break;
//		case "2" : DepositWithdrawalDetails(); break;
////		case "3" : 이전화면
//		}
	}

	// 잔액 구하기
	private int findBalance() {
		int nowMoney = 0;
		try {
			// PayVo 객체 생성
			PayVo vo = new PayVo();
			vo.setUserNo(USERNO);

			// 잔액을 불러와서 저장
			nowMoney = service.findBalance(vo);

			if (nowMoney < 0) {
				throw new Exception();
			}
			System.out.println("현재 잔액 : " + nowMoney);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nowMoney;
	}

	// 페이충전
	private void selectChargePay() {
		System.out.println("------페이 충전 여부-------");
		int nowMoney = findBalance();
		System.out.print("페이를 충전 하시겠습니까? (1. 충전하기 /2. 뒤로가기)");
		String chargeYn = sc.nextLine();

		switch (chargeYn) {
		case "1":
			chargePay();
			break;
		case "2":
			selectPayMenu();
			break;
		}

	}

	private void chargePay() {
		try {
			System.out.println();
			int nowMoney = findBalance();

			System.out.println("충전 할 금액을 입력하세요 : ");
			String chargeAmount = sc.nextLine();
			// 문자열 -> 정수형으로 변환
			int intChargeAmount = 0;

			intChargeAmount = Integer.parseInt(chargeAmount);
			String afterMoney = Integer.toString(nowMoney + intChargeAmount);
			int result = service.chargePay(USERNO,afterMoney);
			
			if(result == 1) {
				System.out.println("충전이 완료 되었습니다.");
				findBalance();
			}else {
				System.out.println("회원정보에 저장 실패(MEMBER에서 SELECT문 수정)");
			}
		} catch (NumberFormatException e) {
			//충전 금액에 정수를 입력 하지 않았을 경우
			System.out.println("충전 금액을 정수형(예 : 20000)으로 입력 해 주세요.");
			chargePay();
		}catch (SQLException e) {
			//디비 문제가 생길 시
			e.printStackTrace();
		}

	}

	// 입출금 내역
	private void DepositWithdrawalDetails() {
		try {
			System.out.println("-----입출금 내역확인-------");

			PayVo vo = new PayVo();

			// 데이터
			List<PayVo> voList = service.payList(USERNO);
			// 결과
			for (PayVo vo1 : voList) {
				System.out.print(vo1.getSource());
				System.out.print(" / ");
				System.out.print(vo1.getPay());
				System.out.print(" / ");
				System.out.print(vo1.getPayDate());
				System.out.print(" / ");
				System.out.println(vo1.getBalance());

			}
		} catch (Exception e) {
			System.out.println("입출금 내역 불러오기 실패");
			e.printStackTrace();
		}

	}

	// 페이 충전하기

	// 출금 내역 확인

}
