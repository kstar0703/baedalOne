package com.baedal.one.pay.vo;

public class PayVo {

	private String payNo;
	private String userNo;
	private String source;
	private String pay;
	private String payDate;
	private String balance;
	public PayVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PayVo(String payNo, String userNo, String source, String pay, String payDate, String balance) {
		super();
		this.payNo = payNo;
		this.userNo = userNo;
		this.source = source;
		this.pay = pay;
		this.payDate = payDate;
		this.balance = balance;
	}
	
	public PayVo(String userNo, String source, String pay, String payDate, String balance) {
		this.userNo = userNo;
		this.source = source;
		this.pay = pay;
		this.payDate = payDate;
		this.balance = balance;
	}
	
	public PayVo(String userNo, String source, String pay, String payDate) {
		this.userNo = userNo;
		this.source = source;
		this.pay = pay;
		this.payDate = payDate;
	}
	@Override
	public String toString() {
		return "PayVo [payNo=" + payNo + ", userNo=" + userNo + ", source=" + source + ", pay=" + pay + ", payDate="
				+ payDate + ", balance=" + balance + "]";
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	

	

}
