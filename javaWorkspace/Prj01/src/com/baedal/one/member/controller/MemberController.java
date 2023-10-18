package com.baedal.one.member.controller;

import java.util.Scanner;

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
			System.out.println("3.회원정보수정");
			System.out.println("4.회원탈퇴");
			System.out.println("5.뒤로 가기");
			
			num = sc.nextLine();
			switch(num) {
			case "1" : join();break;
			case "2" : login();break;
			case "3" : infoModify();break;
			case "4" : quit();break;
			case "5" : back();break;
			default : System.out.println("그런 매뉴 없음");
			}
			
		}while(num.equals(5));
		
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
		System.out.println("전화번호:");
		String phone = sc.nextLine();
		System.out.println("결제 비밀번호:");
		String amountPwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setNickName(nickName);
		vo.setNickName(address);
		vo.setNickName(phone);
		vo.setNickName(amountPwd);
		
		//서비스
		try {
			int result = ms.
		
	//로그인
	public void login() {
		System.out.println("-----로그인-----");

	}
			
	//회원정보수정
	public void infoModify() {
		
	}
	
	//회원탈퇴
	public void quit() {
		
	}
			
	//뒤로 가기
	public void back() {
		System.out.println("-----뒤로 가기-----");
	}
		
		
	}

			
	}
	
	
}//class
