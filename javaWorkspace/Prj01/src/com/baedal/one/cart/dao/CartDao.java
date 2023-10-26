package com.baedal.one.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.cart.dto.CheckQuantityDto;
import com.baedal.one.cart.dto.MenuInfoDto;
import com.baedal.one.cart.vo.CartListVo;
import com.baedal.one.cart.vo.CartVo;
import com.baedal.one.jdbcTemplate.JDBCTemplate;

public class CartDao {

	private String query;
	
	/**
	 * 판매 가능한 메뉴리스트 가져오는 메소드
	 * @param storeNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<MenuInfoDto> getMenuInfo(String storeNo, Connection conn) throws Exception {
		query ="SELECT M.MENU_NO, S.STORE_NAME, M.MENU_NAME, M.PRICE, SUBSTR(S.OPENTIME, 1, INSTR(S.OPENTIME, ':')-1) OPENTIME, SUBSTR(S.CLOSETIME, 1, INSTR(S.CLOSETIME, ':')-1) CLOSETIME FROM MENU M INNER JOIN STORE S ON M.STORE_NO = S.STORE_NO WHERE M.STORE_NO = ? AND M.DELETE_YN = 'N' AND M.SELL_YN = 'Y'";

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

	/**
	 * 수량 추가
	 * @param newCartList
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int addMenu(CartListVo newCartList, Connection conn) throws Exception {
		query = "INSERT INTO CART_LIST(CART_LIST_NO, CART_NO, MENU_NO, QUANTITY) VALUES (SEQ_CART_LIST.NEXTVAL, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, newCartList.getCartNo());
		pstmt.setString(2, newCartList.getMenuNo());
		pstmt.setInt(3, newCartList.getQuantity());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}

	/**
	 * 장바구니 같은 물건 담을 시 수량 갱신하기
	 * @param cartNo
	 * @param storeNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int updateStoreNo(String cartNo, String storeNo, Connection conn) throws Exception{
		query = "UPDATE CART SET STORE_NO = ? WHERE CART_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, storeNo);
		pstmt.setString(2, cartNo);
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	/**
	 * 기존의 장바구니 가져오는 메소드
	 * @param newCartList
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public CheckQuantityDto getCartList(CartListVo newCartList, Connection conn) throws Exception {
		query = "SELECT  COUNT(MENU_NO) COUNT, SUM(QUANTITY) QUANTITY FROM CART_LIST WHERE CART_NO = ? AND MENU_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, newCartList.getCartNo());
		pstmt.setString(2, newCartList.getMenuNo());
		ResultSet rs = pstmt.executeQuery();
		CheckQuantityDto dto = null;
		
		if(rs.next()) {
			int count = rs.getInt("COUNT");
			int quantity = rs.getInt("QUANTITY");
			
			dto = new CheckQuantityDto(count, quantity);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return dto;
	}


	/**
	 * 수량 추가하기
	 * @param newCartList
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public int updateQuantity(CartListVo newCartList, Connection conn) throws Exception {
		query = "UPDATE CART_LIST SET QUANTITY = ? WHERE CART_NO = ? AND MENU_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, newCartList.getQuantity());
		pstmt.setString(2, newCartList.getCartNo());
		pstmt.setString(3, newCartList.getMenuNo());
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}
}
