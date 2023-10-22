package com.baedal.one.store.controller;



import java.nio.channels.SelectableChannel;
import java.security.Provider.Service;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.management.loading.PrivateClassLoader;

import com.baedal.one.Main;
import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.store.service.StoreService;
import com.baedal.one.store.testMain.StoreTestMain;
import com.baedal.one.store.vo.StoreVo;

public class StoreController {
	
	private StoreService service;
	
	
	public StoreController() {
		
		service = new StoreService();
	}
	
	
	/**
	 *  점주 매장 관리탭 
	 */
	
	public void storeSelectMenu() {
		
		
			
		System.out.println("=====매장 관리=====");
		System.out.println("1. 매장 등록 ");
		System.out.println("2. 매장 관리 ");
		System.out.println("3. 뒤로가기");
		System.out.println("0. 종료");
		
		System.out.println("번호를 선택하세요");
		String storeSelectNum = Main.SC.nextLine();
		switch (storeSelectNum) {
		case "1" :   registerStore(); break;
		case "2" : chooseStore(showStoreInfo());  break;
		case "3" : System.out.println("로그인 이후 메뉴 메소드 호출변경 예정");	break;
		case "0" : System.out.println("종료"); System.exit(0);
		default : System.out.println("잘못 누르셨습니다 다시 입력하세요."); storeSelectMenu();
		}
	}
	
		
	
	
	
	/**
	 * 매장 등록  
	 */
	
	public void registerStore() {
		System.out.println("===== 매장등록 ======");
		
		try {
			
			//데이터
			//## 수정 OwnerTest.LoginOwner.getOwnerNo -->Main.LoginOwer
			System.out.print("매장명 : ");
			String storeName = Main.SC.nextLine();
			
			
			
			
			
			
			
			
			
			StoreVo
			
			//service
			int result = service.registerStore(ownerNo);
			
			

			
			if(Resul)
			
		}catch (Exception e) {
			
		}
		
	}
	
	

	/*
	 *  매장 정보 점주  
	 */
	public List<StoreVo> showStoreInfo() {
		
			List<StoreVo> storeList =null;
		try {
		
			// 오너 번호변경 OwnerTest.LoginOwner.getOwnerNo -->Main.LoginOwer
			String loginOwnerNo = OwnerTestMain.LoginOwner.getOwnerNo();
			
			// service 호출
			 storeList = service.showStoreInfo(loginOwnerNo);
			
			if(storeList.size()==0) {
				throw new Exception("등록한 매장이 없습니다");
			}
			
			System.out.println("\n");
			System.out.println("=====보유 매장 ======");
			for(int i =0; i<storeList.size(); i++) {
				System.out.println(i+1 +". " + storeList.get(i).getStoreName() );
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("매장 조회 실패");
			e.printStackTrace();
		}
		
		return storeList;
	}
	
	/**
	 * 관리 할 매장 선택
	 */
	
	public StoreVo chooseStore(List<StoreVo> storeList) {
		StoreVo vo =null;
		try {
			List<StoreVo> list = storeList; 
			if(storeList.size() ==0) {
				throw new Exception("선택할 매장이 없습니다");
					}
		
			
			//데이터 
			System.out.println("관리 하실 매장 번호를 선택하세요");
			String chooseNum = Main.SC.nextLine();
			int intChooseNum = Integer.valueOf(chooseNum)-1;
			
			if(intChooseNum >= list.size()) {
				System.out.println("다시 입력하세요");
				chooseStore(storeList);
			}
			
			StoreVo storeVo = list.get(intChooseNum);
			
			
			//service 호출
			 vo = service.chooseStore(storeVo);
			
			//결과 확인
			
			if(vo ==null) {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("매장 선택 실패");
			e.printStackTrace();
			storeSelectMenu();
		}
		
		return vo;
		
	}
	
	/**
	 * 
	 */
	public void showCategory()  {
		
	}
	

		
			
}
	
	

	
	
	


