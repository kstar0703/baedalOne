package com.baedal.one.owneroders.service;

import java.sql.Connection;
import java.util.List;

import com.baedal.one.owneroders.dao.OwnerOdersDao;
import com.baedal.one.owneroders.dto.OwnerCartListDetailDTO;
import com.baedal.one.owneroders.dto.OwnerOdersVo;
import com.kh.app.jdbc.JDBCTemplate;

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

	public List<OwnerCartListDetailDTO> oderDetails(String nowCartNo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		List<OwnerCartListDetailDTO> dtoList = dao.oderDetails(conn, nowCartNo);
		// close
		JDBCTemplate.close(conn);
		return dtoList;
	}

}
