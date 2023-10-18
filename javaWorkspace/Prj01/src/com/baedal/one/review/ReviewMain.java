package com.baedal.one.review;

import com.baedal.one.review.controller.ReviewController;

public class ReviewMain {

	public static void main(String[] args) {
		
		ReviewController reviewController= new ReviewController();
		reviewController.readReview("1");

	}

}
