package com.baedal.one.pay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.pay.vo.PayVo;
import com.kh.app.jdbc.JDBCTemplate;

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
		String sql = "SELECT SOURCE, PAY, PAY_DATE, BALANCE FROM PAY WHERE USER_NO =?";
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

}
