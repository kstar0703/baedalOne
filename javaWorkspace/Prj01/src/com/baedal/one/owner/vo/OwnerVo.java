package com.baedal.one.owner.vo;

public class OwnerVo {
	
	private String ownerNo;	 
	private String	ownerId;	  
	private String	ownerPwd;	   
	private String	enrollDate;	
	private String	quitYn;
	
	
	
	public OwnerVo() {
	}




	public OwnerVo(String ownerNo, String ownerId, String ownerPwd, String enrollDate, String quitYn) {
		this.ownerNo = ownerNo;
		this.ownerId = ownerId;
		this.ownerPwd = ownerPwd;
		this.enrollDate = enrollDate;
		this.quitYn = quitYn;
	}




	public String getOwnerNo() {
		return ownerNo;
	}




	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}




	public String getOwnerId() {
		return ownerId;
	}




	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}




	public String getOwnerPwd() {
		return ownerPwd;
	}




	public void setOwnerPwd(String ownerPwd) {
		this.ownerPwd = ownerPwd;
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




	@Override
	public String toString() {
		return "OwnerVo [ownerNo=" + ownerNo + ", ownerId=" + ownerId + ", ownerPwd=" + ownerPwd + ", enrollDate="
				+ enrollDate + ", quitYn=" + quitYn + "]";
	}
	
	
	
	
	
	
	
	
	
		
	
	

}
