package com.baedal.one.member.controller;

import java.util.Scanner;

import com.baedal.one.Main;
import com.baedal.one.member.service.MemberService;
import com.baedal.one.member.vo.MemberVo;

public class MemberController {
	
	//멤버변수 == 필드
	private final Scanner sc;
	private final MemberService ms;
	
	//기본 생성자
	public MemberController() {
		sc = new  Scanner(System.in);
		ms = new MemberService();
	}

	//사용자 회원가입/로그인/뒤로 가기 중 선택하기
	public void selectMember() {
		
		String num;
		do {
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.println("3.회원탈퇴");
			System.out.println("4.로그아웃");
			System.out.println("5.주문내역조회하기");
			System.out.println("6.뒤로 가기");
			
			num = sc.nextLine();
			switch(num) {
			case "1" : join();break;
			case "2" : login();break;
			case "3" : quit();break;
			case "4" : logout();break;
//			case "5" : ();break;
			case "6" : back();break;
			default : System.out.println("그런 매뉴 없음");
			}
			
		}while(num.equals(6));
		
	}//selectMember

	public void join() {
		System.out.println("-----회원가입-----");
		
		//데이터 준비
		System.out.println("아이디:");
		String id = sc.nextLine();
		System.out.println("비밀번호:");
		String pwd = sc.nextLine();
		System.out.println("닉네임:");
		String nickName = sc.nextLine();
		System.out.println("회원주소:");
		String address = sc.nextLine();
		System.out.println("전화번호(010-1234-5678):");
		String phone = sc.nextLine();
		System.out.println("결제 비밀번호(6자리):");
		String amountPwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setNickName(nickName);
		vo.setAddress(address);
		vo.setPhone(phone);
		vo.setAmountPwd(amountPwd);
		
		//서비스
		try {
			int result = ms.join(vo);
			
			//결과
			if(result == 1) {
				System.out.println("회원가입 성공");
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
	
	}//join
		
	//로그인
	public void login() {
		System.out.println("-----로그인-----");
		
		//데이터 준비
		System.out.println("아이디:");
		String id = sc.nextLine();
		System.out.println("비밀번호:");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		//서비스
		MemberVo dbVo;
		try {
			
			dbVo = ms.login(vo);
			
			//결과
			if(dbVo != null) {
				System.out.println("로그인 성공");
				System.out.println("로그인 구매자 정보: "+ dbVo);
				
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}

	}
			
	//회원탈퇴
	public void quit() {
		System.out.println("-----회원탈퇴-----");
		
		//데이터 준비
		System.out.println("회원탈퇴를 하기위해 기존의 아이디와 비밀번호를 입력하세요.");
		System.out.println("아이디:");
		String id = sc.nextLine();
		System.out.println("비밀번호:");
		String pwd = sc.nextLine();
		
		//로그인여부 검사
//		if(Main.loginMember == null) {
//			throw new Exception("로그인을 하고 회원탈퇴를 시도하세요");
//		}
		//데이터
		
		//서비스
		
		//결과
		
	}
	
	//로그아웃
	public void logout() {
		
	}
	
	//주문내역조회하기
//	public void () {
		
		
//	}
	
			
	//뒤로 가기
	public void back() {
		System.out.println("-----뒤로 가기-----");

	}
	
	
}//class
