package com.baedal.one.review;

import java.util.HashMap;

import com.baedal.one.review.controller.ReviewController;

public class ReviewMain {

	public static void main(String[] args) {
		
		ReviewController reviewController= new ReviewController();
		reviewController.userReview("1");;
		
	}

}
