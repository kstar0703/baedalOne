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

	// 잔액 구하기 (유저 넘버를 통해 잔액 불러오기)
	public int findBalance(String userno) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		int nowMoney = dao.findBalance(conn, userno);

		JDBCTemplate.close(conn);

		return nowMoney;
	}

	// 입출금 내역 (페이 테이블의 내용 불러오기)
	public List<PayVo> payList(String userno) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		List<PayVo> voList = dao.payList(conn, userno);

		// close
		JDBCTemplate.close(conn);
		return voList;
	}

	// 페이 충전하기 (멤버 테이블에 잔액 수정, 페이테이블 튜플 추가)
	public int chargePay(String userno, String afterMoney, PayVo vo) throws SQLException {
		Connection conn = JDBCTemplate.getConnection();
		// 멤버 테이블에 잔액 UPDATE
		int result1 = dao.chargePay(conn, userno, afterMoney);
		// 페이 테이블 튜플 추가
		int result2 = dao.chargePay(conn, userno, vo);

		// 두 작업 모두 성공할 경우 커밋 아닐 경우 리셋
		if (result1 == 1 && result2 == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);
		return result1;
	}

}
