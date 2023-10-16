package com.baedal.one.purchase.dao;

public class PurchaseDao {

	private String query;
	
	public /*List<Menu>*/ printMenu(/*메뉴 번호*/, /*Connection 객체*/) {
		query = "SELECT FROM MENU M WHERE M.STORE_NO = ? AND M.DELETE_YN = 'N' AND M.SELL_YN = 'Y'";
		/*
		 * PreparedStatement pstmt = Connection 객체.prepareStatement(query);
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
}
