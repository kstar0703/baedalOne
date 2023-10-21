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
	public static List<MenuVo> voList ;
	//기본 생성자
	public MenuController() {
		menuService = new MenuService();
		try {
			voList = menuService.menuList(MenuMain.storeNo);
		} catch (Exception e) {
			System.out.println("전체 메뉴 리스트 불러오기 실패");
			e.printStackTrace();
		}
	}
	
	//점주 비밀번호 가져오기
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
	public void selectMenu(String storeNo) {
		
		while(true) {
			System.out.println("▼ 메뉴 선택 ");
			System.out.println("1. 메뉴 등록");
			System.out.println("2. 메뉴 수정");
			System.out.println("3. 메뉴 삭제");
			System.out.println("4. 삭제한 메뉴 복구하기");
			System.out.println("5. 메뉴 리스트 보기 (매장 전체 메뉴)");
			System.out.println("6. 삭제된 메뉴 리스트 보기");
			System.out.println("7. 뒤로가기");
			
			String num = MenuMain.SC.nextLine();
			
			switch(num) {
			case "1" : addMenu(storeNo); break;
			case "2" : editMenu(); break;
			case "3" : deleteMenu(); break;
			case "4" : rollbackDeleteMenu(); break;
			case "5" : menuList(storeNo); break;
			case "6" : deleteMenuList(storeNo); break;
			case "7" : return;
			default : System.out.println("잘못된 명령입니다.");
			}
		}
		
	}

	
	//메뉴 등록 여부 
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
	 * if문 두개
	 * 
	 */
	public void editMenu() {
		
		try {
			System.out.println("▼ 메뉴 수정 ");
			
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
				System.out.println("메뉴 정보가 수정되었습니다.");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("메뉴정보 수정 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
	}
	
	
//	/**
//	 * 메뉴 수정 (번호로)
//	 */
//	public void editMenu() {
//
//	    try {
//	        System.out.println("▼ 메뉴 수정 ");
//
//	        // 데이터
//	        System.out.print("수정할 메뉴 번호 : "); // 번호
//	        String menuNo = MenuMain.SC.nextLine();
//	        
//	        System.out.println("1. 메뉴 이름 수정");
//	        System.out.println("2. 메뉴 가격 수정");
//	        System.out.println("3. 메뉴 이름과 가격 수정");
//	        System.out.print("수정할 옵션을 선택하세요: ");
//	        int option = Integer.parseInt(MenuMain.SC.nextLine());
//
//	        String menuName = "";
//	        String price = "";
//	        
//	        if (option == 1 || option == 3) {
//	            System.out.print("수정할 메뉴 이름 : ");
//	            menuName = MenuMain.SC.nextLine();
//	        }
//
//	        if (option == 2 || option == 3) {
//	            System.out.print("수정할 메뉴 가격 : ");
//	            price = MenuMain.SC.nextLine();
//	        }
//	        
//	        price = MenuMain.SC.nextLine();
//
//	    	MenuVo menuVo = new MenuVo();
//	    	menuVo.setMenuNo(menuNo);
//	    	menuVo.setMenuName(menuName);
//
//	    	if (price != null && !price.isEmpty()) {
//	    	    menuVo.setPrice(price);
//	    	}
//
////	        MenuVo menuVo = new MenuVo();
////	        menuVo.setMenuNo(menuNo);
////	        menuVo.setMenuName(menuName);
////	        menuVo.setPrice(menuPrice);
//
//	        // 서비스 호출
//	        int result = menuService.editMenu(menuVo);
//
//	        // 결과처리
//	        if (result == 1) {
//	            System.out.println("메뉴 정보가 수정되었습니다.");
//	        } else {
//	            System.out.println("메뉴 정보 수정에 실패했습니다.");
//	        }
//	    } catch (Exception e) {
//	        System.out.println("메뉴 정보 수정 중 오류가 발생했습니다.");
//	        e.printStackTrace();
//	    }
//	}
//	
//	
//
//	// 이후 서비스 호출 등의 코드...

	
	//메뉴 삭제
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
					menuVo.setDeleteYn("N");	//deletr_yn을 N로 설정
					
					//서비스 호출
					int result = menuService.deleteMenu(menuVo);
					
					
					if(result != 1) {
						System.out.println("메뉴 삭제 실패");
					} else {
						System.out.println("메뉴 삭제가 완료되었습니다.");
					}
					
					break;
					
				case "n" : 
					MenuVo menuVo2 = new MenuVo();
					menuVo2.setMenuNo(menuNo);
					menuVo2.setDeleteYn("N");
					menuVo2.setDeleteYn("Y");
					
					int result2 = menuService.deleteMenu(menuVo2);
					
					if(result2 != 1) {
						System.out.println("메뉴 삭제 취소 실패");
					} else {
						System.out.println("메뉴 삭제 취소");
					}
					System.out.println("뒤로 이동합니다.");
					break;
					
				default : 
					System.out.println("다시 선택하세요.");
					break;
				} 
		}catch(Exception e) {
					System.out.println("메뉴 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
					e.printStackTrace();
				}
		
		return isDelete;
	}
	
	
	//삭제한 메뉴 복구
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
					menuVo.setDeleteYn("Y");	//deletr_yn을 Y로 설정
					
					//서비스 호출
					int result = menuService.rollbackDeleteMenu(menuVo);
					
					
					if(result != 1) {
						System.out.println("삭제한 메뉴 복구 실패");
					} else {
						System.out.println("삭제한 메뉴 복구가 완료되었습니다.");
					}
					
					break;
					
				case "n" : 
					MenuVo menuVo2 = new MenuVo();
					menuVo2.setMenuNo(menuNo);
					menuVo2.setDeleteYn("Y");
					menuVo2.setDeleteYn("N");
					
					int result2 = menuService.deleteMenu(menuVo2);
					
					if(result2 != 1) {
						System.out.println("삭제한 메뉴 복구 취소에 실패하였습니다.");
					} else {
						System.out.println("삭제한 메뉴의 복구가 취소됨");
					}
					System.out.println("뒤로 이동합니다.");
					break;
					
				default : 
					System.out.println("다시 선택하세요.");
					break;
				} 
		}catch(Exception e) {
					System.out.println("메뉴 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
					e.printStackTrace();
				}
		
		return isDelete;
	}
		
		
		
	//메뉴 전체 리스트
	public void menuList(String storeNo) {
		
		try {
			System.out.println("--- 메뉴 전체 목록 조회---");
			
			//서비스 호출
			
			
			//결과처리
			if(voList.size() == 0) {
				System.out.println("메뉴 정보가 없습니다.");
			} else {
				System.out.println("================================");
				System.out.print("\t메뉴 번호");
				System.out.print("\t | ");
				System.out.print("\t메뉴 이름");
				System.out.print("\t | ");
				System.out.print("\t가격");
				System.out.print("\t | ");
				System.out.print("\t삭제 여부");
				System.out.print("\t | ");
				System.out.println("\t판매 여부");
				for (MenuVo menuVo : voList) {
					System.out.print("\t" + menuVo.getMenuNo());
					System.out.print("\t | ");
					System.out.print("\t" + menuVo.getMenuName());
					System.out.print("\t | ");
					System.out.print("\t" + menuVo.getPrice());
					System.out.print("\t | ");
					System.out.print("\t" + menuVo.getDeleteYn());
					System.out.print("\t | ");
					System.out.println("\t" + menuVo.getSellYn());
				}
				System.out.println("================================");
			}
			
		}catch(Exception e) {
			System.out.println("메뉴 리스트 조회 실패");
			e.printStackTrace();
		}
		
	}
	
	
	// 메뉴 삭제 리스트
	public void deleteMenuList(String storeNo) {
		
		try {
			System.out.println("--- 메뉴 삭제 리스트 ---");
			
			//서비스
			List<MenuVo> voDeleteList = menuService.deleteMenuList(storeNo);
			
			//서비스
			if(voDeleteList.size() == 0) {
				System.out.println("삭제된 메뉴 정보가 없습니다.");
			} else {
				System.out.println("================================");
				System.out.print("메뉴 번호");
				System.out.print(" | ");
				System.out.print("매장 번호");
				System.out.print(" | ");
				System.out.print("메뉴 이름");
				System.out.print(" | ");
				System.out.println("가격");
				for (MenuVo menuVo : voDeleteList) {
					System.out.print(menuVo.getMenuNo());
					System.out.print(" | ");
					System.out.print(menuVo.getStoreNo());
					System.out.print(" | ");
					System.out.print(menuVo.getMenuName());
					System.out.print(" | ");
					System.out.println(menuVo.getPrice());
				}
				System.out.println("================================");
			}
		} catch(Exception e) {
			System.out.println("삭제 리스트를 불러올 수 없습니다.");
			e.printStackTrace();
		}
			
			
	}
	
	
