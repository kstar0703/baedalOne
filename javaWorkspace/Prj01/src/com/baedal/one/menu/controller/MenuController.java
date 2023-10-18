package com.baedal.one.menu.controller;

import java.util.Scanner;

import com.baedal.one.Main;
import com.baedal.one.menu.mian.*;
import com.baedal.one.menu.service.MenuService;
import com.baedal.one.menu.vo.MenuVo;

public class MenuController {
	
	//필드
	private final MenuService menuService;
	private static final String OWNERNO ="3";
	
	//기본 생성자
	public MenuController() {
		menuService = new MenuService();
	}
	
	//메뉴선택
	//1번 밖에 실행 안됨!!
	public void selectMenu() {
		
		while(true) {
			System.out.println("▼ 메뉴 선택 ");
			
			System.out.println("1. 메뉴 등록");
			System.out.println("2. 메뉴 수정");
			System.out.println("3. 메뉴 삭제");
			
			String num = MenuMain.SC.nextLine();
			
			switch(num) {
			case "1" : addMenu(); break;
			case "2" : editMenu(); break;
//		case "3" : deleteMenu(); break;
			default : System.out.println("잘못된 명령입니다.");
			}
		}
		
	}

	//점주 비밀번호 가져오기
	public void findPwd() {
		//입력할 비밀번호
		String nowPwd = null;
		//DB에서 가져오는 비밀번호
		String ownerPwd = null;
		try {
			
			ownerPwd = menuService.findPwd(OWNERNO);
			System.out.print("비밀번호 입력 :");
			//입력
			System.out.println(nowPwd);
			if(ownerPwd.equals(nowPwd)) {
				//메뉴 관리 기능 오픈
				selectMenu();
			} else {
				throw new Exception("점주만 메뉴를 관리할 수 있습니다.");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	//메뉴 등록 여부 
	public void addMenu() {
		System.out.println("▼ 메뉴 등록");
		
		String answer = "";
		int result = 0;
		
		do {
			
			//데이터(메뉴 이름, 가격)
			System.out.print("메뉴 이름 : ");
			String menuName = MenuMain.SC.nextLine();
			System.out.print("메뉴 가격 : ");
			String price = MenuMain.SC.nextLine();
			
			System.out.print("메뉴를 등록하시겠습니까? (Y / N)");
			answer = MenuMain.SC.nextLine().toLowerCase();
			switch(answer) {
			case "y": 
				try {
					//객체로 만들기(매장 번호, 메뉴이름 가격)
					MenuVo menuVo = new MenuVo();
					menuVo.setStoreNo();
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
		} while (!answer.equals("y"))
	}
			
//			
//			MenuVo menuVo = new MenuVo();
//			menuVo.setMenuName(menuName);
//			menuVo.setPrice(price);
//			
//			
//			
//			//서비스
//			int result = menuService.addMenu(menuVo);
//			
//			//결과
//			if(result != 1) {
//				throw new Exception();
//			} 
//			System.out.println("메뉴 등록이 완료되었습니다.");
//			
//		} catch(Exception e) {
//			System.out.println("메뉴 등록에 실패하였습니다. 다시 시도해주세요.");
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 메뉴 수정 (번호로)
	 * 
	 */
	public void editMenu() {
		System.out.println("▼ 메뉴 수정 ");
		
		
		
		try {
			
			//데이터
			System.out.print("수정할 메뉴 번호 : ");		//번호
			String menuNo = MenuMain.SC.nextLine();
			System.out.print("수정할 메뉴 이름 :");		//최종으로 수정하고 싶은 메뉴 이름
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
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("메뉴정보가 수정되었습니다.");
		} catch(Exception e) {
			System.out.println("메뉴정보 수정 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
	}
	
	
	//메뉴 삭제
	public boolean deleteMenu(String menuNo) {
		System.out.println("▼ 메뉴 삭제 ");
		
		boolean isDelete = false; 
		String answer = "";
		int result = 0;
		
		do {
			//데이터
			System.out.print("메뉴를 삭제하시겠습니까? (Y / N) ");
			answer = MenuMain.SC.nextLine();
			switch(answer) {
			case "y", "Y" :
				try {
					
					//서비스 호출
					result = menuService.deleteMenu(menuNo);
					if(result == 1) {
						System.out.println("메뉴 삭제가 완료되었습니다.");
						isDelete = true;
						return isDelete;
					}
				} catch(Exception e) {
					System.out.println("메뉴 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
					e.printStackTrace();
				}
				break;
			case "n", "N" : 
				System.out.println("뒤로 이동합니다.");
				return isDelete;
			default : 
				System.out.println("다시 시도하세요.");
				break;
			}
		} while (!answer.equals("y") || !answer.equals("Y"));
		return isDelete;
		
		MenuVo menuVo = new MenuVo();
		menuVo.setMenuNo(menuNo);
		
		
		
		
	}
	
	//뒤로가기
	public void back() {
		
	}
	
	

}
