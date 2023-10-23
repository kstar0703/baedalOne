package com.baedal.one.store.controller;



import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.management.loading.PrivateClassLoader;
import javax.sql.rowset.JoinRowSet;

import org.w3c.dom.ls.LSOutput;

import com.baedal.one.Main;
import com.baedal.one.cart.TestMain;
import com.baedal.one.menu.controller.MenuController;
import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.owner.vo.OwnerVo;
import com.baedal.one.store.dto.StoreCategoryDto;
import com.baedal.one.store.service.StoreService;
import com.baedal.one.store.testMain.StoreTestMain;
import com.baedal.one.store.vo.StoreVo;

public class StoreController {
	
	private StoreService service;
	private MenuController menuController;
	
	
	public StoreController() {
		
		service = new StoreService();
		menuController = new MenuController();
	}
	
	
	/**
	 *  점주 매장 관리탭 
	 */
	
	public void storeSelectMenu() {
		
		while(true) {
		System.out.println("=====매장 관리 선택=====");
		System.out.println("1. 매장 정보 확인");
		System.out.println("2. 매장 등록 ");
		System.out.println("3. 매장 메뉴 관리");
		System.out.println("4. 매장 주문 관리 및 매출관리");
		System.out.println("5. 매장 정보 수정 및 폐점");
		System.out.println("9. 뒤로가기");
		System.out.println("0. 종료");
		
		System.out.println("번호를 선택하세요");
		String storeSelectNum = Main.SC.nextLine();
		switch (storeSelectNum) {
		case "1" :   System.out.println("디테일 매장 메소드 출력"); break;
		case "2" :   registerStore(); break;
		case "3" : System.out.println("송희님 메소드 호출");;
		case "4" : System.out.println("범렬님 메소드 가져오기"); break;
		case "5" : storeManger(chooseStore(showStoreInfo())); break;
		case "9" : return; 
		case "0" :  System.exit(0);
		default : System.out.println("잘못 누르셨습니다 다시 입력하세요."); storeSelectMenu();
		}
		}
	}
	
	/**
	 * 매장 관리 -- 매장 삭제 , 정보 수정 -- 
	 */
	public void storeManger(StoreVo vo) {
		System.out.println("===== 매장 수정 및 폐업 =====");
		System.out.println("1. 매장 정보 수정"); 		
		System.out.println("2. 매장 폐점");
		System.out.println("9. 뒤로 가기");
		System.out.println("0. 종료");
		
		String storeMangetChooseNum = Main.SC.nextLine();
		
		switch (storeMangetChooseNum) {
		case "1" : changeStoreInfo(vo); break;
		case "2" : shoutDownStore(vo); break;
		case "9" : return; 
		case "0" : System.exit(0); break;
		default : System.out.println("잘못 누르셨습니다 다시 입력하세요."); storeManger(vo);
		}
		
	}
	/**
	 * 매장 수정
	 * @param vo
	 * 매장 이름 수정  ,카테고리 전화번호 수정, 매장주소 수정, 매장 영업시간 수정
	 * 
	 */
	public void changeStoreInfo(StoreVo vo) {
		System.out.println("===== 매장 정보 수정=====");
		
		// 메소드 블루
		System.out.println("=====" + vo.getStoreName() + "======");
		System.out.println("전화번호 : " + vo.getStorePhone());
		System.out.println("주소 : " + vo.getStoreADDRESS());
		System.out.println("오픈 시간 : " + vo.getOpenTime());
		System.out.println("마감 시간 : " +vo.getCloseTime());
		System.out.println("=====================================");
		
		try {
			
		
		// 데이터
		System.out.println("#수정을 원치 않는 메뉴는 ENTER키를 입력해주세요");
		
		System.out.print("변경 할 매장명 : " );
		String StoreName = Main.SC.nextLine();
		
	
		System.out.println("#예)02-0000-0101# 12자리");
		System.out.print("변경 할 매장 전화번호 : ");
		String storePhone = Main.SC.nextLine();
		
		System.out.print("변경 할 가게 주소 :");
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
		
		//service
		int result = service.changeStoreInfo(vo,changeVo);
		
		//결과처리
		if(result != 1) {
			throw new Exception();
		}
		
		} catch (Exception e) {
			System.out.println("매장 정보 변경 실패");
		}
		System.out.println("매장 정보 변경 성공");
		
	}
	
	/**
	 * 매장 폐업 
	 */
	public void shoutDownStore (StoreVo vo) {
		
		System.out.println("===== 매장 정보 =====");
		
		try {
		System.out.println("=====" + vo.getStoreName() + "======");
		System.out.println("전화번호 : " + vo.getStorePhone());
		System.out.println("주소 : " + vo.getStoreADDRESS());
		System.out.println("오픈 시간 : " + vo.getOpenTime());
		System.out.println("마감 시간 : " +vo.getCloseTime());
		System.out.println("=====================================");
		
		//정보
		System.out.print("폐점 하려면 비밀번호를 입력하세요 : ");
		String password = Main.SC.nextLine();
		
		//service
		int result = service.shoutDownStore(vo,password);
		
		//결과 처리
		if(result != 1) {
			throw new Exception();
		}
		System.out.println("매장 폐업 성공");
		} catch (Exception e) {
			System.out.println("매장 폐점 실패");
		}
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
	 * 서비스 필요없음 
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
	 * 
	 * 디테일 매장 정보
	 * 
	 */
	
	
	
	/**
	 * 매장 선택 정보 출력(점주),카테고리명 출력
	 * 수정 변경 및 삭제 재활용 가능, 
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
			System.out.println("===== 보유 매장 ======");
			for(int i =0; i<storeList.size(); i++) {
				System.out.println(i+1 +". " + storeList.get(i).getStoreName() + "(" +"카테고리 :" + storeList  +")" );
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
	
	

	
	
	


