package com.baedal.one.owner;

import com.baedal.one.owner.controller.OwnerController;
import com.baedal.one.owner.vo.OwnerVo;
import com.baedal.one.store.controller.StoreController;

public class OwnerTestMain {
	
	public static OwnerVo LoginOwner;
	
	public static void main(String[] args) {
		OwnerController ow = new OwnerController();
		StoreController storeController = new StoreController();
		
		
		while(true) {
		ow.selectMenu();
	
		}
		
	}

}
