package com.baedal.one.menu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.menu.vo.MenuVo;

public class MenuDao {
	
	//점주 비밀번호 가져오기
	public String findPwd(Connection conn, String ownerno) throws Exception {
		
		String sql = "SELECT OWNER_PWD FROM OWNER WHERE OWNER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ownerno);
		ResultSet rs = pstmt.executeQuery();
		
		String nowPwd = null;
		if (rs.next()) {
			nowPwd = rs.getString("OWNER_PWD");
		} else {
			System.out.println("비밀번호를 불러오지 못했습니다.");
		}

		return nowPwd;
		
	}
	
	//메뉴 등록
	public int addMenu(Connection conn, MenuVo menuVo) throws Exception {
		
		//sql
		String sql = "INSERT INTO MENU(MENU_NO,STORE_NO, MENU_NAME, PRICE) VALUES(SEQ_MENU.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, menuVo.getStoreNo());
		pstmt.setString(2, menuVo.getMenuName());
		pstmt.setString(3, menuVo.getPrice());
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
	//메뉴 수정
	public int editMenu(Connection conn, MenuVo menuVo) throws Exception {
		
		//sql
		String sql = "UPDATE MENU SET MENU_NO = ? WHERE MENU_NAME = ? AND PRICE = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, menuVo.getMenuNo());
		pstmt.setString(2, menuVo.getMenuName());
		pstmt.setString(3, menuVo.getPrice());
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
	
	//메뉴 삭제
	public int deleteMenu(Connection conn, MenuVo menuVo) throws Exception {
		
		//sql
		String sql = "UPDATE MENU SET DELETE_TN = 'Y' WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, menuVo.getDeleteYn());
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	
	

}
