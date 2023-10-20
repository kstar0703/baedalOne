package com.baedal.one.pay;

import com.baedal.one.pay.controller.PayController;

/**
 * 페이 기능 테스트 메인
 */
public class PayTestMain {

	public static void main(String[] args) {
		PayController payController = new PayController();
		payController.selectPayMenu();

	}
}
