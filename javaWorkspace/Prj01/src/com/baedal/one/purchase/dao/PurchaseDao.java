package com.baedal.one.purchase.dao;

import java.sql.Connection;

import com.baedal.one.purchase.vo.CartVo;

public class PurchaseDao {

	private String query;
	
	public /*List<Menu>*/ printMenu(/*메뉴 번호*/, Connection conn) {
		query = "SELECT FROM MENU M WHERE M.STORE_NO = ? AND M.DELETE_YN = 'N' AND M.SELL_YN = 'Y'";
		/*
		 * PreparedStatement pstmt = conn.prepareStatement(query);
		 * pstmt.setString(1, 메뉴 번호);
		 * ResultSet rs = pstmt.executeQuery();
		 * List<Menu> menuList = null;
		 * while(rs.next()) {
		 * 		//메뉴 객체 받기
		 * 		menuList.add(new Menu(~~~))
		 * }
		 * 
		 * return menuList;
		 */
	}


	public CartVo getMyCart(/*회원번호*/ ,Connection conn) {
		query = "SELECT A.* FROM CART A LEFT OUTER JOIN ORDERS B ON A.CART_NO = B.CART_NO WHERE A.USER_NO = ? AND B.ORDER_NO IS NULL";
	}
}
