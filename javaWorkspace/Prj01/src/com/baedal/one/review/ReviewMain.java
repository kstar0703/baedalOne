package com.baedal.one.review;

import java.util.HashMap;
import java.util.Map;

import com.baedal.one.Main;
import com.baedal.one.review.controller.ReviewController;

public class ReviewMain {

	public static void main(String[] args) {
		
		ReviewController reviewController= new ReviewController();
		System.out.println("1. 리뷰작성");
		System.out.println("2. 매장 리뷰 조회");
		System.out.println("3. 회원 리뷰 조회");
		String num = Main.SC.nextLine();
		
		switch (num) {
		case "1" : reviewController.writeReview("2","2","7"); break;
		case "2" : Map<Integer, String> map = reviewController.storeReview("5"); reviewController.selectReply(map); break;
		case "3" : reviewController.userReview("4"); break;
		default: System.out.println("다시 입력 해주세요.");
		}
		
	}

}
