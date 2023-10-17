package com.baedal.one.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.cart.dto.MenuInfoDto;
import com.baedal.one.cart.vo.CartListVo;
import com.baedal.one.cart.vo.CartVo;
import com.kh.app.jdbc.JDBCTemplate;

public class CartDao {

	private String query;
	
	public List<MenuInfoDto> getMenuInfo(String storeNo, Connection conn) throws Exception {
		query ="SELECT M.MENU_NO, S.STORE_NAME, M.MENU_NAME, M.PRICE, SUBSTR(S.OPENTIME, 1, 2) OPENTIME, SUBSTR(S.CLOSETIME, 1, 2) CLOSETIME FROM MENU M INNER JOIN STORE S ON M.STORE_NO = S.STORE_NO WHERE M.STORE_NO = ? AND M.DELETE_YN = 'N' AND M.SELL_YN = 'Y'";

		 PreparedStatement pstmt = conn.prepareStatement(query);
		 pstmt.setString(1, storeNo);
		 ResultSet rs = pstmt.executeQuery();
		 List<MenuInfoDto> menuList = new ArrayList<>();
		 
		 while(rs.next()) {
		 		String menuNo = rs.getString("MENU_NO");
		 		String storeName = rs.getString("STORE_NAME");
		 		String menuName = rs.getString("MENU_NAME");
		 		String price = rs.getString("PRICE");
		 		String openTime = rs.getString("OPENTIME");
		 		String closeTime = rs.getString("CLOSETIME");
		 		
		 		menuList.add(new MenuInfoDto(menuNo, storeName, menuName, price, openTime, closeTime));
		 }
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		 return menuList;
	}


	/**
	 * 내 장바구니 가져오기
	 * @param memberNo 회원 번호
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public CartVo getMyCart(String memberNo, Connection conn) throws Exception {
		query = "SELECT A.CART_NO, A.USER_NO, NVL(A.STORE_NO, 0) STORE_NO FROM CART A LEFT OUTER JOIN ORDERS B ON A.CART_NO = B.CART_NO WHERE A.USER_NO = ? AND B.ORDER_NO IS NULL";

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberNo);
		ResultSet rs = pstmt.executeQuery();

		CartVo findCart = null;
	
		if(rs.next()) {
			String cartNo = rs.getString("CART_NO");
			String userNo = rs.getString("USER_NO");
			String storeNo = rs.getString("STORE_NO");
			
			findCart = new CartVo(cartNo, userNo, storeNo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return findCart;
	}

	/**
	 * 기존 카트에 담긴 메뉴 리스트 삭제
	 * @param cartNo 장바구니 번호
	 * @param conn 
	 * @return
	 * @throws Exception
	 */
	public int deleteCartList(String cartNo, Connection conn) throws Exception {
		query = "DELETE FROM CART WHERE CART_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, cartNo);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}


	public int emptyStoreNo(String cartNo, Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 신규 장바구니 생성
	 * @param newCart 신규 장바구니 객체
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int createNewCart(CartVo newCart, Connection conn) throws Exception{
		query = "INSERT INTO CART(CART_NO, USER_NO, STORE_NO) VALUES (SEQ_CART.NEXTVAL, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, newCart.getUserNo());
		pstmt.setString(2, newCart.getStoreNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}


	public int addMenu(CartListVo newCartList, Connection conn) throws Exception {
		query = "INSERT INTO CART_LIST(CART_LIST_NO, CART_NO, MENU_NO, QUANTITY) VALUES (SEQ_CART_LIST.NEXTVAL, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, newCartList.getCartNo());
		pstmt.setString(2, newCartList.getMenuNo());
		pstmt.setString(3, newCartList.getQuantity());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}


	public int updateStoreNo(String cartNo, String storeNo, Connection conn) throws Exception{
		query = "UPDATE CART SET STORE_NO = ? WHERE CART_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, storeNo);
		pstmt.setString(2, cartNo);
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}
}
