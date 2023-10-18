package com.baedal.one.review.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baedal.one.Main;
import com.baedal.one.review.dao.ReviewDao;
import com.baedal.one.review.service.ReviewService;
import com.baedal.one.review.vo.ReviewVo;

public class ReviewController {

	ReviewService service;
	ReviewDao dao;

	public ReviewController() {
		dao = new ReviewDao();
		service = new ReviewService();
	}

	// 리뷰 작성
	public void writeReview(String storeNo) {

		try {
			// 데이터 입력받기
			System.out.println("\n===== 리뷰 작성 =====");
			System.out.print("\n내용을 입력하세요: ");
			String content = Main.SC.nextLine();
			
			ReviewVo vo = new ReviewVo();

			vo.setStoreNo(storeNo);
			vo.setContent(content);

			// 서비스 호출
			int result = service.writeReview(vo,1);

			// 결과집합
			if (result == 1) {
				System.out.println("\n리뷰 작성이 완료 되었습니다.");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n리뷰 작성중 문제가 발생했습니다.");
		}

	}

	// 매장 리뷰 조회
	public void readReview(String storeNo) {

		try {

			// 스토어번호 입력 받기
			ReviewVo vo = new ReviewVo();
			vo.setStoreNo(storeNo);

			// 매장에 해당하는 모든 오더번호 조회 
			List<ReviewVo> allOrderNo = service.allOrderNo(vo);

			//결과비교 
			if (allOrderNo == null) {
				throw new Exception();
			}

			// 매장 오더번호중 마지막 오더번호 조회 
			int endOrderNo = service.endOrderNo(vo);

			//결과비교 
			if (endOrderNo == 0) {
				throw new Exception();
			}

			// ArrayList에 담은 오더번호 가져오기 
			for (int i = 0; i < endOrderNo; i++) {

				// 매장에 해당하는 모든 오더번호 하나 순서대로 변수에 할당 
				int orderNo = allOrderNo.get(i).getAllorderNo();

				// 서비스 호출
				List<ReviewVo> voList = service.readReview(vo, orderNo);

				// 결과비교 
				if (voList == null) {
					throw new Exception();
				}

				//리뷰 꾸민거 + 닉네임,작성일자,리뷰내용,메뉴이름 출력
				System.out.println("===== 리뷰 조회 =====");
				System.out.println();
				System.out.println("꒰⑅•ᴗ•⑅꒱ " + voList.get(0).getWriterName());
				System.out.println(voList.get(0).getWriteDate());
				System.out.println("\n<내용>");
				System.out.println("--------------------------------------------------");
				System.out.println(voList.get(0).getContent());
				System.out.println("\n<메뉴>");
				System.out.println("--------------------------------------------------");
				System.out.print("(" + voList.get(0).getMenuName());

				//리뷰 한개당 주문한 모든 메뉴 조회  
				for (int a = 1; a < voList.size(); a++) {
					System.out.print(",");
					System.out.print(voList.get(a).getMenuName());
				}
				//꾸미기 
				System.out.println(")");
				System.out.println("\n=================================================");
			}

			//예외처리 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 조회 실패...");
		}

	}

	// 유저 리뷰 조회
	public void userReadReview(String userNo) {

		try {

			//회원번호 입력 받기
			ReviewVo vo = new ReviewVo();
			vo.setUserNo(userNo);

			// ArrayList에 담은 오더번호 가져오기 
			List<ReviewVo> allOrderNo = service.userAllOrderNo(vo);
			
			if (allOrderNo == null) {
				throw new Exception();
			}

			// 매장에 해당하는 모든 오더번호 하나 순서대로 변수에 할당 
			int endOrderNo = service.userEndOrderNo(vo);
			
			if (endOrderNo == 0) {
				throw new Exception();
			}
			
			for (int i = 1; i <= endOrderNo; i++) {

				int orderNo = allOrderNo.get(i - 1).getAllorderNo();

				// 서비스 호출
				List<ReviewVo> voList = service.readReview(vo, orderNo);

				// 결과비교
				if (voList == null) {
					throw new Exception();
				}

				//리뷰 꾸민거 + 닉네임,작성일자,리뷰내용,메뉴이름 출력
				System.out.println("===== 리뷰 조회 =====");
				System.out.println();
				System.out.println("꒰⑅•ᴗ•⑅꒱ " + voList.get(0).getWriterName());
				System.out.println(voList.get(0).getWriteDate());
				System.out.println("\n<내용>");
				System.out.println("--------------------------------------------------");
				System.out.println(voList.get(0).getContent());
				System.out.println("\n<메뉴>");
				System.out.println("--------------------------------------------------");
				System.out.print("(" + voList.get(0).getMenuName());

				//리뷰 한개당 주문한 모든 메뉴 조회  
				for (int a = 1; a < voList.size(); a++) {
					System.out.print(",");
					System.out.print(voList.get(a).getMenuName());
				}
				//꾸미기
				System.out.println(")");
				System.out.println("\n=================================================");
				
			}
			deleteReview();
			//예외처리 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 조회 실패...");
		}

	}

	// 리뷰 삭제 
	public void deleteReview() {

		// 삭제 할것인지 물어보기 
		System.out.println("삭제하고 싶은 리뷰가 있습니까?");
		System.out.println("1. 네. / 2. 아니요.");
		String num = Main.SC.nextLine();
		switch (num) {
		case "1" :
		case "2" : break;
		default: System.out.println("다시 입력하세요.");
			
		}
		
		
	}

}
