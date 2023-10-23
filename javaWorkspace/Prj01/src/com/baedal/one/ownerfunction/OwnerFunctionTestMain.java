package com.baedal.one.ownerfunction;

import com.baedal.one.ownerfunction.controller.OwnerOdersController;
import com.baedal.one.ownerfunction.controller.SalesController;

public class OwnerFunctionTestMain {
	public static String storeNo = "3";// 테스트용 임시 번호

	public static void main(String[] args) {

		
//		OwnerOdersController ownerOdersController = new OwnerOdersController();
//		ownerOdersController.showOders(storeNo);
		
		SalesController salesController = new SalesController(storeNo);
		salesController.selectSalesMenu(storeNo);
	}
}
