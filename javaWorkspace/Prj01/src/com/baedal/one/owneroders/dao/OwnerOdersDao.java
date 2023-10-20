package com.baedal.one.owneroders.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.owneroders.dto.OwnerCartListDTO;
import com.baedal.one.owneroders.dto.OwnerOdersVo;
import com.kh.app.jdbc.JDBCTemplate;

public class OwnerOdersDao {

	public List<OwnerOdersVo> OwnerOderList(Connection conn, String storeno) throws Exception {
		String sql = "SELECT O.* FROM ORDERS O JOIN CART C ON O.CART_NO = C.CART_NO WHERE C.STORE_NO =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, storeno);

		ResultSet rs = pstmt.executeQuery();

		List<OwnerOdersVo> voList = new ArrayList<OwnerOdersVo>();
		while (rs.next()) {
			String orderNO = rs.getString("ORDER_NO");
			String userNo = rs.getString("USER_NO");
			String cartNo = rs.getString("CART_NO");
			String oderDate = rs.getString("ODER_DATE");
			String totalPrice = rs.getString("TOTAL_PRICE");
			String menuName = rs.getString("MENU_NAME");
			String totalQuantity = rs.getString("TOTAL_QUANTITY");

			OwnerOdersVo vo = new OwnerOdersVo();

			vo.setOrderNo(orderNO);
			vo.setUserNo(userNo);
			vo.setCartNo(cartNo);
			vo.setOderDate(oderDate);
			vo.setTotalPrice(totalPrice);
			vo.setMenuName(menuName);
			vo.setTotalQuantity(totalQuantity);

			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return voList;
	}

	public OwnerCartListDTO findStoreName(Connection conn, String storeNo) throws SQLException {
		String sql = "SELECT S.STORE_NAME FROM CART C JOIN STORE S ON C.STORE_NO = S.STORE_NO WHERE C.STORE_NO = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, storeNo);
		ResultSet rs = pstmt.executeQuery();
		OwnerCartListDTO dto = null;
		if (rs.next()) {
			String storeName = rs.getString("STORE_NAME");

			dto = new OwnerCartListDTO();

			dto.setStoreName(storeName);

		} else {
			System.out.println("DAO 문제");
		}
		return dto;

	}

	public List<OwnerCartListDTO> oderDetails(Connection conn, String nowCartNo) throws Exception {
		String sql = "SELECT M.MENU_NAME ,M.PRICE ,C.QUANTITY FROM CART_LIST C JOIN MENU M ON C.MENU_NO = M.MENU_NO WHERE CART_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nowCartNo);

		ResultSet rs = pstmt.executeQuery();

		List<OwnerCartListDTO> dtoList = new ArrayList<OwnerCartListDTO>();
		while (rs.next()) {
			String menuName = rs.getString("MENU_NAME");
			String price = rs.getString("PRICE");
			String quantity = rs.getString("QUANTITY");
			
			OwnerCartListDTO dto = new OwnerCartListDTO();
			
			dto.setMenuName(menuName);
			dto.setPrice(price);
			dto.setQuantity(quantity);
			
			dtoList.add(dto);
		}
		JDBCTemplate.close(conn);
		JDBCTemplate.close(pstmt);
		return dtoList;
	}

	

}
