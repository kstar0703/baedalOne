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

	public int writeReview(ReviewVo vo) throws Exception {
		
		//디비 연결
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = dao.writeReview(vo,conn);
		
		//commit or rollback
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close(conn)
		JDBCTemplate.close(conn);
		
		return result;
	}

	public List<ReviewVo> readReview(ReviewVo vo) throws Exception {
		
		// 디비연결
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		List<ReviewVo> dbVo = dao.readReview(conn,vo);
		
		// 결과집합
		if(dbVo != null) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return dbVo;
	}

}
