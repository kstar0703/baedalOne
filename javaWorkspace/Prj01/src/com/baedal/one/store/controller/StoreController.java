package com.baedal.one.store.controller;

import java.util.List;
import com.baedal.one.Main;
import com.baedal.one.cart.controller.CartController;
import com.baedal.one.menu.controller.MenuController;
import com.baedal.one.menu.vo.MenuVo;
import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.ownerfunction.controller.OwnerOdersController;
import com.baedal.one.ownerfunction.controller.SalesController;
import com.baedal.one.review.controller.ReviewController;
import com.baedal.one.store.dto.StoreCategoryDto;
import com.baedal.one.store.service.StoreService;
import com.baedal.one.store.vo.StoreVo;
public class StoreController {
	
	private StoreService service;
	private MenuController menuController;
	private StoreVo storeVo;
	private OwnerOdersController ownerOdersController;
	private final CartController cartController;
	private final ReviewController reviewController;
	
	
	public StoreController() {
		menuController = new MenuController();
		ownerOdersController = new OwnerOdersController();
		service = new StoreService();
		menuController = new MenuController();
		cartController = new CartController();
		reviewController = new ReviewController();
	}
	
	/**
	 * 점주 매장선택 이전 선택메뉴
	 */
	public void selectMenuBeforeSelectStore() {
		while(true) {
		System.out.println("--------------- 매장관리 ---------------");
		System.out.println("1. 보유 매장 조회");
		System.out.println("2. 관리 할 매장 선택");
		System.out.println("3. 매장 등록");
		System.out.println("9. 뒤로가기");
		System.out.println("0. 프로그램 종료");
		System.out.println("--------------------------------------");

		System.out.print("\u001B[36m번호를 선택하세요 : \u001B[0m");
		String selectNum = Main.SC.nextLine();
		
		switch (selectNum) {
		case "1" : showOwnerStore(); break;
		case "2" : this.storeVo =selectStore(showOwnerStore());  
					selectMenuAfterSelectStore(this.storeVo);
		break;

		case "3" : registerStore(); break;
		case "9" : return; 
		case "0" : System.exit(0); break;
		default : System.out.println("\u001B[31m다시 입력 하세요\u001B[0m");
		} 
		}
	}
	//

	/**
	 *  점주 매장 관리탭 
	 */
	
	public void selectMenuAfterSelectStore(StoreVo vo) {
		
		while(true) {
			if(storeVo ==null) {
				return;
			}
			SalesController salesController = new SalesController(vo.getStoreNO());
			//
			System.out.println("-----------" +vo.getStoreName() + " 관리 -----------");
			System.out.println("1. 매장 정보 조회");
			System.out.println("2. 매장 정보 수정");
			System.out.println("3. 매장 메뉴 관리");
			System.out.println("4. 주문 내역 조회");
			System.out.println("5. 매출 조회");
			System.out.println("6. 리뷰 조회");
			System.out.println("7. 매장 폐업");
			System.out.println("9. 뒤로가기");
			System.out.println("0. 프로그램 종료");
			System.out.println("--------------------------------------");
			
			System.out.print("\u001B[36m번호를 선택하세요 : \u001B[0m");
			String selectNum = Main.SC.nextLine();
			
			switch (selectNum) {
			case "1" : showStoreInfo(vo); break;
			case "2" : changeStoreInfo(vo); return;
			case "3" : menuController.findPwd(vo.getStoreNO(),vo.getOwnerNo()); break; // 송희님
			case "4" : ownerOdersController.showOders(vo.getStoreNO()); break; // 범렬님
			case "5" : salesController.showMonthSales(vo.getStoreNO()); break; // 범렬님
			case "6" : reviewController.selectReply(reviewController.storeReview(vo.getStoreNO()));  break;
			case "7" : shutDownStore(vo); 
						if(storeVo ==null) {	
						return;} break;
			case "9" : return; 
			case "0" : System.exit(0); break;
			default : System.out.println("\u001B[31m다시 입력 하세요\u001B[0m");;
			} 		
		}
	}

