package com.baedal.one.pay;

import com.baedal.one.pay.controller.PayController;

/**
 * 페이 기능 테스트 메인
 */
public class PayTestMain {
	private static final String USERNO = "2"; //임시 멤버 번호
	
	public static void main(String[] args) {
		PayController payController = new PayController();
		payController.selectPayMenu(USERNO);

	}
}
