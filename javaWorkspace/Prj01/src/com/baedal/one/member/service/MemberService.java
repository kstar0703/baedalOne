package com.baedal.one.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.member.dao.MemberDao;
import com.baedal.one.member.vo.MemberVo;
import com.baedal.one.owner.vo.OwnerVo;


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
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	//로그인
	public MemberVo login(MemberVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		MemberVo dbVo = dao.login(conn,vo);
		
		//close
		JDBCTemplate.close(conn);
		
		return dbVo;
		
	}

	//회원탈퇴
	public int quit(String no) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = dao.quit(conn,no);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
	}
		
	//비밀번호 변경하기
	public int changePwd(MemberVo vo, String newPwd) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = dao.changePwd(conn,newPwd, vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
	}

	//닉네임 변경하기
	public int changeNickName(MemberVo vo, String newNick) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = dao.changeNickName(conn,newNick, vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;

	}
	
	//결제 비밀번호 변경하기
	public int changeAmountPwd(MemberVo vo, String newAmountPwd) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = dao.changeAmountPwd(conn,newAmountPwd, vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
}