	/**
	 * 매장 수정
	 * @param vo
	 * 매장 이름 수정  ,카테고리 전화번호 수정, 매장주소 수정, 매장 영업시간 수정
	 * 
	 */
	public void changeStoreInfo(StoreVo vo) {
		System.out.println("------------- 매장 정보 수정 -------------");
		
		
		showStoreInfo(vo);
		
		try {
			
		// 데이터
		// enter키만 눌렀을시 수정 안한걸로 인식 dao측에서 처리
		System.out.println("\u001B[31m수정을 원치 않는 메뉴는 ENTER키를 눌러주세요\u001B[0m");
		
		System.out.print("\u001B[36m변경 할 매장명 : \u001B[0m");
		String StoreName = Main.SC.nextLine();
		
	
		System.out.println("\u001B[34mEX)02-0000-0000(12자리)\u001B[0m");
		System.out.print("변경 할 매장 전화번호 : ");
		String storePhone = Main.SC.nextLine();
		
		
		System.out.print("\u001B[36m변경 할 가게 주소 :\u001B[0m");
		String storeAddress = Main.SC.nextLine();
		
		System.out.println("\u001B[34mEX)10:00(5자리)\u001B[0m");
		System.out.print("\u001B[36m변경 할 오픈 시간 : \u001B[0m");
		String openTime = Main.SC.nextLine();
		
		System.out.println("\u001B[34mEX)20:00(5자리)\u001B[0m");
		System.out.print("\u001B[36m변경 할 마감 시간 : \u001B[0m");
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
		}else {
			System.out.println("매장 정보 변경 성공");
			return;
		}
		
		
		} catch (Exception e) {
			System.out.println("\u001B[31m매장 정보 변경 실패\u001B[0m");
		}
		
