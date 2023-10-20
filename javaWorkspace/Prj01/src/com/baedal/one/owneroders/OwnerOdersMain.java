package com.baedal.one.owneroders;

import com.baedal.one.owneroders.controller.OwnerOdersController;

public class OwnerOdersMain {
	
	public static void main(String[] args) {
		String storeNo = "2";
		OwnerOdersController ownerOdersController = new OwnerOdersController();
		ownerOdersController.showOders(storeNo);
	}
}
