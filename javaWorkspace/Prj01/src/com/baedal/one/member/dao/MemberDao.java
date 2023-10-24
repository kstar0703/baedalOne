package com.baedal.one.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baedal.one.member.vo.MemberVo;
import com.kh.app.jdbc.JDBCTemplate;

public class MemberDao {

	//회원가입
	public int join(Connection conn, MemberVo vo) throws Exception {
		
		//sql
		String sql = "INSERT INTO MEMBER(MEMBER_NO,ID,PWD,NICKNAME,ADDRESS,PHONE,AMOUNT_PWD) VALUES (SEQ_MEMBER.NEXTVAL,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getNickName());
		pstmt.setString(4, vo.getAddress());
		pstmt.setString(5, vo.getPhone());
		pstmt.setString(6, vo.getAmountPwd());
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	//로그인
	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		//sql
		String sql = "SELECT * fROM MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		MemberVo dbVo = null;
		if(rs.next()) {
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PwD");
			String dbNickName = rs.getString("NICKNAME");
			
			dbVo = new MemberVo();
			dbVo.setId(dbId);
			dbVo.setPwd(dbPwd);
			dbVo.setNickName(dbNickName);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return dbVo;
	}
	
	//회원탈퇴하기
	public int quit(Connection conn, String no) throws Exception {
		
		//SQL
		String sql = "UPDATE MEMBER SET DEL_YN = 'Y', MODIFY_DATE = SYSDATE WHERE MEMBER_NO = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,no);
		
		//rs
		
		return 0;
	}

	
	
}
