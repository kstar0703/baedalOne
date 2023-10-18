package com.baedal.one.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.baedal.one.member.vo.MemberVo;
import com.kh.app.jdbc.JDBCTemplate;

public class MemberDao {

	//회원가입
	public int join(Connection conn, MemberVo vo) throws Exception {
		
		//sql
		String sql = "INSERT INTO MEMBER(ID,PWD,NICKNAME,ADDRESS,PHONE,AMOUNT_PWD) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getNickName());
		pstmt.setString(4, vo.getAddress());
		pstmt.setString(5, vo.getAmountPwd());
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

}
