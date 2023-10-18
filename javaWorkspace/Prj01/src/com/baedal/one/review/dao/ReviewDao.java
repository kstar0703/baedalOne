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

	// 매장 모든 리뷰 조회
	public List<ReviewVo> readReview(Connection conn, ReviewVo vo, int orderNo) throws Exception {

		// sql
		String sql = "SELECT DISTINCT NICKNAME, TO_CHAR(WRITE_DATE,'YYYY-MM-DD hh24:mi') AS WRITE_DATE, CONTENT,MENU_NAME, R.ORDER_NO, R.STORE_NO FROM REVIEW R JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO JOIN MEMBER MB ON O.USER_NO = MB.MEMBER_NO RIGHT JOIN CART_LIST C ON O.CART_NO = C.CART_NO JOIN MENU M ON C.MENU_NO = M.MENU_NO WHERE R.ORDER_NO = ? AND R.STORE_NO = ?";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, orderNo);
		pstmt.setString(2, vo.getStoreNo());

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 모든 리뷰의 데이터를 가져올 리스트생성
		List<ReviewVo> dbVo = new ArrayList<ReviewVo>();

		// rs실행
		while (rs.next()) {

			ReviewVo readVo = new ReviewVo();

			// 작성자
			readVo.setWriterName(rs.getString("NICKNAME"));

			// 작성일
			readVo.setWriteDate(rs.getString("WRITE_DATE"));

			// 내용
			readVo.setContent(rs.getString("CONTENT"));

			// 메뉴이름
			readVo.setMenuName(rs.getString("MENU_NAME"));

			// 리스트에 담기
			dbVo.add(readVo);

		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return dbVo;
	}

	// 매장 마지막 오더 번호 구하기
	public int endOrderNo(ReviewVo vo, Connection conn) throws Exception {

		// sql
		String sql = "SELECT COUNT(ORDER_NO) AS ORDER_NO FROM REVIEW WHERE STORE_NO = ?";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 마지막 오더번호 받을 변수생성
		int lastOrderNo = 0;

		// rs 실행
		if (rs.next()) {
			lastOrderNo = rs.getInt("ORDER_NO");
		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return lastOrderNo;
	}

	// 회원에 해당하는 오더 번호 구하기
	public List<ReviewVo> allOrderNo(ReviewVo vo, Connection conn) throws Exception {

		// sql
		String sql = "SELECT ORDER_NO  FROM REVIEW WHERE STORE_NO = ?";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 모든 오더 번호를 담을 리스트생성
		List<ReviewVo> allOrderNo = new ArrayList<ReviewVo>();

		// rs 실행
		while (rs.next()) {

			ReviewVo dbVo = new ReviewVo();

			dbVo.setAllorderNo(rs.getInt("ORDER_NO"));

			// 오더번호 리스트에 담기
			allOrderNo.add(dbVo);

		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return allOrderNo;

	}

	// 회원 마지막 오더 번호 구하기
	public int userEndOrderNo(ReviewVo vo, Connection conn) throws Exception {

		// sql
		String sql = "SELECT COUNT(R.ORDER_NO) AS ORDER_NO FROM REVIEW R  JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO  JOIN MEMBER M ON O.USER_NO = M.MEMBER_NO  WHERE M.MEMBER_NO = ?";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUserNo());

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 마지막 오더번호 받을 변수생성
		int lastOrderNo = 0;

		// rs 실행
		if (rs.next()) {
			lastOrderNo = rs.getInt("ORDER_NO");
		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return lastOrderNo;
	}

	// 회원에 해당하는 오더 번호 구하기
	public List<ReviewVo> userAllOrderNo(ReviewVo vo, Connection conn) throws Exception {

		// sql
		String sql = "SELECT R.ORDER_NO FROM REVIEW R  JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO  JOIN MEMBER M ON O.USER_NO = M.MEMBER_NO  WHERE M.MEMBER_NO = ?";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUserNo());

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 모든 오더 번호를 담을 리스트생성
		List<ReviewVo> allOrderNo = new ArrayList<ReviewVo>();

		// rs 실행
		while (rs.next()) {

			ReviewVo dbVo = new ReviewVo();

			dbVo.setAllorderNo(rs.getInt("ORDER_NO"));

			// 오더번호 리스트에 담기
			allOrderNo.add(dbVo);

		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return allOrderNo;

	}

	// 유저 모든 리뷰 조회 
	public List<ReviewVo> readUserReview(Connection conn, ReviewVo vo, int orderNo) throws Exception {

		// sql
		String sql = "SELECT DISTINCT NICKNAME , TO_CHAR(WRITE_DATE,'YYYY-MM-DD hh24:mi') AS WRITE_DATE , CONTENT ,MENU_NAME , R.ORDER_NO , R.STORE_NO  FROM REVIEW R  JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO  JOIN MEMBER MB ON O.USER_NO = MB.MEMBER_NO  RIGHT JOIN CART_LIST C ON O.CART_NO = C.CART_NO  JOIN MENU M ON C.MENU_NO = M.MENU_NO  WHERE R.ORDER_NO = ?  AND O.USER_NO = ?";

		// pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, orderNo);
		pstmt.setString(2, vo.getUserNo());

		// rs
		ResultSet rs = pstmt.executeQuery();

		// 모든 리뷰의 데이터를 가져올 리스트생성
		List<ReviewVo> dbVo = new ArrayList<ReviewVo>();

		// rs실행
		while (rs.next()) {

			ReviewVo readVo = new ReviewVo();

			// 작성자
			readVo.setWriterName(rs.getString("NICKNAME"));

			// 작성일
			readVo.setWriteDate(rs.getString("WRITE_DATE"));

			// 내용
			readVo.setContent(rs.getString("CONTENT"));

			// 메뉴이름
			readVo.setMenuName(rs.getString("MENU_NAME"));

			// 리스트에 담기
			dbVo.add(readVo);

		}

		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return dbVo;
	}
	
}
