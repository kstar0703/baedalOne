package com.baedal.one.pay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.pay.vo.PayVo;

public class PayDao {
	// 잔액 찾기
	public int findBalance(Connection conn, String userno) throws Exception {
		// SQL
		String sql = "SELECT M.MONEY FROM MEMBER M JOIN PAY P ON M.MEMBER_NO= P.USER_NO WHERE M.MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userno);
		ResultSet rs = pstmt.executeQuery();

		int nowMoney = 0; // 유저 번호로 멤버 테이블에 접근해 저장된 잔액 담을 변수
		if (rs.next()) {
			nowMoney = rs.getInt("MONEY");
		} else {
		}

		return nowMoney;
	}

	// 출금 내역
	public List<PayVo> payList(Connection conn, String userno) throws Exception {
		String sql = "SELECT SOURCE, PAY, PAY_DATE, BALANCE FROM PAY WHERE USER_NO =? ORDER BY PAY_DATE DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userno);

		ResultSet rs = pstmt.executeQuery();

		// 지출내역 테이블을 모아서 리스트에 저장
		List<PayVo> voList = new ArrayList<PayVo>();
		while (rs.next()) {
			String source = rs.getString("SOURCE");
			String pay = rs.getString("PAY");
			String payDate = rs.getString("PAY_DATE");
			String balance = rs.getString("BALANCE");

			PayVo vo = new PayVo();
			vo.setSource(source);
			vo.setPay(pay);
			vo.setPayDate(payDate);
			vo.setBalance(balance);

			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return voList;
	}

	// 페이충전(멤버테이블 안에 잔액 저장)
	public int chargePay(Connection conn, String userno, String afterMoney) throws SQLException {
		String sql = "UPDATE MEMBER SET MONEY = ? WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, afterMoney);
		pstmt.setString(2, userno);
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);

		return result;
	}

	// 페이충전(입출금내역 테이블 생성)
	public int chargePay(Connection conn, String userno, PayVo vo) throws SQLException {
		String sql = " INSERT INTO PAY(PAY_NO,USER_NO,PAY,PAY_DATE,BALANCE) VALUES (SEQ_PAY.NEXTVAL,?, ?, SYSDATE, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUserNo());
		pstmt.setString(2, vo.getPay());
		pstmt.setString(3, vo.getBalance());

		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);
		return result;
	}

}
