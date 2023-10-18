package com.baedal.one.member.service;

import com.kh.app.jdbc.JDBCTemplate;

public class MemberService {
	
	//필드 == 멤버변수
	private final MemberDao dao;
	
	//기본생성자
	public MemberService() {
		dao = new MemberDao();
	}
	
	public int join(MemberVo vo) {
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
	}

}
