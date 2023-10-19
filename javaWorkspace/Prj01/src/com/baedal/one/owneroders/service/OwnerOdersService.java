package com.baedal.one.owneroders.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.baedal.one.orders.dto.CartListDto;
import com.baedal.one.owneroders.dao.OwnerOdersDao;
import com.baedal.one.owneroders.dto.OwnerCartListDTO;
import com.baedal.one.owneroders.dto.OwnerOdersVo;
import com.kh.app.jdbc.JDBCTemplate;

public class OwnerOdersService {
	private final OwnerOdersDao dao;

	public OwnerOdersService () {
		dao = new OwnerOdersDao();
	}

	public List<OwnerOdersVo> OwnerOderList(String storeno) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		List<OwnerOdersVo> voList = dao. OwnerOderList(conn, storeno);

		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	public OwnerCartListDTO findStoreName(String storeNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		OwnerCartListDTO cartListDto = dao.findBalance(conn, storeNo);

		JDBCTemplate.close(conn);
		return cartListDto;

	}

	public List<OwnerCartListDTO> oderDetails(String nowCartNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		List<OwnerCartListDTO> dtoList = dao.oderDetails(conn,nowCartNo);
		
		JDBCTemplate.close(conn);
		return dtoList;
	}

}
