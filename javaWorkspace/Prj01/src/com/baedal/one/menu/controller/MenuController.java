package com.baedal.one.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.baedal.one.Main;
import com.baedal.one.menu.mian.*;
import com.baedal.one.menu.service.MenuService;
import com.baedal.one.menu.vo.MenuVo;

public class MenuController {
	
	//필드
	private final MenuService menuService;
	private List<MenuVo> menuList;
	private static final String OWNERNO = "1";
	public static final String STORENO = "1";
	
	//기본 생성자
	public MenuController() {
		menuService = new MenuService();
	}
	
	//점주 비밀번호 가져오기
	public void findPwd() {
		
		//입력할 비밀번호
		String nowPwd = "";
		//DB에서 가져오는 비밀번호
		String ownerPwd = "";
		String storeNo = "";
		boolean isTruePwd = false;
		
		try {
//			 System.out.print("매장 번호를 입력하세요: ");
//			 storeNo = Main.SC.nextLine();
//			
//			//로그인 여부 체크
//			if(MenuMain.loginOwner== null) {
//				throw new Exception("로그인 안돼서 에러 발생");
//			}
			
			// 매장 번호 입력
//			storeNo = menuService.findPwd(STORENO);
			
			ownerPwd = menuService.findPwd(OWNERNO);
			
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("1", storeNo);
//			map.put("1", ownerPwd);
//			int result = menuService.findPwd(ownerPwd, storeNo);
			
			while(!isTruePwd) {
				System.out.print("점주 비밀번호 입력 : ");
				nowPwd = Main.SC.nextLine();
				
				if(ownerPwd.equals(nowPwd)) {
					isTruePwd = true;
					//메뉴 관리 기능 오픈
					selectMenu();
				} else {
					System.out.println("잘못된 비밀번호입니다. 다시 입력해주세요." );
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	//메뉴선택
	//1번 밖에 실행 안됨!!
	public void selectMenu() {
		System.out.println("▼ 메뉴 선택 ");
		
		while(true) {
			
			System.out.println("1. 메뉴 등록");
			System.out.println("2. 메뉴 수정");
			System.out.println("3. 메뉴 삭제");
			
			String num = MenuMain.SC.nextLine();
			
			switch(num) {
			case "1" : addMenu(); break;
			case "2" : editMenu(); break;
			case "3" : deleteMenu(); break;
			default : System.out.println("잘못된 명령입니다.");
			}
		}
		
	}

	//메뉴 전체 리스트
	public void menuList() {
		System.out.println("--- 메뉴 전체 목록 조회---");
		
		try {
				
			//서비스 호출
			List<MenuVo> voList = menuService.menuList();
			
			//결과처리
			if(voList.size() == 0) {
				System.out.println("메뉴 정보가 없습니다.");
			} else {
				System.out.println("=== ===");
				System.out.print("메뉴 번호");
				System.out.print(" | ");
				System.out.print("매장 번호");
				System.out.print(" | ");
				System.out.print("메뉴 이름");
				System.out.print(" | ");
				System.out.println("가격");
				for (MenuVo menuVo : voList) {
					System.out.print(menuVo.getMenuNo());
					System.out.print(" | ");
					System.out.print(menuVo.getStoreNo());
					System.out.print(" | ");
					System.out.println(menuVo.getMenuName());
					System.out.print(" | ");
					System.out.println(menuVo.getPrice());
					
					System.out.println("");
				}
			}
		
		}catch(Exception e) {
			System.out.println("메뉴 리스트 조회 실패");
			e.printStackTrace();
		}
		
	}
	
	//메뉴 등록 여부 
	public void addMenu() {
		System.out.println("▼ 메뉴 등록");
		
		
		
		//이전에 조회한 메뉴 목록 확용
		for(MenuVo menuVo : menuList) {
			System.out.println(menuVo.getMenuNo() + "|");
			System.out.println(menuVo.getStoreNo()+ "|");
			System.out.println(menuVo.getMenuName() + "|");
			System.out.println(menuVo.getPrice() + "|");
			System.out.println(menuVo.getDeleteYn() + "|");
			System.out.println(menuVo.getSellYn()+ "|");
		}
		
//		List<MenuVo>  = null;
//		String storeNum = "";
		String menuName = "";
		String price = "";
		String answer = "";
		int result = 0;
		
		do {
			
			//데이터(메뉴 이름, 가격)
//			System.out.print("매장 번호 : ");
//			storeNum = MenuMain.SC.nextLine();
			System.out.print("메뉴 이름 : ");
			menuName = MenuMain.SC.nextLine();
			System.out.print("메뉴 가격 : ");
			price = MenuMain.SC.nextLine();
			
			System.out.println("메뉴를 등록하시겠습니까? (Y / N) ");
			answer = MenuMain.SC.nextLine().toLowerCase();
			switch(answer) {
			case "y": 
				try {
					//객체로 만들기(매장 번호, 메뉴이름 가격)
					MenuVo menuVo = new MenuVo();
					menuVo.setStoreNo(STORENO);
					menuVo.setMenuName(menuName);
					menuVo.setPrice(price);
					
					//서비스
					result = menuService.addMenu(menuVo);
					
					//결과
					if(result == 1) {
						System.out.println("메뉴 등록이 완료되었습니다.");
					}  else {
						throw new Exception();
					}
				}catch(Exception e) {
					System.out.println("메뉴 등록에 실패하였습니다. 다시 시도해주세요.");
					e.printStackTrace();
				}
				break;
			case "n": 
				System.out.println("뒤로 이동");
			default : 
				System.out.println("다시시도");
				break;
			}
		} while (!answer.equals("y"));
	}
		
	
	
	/**
	 * 메뉴 수정 (번호로)
	 * 
	 */
	public void editMenu() {
		System.out.println("▼ 메뉴 수정 ");
		
		menuList();
		
		try {
			//데이터
			System.out.print("수정할 메뉴 번호 : ");		//번호
			String menuNo = MenuMain.SC.nextLine();
			System.out.print("수정할 메뉴 이름 : ");		//최종으로 수정하고 싶은 메뉴 이름
			String menuName = MenuMain.SC.nextLine();
			System.out.print("수정할 메뉴 가격 : ");		
			String menuPrice = MenuMain.SC.nextLine();
			
			MenuVo menuVo = new MenuVo();
			menuVo.setMenuNo(menuNo);
			menuVo.setMenuName(menuName);
			menuVo.setPrice(menuPrice);
			
			//서비스 호출
			int result = menuService.editMenu(menuVo);
			
			//결과처리
			if(result == 1) {
//				throw new Exception();
				System.out.println("메뉴 정보가 수정되었습니다.");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("메뉴정보 수정 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
	}
	
	
	//메뉴 삭제
	public boolean deleteMenu() {
		System.out.println("▼ 메뉴 삭제 ");
		
		menuList();
		
		boolean isDelete = false; 
		String answer = "";
		int result = 0;
		
		do {
			//데이터
			System.out.println("메뉴를 삭제하시겠습니까? (Y / N) ");
			answer = MenuMain.SC.nextLine().toLowerCase();
			
			switch(answer) {
			case "y" :
				try {
					//객체로 만들기(매장 번호, 메뉴이름 가격)
					System.out.print("매장 번호 : ");
					String storeNo = Main.SC.nextLine();
					System.out.print("메뉴 이름 : ");
					String menuName = Main.SC.nextLine();
					System.out.print("가격 : ");
					String price = Main.SC.nextLine();
					
					MenuVo menuVo = new MenuVo();
					menuVo.setStoreNo(storeNo);
					menuVo.setMenuName(menuName);
					menuVo.setPrice(price);
					
					//서비스 호출
					result = menuService.deleteMenu(menuVo);
					if(result == 1) {
						System.out.println("메뉴 삭제가 완료되었습니다.");
						isDelete = true;
					}
				} catch(Exception e) {
					System.out.println("메뉴 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
					e.printStackTrace();
				}
				break;
			case "n" : 
				System.out.println("뒤로 이동합니다.");
				return isDelete;
			default : 
				System.out.println("다시 시도하세요.");
				break;
			}
		} while (!answer.equals("n") && !answer.equals("y"));
		
		return isDelete;
	}
		
//		MenuVo menuVo = new MenuVo();
//		menuVo.setMenuNo(menuNo);
		
	
	//뒤로가기
	public void back() {
		
	}
	
	

}
