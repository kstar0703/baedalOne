package com.baedal.one.review.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.baedal.one.review.service.ReviewService;
import com.baedal.one.review.vo.ReviewVo;

public class ReviewController {
	Scanner sc;
	ReviewService service;
	
	public ReviewController() {
		service = new ReviewService();
		sc = new Scanner(System.in);
	}

	public void writeReview() {
		
		try {
			// 데이터 입력받기 
			System.out.println("\n===== 리뷰 작성 =====");
			System.out.print("\n제목을 입력하세요: ");
			String title = sc.nextLine();
			System.out.print("\n내용을 입력하세요: ");
			String content = sc.nextLine();
			
			ReviewVo vo = new ReviewVo();
			
			vo.setStoreNo("1");
			vo.setOrderNo("2");
			vo.setTitle(title);
			vo.setContent(content);
			
			// 서비스 호출
			int result = service.writeReview(vo);
			
			// 결과집합
			if(result == 1) {
				System.out.println("\n리뷰 작성이 완료 되었습니다.");
			}else {
				throw new Exception();
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n리뷰 작성중 문제가 발생했습니다.");
		}
		
	}
	
	public void readReview() {
		try {
			System.out.println("===== 리뷰 조회 =====");
			
			//데이터 입력 받기
			ReviewVo vo = new ReviewVo();
			vo.setStoreNo("1");
			
			// 서비스 호출
			ArrayList<ReviewVo> dbVo = service.readReview(vo);
			
			// 결과집합
			if(dbVo != null) {
				for(ReviewVo printVo: dbVo) {
					System.out.println(printVo.getTitle());
				}
			}else {
				throw new Exception();
			}
		}catch (Exception e) {
			
		}
		
		
	}
}
