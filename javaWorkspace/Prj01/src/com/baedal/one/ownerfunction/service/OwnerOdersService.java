package com.baedal.one.ownerfunction.service;

import java.sql.Connection;
import java.util.List;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.ownerfunction.dao.OwnerOdersDao;
import com.baedal.one.ownerfunction.dto.CartListDetailDTO;
import com.baedal.one.ownerfunction.dto.OwnerOdersVo;

public class OwnerOdersService {
	private final OwnerOdersDao dao;

	public OwnerOdersService() {
		dao = new OwnerOdersDao();
	}

	public List<OwnerOdersVo> OwnerOderList(String storeno) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		List<OwnerOdersVo> voList = dao.OwnerOderList(conn, storeno);

		// close
		JDBCTemplate.close(conn);

		return voList;
	}

	public String findStoreName(String storeNo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		String storeName = dao.findStoreName(conn, storeNo);
		// close
		JDBCTemplate.close(conn);
		return storeName;

	}

	public List<CartListDetailDTO> oderDetails(String nowCartNo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		List<CartListDetailDTO> dtoList = dao.oderDetails(conn, nowCartNo);
		// close
		JDBCTemplate.close(conn);
		return dtoList;
	}

	public List<OwnerOdersVo> userOderList(String userNo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		List<OwnerOdersVo> voList = dao.userOderList(conn, userNo);

		// close
		JDBCTemplate.close(conn);

		return voList;
	}

	public String nowStoreName(String cartNo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		String storeName = dao.nowStoreName(conn, cartNo);
		// close
		JDBCTemplate.close(conn);
		return storeName;
	}

}
