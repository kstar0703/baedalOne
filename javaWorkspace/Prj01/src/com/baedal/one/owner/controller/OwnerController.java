package com.baedal.one.owner.controller;


import com.baedal.one.Main;
import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.owner.service.OwnerService;
import com.baedal.one.owner.vo.OwnerVo;
import com.baedal.one.store.controller.StoreController;

public class OwnerController {
	
	OwnerService ownerService;
	StoreController storeController;
	
	public OwnerController() {
	
		ownerService = new OwnerService();
		// 매장
		storeController = new StoreController();
	}
	
	/**
	 * 로그인전 메뉴 
	 */
	public void beforeLoginselectMenu() {
		
		while(true) {
		
		System.out.println("----------- 배달의 달인 (점주) -----------");	

		System.out.println("1. 회원가입");
		System.out.println("2. 로그인 ")  ;
		System.out.println("9. 뒤로가기");
		System.out.println("0. 종료");
		System.out.println("--------------------------------------");
		// 로그인 이전
		System.out.print("\u001B[36m번호를 선택하세요 : \u001B[0m"); 
		String beforeLoginChooseNum = Main.SC.nextLine();
		
		switch (beforeLoginChooseNum) {
		case "1" : join(); break;
		case "2" : login(); if(Main.loginOwner !=null) {
			afterLoginselecMenu();
		}
		break;
		case "9" :  return;
		case "0" : System.exit(0); break; 
		default : System.out.println("잘못누르셨습니다 다시 입력하세요"); break; 
		}
			}	
		}
	
	/**
	 * 로그인 이후 메뉴
	 */
	public void afterLoginselecMenu() { 
		while(true) {
			
			
		System.out.println("=====메뉴를 선택하세요=====");	
			
		System.out.println("1. 매장 관리 및 확인 ");
		System.out.println("2. 유저정보 확인");
		System.out.println("3. 비밀번호 변경");
		System.out.println("4. 로그아웃");
		System.out.println("5. 회원탈퇴 ");
		System.out.println("9. 뒤로가기");
		System.out.println("0. 종료");
		
		// 로그인 이후
		System.out.print("\u001B[36m번호를 선택하세요 : \u001B[0m");
		String afterLoginchooseNum = Main.SC.nextLine();
	
		switch (afterLoginchooseNum) {
		case "1" : storeController.selectMenuBeforeSelectStore(); break;
		case "2" : showStoreInfo(); break;
		case "3" : changePwd(); break;
		case "4" : logout(); return; 
		case "5" : quit(); return; 
		case "9" : Main.loginOwner = null; return; 
		case "0" : System.exit(0); break;
		}
		}
	}
			
		
			
