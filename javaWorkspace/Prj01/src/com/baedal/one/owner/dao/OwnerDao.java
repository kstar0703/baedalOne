package com.baedal.one.owner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import com.baedal.one.owner.vo.OwnerVo;
import com.kh.app.jdbc.JDBCTemplate;

public class OwnerDao {
	
	
	//회원가입
	public int join(Connection conn, OwnerVo vo) throws Exception {
		
	//sql
	String sql = "INSERT INTO OWNER (OWNER_NO, OWNER_ID ,OWNER_PWD) VALUES (SEQ_OWNER.NEXTVAL,?,?)";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, vo.getOwnerId());
	pstmt.setString(2, vo.getOwnerPwd());
	int result = pstmt.executeUpdate();
	
	// close
	JDBCTemplate.close(pstmt);
	return result;
	
	
	}

	public OwnerVo login(Connection conn, OwnerVo vo) throws Exception {
		
	
	//sql
	String sql = "SELECT * FROM OWNER WHERE OWNER_ID =? AND OWNER_PWD = ? AND QUIT_YN ='N'";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, vo.getOwnerId());
	pstmt.setString(2, vo.getOwnerPwd());
	
	ResultSet rs = pstmt.executeQuery();
	
	OwnerVo ownerLogin = null;
	
	if(rs.next()) {
		String ownerNO = rs.getString("OWNER_NO");
		String ownerId = rs.getString("OWNER_ID");
		String ownerPwd = rs.getString("OWNER_PWD");
		
		String quitYn = rs.getString("QUIT_YN");
		
		ownerLogin = new OwnerVo();
		
	}
	
	
	
	
	
	
	}

}
