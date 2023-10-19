package com.baedal.one.review.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
			int result = service.writeReview(vo, 7);

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

	// 모든 매장 리뷰 조회하기
	public void storeReview(String storeNo) {
		try {
			System.out.println("===== 리뷰조회 =====");
			// 매장번호 입력받기
			ReviewVo vo = new ReviewVo();

			// vo객체에 매장번호 넣기
			vo.setStoreNo(storeNo);
			System.out.println();
			// 서비스 호출하기
			ArrayList<ReviewVo> dbVo = service.storeReview(vo);
			String orderNo = dbVo.get(0).getOrderNo();
			String content = dbVo.get(0).getContent();
			// Vo 출력
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			int i = 1;
			for (ReviewVo voList : dbVo) {

				if (voList.getOrderNo().equals(orderNo)) {
					System.out.println(voList.getReviewNo());
					map.put(i, voList.getReviewNo());
					System.out.println(map.get(i));
					System.out.println(i + ".꒰⑅•ᴗ•⑅꒱ " + voList.getNickName());
					System.out.println(voList.getWriteDate());
					System.out.println();
					System.out.println(voList.getContent());
					System.out.println();
					System.out.println("< 주문메뉴 >");
					System.out.print(voList.getMenuName());
					orderNo = "";
					i++;

				} else if (!voList.getContent().equals(content)) {
					map.put(i, voList.getReviewNo());

					System.out.println();
					System.out.println("\n---------------------------------");
					System.out.println();
					System.out.println(map.get(i));
					System.out.println(i + ".꒰⑅•ᴗ•⑅꒱ " + voList.getNickName());
					System.out.println(voList.getWriteDate());
					System.out.println();
					System.out.println(voList.getContent());
					System.out.println();
					System.out.println("< 주문메뉴 >");
					System.out.print(voList.getMenuName());

					content = voList.getContent();
					i++;

				} else if (!orderNo.equals(voList.getOrderNo())) {

					System.out.print("/" + voList.getMenuName());

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 조회 실패...");
		}
	}

	// 유저 리뷰 조회
	public void userReview(String userNo) {
		try {
			System.out.println("===== 리뷰조회 =====");
			System.out.println();
			// 서비스 호출하기
			ArrayList<ReviewVo> dbVo = service.userReview(userNo);
			String orderNo = dbVo.get(0).getOrderNo();
			String content = dbVo.get(0).getContent();
			// Vo 출력
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			int i = 1;
			for (ReviewVo voList : dbVo) {

				if (voList.getOrderNo().equals(orderNo)) {
					map.put(i, voList.getReviewNo());
					System.out.println(i + ".꒰⑅•ᴗ•⑅꒱ " + voList.getNickName());
					System.out.println(voList.getWriteDate());
					System.out.println();
					System.out.println(voList.getContent());
					System.out.println();
					System.out.println("< 주문메뉴 >");
					System.out.print(voList.getMenuName());
					orderNo = "";
					i++;

				} else if (!voList.getContent().equals(content)) {
					map.put(i, voList.getReviewNo());

					System.out.println();
					System.out.println("\n---------------------------------");
					System.out.println();
					System.out.println(i + ".꒰⑅•ᴗ•⑅꒱ " + voList.getNickName());
					System.out.println(voList.getWriteDate());
					System.out.println();
					System.out.println(voList.getContent());
					System.out.println();
					System.out.println("< 주문메뉴 >");
					System.out.print(voList.getMenuName());

					content = voList.getContent();
					i++;

				} else if (!orderNo.equals(voList.getOrderNo())) {

					System.out.print("/" + voList.getMenuName());

				}
				
			}
			// 삭제 할것인지 물어보기
			System.out.println();
			System.out.println("\n---------------------------------");
			System.out.println("1. 리뷰삭제 / 2. 리뷰수정 / 3.뒤로가기");
			System.out.print("\n번호를 입력하세요: ");
			String num = Main.SC.nextLine();
			switch (num) {
			case "1": DeleteReview(map,userNo);
			case "2":
			case "3":
			default: System.out.println("다시 입력하세요."); break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 조회 실패...");
		}
	}

	/**
	 *  리뷰 삭제
	 *  
	 *  UPDATE REVIEW
	 *  	SET
	 *  		DELETE_YN = 'Y'
	 *  WHERE REVIEW_NO = ?
	 *  
	 * @param map
	 * @param userNo
	 */
	public void DeleteReview(HashMap<Integer, String> map,String userNo) {
		
		try {
			// 삭제 할것인지 물어보기
			System.out.println("\n===== 리뷰삭제 =====");
			System.out.println("\n삭제할 리뷰에 번호를 입력해주세요: ");
			String reviewNo = Main.SC.nextLine();
			
			ReviewVo vo = new ReviewVo();
			vo.setReviewNo(map.get(reviewNo));
			vo.setUserNo(userNo);
			
			// service 호출 
			int result = service.deleteReview(vo);
			
			// 결과 처
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("리뷰 삭제완료!");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 삭제실패..");
		}
		
	}

}
