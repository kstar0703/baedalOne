package com.baedal.one.owner.controller;

import java.lang.reflect.Member;
import java.security.Provider.Service;
import java.util.concurrent.ExecutionException;

import com.baedal.one.Main;
import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.owner.service.OwnerService;
import com.baedal.one.owner.vo.OwnerVo;

public class OwnerController {
	
	OwnerService ownerService;
	
	public OwnerController() {
	
		ownerService = new OwnerService();
	}
	
	
	
	
	
	
	// 점주 회원가입
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
	
	public void login() {
		
		// 오너 로그인 정보
		
		try {
			System.out.println("=====로그인=====");
			
		// 데이터
		System.out.print("아이디 : ");
		String id = Main.SC.nextLine();
		System.out.print("패스워드 : ");
		String pwd = Main.SC.nextLine();
		
		OwnerVo vo = new OwnerVo();
		
		vo.setOwner(id);
		vo.setOwnerPwd(pwd);
		
		// 서비스 호출
		OwnerVo ownerLoginInfo = ownerService.login(vo);
		
		OwnerTestMain.LoginOwner = ownerLoginInfo;
		// Main.LoginOwner = ownerLoginInfo; ---> 요걸로 변경 해야함
		
		
		//결과
		if(ownerLoginInfo!=null) {
			throw new Exception();
		}
		System.out.println("로그인 성공!");
		
		
		}catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}	
			
	}
}
