package com.baedal.one.review.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.review.dao.ReviewDao;
import com.baedal.one.review.vo.ReplyVo;
import com.baedal.one.review.vo.ReviewReplyVo;
import com.baedal.one.review.vo.ReviewVo;

public class ReviewService {

	ReviewDao dao;

	public ReviewService() {
		dao = new ReviewDao();
	}

	// 리뷰 작성
	public int writeReview(ReviewVo vo) throws Exception {

		// 디비 연결
		Connection conn = JDBCTemplate.getConnection();

		// dao
		int result = dao.writeReview(vo, conn);

		// commit , rollback
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
	public ArrayList<ReviewReplyVo> storeReview(ReviewVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao호출
		ArrayList<ReviewReplyVo> reRpVoList = dao.storeReview(vo, conn);

		// close
		JDBCTemplate.close(conn);

		return reRpVoList;
	}

	// 유저 모든 리뷰 조회
	public ArrayList<ReviewReplyVo> userReview(String userNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao호출
		ArrayList<ReviewReplyVo> reRpVoList = dao.userReview(conn, userNo);

		// close
		JDBCTemplate.close(conn);

		return reRpVoList;
	}

	// 리뷰삭제
	public int deleteReview(ReviewVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		int result = dao.deleteReview(vo, conn);

		// 결과처리
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close
		JDBCTemplate.close(conn);

		return result;
	}

	// 리뷰 수정
	public int updateReview(ReviewVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		int result = dao.updateReview(vo, conn);

		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		// close
		JDBCTemplate.close(conn);

		return result;
	}

	// 답변작성
	public int writeReply(ReplyVo vo) throws Exception {

		// conn생성
		Connection conn = JDBCTemplate.getConnection();

		// dao 호출
		int result = dao.writeReply(vo, conn);

		// commit / rollback
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close
		JDBCTemplate.close(conn);

		return result;
	}

	// 답변수정
	public int modifyReply(ReplyVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		int result = dao.modifyReply(vo, conn);

		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		// close
		JDBCTemplate.close(conn);

		return result;
	}

	// 답변 삭제
	public int deleteReply(ReplyVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		int result = dao.deleteReply(vo, conn);

		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		// close
		JDBCTemplate.close(conn);

		return result;
	}

}
