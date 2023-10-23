package com.baedal.one.store.vo;

import java.util.Locale.Category;

import oracle.security.o3logon.a;


public class StoreVo {
	
	
	private StoreVo vo; 
	private String storeNO;
	private String categoryNO;
	private String ownerNo;
	private String storeName;
	private String storePhone;
	private String storeADDRESS;
	private String enrollDate;
	private String closeYn;
	private String openTime;
	private String closeTime;
	private String categoryName;
	
	
	
	public StoreVo(StoreVo vo, String storeNO, String categoryNO, String ownerNo, String storeName, String storePhone,
			String storeADDRESS, String enrollDate, String closeYn, String openTime, String closeTime,
			String categoryName) {
		this.vo = vo;
		this.storeNO = storeNO;
		this.categoryNO = categoryNO;
		this.ownerNo = ownerNo;
		this.storeName = storeName;
		this.storePhone = storePhone;
		this.storeADDRESS = storeADDRESS;
		this.enrollDate = enrollDate;
		this.closeYn = closeYn;
		this.openTime = openTime;
		this.closeTime = closeTime;
		
		
		this.categoryName = categoryName;
	}

	public StoreVo(String storeNO, String categoryNO, String ownerNo, String storeName, String storePhone,
			String storeADDRESS, String enrollDate, String closeYn, String openTime, String closeTime) {
		this.storeNO = storeNO;
		this.categoryNO = categoryNO;
		this.ownerNo = ownerNo;
		this.storeName = storeName;
		this.storePhone = storePhone;
		this.storeADDRESS = storeADDRESS;
		this.enrollDate = enrollDate;
		this.closeYn = closeYn;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	
	public StoreVo() {
	}

	public StoreVo getVo() {
		return vo;
	}

	public void setVo(StoreVo vo) {
		this.vo = vo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStoreNO() {
		return storeNO;
	}





	public void setStoreNO(String storeNO) {
		this.storeNO = storeNO;
	}





	public String getCategoryNO() {
		return categoryNO;
	}





	public void setCategoryNO(String categoryNO) {
		this.categoryNO = categoryNO;
	}





	public String getOwnerNo() {
		return ownerNo;
	}





	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}





	public String getStoreName() {
		return storeName;
	}





	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}





	public String getStorePhone() {
		return storePhone;
	}





	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}





	public String getStoreADDRESS() {
		return storeADDRESS;
	}





	public void setStoreADDRESS(String storeADDRESS) {
		this.storeADDRESS = storeADDRESS;
	}





	public String getEnrollDate() {
		return enrollDate;
	}





	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}





	public String getCloseYn() {
		return closeYn;
	}





	public void setCloseYn(String closeYn) {
		this.closeYn = closeYn;
	}





	public String getOpenTime() {
		return openTime;
	}





	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}





	public String getCloseTime() {
		return closeTime;
	}





	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}





	@Override
	public String toString() {
		return "StoreVo [vo=" + vo + ", storeNO=" + storeNO + ", categoryNO=" + categoryNO + ", ownerNo=" + ownerNo
				+ ", storeName=" + storeName + ", storePhone=" + storePhone + ", storeADDRESS=" + storeADDRESS
				+ ", enrollDate=" + enrollDate + ", closeYn=" + closeYn + ", openTime=" + openTime + ", closeTime="
				+ closeTime + ", categoryName=" + categoryName + "]";
	}
	
	
	
	
	
	
		
	

}
