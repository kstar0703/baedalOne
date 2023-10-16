package com.baedal.one.purchase.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.baedal.one.purchase.dao.PurchaseDao;
import com.baedal.one.purchase.vo.CartVo;
import com.kh.app.jdbc.JDBCTemplate;

public class PurchaseService {

	private final PurchaseDao purchaseDao;
	
	public PurchaseService() {
		purchaseDao = new PurchaseDao();
	}
	
	public /*Menu<List>*/ void printMenu(/*매장 번호*/) throws Exception {
		//Connection
		Connection conn = JDBCTemplate.getConnection();
		 /* List<Menu> menuList =*/ purchaseDao.printMenu(/*매장 번호*/, conn);
		
		//close
		 JDBCTemplate.close(conn);
		//return menuList;
	}
	public CartVo getMyCart(/*회원번호*/) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		purchaseDao.getMyCart(/*회원 번호*/, conn);
		JDBCTemplate.close(conn);
		return null;
	}
	
	public int deleteCartList(/*회원번호*/) throws Exception {
		//Connection
		Connection conn = JDBCTemplate.getConnection();
		JDBCTemplate.close(conn);
		return 0;
	}
}
