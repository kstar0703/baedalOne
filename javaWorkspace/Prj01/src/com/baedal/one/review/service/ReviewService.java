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
	public int writeReview(ReviewVo vo,int orderNo) throws Exception {

		// 디비 연결
		Connection conn = JDBCTemplate.getConnection();

		// dao
		int result = dao.writeReview(vo, conn,orderNo);

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

	// 유저 모든 리뷰 조회
	public List<ReviewVo> readUserReview(ReviewVo vo, int orderNo) throws Exception {

		// 디비연결
		Connection conn = JDBCTemplate.getConnection();

		// dao
		List<ReviewVo> dbVo = dao.readUserReview(conn, vo, orderNo);

		// close
		JDBCTemplate.close(conn);

		return dbVo;
	}

	// 매장 마지막 오더번호 구하기
	public int endOrderNo(ReviewVo vo) throws Exception {
		// 디비연결
		Connection conn = JDBCTemplate.getConnection();

		// dao
		int lastOrderNo = dao.endOrderNo(vo, conn);

		// close
		JDBCTemplate.close(conn);

		return lastOrderNo;
	}

	// 매장에 해당하는 모든 오더번호 구하기
	public List<ReviewVo> allOrderNo(ReviewVo vo) throws Exception {

		// 디비연결
		Connection conn = JDBCTemplate.getConnection();

		// dao
		List<ReviewVo> allOrderNo = dao.allOrderNo(vo, conn);

		// close
		JDBCTemplate.close(conn);

		return allOrderNo;
	}

	// 회원에 해당하는 모든 오더번호 구하기
	public List<ReviewVo> userAllOrderNo(ReviewVo vo) throws Exception {

		// 디비연결
		Connection conn = JDBCTemplate.getConnection();

		// dao
		List<ReviewVo> allOrderNo = dao.userAllOrderNo(vo, conn);

		// close
		JDBCTemplate.close(conn);

		return allOrderNo;
	}

	// 회원 마지막 오더번호 구하기
	public int userEndOrderNo(ReviewVo vo) throws Exception {
		// 디비연결
		Connection conn = JDBCTemplate.getConnection();

		// dao
		int lastOrderNo = dao.userEndOrderNo(vo, conn);

		// close
		JDBCTemplate.close(conn);

		return lastOrderNo;
	}

	// 유저 모든 리뷰 조회
	public List<ReviewVo> readReview(ReviewVo vo, int orderNo) throws Exception {

		// 디비연결
		Connection conn = JDBCTemplate.getConnection();

		// dao
		List<ReviewVo> dbVo = dao.readUserReview(conn, vo, orderNo);

		// close
		JDBCTemplate.close(conn);

		return dbVo;
	}

}
