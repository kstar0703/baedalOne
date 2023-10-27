package com.baedal.one.ownerfunction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.ownerfunction.dto.CartListDetailDTO;
import com.baedal.one.ownerfunction.dto.OwnerOdersVo;

public class OwnerOdersDao {

	public List<OwnerOdersVo> userOderList(Connection conn, String userNo) throws Exception {
		String sql = "SELECT * FROM ORDERS WHERE USER_NO =? ORDER BY ORDER_DATE DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userNo);
		ResultSet rs = pstmt.executeQuery();

		List<OwnerOdersVo> voList = new ArrayList<OwnerOdersVo>();

		while (rs.next()) {
			// 칼럼 정보 저장
			String orderNO = rs.getString("ORDER_NO");
			String cartNo = rs.getString("CART_NO");
			String oderDate = rs.getString("ORDER_DATE");
			String totalPrice = rs.getString("TOTAL_PRICE");
			String menuName = rs.getString("MENU_NAME");
			String totalQuantity = rs.getString("TOTAL_QUANTITY");

			// 간편 내역 객체에 정보저장
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
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return voList;
	}

	public List<OwnerOdersVo> OwnerOderList(Connection conn, String storeno) throws Exception {
		// sql
		String sql = "SELECT O.* FROM ORDERS O JOIN CART C ON O.CART_NO = C.CART_NO WHERE C.STORE_NO =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, storeno);
		ResultSet rs = pstmt.executeQuery();

		// 간편 내역 객체를 담을 리스트
		List<OwnerOdersVo> voList = new ArrayList<OwnerOdersVo>();

		while (rs.next()) {
			// 칼럼 정보 저장
			String orderNO = rs.getString("ORDER_NO");
			String userNo = rs.getString("USER_NO");
			String cartNo = rs.getString("CART_NO");
			String oderDate = rs.getString("ORDER_DATE");
			String totalPrice = rs.getString("TOTAL_PRICE");
			String menuName = rs.getString("MENU_NAME");
			String totalQuantity = rs.getString("TOTAL_QUANTITY");

			// 간편 내역 객체에 정보저장
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
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return voList;
	}

	public String findStoreName(Connection conn, String storeNo) throws SQLException {
		// sql
		String sql = "SELECT S.STORE_NAME FROM CART C JOIN STORE S ON C.STORE_NO = S.STORE_NO WHERE C.STORE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, storeNo);
		ResultSet rs = pstmt.executeQuery();
		String storeName = null;

		// 1번째 행 칼럼값 저장
		if (rs.next()) {
			storeName = rs.getString("STORE_NAME");
		} else {
		}
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);

		return storeName;

	}

	public List<CartListDetailDTO> oderDetails(Connection conn, String nowCartNo) throws Exception {
		// sql
		String sql = "SELECT M.MENU_NAME ,M.PRICE ,C.QUANTITY FROM CART_LIST C JOIN MENU M ON C.MENU_NO = M.MENU_NO WHERE CART_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nowCartNo);
		ResultSet rs = pstmt.executeQuery();

		// 상세 보기 객체를 담을 리스트
		List<CartListDetailDTO> dtoList = new ArrayList<CartListDetailDTO>();

		while (rs.next()) {
			// 칼럼 정보 저장
			String menuName = rs.getString("MENU_NAME");
			String price = rs.getString("PRICE");
			String quantity = rs.getString("QUANTITY");

			// 상세 내역 객체에 정보 담기
			CartListDetailDTO dto = new CartListDetailDTO();
			dto.setMenuName(menuName);
			dto.setPrice(price);
			dto.setQuantity(quantity);

			dtoList.add(dto);
		}
		JDBCTemplate.close(conn);
		JDBCTemplate.close(pstmt);
		return dtoList;
	}

	public String nowStoreName(Connection conn, String cartNo) throws SQLException {
		// sql
		String sql = "SELECT S.STORE_NAME FROM STORE S JOIN CART C ON S.STORE_NO = C.STORE_NO WHERE S.STORE_NO=(SELECT C.STORE_NO FROM ORDERS O JOIN CART C ON O.CART_NO = C.CART_NO WHERE O.CART_NO =?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cartNo);
		ResultSet rs = pstmt.executeQuery();
		String storeName = null;

		// 1번째 행 칼럼값 저장
		if (rs.next()) {
			storeName = rs.getString("STORE_NAME");
		} else {
			System.out.println("DAO 문제");
		}
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);

		return storeName;
	}

	public String nowStoreNo(Connection conn, String cartNo) throws SQLException {
		// sql
		String sql = "SELECT S.STORE_NO FROM STORE S JOIN CART C ON S.STORE_NO = C.STORE_NO WHERE S.STORE_NO=(SELECT C.STORE_NO FROM ORDERS O JOIN CART C ON O.CART_NO = C.CART_NO WHERE O.CART_NO =?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cartNo);
		ResultSet rs = pstmt.executeQuery();
		String storeNo = null;

		// 1번째 행 칼럼값 저장
		if (rs.next()) {
			storeNo = rs.getString("STORE_NO");
		} else {
			System.out.println("DAO 문제");
		}
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);

		return storeNo;
	}

}
