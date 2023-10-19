package com.baedal.one.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.baedal.one.review.vo.ReviewVo;
import com.kh.app.jdbc.JDBCTemplate;

public class ReviewDao {

	// 리뷰 작성
	public int writeReview(ReviewVo vo, Connection conn, int orderNo) throws Exception {

		// SQL
		String sql = "INSERT INTO REVIEW (REVIEW_NO, STORE_NO, ORDER_NO, CONTENT) VALUES (SEQ_REVIEW.NEXTVAL,?,?,?)";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());
		pstmt.setInt(2, orderNo);
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();

		// close
		JDBCTemplate.close(pstmt);

		return result;
	}

	// 매장 모든리뷰 조회
	public ArrayList<ReviewVo> storeReview(ReviewVo vo, Connection conn) throws Exception {

		// sql
		String sql = "SELECT NICKNAME , TO_CHAR(WRITE_DATE,'YYYY-MM-DD hh24:mi') AS WRITE_DATE , CONTENT,MENU_NAME , R.ORDER_NO, R.STORE_NO, R.REVIEW_NO  FROM REVIEW R  JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO  JOIN MEMBER MB ON O.USER_NO = MB.MEMBER_NO  RIGHT JOIN CART_LIST C ON O.CART_NO = C.CART_NO  JOIN MENU M ON C.MENU_NO = M.MENU_NO  WHERE R.STORE_NO = ? ORDER BY R.REVIEW_NO";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 디비 정보 담을 리스트생성
		ArrayList<ReviewVo> voList = new ArrayList<ReviewVo>();
		while (rs.next()) {

			ReviewVo dbVo = new ReviewVo();
			dbVo.setStoreNo(rs.getString("STORE_NO"));
			dbVo.setOrderNo(rs.getString("ORDER_NO"));
			dbVo.setNickName(rs.getString("NICKNAME"));
			dbVo.setWriteDate(rs.getNString("WRITE_DATE"));
			dbVo.setContent(rs.getNString("CONTENT"));
			dbVo.setMenuName(rs.getString("MENU_NAME"));
			dbVo.setReviewNo(rs.getString("REVIEW_NO"));
			voList.add(dbVo);
		}

		return voList;
	}


	// 유저 모든 리뷰 조회
	public ArrayList<ReviewVo> userReview(Connection conn,String userNo) throws Exception {

		// sql
		String sql = "SELECT NICKNAME , TO_CHAR(WRITE_DATE,'YYYY-MM-DD hh24:mi') AS WRITE_DATE , CONTENT ,MENU_NAME, R.ORDER_NO, R.STORE_NO, R.REVIEW_NO FROM REVIEW R  JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO  JOIN MEMBER MB ON O.USER_NO = MB.MEMBER_NO  RIGHT JOIN CART_LIST C ON O.CART_NO = C.CART_NO  JOIN MENU M ON C.MENU_NO = M.MENU_NO WHERE O.USER_NO = ? ORDER BY R.REVIEW_NO";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userNo);

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 디비 정보 담을 리스트생성
		ArrayList<ReviewVo> voList = new ArrayList<ReviewVo>();
		
		while (rs.next()) {

			ReviewVo dbVo = new ReviewVo();
			dbVo.setStoreNo(rs.getString("STORE_NO"));
			dbVo.setOrderNo(rs.getString("ORDER_NO"));
			dbVo.setNickName(rs.getString("NICKNAME"));
			dbVo.setWriteDate(rs.getNString("WRITE_DATE"));
			dbVo.setContent(rs.getNString("CONTENT"));
			dbVo.setMenuName(rs.getString("MENU_NAME"));
			dbVo.setReviewNo(rs.getString("REVIEW_NO"));
			voList.add(dbVo);
		}

		return voList;
	}


	// 리뷰삭제 
	public int deleteReview(ReviewVo vo, Connection conn) throws Exception {
		
		String sql = "UPDATE REVIEW SET DELETE_YN = 'Y' WHERE REVIEW_NO = ? AND ORDER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getReviewNo());
		pstmt.setString(2, vo.getUserNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

}
