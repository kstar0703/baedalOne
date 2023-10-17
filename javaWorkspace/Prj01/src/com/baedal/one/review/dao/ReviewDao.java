package com.baedal.one.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.baedal.one.review.vo.ReviewVo;
import com.kh.app.jdbc.JDBCTemplate;

public class ReviewDao {

	public int writeReview(ReviewVo vo, Connection conn) throws Exception {

		// SQL
		String sql = "INSERT INTO REVIEW (REVIEW_NO, STORE_NO, ORDER_NO, CONTENT) VALUES (SEQ_REVIEW.NEXTVAL,?,?,?)";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());
		pstmt.setString(2, vo.getOrderNo());
		pstmt.setString(4, vo.getContent());
		int result = pstmt.executeUpdate();

		// close
		JDBCTemplate.close(pstmt);

		return result;
	}

	public ArrayList<ReviewVo> readReview(Connection conn,ReviewVo vo) throws Exception {

			ArrayList<ReviewVo> dbVo = new ArrayList<ReviewVo>();

			String orderNo = vo.getOrderNo();
			String storeNo = vo.getStoreNo();
			
			// sql
			String sql = "SELECT NICKNAME, WRITE_DATE, CONTENT,MENU_NAME FROM REVIEW R JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO JOIN MEMBER MB ON O.USER_NO = MB.MEMBER_NO RIGHT JOIN CART_LIST C ON O.CART_NO = C.CART_NO JOIN MENU M ON C.MENU_NO = M.MENU_NO WHERE R.STORE_NO = ? ORDER BY REVIEW_NO , MENU_NAME";

			// pstmt
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			pstmt.setString(2, storeNo);
			// rs
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				ReviewVo readVo = new ReviewVo();

				readVo.setNickName(rs.getString("NICKNAME"));

				readVo.setWriteDate(rs.getString("WRITE_DATE"));

				readVo.setContent(rs.getString("CONTENT"));

				readVo.setMenuName(rs.getString("MENU_NAME"));

				dbVo.add(readVo);
				
			}
			
			System.out.println(dbVo.get(0));
			System.out.println(dbVo.get(1));
			System.out.println(dbVo.get(2));
			System.out.println(dbVo.get(3));
			
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
			
		return dbVo;
	}

}
