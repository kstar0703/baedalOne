package com.baedal.one.member.vo;

public class MemberVo {
	
	public MemberVo() {
			
		}
	
	public MemberVo(String id, String pwd, String nickName, String address, String phone, String enrollDate,
			String quitYn, String updateDate, String money, String amountPwd) {
		
		this.id = id;
		this.pwd = pwd;
		this.nickName = nickName;
		this.address = address;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.quitYn = quitYn;
		this.updateDate = updateDate;
		this.money = money;
		this.amountPwd = amountPwd;
	}

	private String id;
	private String pwd;
	private String nickName;
	private String address;
	private String phone;
	private String enrollDate;
	private String quitYn;
	private String updateDate;
	private String money;
	private String amountPwd;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getQuitYn() {
		return quitYn;
	}

	public void setQuitYn(String quitYn) {
		this.quitYn = quitYn;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getAmountPwd() {
		return amountPwd;
	}

	public void setAmountPwd(String amountPwd) {
		this.amountPwd = amountPwd;
	}

	@Override
	public String toString() {
		return "Membercontroller [id=" + id + ", pwd=" + pwd + ", nickName=" + nickName + ", address=" + address
				+ ", phone=" + phone + ", enrollDate=" + enrollDate + ", quitYn=" + quitYn + ", updateDate="
				+ updateDate + ", money=" + money + ", amountPwd=" + amountPwd + "]";
	}

}
