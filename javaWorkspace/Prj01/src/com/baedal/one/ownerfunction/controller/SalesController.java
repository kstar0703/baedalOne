package com.baedal.one.ownerfunction.controller;

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

	public void selectSalesMenu(String storeNo) {

		System.out.println("======매출확인=====");
		System.out.println("1. 월 매출 확인");
		System.out.println("2. 뒤로가기");
		System.out.print("입력 :");
		String input = sc.nextLine();

		switch (input) {
		case "1": {
			showMonthSales(storeNo);
		}
		case "2":
			return;
		default:
			System.out.println("잘 못 입력");
		}
	}

	// 월별 매출 확인
	private void showDaySales(String storeNo) {

	}

	// 일별 매출 확인
	private void showMonthSales(String storeNo) {
		try {
			System.out.println(storeName + "의 월 매출");
			List<OwnerOdersVo> voList = service.OwnerOderList(storeNo);
			for (OwnerOdersVo vo : voList) {
				if (vo.getOderDate().contains("2023-10")) {
					System.out.print(vo.getOderDate() + " //");
					while(vo.getTotalPrice() == null) {}
					System.out.println(vo.getTotalPrice() + " ");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
