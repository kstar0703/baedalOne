package com.baedal.one.purchase.service;

import com.baedal.one.purchase.dao.PurchaseDao;
import com.baedal.one.purchase.vo.CartVo;

public class PurchaseService {

	private final PurchaseDao purchaseDao;
	
	public PurchaseService() {
		purchaseDao = new PurchaseDao();
	}
	public /*Menu*/ printMenu(/*매장 번호*/) {
		//Connection
		
		//close
		purchaseDao.printMenu(/*매장 번호*/, /*Connection 객체*/);
		
	}
	public CartVo getMyCart() {
		// TODO Auto-generated method stub
		return null;
	}
}
