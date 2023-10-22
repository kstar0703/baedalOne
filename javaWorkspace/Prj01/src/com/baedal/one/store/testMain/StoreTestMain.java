package com.baedal.one.store.testMain;

import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.owner.vo.OwnerVo;
import com.baedal.one.store.controller.StoreController;

public class StoreTestMain {
	
	public static void main(String[] args) {
		
		OwnerTestMain.LoginOwner = new OwnerVo("1","OWNER01","1234","22-10-22","","N"); 
		
		StoreController sc = new StoreController();
		
		sc.storeSelectMenu();
		
		
		
		
	}

}
