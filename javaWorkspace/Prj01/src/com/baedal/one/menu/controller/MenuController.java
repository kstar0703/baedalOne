package com.baedal.one.menu.controller;

import java.util.ArrayList;
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
	private static final String OWNERNO = "1";
	public List<MenuVo> voList ;
	
	//기본 생성자
	public MenuController() {
		menuService = new MenuService();
		
	}
	
	/**
	 * 점주 비밀번호 가져오기
	 * 
	 * String storeNo 가져오기
	 * 
	 * SELECT OWNER_PWD 
	 * FROM OWNER 
	 * WHERE OWNER_NO = ?
	 * 
	 */
	public void findPwd(String storeNo) {
		
		//입력할 비밀번호
		String nowPwd = "";
		//DB에서 가져오는 비밀번호
		String ownerPwd = "";
		boolean isTruePwd = false;
		
		try {
			ownerPwd = menuService.findPwd(OWNERNO);
			
			while(!isTruePwd) {
				System.out.print("점주 비밀번호 입력 : ");
				nowPwd = Main.SC.nextLine();
				
				if(ownerPwd.equals(nowPwd)) {
					isTruePwd = true;
					//메뉴 관리 기능 오픈
					selectMenu(storeNo);
				} else {
					System.out.println("잘못된 비밀번호입니다. 다시 입력해주세요.\n" );
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 메뉴선택
	 * 
	 */
	public void selectMenu(String storeNo) {
		
		while(true) {
			System.out.println("▼ 메뉴 선택 ");
			System.out.println("1. 메뉴 등록");
			System.out.println("2. 메뉴 수정");
			System.out.println("3. 메뉴 삭제");
			System.out.println("4. 삭제한 메뉴 복구하기");
			System.out.println("5. 메뉴 리스트 보기 (매장 전체 메뉴)");
			System.out.println("6. 삭제된 메뉴 리스트 보기");
			System.out.println("9. 뒤로가기");
			
			String num = MenuMain.SC.nextLine();
			
			switch(num) {
			case "1" : addMenu(storeNo); break;
			case "2" : editMenu(); break;
			case "3" : deleteMenu(); break;
			case "4" : rollbackDeleteMenu(); break;
			case "5" : menuList(storeNo); break;
			case "6" : deleteMenuList(storeNo); break;
			case "9" : return;
			default : System.out.println("잘못된 명령입니다.\n");
			}
		}
	}

	
	/**
	 * 메뉴 등록 여부 
	 * 
	 * INSERT INTO MENU(MENU_NO,STORE_NO, MENU_NAME, PRICE) 
	 * VALUES(SEQ_MENU.NEXTVAL, ?, ?, ?)
	 * 
	 */
	public void addMenu(String storeNo) {
		System.out.println("▼ 메뉴 등록");
		
		String menuName = "";
		String price = "";
		String answer = "";
		int result = 0;
		
		do {
			
			//데이터(메뉴 이름, 가격)
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
					menuVo.setStoreNo(storeNo);
					menuVo.setMenuName(menuName);
					menuVo.setPrice(price);
					
					//서비스
					result = menuService.addMenu(menuVo);
					
					//결과
					if(result == 1) {
						System.out.println("메뉴 등록이 완료되었습니다.\n");
					}  else {
						throw new Exception();
					}
					voList = menuService.menuList(MenuMain.storeNo); //등록이 완료되면 객체?에 저장
				}catch(Exception e) {
					System.out.println("메뉴 등록에 실패하였습니다. 다시 시도해주세요.\n");
					e.printStackTrace();
				}
				break;
			case "n": 
				System.out.println("뒤로 이동");
			default : 
				System.out.println("다시시도\n");
				break;
			}
		} while (!answer.equals("y"));
	}
	
	
	/**
	 * 메뉴 수정
	 * 
	 * UPDATE MENU 
	 * 	SET 
	 * 		MENU_NAME = ? 
	 * 		, PRICE = ?  
	 * WHERE MENU_NO = ? 
	 * 
	 */
	public void editMenu() {
	    try {
	        System.out.println("▼ 메뉴 수정 ");
	        
	        // 데이터 입력 받기
	        System.out.print("수정할 메뉴 번호 : ");
	        String menuNo = MenuMain.SC.nextLine();
	        
	        // 메뉴 번호를 이용하여 해당 메뉴 정보를 불러옴
	        MenuVo menuVo = getMenuByMenuNo(menuNo);
	        
	        if (menuVo != null) {
	            System.out.println("현재 메뉴 이름: " + menuVo.getMenuName());
	            System.out.println("현재 메뉴 가격: " + menuVo.getPrice());
	            
	            System.out.print("새로운 메뉴 이름 (변경하지 않을 경우 엔터) : ");
	            String menuName = MenuMain.SC.nextLine();
	            if (!menuName.isEmpty()) {
	                menuVo.setMenuName(menuName);
	            }
	            
	            System.out.print("새로운 메뉴 가격 (변경하지 않을 경우 엔터) : ");
	            String menuPrice = MenuMain.SC.nextLine();
	            if (!menuPrice.isEmpty()) {
	                menuVo.setPrice(menuPrice);
	            }
	            
	            // 서비스 호출
	            int result = menuService.editMenu(menuVo);
	            
	            // 결과 처리
	            if(result == 1) {
	                System.out.println("메뉴 정보가 수정되었습니다.\n");
	            } else {
	                throw new Exception();
	            }
	        } else {
	            System.out.println("해당 메뉴가 존재하지 않습니다.\n");
	        }
	    } catch(Exception e) {
	        System.out.println("메뉴정보 수정 중 오류가 발생했습니다.\n");
	        e.printStackTrace();
	    }
	}
	// 메뉴 번호를 이용하여 해당 메뉴 정보를 불러오는 메서드
	public MenuVo getMenuByMenuNo(String menuNo) {
	    for (MenuVo menu : voList) {
	        if (menu.getMenuNo().equals(menuNo)) {
	            return menu;
	        }
	    }
	    return null;
	}

	
	/**
	 * 메뉴 삭제
	 * 
	 * UPDATE MENU 
	 * SET 
	 * 		DELETE_YN = 'Y' 
	 * 		, SELL_YN = 'N' 
	 * WHERE MENU_NO = ?
	 * 
	 */
	public boolean deleteMenu() {
		System.out.println("▼ 메뉴 삭제 ");
		
		boolean isDelete = false; 
		String answer = "";
		
		try {
			//데이터
			System.out.print("매뉴 번호 : ");
			String menuNo = Main.SC.nextLine();
			
			System.out.println("메뉴를 삭제하시겠습니까? (Y / N) ");
			answer = MenuMain.SC.nextLine().toLowerCase();
			
			switch(answer) {
			case "y" :
					//객체로 만들기(매장 번호, 메뉴이름 가격)
					MenuVo menuVo = new MenuVo();
					menuVo.setMenuNo(menuNo);
					menuVo.setDeleteYn("Y");	//deletr_yn을 Y로 설정
					menuVo.setSellYn("N");	//sell_yn을 N로 설정
					
					//서비스 호출
					int result = menuService.deleteMenu(menuVo);
					
					
					if(result != 1) {
						System.out.println("메뉴 삭제 실패\n");
					} else {
						System.out.println("메뉴 삭제가 완료되었습니다.\n");
					}
					
					break;
					
				case "n" : 
					MenuVo menuVo2 = new MenuVo();
					menuVo2.setMenuNo(menuNo);
					menuVo2.setDeleteYn("N");
					menuVo2.setSellYn("Y");
					
					int result2 = menuService.deleteMenu(menuVo2);
					
					if(result2 != 1) {
						System.out.println("메뉴 삭제 취소 실패\n");
					} else {
						System.out.println("메뉴 삭제 취소\n");
					}
					System.out.println("뒤로 이동합니다.");
					break;
					
				default : 
					System.out.println("다시 선택하세요.\n");
					break;
				} 
			voList = menuService.menuList(MenuMain.storeNo);
		}catch(Exception e) {
					System.out.println("메뉴 삭제 중 오류가 발생했습니다. 다시 시도해주세요.\n");
					e.printStackTrace();
				}
		
		return isDelete;
	}
	
	
	/**
	 * 삭제한 메뉴 복구
	 * 
	 * UPDATE MENU 
	 * SET 
	 * 		DELETE_YN = 'N' 
	 * 		, SELL_YN = 'Y' 
	 * WHERE MENU_NO = ?
	 * 
	 */
	public boolean rollbackDeleteMenu() {
		System.out.println("▼ 메뉴 삭제 취소");
		
		boolean isDelete = false; 
		String answer = "";
		
		try {
			//데이터
			System.out.print("매뉴 번호 : ");
			String menuNo = Main.SC.nextLine();
			
			System.out.println("삭제한 메뉴를 복구하시겠습니까? (Y / N) ");
			answer = MenuMain.SC.nextLine().toLowerCase();
			
			switch(answer) {
			case "y" :
					
					//객체로 만들기(매장 번호, 메뉴이름 가격)
					MenuVo menuVo = new MenuVo();
					menuVo.setMenuNo(menuNo);
					menuVo.setDeleteYn("N");	//deletr_yn을 N로 설정
					menuVo.setSellYn("Y");	//sell_yn을 Y로 설정
					
					//서비스 호출
					int result = menuService.rollbackDeleteMenu(menuVo);
					
					
					if(result != 1) {
						System.out.println("삭제한 메뉴 복구 실패\n");
					} else {
						System.out.println("삭제한 메뉴 복구가 완료되었습니다.\n");
					}
					
					break;
					
				case "n" : 
					MenuVo menuVo2 = new MenuVo();
					menuVo2.setMenuNo(menuNo);
					menuVo2.setDeleteYn("Y");
					menuVo2.setSellYn("N");
					
					int result2 = menuService.deleteMenu(menuVo2);
					
					if(result2 != 1) {
						System.out.println("삭제한 메뉴 복구 취소에 실패하였습니다.\n");
					} else {
						System.out.println("삭제한 메뉴의 복구가 취소됨\n");
					}
					System.out.println("뒤로 이동합니다.");
					break;
					
				default : 
					System.out.println("다시 선택하세요.\n");
					break;
					
				} 
			voList = menuService.menuList(MenuMain.storeNo);
		}catch(Exception e) {
					System.out.println("메뉴 삭제 중 오류가 발생했습니다. 다시 시도해주세요.\n");
					e.printStackTrace();
				}
		
		return isDelete;
	}
		
		
		
	/**
	 * 메뉴 전체 리스트
	 * String storeNo 받기
	 * 
	 * SELECT 
	 * 		MENU_NO
	 * 		, MENU_NAME
	 * 		, PRICE, DELETE_YN
	 * 		, SELL_YN 
	 * FROM MENU 
	 * WHERE STORE_NO = ? 
	 * ORDER BY MENU_NO ASC
	 * 
	 */
	public void menuList(String storeNo) {
		
		try {
			System.out.println("--- 메뉴 전체 목록 조회---");
			
			//서비스 호출
			try {
				voList = menuService.menuList(storeNo);
			} catch (Exception e) {
				System.out.println("전체 메뉴 리스트 불러오기 실패");
				e.printStackTrace();
			}
			
			//결과처리
			if(voList.size() == 0) {
				System.out.println("메뉴 정보가 없습니다.");
			} else {
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("메뉴 번호 ");
				System.out.print("| ");
				System.out.print("메뉴 이름");
				System.out.print(" | ");
				System.out.print(" 가격 ");
				System.out.print(" | ");
				System.out.print("삭제 여부");
				System.out.print(" | ");
				System.out.println("판매 여부");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				for (MenuVo menuVo : voList) {
					System.out.print("   " + menuVo.getMenuNo() + "  ");
					System.out.print(" | ");
					System.out.print(menuVo.getMenuName());
					System.out.print(" | ");
					System.out.print(menuVo.getPrice());
					System.out.print(" | ");
					System.out.print("  " + menuVo.getDeleteYn() + "  ");
					System.out.print(" | ");
					System.out.println("  " + menuVo.getSellYn() + "  ");
				}
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
			}
			
		}catch(Exception e) {
			System.out.println("메뉴 리스트 조회 실패");
			e.printStackTrace();
		}
	}
	
	
	/**
	 *  메뉴 삭제 리스트
	 * String storeNo 받기
	 * 
	 * SELECT 
	 * 		MENU_NO
	 * 		, MENU_NAME
	 * 		, PRICE
	 * 		, DELETE_YN
	 * 		, SELL_YN 
	 * FROM MENU 
	 * WHERE STORE_NO = ? 
	 * ORDER BY MENU_NO ASC
	 * 
	 */
	public void deleteMenuList(String storeNo) {
		
		try {
			System.out.println("--- 메뉴 삭제 리스트 ---");
			
			//서비스
			List<MenuVo> voDeleteList = menuService.deleteMenuList(storeNo);
			
			//서비스
			if(voDeleteList.size() == 0) {
				System.out.println("삭제된 메뉴 정보가 없습니다.");
			} else {
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print(" ");	//번호 부여
				System.out.print("\t |");
				System.out.print("    메뉴 번호");
				System.out.print("    | ");
				System.out.print("    메뉴 이름");
				System.out.print("    | ");
				System.out.print(" 가격 ");
				System.out.print(" | ");
				System.out.print("삭제 여부");
				System.out.print(" | ");
				System.out.println("노출 여부");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
//				for (MenuVo menuVo : voDeleteList) {
				for (int i = 0; i < voDeleteList.size(); i++) {
					MenuVo menuVo = voDeleteList.get(i);
					System.out.print("    " + (i + 1) + "   ");
					System.out.print(" | ");
					System.out.print("\t" + menuVo.getMenuNo());
					System.out.print("\t| ");
//					System.out.print(menuVo.getStoreNo());
//					System.out.print(" | ");
					System.out.print("    " + menuVo.getMenuName());
					System.out.print("\t | ");
					System.out.print("" + menuVo.getPrice());
					System.out.print(" |   ");
					System.out.print("" + menuVo.getDeleteYn());
					System.out.print("   | ");
					System.out.println("   " + menuVo.getSellYn());
				}
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
			}
			voList = menuService.menuList(MenuMain.storeNo);
		} catch(Exception e) {
			System.out.println("삭제 리스트를 불러올 수 없습니다.");
			e.printStackTrace();
		}
			
	}
	
	
	//뒤로가기
//	public void back() {
//		
//	}
	

}
