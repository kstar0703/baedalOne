package com.baedal.one.owner.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.baedal.one.owner.dao.OwnerDao;
import com.baedal.one.owner.vo.OwnerVo;
import com.kh.app.jdbc.JDBCTemplate;

public class OwnerService {
	// 오너 dao
	OwnerDao dao;
	
	public OwnerService() {
		 dao = new OwnerDao();
	}

	public int join(OwnerVo vo) throws Exception {

		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//Dao
		int result = dao.join(conn,vo);
		
		// tx
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		conn.close();
		
		return result;	
	}
	// 오너 login
	public OwnerVo login(OwnerVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//Dao
		OwnerVo ownerVo = dao.login(conn, vo);
		
		//close
		conn.close();
		
	
		return ownerVo;
	}
	
	

}