//	//메뉴 삭제
//	public boolean deleteMenu() {
//	    System.out.println("▼ 메뉴 삭제 ");
//
//	    boolean isDelete = false;
//	    String answer = "";
//
//	    try {
//	        //데이터 입력
//	        System.out.print("메뉴 번호 : ");
//	        String menuNo = Main.SC.nextLine();
//
//	        System.out.println("메뉴를 삭제하시겠습니까? (Y / N) ");
//	        answer = MenuMain.SC.nextLine().toLowerCase();
//
//	        switch (answer) {
//	            case "y":
//	                MenuVo menuVo = new MenuVo();
//	                menuVo.setMenuNo(menuNo);
//	                menuVo.setDeleteYn("Y");  // delete_yn을 Y로 설정
//	                menuVo.setSellYn("N");    // sell_yn을 N로 설정
//
//	                int result = menuService.deleteMenu(menuVo);
//
//	                if (result == 1) {
//	                    System.out.println("메뉴 삭제가 완료되었습니다.");
//	                } else {
//	                    System.out.println("메뉴 삭제에 실패하였습니다.");
//	                }
//	                break;
//	                
//	            case "n":
//	                MenuVo menuVo2 = new MenuVo();
//	                menuVo2.setMenuNo(menuNo);
//	                menuVo2.setDeleteYn("N");  // delete_yn을 N로 설정
//	                menuVo2.setSellYn("Y");    // sell_yn을 Y로 설정
//
//	                int rollbackResult = menuService.deleteMenu(menuVo2);
//
//	                if (rollbackResult == 1) {
//	                    System.out.println("메뉴 삭제가 취소되었습니다.");
//	                } else {
//	                    System.out.println("메뉴 삭제 취소에 실패하였습니다.");
//	                }
//	                break;
//	                
//	            default:
//	                System.out.println("다시 시도하세요.");
//	                break;
//	        }
//	        
//	    } catch (Exception e) {
//	        System.out.println("메뉴 삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
//	        e.printStackTrace();
//	    }
//
//	    return isDelete;
//	}
	
	
//		MenuVo menuVo = new MenuVo();
//		menuVo.setMenuNo(menuNo);
		
	
	//뒤로가기
//	public void back() {
//		
//	}
	
	

}
