package com.baedal.one.store.dto;

public class StoreCategoryDto {
	
	String categoryNo;
	String categoryName;

	public StoreCategoryDto(String categoryNo, String categoryName) {
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}
	public StoreCategoryDto() {
	}
	
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "StoreCategoryDto [categoryNo=" + categoryNo + ", categoryName=" + categoryName + "]";
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	

}
