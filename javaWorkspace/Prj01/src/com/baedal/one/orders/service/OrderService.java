package com.baedal.one.orders.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.baedal.one.Main;
import com.baedal.one.cart.TestMain;
import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.orders.dao.OrderDao;
import com.baedal.one.orders.dto.CartListDto;
import com.baedal.one.orders.vo.OrdersVo;
import com.baedal.one.pay.vo.PayVo;

public class OrderService {
	
	private final OrderDao orderDao;
	
	public OrderService() {
		orderDao = new OrderDao();
	}
	
	/**
	 * 장바구니 물품 가져오기
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<CartListDto> getCartList(String memberNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<CartListDto> cartList = orderDao.getCartList(memberNo, conn);
		
		JDBCTemplate.close(conn);
		return cartList;
	}
	
	/**
	 * 현재 나의 충전 머니 조회하기
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int getMoneyById(String memberNo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int money = orderDao.getMoneyById(memberNo, conn);
		
		JDBCTemplate.close(conn);
		return money;
	}
	
	/**
	 * 결제하기 
	 * 주문 내역 추가 -> 페이 내역 추가 -> 회원 업데이트
	 * @param newOrder
	 * @param money 보유 머니
	 * @return
	 * @throws Exception
	 */
	public OrdersVo pay(OrdersVo newOrder, int money) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		OrdersVo recentOrder = null;
		int result = orderDao.addOrders(newOrder, conn);
		//DB에서 가장 최근에 결제한 내역 가져오기
		PayVo findOrder = null;

		if(result==1) {
			JDBCTemplate.commit(conn);
			findOrder = orderDao.getRecentOrderByUserNo(Main.loginMember.getMemberNo(), conn);
		} else {
			JDBCTemplate.rollback(conn);
			throw new Exception("주문 내역 저장 실패");
		}
		
		String balance = String.valueOf(money-Integer.parseInt(findOrder.getPay()));
		PayVo newPayLog = new PayVo(findOrder.getUserNo(), findOrder.getSource(), findOrder.getPay(), findOrder.getPayDate(), balance);
		
		//결제 내역에 추가
		result = orderDao.addPay(newPayLog, conn);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
			result = orderDao.updateMoney(Main.loginMember.getMemberNo(), balance, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
				throw new Exception("구매 후 잔액 수정 실패");
			}
		} else {
			JDBCTemplate.rollback(conn);
			throw new Exception("페이 테이블 추가 실패");
		}
		
		if(result == 1) {
			recentOrder = orderDao.getRecentOrder(Main.loginMember.getMemberNo(), conn);
		}
		
		JDBCTemplate.close(conn);
		return recentOrder;
	}
	
	/**
	 * 상품 수정하는 메소드
	 * @param cartListNo
	 * @param quantity
	 * @return
	 * @throws Exception
	 */
	public int updateQuantity(String cartListNo, String quantity) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = orderDao.updateQuantity(cartListNo, quantity, conn);
		
		if(result == 1) 
			JDBCTemplate.commit(conn);
		else 
			JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		return result;
	}
	
	/**
	 * 
	 * @param cartListNo
	 * @return
	 * @throws Exception 
	 */
	public int deleteCartList(String cartListNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = orderDao.deleteCartList(cartListNo, conn);
		if(result == 1) 
			JDBCTemplate.commit(conn);
		else 
			JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		return result;
	}

	public String getAmountPwd(String memberNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		String findAmountPwd = orderDao.getAmountPwd(memberNo, conn);
		
		JDBCTemplate.close(conn);
		return findAmountPwd;
	}

}
