package com.baedal.one.inquiryOrder;

import com.baedal.one.inquiryOrder.controller.InquiryOrderController;

public class InquiryOrderMain {
	public static String USERNO = "5";// 테스트용 임시 번호
	public static void main(String[] args) {
		InquiryOrderController inquiryOrderController = new InquiryOrderController();
		inquiryOrderController.showOder(USERNO); 
	}

}
