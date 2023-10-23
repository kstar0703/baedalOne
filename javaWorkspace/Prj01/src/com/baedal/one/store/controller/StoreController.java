package com.baedal.one.store.controller;



import java.nio.channels.SelectableChannel;
import java.security.Provider.Service;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.management.loading.PrivateClassLoader;

import com.baedal.one.Main;
import com.baedal.one.cart.TestMain;
import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.store.dto.StoreCategoryDto;
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
		
		System.out.println("=====매장 관리 선택=====");
		System.out.println("1. 매장 정보 확인");
		System.out.println("2. 매장 등록 ");
		
		System.out.println("3. 매장 관리 ");
		System.out.println("4. 매장 정보 수정 및 삭제");
		System.out.println("9. 뒤로가기");
		System.out.println("0. 종료");
		
		System.out.println("번호를 선택하세요");
		String storeSelectNum = Main.SC.nextLine();
		switch (storeSelectNum) {
		case "1" :   showStoreInfo(); break;
		case "2" :   registerStore();  break;
		case "3" :  System.out.println("매장관리 메소드 출력 선택 삭제"); break;
		case "9" : return; 
		case "0" :  System.exit(0);
		default : System.out.println("잘못 누르셨습니다 다시 입력하세요."); storeSelectMenu();
		}
	}
	
	/*
	 * 매장 관리 -- 매장 삭제 -- 
	 */
	public void storeManger(StoreVo vo) {
		System.out.println("===== 매장 수정 및 폐업 =====");
		
			
	}
	/**
	 * 매장 수정
	 * @param vo
	 * 매장 이름 수정  ,카테고리 전화번호 수정, 매장주소 수정, 매장 영업시간 수정
	 * 
	 */
	public void ChangeStoreInfo(StoreVo vo) {
		System.out.println("===== 매장 정보 수정=====");
		System.out.println("#수정을 원치 않는 메뉴는 ENTER키를 입력해주세요");
		
		System.out.print("변경 할 매장명 : " );
		String StoreName = Main.SC.nextLine();
		
	
		System.out.println("#예)02-0000-0101# 12자리");
		System.out.print("변경 할 매장 전화번호 : ");
		String storePhone = Main.SC.nextLine();
		
		System.out.print("변경 할 가게  주소 :");
		String storeAddress = Main.SC.nextLine();
		
		System.out.println("#예) 10:00 #");
		System.out.print("변경 할 오픈 시간 : ");
		String openTime = Main.SC.nextLine();
		
		System.out.println("#예) 20:00 #");
		System.out.print("변경 할 마감 시간 : ");
		String closeTime = Main.SC.nextLine();
		
		StoreVo changeVo = new StoreVo();
		
		changeVo.setStoreName(StoreName);
		changeVo.setStorePhone(storePhone);
		changeVo.setStoreADDRESS(storeAddress);
		changeVo.setCloseTime(closeTime);
		changeVo.setOpenTime(openTime);
		
		
		
		
		
		
		
	}
	
	/*
	 **
	 */
	
	public void M(StoreVo vo) {
		
	}
	
	
	
	
	
		
	
	
	
	/**
	 * 매장 등록 추가
	 */
	
	public void registerStore() {
		System.out.println("===== 매장등록 ======");
		
		try {
			
			//데이터
			//## 수정 OwnerTest.LoginOwner.getOwnerNo -->Main.LoginOwer
			System.out.print("매장명 : ");
			String storeName = Main.SC.nextLine();
			
			
			// 카테고리 번호
			String categoryNum = selectCategory(showCategory());
			// 점주번호
			String ownerNo = OwnerTestMain.LoginOwner.getOwnerNo();
			
			System.out.println("#예)02-0000-0101# 12자리");
			System.out.print("매장 전화번호 : ");
			String storePhone = Main.SC.nextLine();
			
			System.out.print("가게 주소 :");
			String storeAddress = Main.SC.nextLine();
			
			System.out.println("#예) 10:00 #");
			System.out.print("오픈 시간 : ");
			String openTime = Main.SC.nextLine();
			
			System.out.println("#예) 20:00 #");
			System.out.print("마감 시간 : ");
			String closeTime = Main.SC.nextLine();
			
			StoreVo vo = new StoreVo();
			
			vo.setStoreName(storeName);
			vo.setStorePhone(storePhone);
			vo.setStoreADDRESS(storeAddress);
			vo.setCategoryNO(categoryNum);
			vo.setOwnerNo(ownerNo);
			vo.setOpenTime(openTime);
			vo.setCloseTime(closeTime);
			
			//service
			int result = service.registerStore(vo);
			
			
			if(result !=1) {
				throw new Exception(); 
			}
			
		}catch (Exception e) {
			System.out.println("매장등록 실패");
			e.printStackTrace();
		}
		System.out.println("가게 등록 성공!!");
		
		
	}
	
	/**
	 * 카테고리 목록 출력 - 매장등록시 사용 , 구매자 검색 할때 사용
	 */
	public List<StoreCategoryDto> showCategory()  {
		List<StoreCategoryDto> list	 = null;
		try {
		
		System.out.println("===== 카테고리 목록 ===== ");
		
		//service
		
		list = service.showCategory();
		
		//결과처리
		if(list.size() ==0) {
			throw new Exception();
		}
	
		// 카테고리명 출력문
		for(int i=0; i<list.size(); i++) {
			System.out.println(i+1 + ". " + list.get(i).getCategoryName());
		}
		

		}catch (Exception e) {
			System.out.println("카테고리 목록 출력 실패");
			e.printStackTrace();
		}
		
		return list;	
	}
	
	/**
	 * 카테고리 선택 - 매장등록시 사용 , 구매자 검색 할때 사용 ,메뉴 등록 
	 * 서비스 필요없음 ㅠㅠ
	 */
	
	public String selectCategory(List<StoreCategoryDto> list) {
		String categoryNum ="";
		try {
			System.out.println("===== 카테고리 선택 =====");
			// 데이터 
			System.out.print("카테고리를 선택 하세요");
			String chooseCategoryNum = Main.SC.nextLine();
			int intChooseCategoryNum = Integer.valueOf(chooseCategoryNum);
			
			categoryNum = list.get(intChooseCategoryNum-1).getCategoryNo();
				
		}catch (Exception e) {
			
		}
		
		return categoryNum;
	}
	
	
	

	/**
	 * 매장 정보 출력(점주)
	 * 수정 변경 및 삭제 재활용 가능
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
	 * 가게 선택 (점주)
	 * 수정 변경 및 삭제 재활용 가능
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
	
	
	
	
	
	
	
}
	
	

	
	
	


