package com.baedal.one.orders.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.orders.dto.CartListDto;
import com.baedal.one.orders.vo.OrdersVo;
import com.baedal.one.pay.vo.PayVo;
import com.kh.app.jdbc.JDBCTemplate;

public class OrderDao {

	private String query;
	/**
	 * db에서 장바구니 리스트 가져오는 메소드
	 * @param memberNo
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public List<CartListDto> getCartList(String memberNo, Connection conn) throws Exception {
		query = "SELECT A.CART_LIST_NO , A.CART_NO , B.STORE_NAME , B.OPENTIME , B.CLOSETIME , A.MENU_NAME , A.PRICE , SUM(A.QUANTITY) QUANTITY , SUM(A.PRICE*A.QUANTITY) SUBTOTAL "
				+ "FROM ( SELECT C.CART_LIST_NO , C.CART_NO , C.QUANTITY , M.MENU_NAME , M.PRICE FROM CART_LIST C INNER JOIN MENU M ON C.MENU_NO = M.MENU_NO ) A "
				+ "INNER JOIN ( SELECT C.CART_NO , C.USER_NO , S.STORE_NAME , TO_NUMBER(SUBSTR(S.OPENTIME, 1, 2)) OPENTIME , TO_NUMBER(SUBSTR(S.CLOSETIME, 1, 2)) CLOSETIME FROM CART C INNER JOIN STORE S ON C.STORE_NO = S.STORE_NO ) B "
				+ "ON A.CART_NO = B.CART_NO LEFT OUTER JOIN ORDERS O "
				+ "ON B.CART_NO = O.CART_NO "
				+ "WHERE O.ORDER_NO IS NULL AND B.USER_NO = ? "
				+ "GROUP BY A.CART_LIST_NO, A.CART_NO, B.STORE_NAME, A.MENU_NAME, A.PRICE , B.OPENTIME, B.CLOSETIME";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberNo);
		ResultSet rs = pstmt.executeQuery();
		List<CartListDto> cartList = new ArrayList<CartListDto>();
		
		while(rs.next()) {
			String cartListNo = rs.getString("CART_LIST_NO");
			String cartNo = rs.getString("CART_NO");
			String storeName = rs.getString("STORE_NAME");
			String openTime = rs.getString("OPENTIME");
			String closeTime = rs.getString("CLOSETIME");
			String menuName = rs.getString("MENU_NAME");
			int price = rs.getInt("PRICE");
			int quantity = rs.getInt("QUANTITY");
			int subTotal = rs.getInt("SUBTOTAL");
			
			cartList.add(new CartListDto(cartListNo, cartNo, storeName, menuName, openTime, closeTime, price, quantity, subTotal));
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return cartList;
	}
	/**
	 * 회원의 잔액 조회하기
	 * @param memberNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int getMoneyById(String memberNo, Connection conn) throws Exception {
		query = "SELECT MONEY FROM MEMBER WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberNo);
		ResultSet rs = pstmt.executeQuery();
		int money = 0;
		
		if(rs.next()) 
			money = rs.getInt("MONEY");
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return money;
	}
	
	/**
	 * 주문 내역 추가
	 * @param newOrder
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int addOrders(OrdersVo newOrder, Connection conn) throws Exception {
		query = "INSERT INTO ORDERS (ORDER_NO, USER_NO, CART_NO, ORDER_DATE, TOTAL_PRICE, MENU_NAME, TOTAL_QUANTITY) VALUES (SEQ_ORDERS.NEXTVAL, ?, ?, SYSDATE, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, newOrder.getUserNo());
		pstmt.setString(2, newOrder.getCartNo());
		pstmt.setInt(3, newOrder.getTotalPrice());
		pstmt.setString(4, newOrder.getMenuName());
		pstmt.setInt(5, newOrder.getTotalQuantity());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	/**
	 * 가장 최근에 결제한 내역 조회
	 * @param memberNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public PayVo getRecentOrderByUserNo(String memberNo, Connection conn) throws Exception {
		query = "SELECT O.USER_NO , S.STORE_NAME , O.TOTAL_PRICE , TO_CHAR(O.ORDER_DATE, 'YYYY-MM-DD HH24:MM') ORDER_DATE FROM ORDERS O INNER JOIN CART C ON O.CART_NO = C.CART_NO INNER JOIN STORE S ON C.STORE_NO = S.STORE_NO WHERE O.USER_NO = ? ORDER BY O.ORDER_DATE DESC FETCH FIRST 1 ROWS ONLY";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1,memberNo);
		ResultSet rs = pstmt.executeQuery();
		PayVo addPayLog = null;
		
		if(rs.next()) {
			String userNo = rs.getString("USER_NO");
			String storeName = rs.getString("STORE_NAME");
			int totalPrice = rs.getInt("TOTAL_PRICE");
			String orderDate = rs.getString("ORDER_DATE");
			
			addPayLog = new PayVo(userNo, storeName, String.valueOf(totalPrice), orderDate);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return addPayLog;
	}
	
	/**
	 * 내역 추가
	 * @param newPayLog
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int addPay(PayVo newPayLog, Connection conn) throws Exception {
		query = "INSERT INTO PAY (PAY_NO, USER_NO, SOURCE, PAY, PAY_DATE, BALANCE) VALUES (SEQ_PAY.NEXTVAL, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, newPayLog.getUserNo());
		pstmt.setString(2, newPayLog.getSource());
		pstmt.setString(3, newPayLog.getPay());
		pstmt.setString(4, newPayLog.getPayDate());
		pstmt.setString(5, newPayLog.getBalance());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	/**
	 * 잔액 수정
	 * @param memberNo
	 * @param balance
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int updateMoney(String memberNo, String balance, Connection conn) throws Exception {
		query = "UPDATE MEMBER SET MONEY = ? WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, balance);
		pstmt.setString(2, memberNo);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	/**
	 * 수량 수정
	 * @param cartListNo
	 * @param quantity
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int updateQuantity(String cartListNo, String quantity, Connection conn) throws Exception {
		query = "UPDATE CART_LIST SET QUANTITY = ? WHERE CART_LIST_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, quantity);
		pstmt.setString(2, cartListNo);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	/**
	 * 장바구니에 담긴 상품 삭제
	 * @param cartListNo
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public int deleteCartList(String cartListNo, Connection conn) throws Exception {
		query = "DELETE FROM CART_LIST WHERE CART_LIST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, cartListNo);
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	
	public String getAmountPwd(String memberNo, Connection conn) throws Exception {
		query = "SELECT AMOUNT_PWD FROM MEMBER WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberNo);
		ResultSet rs = pstmt.executeQuery();
		String findAmountPwd = null;
		if(rs.next()) {
			findAmountPwd = new String(rs.getString("AMOUNT_PWD"));
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);	
		return findAmountPwd;
	}
	
	/**
	 * 최근에 결제한 내역 보여주기
	 * @param memberNo
	 * @return
	 * @throws Exception 
	 */
	public OrdersVo getRecentOrder(String memberNo, Connection conn) throws Exception {
		query = "SELECT ORDER_NO , TO_CHAR(ORDER_DATE, 'YYYY\"년\" MM\"월\" DD\"일\" HH24\"시\" MM\"분\"') ORDER_DATE , TOTAL_PRICE , MENU_NAME , TOTAL_QUANTITY-1 TOTAL_QUANTITY FROM ORDERS WHERE USER_NO = ? ORDER BY 2 DESC FETCH FIRST 1 ROWS ONLY";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberNo);
		ResultSet rs = pstmt.executeQuery();
		OrdersVo resultVo = null;
		if(rs.next()) {
			String orderNo = rs.getString("ORDER_NO");
			String orderDate = rs.getString("ORDER_DATE");
			int totalPrice = rs.getInt("TOTAL_PRICE");
			String menuName = rs.getString("MENU_NAME");
			int totalQuantity = rs.getInt("TOTAL_QUANTITY");

			resultVo = new OrdersVo(orderNo, null, null, orderDate, totalPrice, menuName, totalQuantity);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return resultVo;
	}

}
