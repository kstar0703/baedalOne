package com.baedal.one.store.controller;



import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.management.loading.PrivateClassLoader;

import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.store.service.StoreService;
import com.baedal.one.store.vo.StoreVo;

public class StoreController {
	
	private StoreService service;
	
	
	public StoreController() {
		
		StoreService storeService = new StoreService();
	}
	
	
	
	// 점주 출력 
	public List<StoreVo> showStoreInfo() {
			List<StoreVo> storeList =null;
		try {
			// 오너 번호
			String loginOwnerNo = OwnerTestMain.LoginOwner.getOwnerNo();
			
			// service 호출
			 storeList = service.showStoreInfo(loginOwnerNo);
			
			if(storeList.size()==0) {
				throw new Exception("등록한 매장이 없습니다");
			}
			
			for(int i =0; i<storeList.size(); i++) {
				System.out.println(i+1 +"번" + storeList.get(i).getStoreName() );
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("매장 조회 실패");
			e.printStackTrace();
		}
		
		return storeList;
	}
		
		
		/**
		 * #매장 등록
		 * 
		 */
		
			
}
	
	

	
	
	


