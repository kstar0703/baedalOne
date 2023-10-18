package com.baedal.one.pay.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.baedal.one.pay.dao.PayDao;
import com.baedal.one.pay.vo.PayVo;
import com.kh.app.jdbc.JDBCTemplate;

public class PayService {
	private final PayDao dao;

	public PayService() {
		dao = new PayDao();
	}

	public int findBalance(PayVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		int nowMoney = dao.findBalance(conn, vo);

		JDBCTemplate.close(conn);

		return nowMoney;
	}

	public List<PayVo> payList(String userno) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		List<PayVo> voList = dao.payList(conn, userno);

		// close
		JDBCTemplate.close(conn);
		return voList;
	}

	public int chargePay(String userno, String afterMoney) throws SQLException {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.chargePay(conn,userno, afterMoney);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

}
