package com.baedal.one.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.review.vo.ReplyVo;
import com.baedal.one.review.vo.ReviewReplyVo;
import com.baedal.one.review.vo.ReviewVo;

public class ReviewDao {

	// 리뷰 작성
	public int writeReview(ReviewVo vo, Connection conn) throws Exception {

		// SQL
		String sql = "INSERT INTO REVIEW (REVIEW_NO, STORE_NO, ORDER_NO, CONTENT, USER_NO, REVIEW_RATING) VALUES (SEQ_REVIEW.NEXTVAL,?,?,?,?,?)";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());
		pstmt.setString(2, vo.getOrderNo());
		pstmt.setString(3, vo.getContent());
		pstmt.setString(4, vo.getUserNo());
		pstmt.setString(5, vo.getRating());
		int result = pstmt.executeUpdate();

		// close
		JDBCTemplate.close(pstmt);

		return result;
	}

	// 매장 모든리뷰 조회
	public ArrayList<ReviewReplyVo> storeReview(ReviewVo vo, Connection conn) throws Exception {

		// sql
		String sql = "SELECT MB.NICKNAME, RP.DELETE_YN REPLY_DELETE_YN , REVIEW_RATING , TO_CHAR(R.WRITE_DATE,'YYYY-MM-DD hh24:mi') AS WRITE_DATE  , R.CONTENT  , TO_CHAR(RP.WRITE_DATE,'YYYY-MM-DD hh24:mi') AS REPLY_WRITE_DATE , RP.CONTENT REPLY_CONTENT , RP.REPLY_NO  , M.MENU_NAME  , R.ORDER_NO  , R.STORE_NO   , R.REVIEW_NO  FROM REVIEW R   LEFT JOIN REPLY RP ON R.REVIEW_NO = RP.REVIEW_NO  JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO   JOIN MEMBER MB ON O.USER_NO = MB.MEMBER_NO   RIGHT JOIN CART_LIST C ON O.CART_NO = C.CART_NO   JOIN MENU M ON C.MENU_NO = M.MENU_NO   WHERE R.STORE_NO = ? AND R.DELETE_YN = 'N' ORDER BY R.REVIEW_NO";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 디비 정보 담을 리스트생성
		ArrayList<ReviewReplyVo> reRpVoList = new ArrayList<ReviewReplyVo>();
		
		while (rs.next()) {

			ReviewVo reviewVo = new ReviewVo();
			reviewVo.setStoreNo(rs.getString("STORE_NO"));
			reviewVo.setOrderNo(rs.getString("ORDER_NO"));
			reviewVo.setNickName(rs.getString("NICKNAME"));
			reviewVo.setWriteDate(rs.getNString("WRITE_DATE"));
			reviewVo.setContent(rs.getNString("CONTENT"));
			reviewVo.setMenuName(rs.getString("MENU_NAME"));
			reviewVo.setReviewNo(rs.getString("REVIEW_NO"));
			reviewVo.setRating(rs.getString("REVIEW_RATING"));
			
			ReplyVo replyVo = new ReplyVo();
			replyVo.setDeleteYn(rs.getString("REPLY_DELETE_YN"));
			replyVo.setReplyNo(rs.getString("REPLY_NO"));			
			replyVo.setReviewNo(rs.getString("REVIEW_NO"));
			replyVo.setContent(rs.getString("REPLY_CONTENT"));
			replyVo.setReplyWriteDate(rs.getString("REPLY_WRITE_DATE"));
			
			ReviewReplyVo reRpVo = new ReviewReplyVo();
			reRpVo.setReplyVo(replyVo);
			reRpVo.setReviewVo(reviewVo);
			
			reRpVoList.add(reRpVo);
			
		}

		return reRpVoList;
	}
	
	// 유저 모든 리뷰 조회
	public ArrayList<ReviewReplyVo> userReview(Connection conn, String userNo) throws Exception {

		// sql
		String sql = "SELECT MB.NICKNAME, RP.DELETE_YN REPLY_DELETE_YN , REVIEW_RATING, R.USER_NO, TO_CHAR(R.WRITE_DATE,'YYYY-MM-DD hh24:mi') AS WRITE_DATE  , R.CONTENT  , TO_CHAR(RP.WRITE_DATE,'YYYY-MM-DD hh24:mi') AS REPLY_WRITE_DATE , RP.CONTENT REPLY_CONTENT , RP.REPLY_NO  , M.MENU_NAME  , R.ORDER_NO  , R.STORE_NO   , R.REVIEW_NO  FROM REVIEW R   LEFT JOIN REPLY RP ON R.REVIEW_NO = RP.REVIEW_NO  JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO   JOIN MEMBER MB ON O.USER_NO = MB.MEMBER_NO   RIGHT JOIN CART_LIST C ON O.CART_NO = C.CART_NO   JOIN MENU M ON C.MENU_NO = M.MENU_NO WHERE O.USER_NO = ? AND R.DELETE_YN = 'N' ORDER BY R.REVIEW_NO";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userNo);

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 디비 정보 담을 리스트생성
		ArrayList<ReviewReplyVo> reRpVoList = new ArrayList<ReviewReplyVo>();
		
		while (rs.next()) {

			ReviewVo reviewVo = new ReviewVo();
			reviewVo.setUserNo(rs.getString("USER_NO"));
			reviewVo.setStoreNo(rs.getString("STORE_NO"));
			reviewVo.setOrderNo(rs.getString("ORDER_NO"));
			reviewVo.setNickName(rs.getString("NICKNAME"));
			reviewVo.setWriteDate(rs.getNString("WRITE_DATE"));
			reviewVo.setContent(rs.getNString("CONTENT"));
			reviewVo.setMenuName(rs.getString("MENU_NAME"));
			reviewVo.setReviewNo(rs.getString("REVIEW_NO"));
			reviewVo.setRating(rs.getString("REVIEW_RATING"));
			
			ReplyVo replyVo = new ReplyVo();
			replyVo.setDeleteYn(rs.getString("REPLY_DELETE_YN"));
			replyVo.setReplyNo(rs.getString("REPLY_NO"));			
			replyVo.setReviewNo(rs.getString("REVIEW_NO"));
			replyVo.setContent(rs.getString("REPLY_CONTENT"));
			replyVo.setReplyWriteDate(rs.getString("REPLY_WRITE_DATE"));
			
			ReviewReplyVo reRpVo = new ReviewReplyVo();
			reRpVo.setReplyVo(replyVo);
			reRpVo.setReviewVo(reviewVo);
			
			reRpVoList.add(reRpVo);
			
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return reRpVoList;
	}

	// 리뷰삭제
	public int deleteReview(ReviewVo vo, Connection conn) throws Exception {

		String sql = "UPDATE REVIEW SET DELETE_YN = 'Y' WHERE ORDER_NO = ? AND USER_NO = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getOrderNo());
		pstmt.setString(2, vo.getUserNo());
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);

		return result;
	}

	// 리뷰 수정
	public int updateReview(ReviewVo vo, Connection conn) throws Exception {

		String sql = "UPDATE REVIEW SET CONTENT = ? WHERE USER_NO = ? AND ORDER_NO = ? AND DELETE_YN = 'N'";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setString(2, vo.getUserNo());
		pstmt.setString(3, vo.getOrderNo());
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);

		return result;
	}

	// 답변 작성
	public int writeReply(ReplyVo vo, Connection conn) throws Exception {

		// sql
		String sql = "INSERT INTO REPLY (REPLY_NO,REVIEW_NO,CONTENT) VALUES (SEQ_REPLY.NEXTVAL,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getReviewNo());
		pstmt.setString(2, vo.getContent());
		int result = pstmt.executeUpdate();

		// 결과집합
		JDBCTemplate.close(pstmt);

		return result;
	}

	
	// 답변 수정 
	public int modifyReply(ReplyVo vo, Connection conn) throws Exception {
		
		String sql = "UPDATE REPLY SET CONTENT = ? WHERE REVIEW_NO = ? AND DELETE_YN = 'N'";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setString(2, vo.getReviewNo());
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);
		
		return result;
	}


	// 답변 삭제 
	public int deleteReply(ReplyVo vo, Connection conn) throws Exception {
		
		String sql = "UPDATE REPLY SET DELETE_YN = 'Y' WHERE REVIEW_NO = ? AND DELETE_YN = 'N'";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getReviewNo());
		
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);
		
		return result;
	}

}
