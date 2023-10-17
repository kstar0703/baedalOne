package com.baedal.one.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.baedal.one.review.vo.ReviewVo;
import com.kh.app.jdbc.JDBCTemplate;

public class ReviewDao {

	public int writeReview(ReviewVo vo, Connection conn) throws Exception {
		
		//SQL
		String sql = "INSERT INTO REVIEW (REVIEW_NO, STORE_NO, ORDER_NO, REVIEW_TITLE, CONTENT) VALUES (SEQ_REVIEW.NEXTVAL,?,?,?,?)";
		
		//pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());
		pstmt.setString(2, vo.getOrderNo());
		pstmt.setString(3, vo.getTitle());
		pstmt.setString(4, vo.getContent());
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public ArrayList<ReviewVo> readReview(Connection conn,ReviewVo vo) throws Exception {
		
		// sql
		String sql = "";
		
		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		//rs
		ResultSet rs = pstmt.executeQuery();
		ArrayList<ReviewVo> dbVo = new ArrayList<ReviewVo>();
		
		while(rs.next()) {
			
			vo.setReviewNo(rs.getString("REVIEW_NO"));
			vo.setStoreNo(rs.getString("STORE_NO")); 
			vo.setOrderNo(rs.getString("ORDER_NO"));
			vo.setTitle(rs.getString("REVIEW_TITLE"));
			vo.setContent(rs.getString("CONTENT")); 
			vo.setWriteDate(rs.getString("WRITE_DATE"));
			vo.setDeleteYn(rs.getString("DELETE_YN"));
			
			dbVo.add(vo);
			
		}
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return dbVo;
		
	}

}
