package com.baedal.one.owneroders;

import com.baedal.one.owneroders.controller.OwnerOdersController;

public class OwnerOdersTestMain {
	
	public static void main(String[] args) {
		String storeNo = "3";
		OwnerOdersController ownerOdersController = new OwnerOdersController();
		ownerOdersController.showOders(storeNo);
	}
}
