package com.baedal.one.owner.service;

import java.sql.Connection;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.owner.dao.OwnerDao;
import com.baedal.one.owner.vo.OwnerVo;

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
	
	// 오너 비밀번호 변경
	public int changePwd(String changPwd) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//Dao
		int result = dao.changePwd(conn, changPwd);
		
		// tx
		if(result==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		// close
		conn.close();
		
		return result;

		
		
		
		
	}

	public int quit() throws Exception {
		
		// conn 
		Connection conn = JDBCTemplate.getConnection();
		
		// Dao
		int result = dao.quit(conn);
		
		//tx
		if(result==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		// close
		conn.close();
		
		return result;
	}
	
	

}
