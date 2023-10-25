package com.baedal.one.member.controller;

import java.util.Scanner;

import com.baedal.one.Main;
import com.baedal.one.inquiryOrder.controller.InquiryOrderController;
import com.baedal.one.member.service.MemberService;
import com.baedal.one.member.vo.MemberVo;
import com.baedal.one.orders.controller.OrderController;
import com.baedal.one.pay.controller.PayController;
import com.baedal.one.review.controller.ReviewController;
import com.baedal.one.store.controller.StoreController;

public class MemberController {
	
	//멤버변수 == 필드
	private final MemberService ms;
	private final OrderController oc;
	private String getMemberVo;
	
	//기본 생성자
	public MemberController() {
		ms = new MemberService();
		oc = new OrderController();
	}

	//사용자 회원가입/로그인/뒤로 가기 중 선택하기
	public void selectMember() {
		
		String num;
		while(true) {
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
			
		}
		
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
			System.out.println("전화번호(010-XXXX-XXXX)를 형식에 맞추어 입력해주세요:");
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
			
			Main.loginMember= dbVo;
			
			//결과
			if(dbVo == null) {
				throw new Exception();
				
			}	
			    
				System.out.println("로그인 성공");
				
				String num;
				while(true) {
					System.out.println("1.회원탈퇴");
					System.out.println("2.로그아웃");
					System.out.println("3.회원정보 수정하기");
					System.out.println("4.내 돈 관리");
					System.out.println("5.리뷰 조회하기");
					System.out.println("6.주문내역 확인하기");
					System.out.println("7.원하는 매장 선택");
					System.out.println("8.장바구니 조회하기");
					System.out.println("9.뒤로 가기");

					ReviewController rc= new ReviewController();
					PayController pcm = new PayController();
					StoreController sc = new StoreController();
					InquiryOrderController ioc = new InquiryOrderController();
					num = Main.SC.nextLine();
					switch(num) {
					case "1" : quit();return;
					case "2" : logout();return;
					case "3" : changeMemberInfo();break;
					case "4" : pcm.selectPayMenu(dbVo.getMemberNo());break;
					case "5" : rc.userReview(dbVo.getMemberNo());break;
					case "6" : ioc.showOder(dbVo.getMemberNo());;break;
					case "7" : sc.showStoreForMemberManager();break;
					case "8" : oc.printMyCartList(); break;
					case "9" : return;

					default : System.out.println("그런 매뉴 없음");
					}
					
				}
					
		}catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}

	}
			

	//회원탈퇴
	public void quit() {
		try {
			System.out.println("-----회원탈퇴-----");
			System.out.println(Main.loginMember);
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
		System.out.println("-----회원정보 변경하기-----");
		System.out.println("1.비밀번호 변경하기");
		System.out.println("2.닉네임 변경하기");
		System.out.println("3.회원주소 변경하기");
		System.out.println("4.전화번호 변경하기");
		System.out.println("5.결제 비밀번호 변경하기");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : changePwd();break;
		case "2" : changeNickName();break;
		case "3" : changeAdress();break;
		case "4" : changePhone();break;
		case "5" : changeAmountPwd();break;
		case "9" : return;

		default : System.out.println("그런 매뉴 없음");
		}
	}
		
		


	public void changePwd() {
		try {
			System.out.println("비밀번호 변경하기");
			
			//데이터
			System.out.println("기존 비번:");
			
			String oldPwd = Main.SC.nextLine();
			System.out.println("신규 비번:");
			String newPwd = Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setPwd(oldPwd);
			vo = Main.loginMember ;
			
			//서비스
			int result = ms.changePwd(vo,newPwd);
			
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("비밀번호 변경 성공!");
			
		}catch(Exception e) {
			System.out.println("비밀번호 변경 실패");
			e.printStackTrace();
		}
		
		
	}
	
	public void changeNickName() {
		
		
	}
	
	public void changeAdress() {
		
		
	}

	public void changePhone() {
		
		
	}

	public void changeAmountPwd() {
		
	}
	
	
}//class
