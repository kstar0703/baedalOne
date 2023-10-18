package com.baedal.one.pay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.baedal.one.pay.vo.PayVo;
import com.kh.app.jdbc.JDBCTemplate;

import oracle.sql.converter.JdbcCharacterConverters;

public class PayDao {

	public int findBalance(Connection conn, PayVo vo) throws Exception {
		String sql = "SELECT M.MONEY FROM MEMBER M JOIN PAY P ON M.MEMBER_NO= P.USER_NO WHERE M.MEMBER_NO = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUserNo());
		ResultSet rs = pstmt.executeQuery();
		int nowMoney = 0;
		if (rs.next()) {
			nowMoney = rs.getInt("MONEY");
		} else {
			System.out.println("DAO 문제");
		}

		return nowMoney;
	}

	public List<PayVo> payList(Connection conn, String userno) throws Exception {
		String sql = "SELECT SOURCE, PAY, PAY_DATE, BALANCE FROM PAY WHERE USER_NO =? ORDER BY PAY_DATE";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userno);
		
		ResultSet rs = pstmt.executeQuery();

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

	public int chargePay(Connection conn, String userno, String afterMoney) throws SQLException {
		String sql = "UPDATE MEMBER SET MONEY = ? WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, afterMoney);
		pstmt.setString(2, userno);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

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
