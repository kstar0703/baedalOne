package com.baedal.one.owner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baedal.one.cart.TestMain;
import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.owner.OwnerTestMain;
import com.baedal.one.owner.vo.OwnerVo;

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
	
	
	// 점주 로그인 
	public OwnerVo login(Connection conn, OwnerVo vo) throws Exception {
		
	
	//sql
	String sql = "SELECT * FROM OWNER WHERE OWNER_ID =? AND OWNER_PWD =? AND QUIT_YN = 'N'";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, vo.getOwnerId());
	pstmt.setString(2, vo.getOwnerPwd());
	
	
	//rs
	ResultSet rs = pstmt.executeQuery();
	
	OwnerVo ownerLogin = null;
	
	
	
	if(rs.next()) {
		String ownerNO = rs.getString("OWNER_NO");
		String ownerId = rs.getString("OWNER_ID");
		String ownerPwd = rs.getString("OWNER_PWD");
		String enrolldate = rs.getString("ENROLL_DATE");
		String updateDate = rs.getString("UPDATE_DATE");
		String quitYn = rs.getString("QUIT_YN");
		
		ownerLogin = new OwnerVo();
		ownerLogin.setOwnerNo(ownerNO);
		ownerLogin.setOwnerId(ownerId);
		ownerLogin.setOwnerPwd(ownerPwd);
		ownerLogin.setEnrollDate(enrolldate);
		ownerLogin.setUpdateDate(updateDate);
		ownerLogin.setQuitYn(quitYn);
		
	}
	
	//close
	JDBCTemplate.close(pstmt);
	JDBCTemplate.close(rs);
	
	
	return ownerLogin;
	
	}

	// 비밀번호 변경
	public int changePwd(Connection conn, String changPwd) throws Exception {
		
		//sql
		String sql = "UPDATE OWNER SET OWNER_PWD = ?, UPDATE_DATE =SYSDATE WHERE OWNER_ID =? AND QUIT_YN ='N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, changPwd);
		pstmt.setString(2, OwnerTestMain.LoginOwner.getOwnerId());
		int result = pstmt.executeUpdate();
		
		// close
		pstmt.close();
		
		return result;
		
	}

    // #회원탈퇴
	public int quit(Connection conn) throws Exception {
		
		//sql
		String sql = "UPDATE OWNER SET QUIT_YN = 'Y' WHERE OWNER_NO =? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// # OwnerTestMain.LoginOwner --> Main.LoginOwner 변수 변경
		pstmt.setString(1, OwnerTestMain.LoginOwner.getOwnerNo());
		int result = pstmt.executeUpdate();
		// close
		pstmt.close();		
		return result;
				
			
	}

    // #정보출력
	public OwnerVo showStoreInfo(Connection conn, String ownerNo) throws Exception {
		
		//sql
		String sql = "SELECT OWNER_NO,OWNER_ID,OWNER_PWD,TO_CHAR(ENROLL_DATE,'YYYY-DD-MM') AS ENROLL_DATE ,UPDATE_DATE,QUIT_YN FROM OWNER WHERE OWNER_NO =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,ownerNo);
	
		// rs
		ResultSet rs = pstmt.executeQuery();
		
		OwnerVo vo = null;
		if(rs.next()) {
			vo = new OwnerVo(rs.getString("OWNER_NO"),rs.getString("OWNER_ID"),rs.getString("OWNER_PWD"),rs.getString("ENROLL_DATE"),rs.getString("UPDATE_DATE"),rs.getString("QUIT_YN"));
		}
		
		JDBCTemplate.close(conn);
		JDBCTemplate.close(pstmt);
		
		return vo;
			
	}

}
