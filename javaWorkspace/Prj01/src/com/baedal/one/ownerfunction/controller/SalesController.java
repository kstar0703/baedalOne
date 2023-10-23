package com.baedal.one.ownerfunction.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import com.baedal.one.ownerfunction.dto.OwnerOdersVo;
import com.baedal.one.ownerfunction.service.OwnerOdersService;
import com.baedal.one.ownerfunction.service.SalesService;

public class SalesController {
	private Scanner sc = new Scanner(System.in);
	private String storeName;
	private final OwnerOdersService service;
	private final SalesService salesService;

	public SalesController(String storeNo) {
		salesService = new SalesService();
		service = new OwnerOdersService();
		OwnerOdersController ownerOdersController = new OwnerOdersController();
		storeName = ownerOdersController.findStoreName(storeNo);

	}

	public void showMonthSales(String storeNo) {
		int monthPrice = 0;
		try {
			System.out.println(storeName + "의 월 매출");
			System.out.print("년도 입력 : ");
			String inputYear = sc.nextLine();
			System.out.print("월 입력 : ");
			String inputMonth = sc.nextLine();

			List<OwnerOdersVo> voList = service.OwnerOderList(storeNo);

			System.out.print(inputYear + "년 " + inputMonth + "월의 매출 : ");

			String inputDate = inputYear + "-" + inputMonth;
			for (OwnerOdersVo vo : voList) {
				if (vo.getOderDate().contains(inputDate)) {
					int totalPrice = Integer.parseInt(vo.getTotalPrice());
					monthPrice += totalPrice;
				}
			}
			// 금액 표시 위한 클래스
			DecimalFormat format = new DecimalFormat("###,###");
			System.out.println(format.format(monthPrice));
			System.out.println("=========================================");
			System.out.print("상세한 매출을 확인하고 싶다면 '1'을 입력,\n돌아가기를 원하면 '2'를 입력하세요 : ");
			String input = sc.nextLine();
			switch (input) {
			case "1": {
				showDaySales(storeNo, inputDate, voList);
			}
			case "2": {
				return;
			}
			default:
				System.out.println("잘못 입력하였습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 월별 매출 확인
	private void showDaySales(String storeNo, String inputDate, List<OwnerOdersVo> voList) {

		int dayPrice = 0;
		// 금액 표시 위한 클래스
		DecimalFormat format = new DecimalFormat("###,###");
		int i = 1;
		for (OwnerOdersVo vo : voList) {
		}

	}

}

//			if (i < 32) {
//				if (vo.getOderDate().contains(inputDate + "-" + i)) {
//					int totalPrice = Integer.parseInt(vo.getTotalPrice());
//					dayPrice += totalPrice;
//						System.out.println();
//						System.out.print(vo.getOderDate()+"/");
//						int totalPrice = Integer.parseInt(vo.getTotalPrice());
//					System.out.println(format.format(dayPrice));
//					System.out.println(i);
//					i++;
//				}
//				System.out.println("for문 안됨");
//			}