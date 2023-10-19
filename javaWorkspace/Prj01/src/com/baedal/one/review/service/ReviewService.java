package com.baedal.one.review.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.review.dao.ReviewDao;
import com.baedal.one.review.vo.ReviewVo;
import com.kh.app.jdbc.JDBCTemplate;

public class ReviewService {

	ReviewDao dao;

	public ReviewService() {
		dao = new ReviewDao();
	}

	// 리뷰 작성
	public int writeReview(ReviewVo vo, int orderNo) throws Exception {

		// 디비 연결
		Connection conn = JDBCTemplate.getConnection();

		// dao
		int result = dao.writeReview(vo, conn, orderNo);

		// commit or rollback
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close(conn)
		JDBCTemplate.close(conn);

		return result;
	}

	// 매장 모든 리뷰조회
	public ArrayList<ReviewVo> storeReview(ReviewVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao호출
		ArrayList<ReviewVo> readVo = dao.storeReview(vo, conn);

		// close
		JDBCTemplate.close(conn);

		return readVo;
	}

	// 유저 모든 리뷰 조회
	public ArrayList<ReviewVo> userReview(String userNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao호출
		ArrayList<ReviewVo> voList = dao.userReview(conn, userNo);

		// close
		JDBCTemplate.close(conn);

		return voList;
	}

	
	public int deleteReview(ReviewVo vo) throws Exception{
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = dao.deleteReview(vo,conn);
		
		//결과처리
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
	}

}