		// selectMenuBeforeSelectStore();
		
	}
	
	/**
	 * 매장 폐업 
	 * 선택 매장정보 받음
	 */
	public void shutDownStore (StoreVo vo) {
		
		System.out.println("--------------- 매장 정보 ---------------");
		
		
		try {
			showStoreInfo(vo);
		
		//정보
			System.out.print("\u001B[36m폐업 하려면 비밀번호를 입력하세요 : \u001B[0m");
		String password = Main.SC.nextLine();
		
		//service
		int result = service.shutDownStore(vo,password);
		
		//결과 처리
		if(result != 1) {
			throw new Exception();
		}
		System.out.println("매장 폐업 성공");
		storeVo =null;
		
	
	
		} catch (Exception e) {
			System.out.println("\u001B[31m매장 폐업 실패\u001B[0m");
		}
	}	
	
	/**
	 * 매장 등록 추가
	 */
	public void registerStore() {
		System.out.println("--------------- 매장등록 ----------------");
		
		
		try {
			//데이터
			//## 수정 OwnerTest.LoginOwner.getOwnerNo -->Main.LoginOwer
			System.out.print("\u001B[36m매장명 : \u001B[0m");
			String storeName = Main.SC.nextLine();
			
			
			// 카테고리 번호
			//System.out.print("\u001B[34m카테고리를 선택하세요 : \u001B[0m");
			String categoryNum = selectCategory(showCategory());
			// 점주번호
			String ownerNo = Main.loginOwner.getOwnerNo();
			
			System.out.println("\u001B[34m예)02-0000-0000(11자리)\u001B[0m");
			System.out.print("\u001B[36m매장 전화번호 : \u001B[0m");
			String storePhone = Main.SC.nextLine();
			
			System.out.print("\u001B[36m가게 주소 : \u001B[0m");
			String storeAddress = Main.SC.nextLine();
			
			System.out.println("\u001B[34mEX)10:00(5자리)\u001B[0m");
			System.out.print("\u001B[36m오픈시간 : \u001B[0m");;
			String openTime = Main.SC.nextLine();
			
			System.out.println("\u001B[34mEX)20:00(5자리)\u001B[0m");
			System.out.print("\u001B[36m마감시간 : \u001B[0m");
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
			System.out.println("매장 등록 성공!!");
			//성공시 이전 화면 강제 호출 ### 수정
			selectMenuBeforeSelectStore();
			
		}catch (Exception e) {
			System.out.println("\u001B[31m매장등록 실패\u001B[0m");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 카테고리 목록 출력 - 매장등록시 사용 , 구매자 검색 할때 사용
	 * 카테고리 선택과 연계사용
	 */
	public List<StoreCategoryDto> showCategory()  {
		List<StoreCategoryDto> list	 = null;
		try {
		
		System.out.println("-------------- 카테고리 목록 -------------");
		
		
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
		System.out.println("--------------------------------------");
	
		}catch (Exception e) {
			System.out.println("\u001B[31m카테고리 목록 출력 실패\u001B[0m");
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
			//System.out.println("-------------- 카테고리 선택 -------------");
			
			// 데이터 
			System.out.print("\u001B[36m카테고리를 선택 하세요 : \u001B[0m");
			String chooseCategoryNum = Main.SC.nextLine();
			int intChooseCategoryNum = Integer.valueOf(chooseCategoryNum);
			
			categoryNum = list.get(intChooseCategoryNum-1).getCategoryNo();
				
		}catch (Exception e) {	
		}
		return categoryNum;
	}
	
	/**
	 * 가게정보출력 재사용 가능 
	 * Owner vo를 받음
	 * 
	 */
	public StoreVo showStoreInfo(StoreVo vo) {
		try {
			if(vo ==null) {
				throw new Exception();
			}
			System.out.println("--------------- 매장정보 ---------------");
			System.out.println("가게명 : " +vo.getStoreName());
			System.out.println("가게 전화번호 : " +vo.getStorePhone());
			System.out.println("가게 주소 : " + vo.getStoreADDRESS());
			System.out.println("가입일자 :" + vo.getEnrollDate());
			System.out.println("OPEN : " + vo.getOpenTime());
			System.out.println("CLOSE : " + vo.getCloseTime());
			System.out.println("카테고리 : " + vo.getCategoryName());
			System.out.println("--------------------------------------");
		}catch (Exception e) {
			System.out.println("\u001B[31m매장 정보 출력 실패\u001B[0m");
		}
		return vo;
	}
	
	/**
	 * 
	 * 
	 */
	


	/**
	 * 
	 * 점주의
	 * 
	 */
	
	
	public List<StoreVo> showOwnerStore() {
		
			List<StoreVo> storeList =null;
		try {
		
			// 오너 번호변경 OwnerTest.LoginOwner.getOwnerNo -->Main.LoginOwer
			String loginOwnerNo = Main.loginOwner.getOwnerNo();
			
			// service 호출
			 storeList = service.showOwnerStore(loginOwnerNo);
			
			if(storeList.size()==0) {
				System.out.println("\u001B[31m등록한 매장이 없습니다.\u001B[0m");
				System.out.println("\u001B[34m매장을 등록하세요\u001B[0m");
			}else {
				System.out.println("----------------보유 매장------------------");
				for(int i =0; i<storeList.size(); i++) {
					System.out.println(i+1 +". " + storeList.get(i).getStoreName() +" /카테고리(" + storeList.get(i).getCategoryName()  +")" );
				}
				System.out.println("--------------------------------------");	
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("\u001B[31m매장 조회 실패\u001B[0m");
			e.printStackTrace();
		}
		return storeList;
	}
	
	
	
	/**
	 * 가게 선택 (점주)
	 * 수정 변경 및 삭제 재활용 가능	 
	 * 또 선택가능
	 */
	
	public StoreVo selectStore(List<StoreVo> storeList) {
		StoreVo storeVo =null;
		try {
			List<StoreVo> list = storeList; 
			if(storeList.size() ==0) {
				System.out.println("\u001B[31m선택 가능 매장이 없습니다\u001B[0m");
					}else {
						//데이터 
						System.out.print("\u001B[36m번호를 선택하세요 : \u001B[0m");
						String chooseNum = Main.SC.nextLine();
						int intChooseNum = Integer.valueOf(chooseNum)-1;
						
						if(intChooseNum >= list.size()) {
							return null;
						}
						
						storeVo = list.get(intChooseNum);
						//결과 확인
						
						if(storeVo ==null) {
							throw new Exception();
						}	
					}
		
			
			
		} catch (Exception e) {
			System.out.println("매장 선택 실패");
			e.printStackTrace();
		}
		return storeVo;
	}
	
	
	
	/**
	 *  유저쪽 메소드
	 *  전체 매장 조회
	 *  매장 선택 메소드 재활용
	 */
	public List<StoreVo> showAllStore(){
		
		List<StoreVo> storeList =null;
		try {
			
			// service 호출
			 storeList = service.showAllStore();
			
			if(storeList.size()==0) {
				throw new Exception("등록한 매장이 없습니다");
			}
			
			System.out.println("\n");
			System.out.println("--------------- 전체 매장 ---------------");
			for(int i =0; i<storeList.size(); i++) {
				System.out.println(i+1 +". " + storeList.get(i).getStoreName() +" /카테고리(" + storeList.get(i).getCategoryName()  +")" );
			}
			System.out.println("--------------------------------------");
			
		} catch (Exception e) {
			System.out.println("매장 조회 실패");
			e.printStackTrace();
		}
		return storeList;
	}
	
	/**
	 * 유저 입력
	 * 카테고리 검색으로 매장 조회
	 * 매장 선택 메소드 재활용
	 */
	public List<StoreVo> showCategoryStore(String categoryNum){
		
		List<StoreVo> storeList =null;
		try {
		
			
			// service 호출
			 storeList = service.showCategoryStore(categoryNum);
			
			if(storeList.size()==0) {
				throw new Exception("보여줄 매장이 없습니다");
			}
			
			System.out.println("--------------- 전체 매장 ---------------");
			for(int i =0; i<storeList.size(); i++) {
				System.out.println(i+1 +". " + storeList.get(i).getStoreName() +" /카테고리(" + storeList.get(i).getCategoryName()  +")" );
			}
			System.out.println("--------------------------------------");
			
		} catch (Exception e) {
			System.out.println("매장 조회 실패");
			e.printStackTrace();
		}
		return storeList;
	}

	/**
	 *  유저 매장 선택 메소드
	 */
	public void showStoreForMemberManager() {
		StoreVo selectStoreVo = null;
		while (true) {
			System.out.println("1. 전체 매장 조회");
			System.out.println("2. 카테고리별 조회");
			System.out.println("9. 뒤로가기");
			System.out.println("0. 종료");

			System.out.print("\u001B[36m번호를 선택 하세요 : \u001B[0m");
			String selectNum = Main.SC.nextLine();

			switch (selectNum) {
			case "1":
					selectStoreVo = selectStore(showAllStore());
					printStoreDetailInfo(selectStoreVo);
					break;
			case "2":
				selectStoreVo = selectStore(showCategoryStore(selectCategory(showCategory())));
				printStoreDetailInfo(selectStoreVo);
				break; // <-- 극혐코드
			case "9":
				return;
			case "0":
				System.exit(0);
			default : System.out.println("\u001B[31m다시 입력 하세요\u001B[0m");
			}
		}
	}
	
	private void printStoreDetailInfo(StoreVo storeVo) {
		
		while (true) {
		showStoreInfo(storeVo);
		reviewController.storeReview(storeVo.getStoreNO());
		menuController.menuList(storeVo.getStoreNO());
		
		System.out.println("1. 메뉴 주문하러 이동");
		System.out.println("2. 뒤로가기");

		System.out.print("\u001B[36m번호를 선택 하세요 : \u001B[0m");
		String select = Main.SC.nextLine();

		switch (select) {
		case "1":
			cartController.selectOption(storeVo.getStoreNO());
			break;
		case "2" : return;
		default : System.out.println("\u001B[31m다시 입력 하세요\u001B[0m");
			
			break;
		
		}
		}
	}

}
	
	
	


