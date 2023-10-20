package com.baedal.one.owneroders;

import com.baedal.one.owneroders.controller.OwnerOdersController;

public class OwnerOdersTestMain {

	public static void main(String[] args) {

		String storeNo = "3";// 테스트용 임시 번호
		OwnerOdersController ownerOdersController = new OwnerOdersController();
		ownerOdersController.showOders(storeNo);
	}
}
