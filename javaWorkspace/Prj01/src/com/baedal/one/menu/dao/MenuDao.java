package com.baedal.one.menu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.menu.vo.MenuVo;

import oracle.jdbc.proxy.annotation.Pre;

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
		String sql = "UPDATE MENU SET MENU_NAME = ? , PRICE = ?  WHERE MENU_NO = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, menuVo.getMenuName());
		pstmt.setString(2, menuVo.getPrice());
		pstmt.setString(3, menuVo.getMenuNo());
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
	
	//메뉴 삭제
	public int deleteMenu(Connection conn, MenuVo menuVo) throws Exception {
		
		//sql
		String sql = "UPDATE MENU SET DELETE_YN = 'Y' , SELL_YN = 'N' WHERE MENU_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, menuVo.getMenuNo());
		
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	//삭제한 메뉴 복구
	public int rollbackDeleteMenu(Connection conn, MenuVo menuVo) throws Exception {
		
		//sql
		String sql = "UPDATE MENU SET DELETE_YN = 'N' , SELL_YN = 'Y' WHERE MENU_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, menuVo.getMenuNo());
		
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
	
	//메뉴 리스트 조회
	public List<MenuVo> menuList(String storeNo, Connection conn) throws Exception {
		
		//sql
		String sql = "SELECT MENU_NO, MENU_NAME, PRICE, DELETE_YN, SELL_YN FROM MENU WHERE STORE_NO = ? ORDER BY MENU_NO ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, storeNo);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<MenuVo> voList = new ArrayList<MenuVo>();
		while(rs.next()) {
			String menuNo = rs.getString("MENU_NO");
			String menuName = rs.getString("MENU_NAME");
			String price = rs.getString("PRICE");
			String deleteYn = rs.getString("DELETE_YN");
			String sellYn = rs.getString("SELL_YN");
			
			MenuVo menuVo = new MenuVo();
			menuVo.setMenuNo(menuNo);
			menuVo.setMenuName(menuName);
			menuVo.setPrice(price);
			menuVo.setDeleteYn(deleteYn);
			menuVo.setSellYn(sellYn);
			
			voList.add(menuVo);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
		
	}


	//메뉴 삭제 리스트
	public List<MenuVo> deleteMenuList(String storeNo, Connection conn) throws Exception {
		//sql
		String sql = "SELECT ROWNUM, A.* FROM (SELECT MENU_NO, MENU_NAME, PRICE, DELETE_YN, SELL_YN FROM MENU WHERE DELETE_YN = 'Y' AND SELL_YN = 'N' AND STORE_NO = ? ORDER BY MENU_NO ASC) A";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, storeNo);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<MenuVo> voDeleteList = new ArrayList<MenuVo>();
		while(rs.next()) {
			String menuNo = rs.getString("MENU_NO");
			String menuName = rs.getString("MENU_NAME");
			String price = rs.getString("PRICE");
			String deleteYn = rs.getString("DELETE_YN");
			String sellYn = rs.getString("SELL_YN");
			
			MenuVo menuVo = new MenuVo();
			menuVo.setMenuNo(menuNo);
			menuVo.setMenuName(menuName);
			menuVo.setPrice(price);
			menuVo.setDeleteYn(deleteYn);
			menuVo.setSellYn(sellYn);
			
			voDeleteList.add(menuVo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voDeleteList;
	
	}


	
		

//	//메뉴 삭제 롤백
//	public int rollbackMenu(Connection conn, MenuVo menuVo) throws Exception {
//		
//		//sql
//		String sql = "UPDATE MENU SET DELETE_YN = N, SELL_YN = Y WHERE MENU_NO = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
////		pstmt.setString(1, menuVo.getDeleteYn());
////		pstmt.setString(2, menuVo.getSellYn());
//		pstmt.setString(3, menuVo.getMenuNo());
//		
//		int result = pstmt.executeUpdate();
//		
//		//close
//		JDBCTemplate.close(pstmt);
//		
//		return result;
//	}
	
	
	

}
