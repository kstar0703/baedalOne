package com.baedal.one.cart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.baedal.one.cart.dao.CartDao;
import com.baedal.one.cart.dto.CheckQuantityDto;
import com.baedal.one.cart.dto.MenuInfoDto;
import com.baedal.one.cart.vo.CartListVo;
import com.baedal.one.cart.vo.CartVo;
import com.kh.app.jdbc.JDBCTemplate;

public class CartService {

	private final CartDao cartDao;
	
	public CartService() {
		cartDao = new CartDao();
	}
	
	/**
	 * 메뉴 및 매장 정보 가져오기
	 * @param storeNo
	 * @return
	 * @throws Exception
	 */
	public List<MenuInfoDto> getMenuInfo(String storeNo) throws Exception {
		//Connection
		Connection conn = JDBCTemplate.getConnection();
		List<MenuInfoDto> menuList = cartDao.getMenuInfo(storeNo, conn);
		
		//close
		 JDBCTemplate.close(conn);
		return menuList;
	}
	
	public CartVo getMyCart(String memberNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		CartVo findCart = cartDao.getMyCart(memberNo, conn);
		JDBCTemplate.close(conn);
		return findCart;
	}
	
	/**
	 * 기존 장바구니 삭제
	 * @param cartNo 장바구니 번호
	 * @return result
	 * @throws Exception
	 */
	public int deleteCartList(String cartNo) throws Exception {
		//Connection
		Connection conn = JDBCTemplate.getConnection();
		
		int result = cartDao.deleteCartList(cartNo, conn);
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		return result;
	}

	/**
	 * 신규 장바구니 생성
	 * @param newCart
	 * @return
	 * @throws Exception
	 */
	public int createNewCart(CartVo newCart) throws Exception {
		
		//Connection
		Connection conn = JDBCTemplate.getConnection();
		
		int result = cartDao.createNewCart(newCart, conn);
		
		if(result == 1) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		return result;
	}

	/**
	 * 장바구니에 메뉴 추가
	 * @param newCartList
	 * @return
	 * @throws Exception
	 */
	public int addMenu(CartListVo newCartList) throws Exception {
		
		//Connection
		Connection conn = JDBCTemplate.getConnection();
		
		//이미 장바구니에 같은 품목이 들어가 있는 지
		CheckQuantityDto resultDto = cartDao.getCartList(newCartList, conn);
		int result = 0;
		if(resultDto.getCount()==0) {
			result = cartDao.addMenu(newCartList, conn);
		} else {
			newCartList = new CartListVo(newCartList.getCartNo(), newCartList.getMenuNo(), newCartList.getQuantity()+resultDto.getQuantity());
			result = cartDao.updateQuantity(newCartList, conn);
		}
				
		if(result == 1) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		return result;
	}

	/**
	 * 기존에 담은 장바구니 품목 수량 갱신
	 * @param cartNo
	 * @param storeNo
	 * @return
	 * @throws Exception
	 */
	public int updateStoreNo(String cartNo, String storeNo) throws Exception {
		//Connection
		Connection conn = JDBCTemplate.getConnection();
		
		int result = cartDao.updateStoreNo(cartNo, storeNo, conn);
		
		if(result == 1) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		return result;
	}
}
