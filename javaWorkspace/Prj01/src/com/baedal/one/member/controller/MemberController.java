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

	public void selectMember() {
		
		do {
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.println("3.뒤로 가기");
			
			String num = sc.nextLine();
			switch(num) {
			case "1" : join();break;
			case "2" : login();break;
			case "3" : back();break;
			default : System.out.println("그런 매뉴 없음");
			}
			
		}while(num == "3");
		
	}//selectMember
	

	//회원가입
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
		String nickName = sc.nextLine();
		System.out.println("전화번호:");
		String nickName = sc.nextLine();
		System.out.println("결제 비밀번호:");
		String nickName = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setNickName(nickName);
		vo.setNickName(nickName);
		vo.setNickName(nickName);
		vo.setNickName(nickName);
		vo.setNickName(nickName);
		vo.setNickName(nickName);
		
		//서비스
		try {
			int result = 
			
		}catch(Exception e) {
			
		}
		
	
	
	//로그인
	public void login() {
		// TODO Auto-generated method stub
		
	}
	
	//뒤로 가기
	public void back() {
		// TODO Auto-generated method stub
		
	}
		
		
	}
	
	
	
	
	
	
	
	
	
	
}//class
