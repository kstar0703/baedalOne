package com.baedal.one.store.controller;



import javax.management.loading.PrivateClassLoader;

import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.store.service.StoreService;
import com.baedal.one.store.vo.StoreVo;

public class StoreController {
	
	private StoreService storeService;
	
	
	public StoreController() {
		
		StoreService storeService = new StoreService();
	}
	
	
	
	
	
	
	
	
	
	/**
	 * #매장 정보 출력
	 */
	// 유저 
	public StoreVo showStoreInfo() {

		
		String loginOwnerNo = OwnerTestMain.LoginOwner.getOwnerNo();
		
		
		
	}		
}
	/**
	 * #매장 등록
	 * 
	 */
	
	

	
	
	

}
