package com.baedal.one.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.review.vo.ReviewVo;
import com.kh.app.jdbc.JDBCTemplate;

public class ReviewDao {

	public int writeReview(ReviewVo vo, Connection conn) throws Exception {

		// SQL
		String sql = "INSERT INTO REVIEW (REVIEW_NO, STORE_NO, ORDER_NO, CONTENT) VALUES (SEQ_REVIEW.NEXTVAL,?,?,?)";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());
		pstmt.setString(2, "2");
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();

		// close
		JDBCTemplate.close(pstmt);

		return result;
	}

	public List<ReviewVo> readReview(Connection conn,ReviewVo vo) throws Exception {
			
			// sql
			String sql = "SELECT NICKNAME , R.CONTENT , MENU_NAME , TO_CHAR(R.WRITE_DATE,'YYYY-MM-DD hh24:mi') WRITE_DATE , TOTAL_QUANTITY  FROM REVIEW R JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO JOIN MEMBER M ON M.MEMBER_NO = O.USER_NO WHERE R.STORE_NO = ?";

			// pstmt
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getStoreNo());
			
			// rs
			ResultSet rs = pstmt.executeQuery();

			List<ReviewVo> dbVo = new ArrayList<ReviewVo>();
			while(rs.next()) {

				ReviewVo readVo = new ReviewVo();

				readVo.setNickName(rs.getString("NICKNAME"));

				readVo.setWriteDate(rs.getString("WRITE_DATE"));

				readVo.setContent(rs.getString("CONTENT"));

				readVo.setMenuName(rs.getString("MENU_NAME"));
				
				readVo.setTotalQuantity(rs.getString("TOTAL_QUANTITY"));

				dbVo.add(readVo);
				
			}
			
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
			
		return dbVo;
	}

}
