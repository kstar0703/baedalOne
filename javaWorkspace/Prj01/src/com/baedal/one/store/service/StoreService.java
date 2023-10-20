package com.baedal.one.store.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.store.dao.StoreDao;
import com.baedal.one.store.vo.StoreVo;

public class StoreService {
	
	private	StoreDao dao;
	
	public StoreService() {
		dao = new StoreDao();
	}
	
	
	/**
	 *
	 */
	public List<StoreVo> showStoreInfo(String loginOwnerNo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		List<StoreVo> list = dao.showStoreInfo(conn,loginOwnerNo);
		
		//close
		conn.close();
		
		return list;
		
		
		
		
	}

}
