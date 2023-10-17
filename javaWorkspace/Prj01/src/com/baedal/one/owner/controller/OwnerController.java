package com.baedal.one.owner.controller;

import java.lang.reflect.Member;
import java.nio.channels.SelectableChannel;
import java.security.PublicKey;
import java.security.Provider.Service;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import javax.imageio.metadata.IIOMetadataFormatImpl;

import com.baedal.one.Main;
import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.owner.service.OwnerService;
import com.baedal.one.owner.vo.OwnerVo;

public class OwnerController {
	
	OwnerService ownerService;
	
	public OwnerController() {
	
		ownerService = new OwnerService();
	}
	
	public void selectMenu() {
		
		
		
		System.out.println("===== OWNER =====");
		
		System.out.println("1. 회원가입");
		System.out.print("2. 로그인 ");
		
		
		
		
		String num = Main.SC.nextLine();
		
		switch (num) {
		case "1" : join(); break;
		case "2" : login(); break;
		
		default : System.out.println("잘못누르셨습니다 다시 입력하세요");
	
		}
	}
	

	/**
	 *  #점주 회원가입#
	 */
	public void join() {
		
		// switch case escape 추가
		
		
		try {
		
		System.out.println("=====회원가입=====");
		
		// 데이터
		System.out.print("아이디 : ");
		String id = Main.SC.nextLine();
		
		System.out.print("패스워드 : ");
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
			
			System.out.println("=====로그인=====");
			
			/**
			 * 이부분 수정 해야함 (테스트 코드 사용)
			 * 
			 * if(Main.LoginOwner !=null){
			 * System.out.println("=====이미 접속중 입니다======");
			 * return;
			 * }
			 */
			if(OwnerTestMain.LoginOwner !=null) {
				System.out.println("=====이미 접속중 입니다=====");
				return;
			}
			
		// 데이터
		System.out.print("아이디 : ");
		String id = Main.SC.nextLine();
		System.out.print("패스워드 : ");
		String pwd = Main.SC.nextLine();
		
		OwnerVo vo = new OwnerVo();
		
		vo.setOwnerId(id);
		vo.setOwnerPwd(pwd);
		
		// 서비스 호출
		OwnerVo ownerLoginInfo = ownerService.login(vo);
		
		//OwnerTestMain.LoginOwner = ownerLoginInfo;
		/**
		 * 
		 *  
		 *  Main.LoginOwner = ownerLoginInfo; ---> 요걸로 변경 해야함
		 *  테스트 코드 사용
		 *
		 */
		
		
		//결과
		if(ownerLoginInfo==null) {
			throw new Exception();
		}
		System.out.println("로그인 성공!");
		
		
		}catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
	}
	
	/*
	 * #비밀번호변경#
	 * 
	 */
	
	public void changePwd() {
		System.out.println("===== 비밀번호 변경 =====");
		
		try {
		// 검사
		// *OwnerTestMain --> Main.loginOwner 변경
		if(OwnerTestMain.LoginOwner ==null) {
			throw new Exception("로그인 하고 회원변경 시도하세요");
		}
		// *OwnerTestMain --> Main.loginOwner 변경
		
		// 데이터 
		String no = OwnerTestMain.LoginOwner.getOwnerNo();
		
		
		/*
		 *  시도횟수 
		 */
		int idx =0;
		
		while (true) {
			
			if(idx ==3) {
				System.out.println("비밀번호를 3회 틀려 로그아웃 됩니다 ");
				// *OwnerTestMain --> Main.loginOwner 변경
				OwnerTestMain.LoginOwner = null;
				return;
			}
			
			System.out.print("기존 비밀번호를 입력하세요 : ");
			String originalPwd = Main.SC.nextLine();
			if(originalPwd.equals(OwnerTestMain.LoginOwner.getOwnerPwd()))  {
				// 기존 비밀번호랑 맞을시 탈출
				break;
			}
			
			
			
			{
				
			}
		}
		
		
		
		
		
		// 서비스
		int result = ownerService.changePwd(no);
		
		
		
		} catch (Exception e) {
			System.out.println("비밀번호 변경 실패...");
			e.printStackTrace();
		}
		
	
		
		
	
}
	
	
	
	
	
}
