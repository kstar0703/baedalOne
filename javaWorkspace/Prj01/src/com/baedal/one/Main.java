package com.baedal.one;

import java.util.Scanner;

import com.baedal.one.member.controller.MemberController;
import com.baedal.one.owner.controller.OwnerController;
import com.kh.app.jdbc.JDBCTemplate;

public class Main {
	
	public static final Scanner SC = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("=====원하는 유형을 선택하세요=====");
		
		//객체 생성
		MemberController mc = new MemberController();
		OwnerController sc = new OwnerController();
		
		while(true) {
			//원하는 유형 보여주기
			System.out.println("1.MEMBER");
			System.out.println("2.OWNER");
			System.out.println("9.프로그램 종료");
			
			//원하는 유형 선택
			String num = Main.SC.nextLine();
			switch(num) {
			case "1" : mc.selectMember(); break;
//			case "2" : bc.selectOwner(); break;
			case "9" : return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
		
	}//main
		
}//class
