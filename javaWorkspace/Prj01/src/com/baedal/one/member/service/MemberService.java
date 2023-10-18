package com.baedal.one.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.baedal.one.member.dao.MemberDao;
import com.baedal.one.member.vo.MemberVo;
import com.kh.app.jdbc.JDBCTemplate;

public class MemberService {
	
	//필드 == 멤버변수
	private final MemberDao dao;
	
	//기본생성자
	public MemberService() {
		dao = new MemberDao();
	}
	
	//회원가입
	public int join(MemberVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO 호출
		int result = dao.join(conn, vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.rollback(conn);
		
		return result;
	}
	
	
	//로그인
	public int login(MemberVo vo) {
		return 0;
		
	}
			

}