	/**
	 *  #점주 회원가입#
	 */
	public void join() {
		
				
		try {
		
		System.out.println("--------------- 회원가입 ---------------");
		
		// 데이터
		System.out.print("\u001B[36m아이디 : \u001B[0m");;
		String id = Main.SC.nextLine();
		
		System.out.print("\u001B[36m패스워드 : \u001B[0m");;
		String pwd = Main.SC.nextLine();
		
		
		OwnerVo vo = new OwnerVo();
		
		vo.setOwnerId(id);
		vo.setOwnerPwd(pwd);
		
		// 멤버 서비스 호출
		int result = ownerService.join(vo);
		
		//결과 처리
		if(result !=1) {
			throw new Exception();
		} System.out.println("회원가입 성공");
		System.out.println("--------------------------------------");
	} catch (Exception e) {
		System.out.println("회원가입 실패 ...");
		e.printStackTrace();
	}
		
	}
	/*
	 *  #점주 로그인#
	 */
	public void login() {
		
		// 오너 로그인 정보
		
		try {
			
			System.out.println("--------------- 로그인 -----------------");;
			
			if(Main.loginOwner !=null) {
				throw new Exception("-------- 이미 접속중 입니다 --------");
				
			}
			
		// 데이터
		System.out.println();
		System.out.print("아이디 : ");
		String id = Main.SC.nextLine();
		System.out.print("패스워드 : ");
		String pwd = Main.SC.nextLine();
		
		OwnerVo vo = new OwnerVo();
		
		vo.setOwnerId(id);
		vo.setOwnerPwd(pwd);
		
		// 서비스 호출
		OwnerVo ownerLoginInfo = ownerService.login(vo);
		Main.loginOwner = ownerLoginInfo;
		
		//결과
		if(ownerLoginInfo==null) {
			throw new Exception();
		}
		System.out.println();
		System.out.println(ownerLoginInfo.getOwnerId() + " 님" + " 로그인 성공!");
		System.out.println("--------------------------------------");
		
		
		}catch (Exception e) {
			System.out.println("로그인 실패");
		}
	}
	
	
	/**
	 * 비밀번호 변경
	 */
	public void changePwd() {
		System.out.println("-------------- 비밀번호 변경 --------------");
		
		try {
		// 검사
		if(Main.loginOwner ==null) {
			throw new Exception("로그인 하고 회원변경 시도하세요");
		}
			
		/*
		 *  시도횟수 
		 */
		
		
		int tryLoginCnt =0;
		
		while (true) {
		
			//--> 서비스 측에서 처리 해야함 
			
			if(tryLoginCnt ==3) {
				// *OwnerTestMain --> Main.loginOwner 변경
				Main.loginOwner = null;
				throw new Exception("비밀번호가 3회 틀려 로그아웃 됩니다");
			}
			
			System.out.print("기존 비밀번호를 입력하세요 : ");
			String originalPwd = Main.SC.nextLine();
			if(originalPwd.equals(Main.loginOwner.getOwnerPwd()))  {
				break;
			}else {
				System.out.println("기존 비밀번호와 다릅니다.");
				tryLoginCnt++;
			}
		}
			String changPwd ="";
			//데이터
			while(true) {
			System.out.print("변경 할 비밀번호를 입력하세요 : ");
			changPwd = Main.SC.nextLine();
			if(changPwd.equals(Main.loginOwner.getOwnerPwd())) {
				System.out.println("동일한 비밀번호 입니다");
				System.out.println("다시 입력하세요");
			}else {
				break;
			}
			}
			
		
			// 서비스
			int result = ownerService.changePwd(changPwd);
			
			//결과
			if(result !=1) {
				throw new Exception();
			}
			
			System.out.println("비밀번호 변경 성공!");
			
			Main.loginOwner.setOwnerPwd(changPwd);
			
			
		} catch (Exception e) {
			System.out.println("비밀번호 변경 실패...");
			e.printStackTrace();
		}
		
	}
		
		/**
		 * 매장정보 출력 및 유저 정보 출력
		 */
		public void showStoreInfo() {
			System.out.println("--------------- 유저 정보 ---------------");
			
			try {
				if(Main.loginOwner == null) {
					throw new Exception();
				}
				
				String ownerNo = Main.loginOwner.getOwnerNo();
				
				OwnerVo vo = ownerService.showStoreInfo(ownerNo);
				
				System.out.println("아이디 : " + vo.getOwnerId());
				System.out.println("비밀번호 : " + vo.getOwnerPwd());
				System.out.println("가입일자 : " + vo.getEnrollDate());
				
				// 가게정보 호출 출력
				storeController.showOwnerStore();
				
			} catch (Exception e) {
					System.out.println("유저 정보 출력 실패");
					e.printStackTrace();
			}
			
		}
		
		/**
		 * 로그아웃
		 * 완료시 종료
		 */
		public void logout() {
			try {
				
			
			// # Main.loginOwner --> Main.LoginOwner 변수 변경
			if(Main.loginOwner ==null) {
				throw new Exception("로그인 상태가 아닙니다.");
			}
			
			// #Main.loginOwner --> Main.LoginOwner 변수 변경
			Main.loginOwner = null;
			System.out.println("로그아웃 성공!");
			
			}catch (Exception e) {
				System.out.println("로그아웃 실패;");
			}
		}
		
		/**
		 * 회원탈퇴
		 * 완료시 종료
		 */
		public void quit() {
			try {
				System.out.println("---------------- 회원 탈퇴 ----------------");
				if(Main.loginOwner ==null) {
					System.out.println("로그인 상태가 아닙니다");
				}
				
				// 비밀번호 입력 및 확인 
				System.out.print("탈퇴하려면 비밀번호를 입력하세요 : ");
				String pwd = Main.SC.nextLine();
				
				if(!Main.loginOwner.getOwnerPwd().equals(pwd)) {
					System.out.println("틀린 비밀번호 입니다");
				}
				
				//서비스
				int result = ownerService.quit();
				
				//결과
				if(result !=1) {
					throw new Exception();
				}
				
				System.out.println("회원탈퇴 성공ㅠ");
				Main.loginOwner = null;
				
			} catch (Exception e) {
				System.out.println("회원탈퇴 실패");			
				e.printStackTrace();
				}		
		}
}
	
	
	
	
	

