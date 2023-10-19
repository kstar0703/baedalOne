package com.baedal.one.owner.vo;

public class OwnerVo {
	
	private String ownerNo;	 
	private String	ownerId;	  
	private String	ownerPwd;	   
	private String	enrollDate;
	private String  updateDate;
	private String	quitYn;
	
	
	public OwnerVo(String ownerNo, String ownerId, String ownerPwd, String enrollDate, String updateDate,
			String quitYn) {
		this.ownerNo = ownerNo;
		this.ownerId = ownerId;
		this.ownerPwd = ownerPwd;
		this.enrollDate = enrollDate;
		this.updateDate = updateDate;
		this.quitYn = quitYn;
	}
	
	public OwnerVo() {
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
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
				+ enrollDate + ", updateDate=" + updateDate + ", quitYn=" + quitYn + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
		
	
	

}
