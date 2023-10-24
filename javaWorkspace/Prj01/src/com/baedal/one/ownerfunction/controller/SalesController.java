package com.baedal.one.ownerfunction.controller;

import java.text.DecimalFormat;
import java.util.List;

import com.baedal.one.Main;
import com.baedal.one.ownerfunction.dto.OwnerOdersVo;
import com.baedal.one.ownerfunction.service.OwnerOdersService;

public class SalesController {
	private String storeName;
	private final OwnerOdersService service;

	public SalesController(String storeNo) {
		service = new OwnerOdersService();
		storeName = new OwnerOdersController().findStoreName(storeNo);

	}

	public void showMonthSales(String storeNo) {
		//월의 총 판매 금액을 담을 변수
		int monthPrice = 0;
		try {
			System.out.println(storeName + "의 월 매출");
			
			//inputYear(년도) 입력
			System.out.print("년도 입력 : ");
			String inputYear = Main.SC.nextLine();
			
			//inputYear가 2000~2050의 수가 아닐 경우 재실행
			if(Integer.parseInt(inputYear)>2050 || Integer.parseInt(inputYear)<2000) {
				System.out.println("2000년 이상 2050년 이하의 수를 넣어주세요.");
				showMonthSales(storeNo);
			}
			
			//inputMonth(달) 입력
			System.out.print("월 입력 : ");
			String inputMonth = Main.SC.nextLine();

			//inputMonth가 1~12의 사이의 수가 아닐시 재실행
			if(12< Integer.parseInt(inputMonth) || 0 >  Integer.parseInt(inputMonth)) {
				System.out.println("1이상 12이하의 수를 넣어주세요.");
				showMonthSales(storeNo);
			}
			//판매 금액과 판매 일시를 담을 리스트
			List<OwnerOdersVo> voList = service.OwnerOderList(storeNo);

			System.out.print(inputYear + "년 "+ inputMonth + "월의 매출 : ");
			
			//year와 month를 합쳐서 inputDate로 바꾸기
			String inputDate = inputYear + "-" + inputMonth;
			
			//입력받은 년도의 월에 판매 된 판매금액을 모두 더해서 monthPrice에 저장한다.
			for (OwnerOdersVo vo : voList) {
				if (vo.getOderDate().contains(inputDate)) {
					int totalPrice = Integer.parseInt(vo.getTotalPrice());
					monthPrice += totalPrice;
				}
			}
			
			// 금액 표시 위한 클래스(1,000,000 식으로 ','를 중간에 넣어준다.)
			DecimalFormat format = new DecimalFormat("###,###");
			System.out.println(format.format(monthPrice));
			System.out.println("=========================================");
			System.out.print("상세한 매출을 확인하고 싶다면 '1'을 입력,\n돌아가기를 원하면 '2'를 입력하세요 : ");
			String input = Main.SC.nextLine();
			switch (input) {
			case "1": {
				showDaySales(storeNo, inputDate, voList, monthPrice);
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

	// 일별 매출 확인
	private void showDaySales(String storeNo, String inputDate, List<OwnerOdersVo> voList, int monthPrice) {

		
		// 금액 표시 위한 클래스(1,000,000 식으로 ','를 중간에 넣어준다.)
		DecimalFormat format = new DecimalFormat("###,###");
		
		// 달마다 다른 마지막 날을 i에 저장 
		int lastDay = 31;
		if(inputDate.contains("-4")||inputDate.contains("-6")||inputDate.contains("-9")||inputDate.contains("-11")) {
			lastDay = 30;
		}else if(inputDate.contains("-2")) {
			lastDay = 28;
		}
		
		//일별로 반복
		for(int i = 1; i <= lastDay ; i ++) {
			
			//하루 매출
			int dayPrice = 0;
			
			//일단위 날짜 저장 문자열
			String Date;
			
			//일(day)가 한 자리일경우 앞에 0을 붙힘 (1 ->01)
			if (i < 10) {
				Date = inputDate +"-"+"0"+Integer.toString(i);
			}else {
				Date = inputDate +"-"+Integer.toString(i);
			}
			
			//날짜에 판매매출을 저장하기 위해 반복
			for (OwnerOdersVo vo : voList) {
				
				//날짜에 맞추어 저장
				if (vo.getOderDate().contains(Date)) {
					int totalPrice = Integer.parseInt(vo.getTotalPrice());
					dayPrice += totalPrice;
				}
			}
			
			//일자별로 매출 출력
			System.out.print(Date + " : ");
			System.out.println(format.format(dayPrice) + "원 ");	
			System.out.println();
		}
		
		//평균 매출 구하기
		System.out.println("이달 평균 매출 : " + monthPrice/lastDay +"원");
		
		System.out.print("확인을 완료 하였다면 '1'을 입력해주세요 : ");
		
	}
	

}
