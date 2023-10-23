package com.baedal.one.member.controller;

import java.util.Scanner;

import com.baedal.one.Main;
import com.baedal.one.member.service.MemberService;
import com.baedal.one.member.vo.MemberVo;
import com.baedal.one.pay.controller.PayController;
import com.baedal.one.review.controller.ReviewController;

public class MemberController {
	
	//멤버변수 == 필드
	private final MemberService ms;
	
	//기본 생성자
	public MemberController() {
		ms = new MemberService();
	}

	//사용자 회원가입/로그인/뒤로 가기 중 선택하기
	public void selectMember() {
		
		String num;
		do {
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.println("9.뒤로 가기");
			System.out.println("0.종료");

			
			num = Main.SC.nextLine();
			switch(num) {
			case "1" : join();break;
			case "2" : login();break;
			case "9" : return;
			case "0" : System.exit(0);

			default : System.out.println("그런 매뉴 없음");
			}
			
		}while(num.equals(8));
		
	}//selectMember

	public void join() {
		
		//서비스
		try {
			System.out.println("-----회원가입-----");
			
			//데이터 준비
			System.out.println("아이디:");
			String id = Main.SC.nextLine();
			System.out.println("비밀번호:");
			String pwd = Main.SC.nextLine();
			System.out.println("닉네임:");
			String nickName = Main.SC.nextLine();
			System.out.println("회원주소:");
			String address = Main.SC.nextLine();
			System.out.println("전화번호(010-XXXX-XXXX):");
			String phone = Main.SC.nextLine();
			System.out.println("결제 비밀번호(6자리):");
			String amountPwd = Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setNickName(nickName);
			vo.setAddress(address);
			vo.setPhone(phone);
			vo.setAmountPwd(amountPwd);
			
			//서비스 호출
			int result = ms.join(vo);
			
			//결과
			if(result != 1) {
				throw new Exception();
			}
				
			System.out.println("회원가입 성공 !");	
			
		}catch(Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
	
	}//join
		
	//로그인
	public void login() {
		
		try {
			System.out.println("-----로그인-----");
			
			//데이터 준비
			System.out.println("아이디:");
			String id = Main.SC.nextLine();
			System.out.println("비밀번호:");
			String pwd = Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			//서비스
			MemberVo dbVo = ms.login(vo);
			
			//결과
			if(dbVo == null) {
				throw new Exception();
				
			}
				System.out.println("로그인 성공");
				
				String num;
				do {
					System.out.println("1.회원탈퇴");
					System.out.println("2.로그아웃");
					System.out.println("3.회원정보 수정하기");
					System.out.println("4.내 돈 관리");
					System.out.println("5.리뷰 작성하기");
					System.out.println("6.리뷰 조회하기");
					System.out.println("7.주문내역 확인하기");
					System.out.println("8.원하는 매장 선택");
					System.out.println("9.뒤로 가기");

					ReviewController rc= new ReviewController();
					PayController pcm = new PayController();
					
					num = Main.SC.nextLine();
					switch(num) {
					case "1" : quit();break;
					case "2" : logout();break;
					case "3" : changeMemberInfo();break;
					case "4" : pcm.selectPayMenu();break;
					case "5" : rc.writeReview(num, num, num);break;
					case "6" : rc.userReview(num);break;
					case "7" : System.out.println("주문내역 확인하기 메소드 불러오기");break;
					case "8" : System.out.println("원하는 매장 선택 메소드 불러오기");break;
					case "9" : return;

					default : System.out.println("그런 매뉴 없음");
					}
					
				}while(num.equals(9));
					
		}catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}

	}
			

	//회원탈퇴
	public void quit() {
		try {
			System.out.println("-----회원탈퇴-----");
			
			//로그인여부 검사
			if(Main.loginMember == null ) {
				throw new Exception("다시 로그인을 하고 회원탈퇴를 시도하세요");
			}
			//데이터
		    String no = Main.loginMember.getMemberNo();
		    
			//서비스
			int result = ms.quit(no);
			
			//결과
			if(result != 1) {
				 throw new Exception();
			}
			System.out.println("회원 탈퇴성공");
			logout();
		}catch(Exception e) {
			System.out.println("회원 탈퇴 실패 ...");
			e.printStackTrace();
		}
		
		
	}
	
	//로그아웃
	public void logout() {
		
		System.out.println("-----로그아웃-----");
		Main.loginMember = null;
		
	}
	
	//회원정보 수정하기
	public void changeMemberInfo() {
		System.out.println("-----회원정보 수정하기-----");
		System.out.println("1.비밀번호 수정하기");
		System.out.println("2.닉네임 수정하기");
		System.out.println("3.회원주소 수정하기");
		System.out.println("4.전화번호 수정하기");
		System.out.println("5.결제 비밀번호 수정하기");
	}
			
	
	
	
}//class
