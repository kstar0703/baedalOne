package com.baedal.one.orders.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.baedal.one.orders.dto.CartListDto;
import com.kh.app.jdbc.JDBCTemplate;

public class OrderService {
	
	
	/**
	 * 장바구니 물품 가져오기
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<CartListDto> getCartList(String memberNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		
		return null;
	}

}
