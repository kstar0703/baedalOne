package com.baedal.one.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.member.vo.MemberVo;


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
		String sql = "SELECT * fROM MEMBER WHERE ID = ? AND PWD = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		MemberVo dbVo = null;
		if(rs.next()) {
			String dbMemberNo =rs.getString("MEMBER_NO");
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PwD");
			String dbNickName = rs.getString("NICKNAME");
			
			dbVo = new MemberVo();
			dbVo.setMemberNo(dbMemberNo);
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
		String sql = "UPDATE MEMBER SET QUIT_YN = 'Y', UPDATE_DATE = SYSDATE WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,no);
		int result = pstmt.executeUpdate();
		
		//rs
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	public int changePwd(Connection conn,String newPwd, MemberVo vo) throws Exception {
		
		//SQL
		String sql = "UPDATE MEMBER SET PWD = ?, UPDATE_DATE = SYSDATE  WHERE MEMBER_NO = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newPwd);
		pstmt.setString(2, vo.getMemberNo());
		pstmt.setString(3, vo.getPwd());
		int result = pstmt.executeUpdate();
		
		//rs
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	public int changeNickName(Connection conn, String newNick, MemberVo vo) throws Exception {
		//SQL
		String sql = "UPDATE MEMBER SET NICKNAME = ?, UPDATE_DATE = SYSDATE  WHERE MEMBER_NO = ? AND NICKNAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newNick);
		pstmt.setString(2, vo.getMemberNo());
		pstmt.setString(3, vo.getNickName());
		int result = pstmt.executeUpdate();
		
		//rs
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int changeAmountPwd(Connection conn, String newAmountPwd, MemberVo vo) throws Exception {
		//SQL
		String sql = "UPDATE MEMBER SET AMOUNT_PWD = ?, UPDATE_DATE = SYSDATE  WHERE MEMBER_NO = ? AND AMOUNT_PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newAmountPwd);
		pstmt.setString(2, vo.getMemberNo());
		pstmt.setString(3, vo.getAmountPwd());
		int result = pstmt.executeUpdate();
		
		//rs
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
}
