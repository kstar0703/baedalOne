package com.baedal.one.owneroders.controller;

import java.util.ArrayList;
import java.util.List;

import com.baedal.one.owneroders.dto.OwnerOdersDTO;

public class OwnerOdersController {
	public static final String STORENO= "2";
	public static List<OwnerOdersDTO> ownerOdersDto ;
	
	public OwnerOdersController() {
		ownerOdersDto = new ArrayList<OwnerOdersDTO>();
	}
	
	public void showOders() {
		System.out.println("======간편 주문 목록 조회======");
		for(OwnerOdersDTO dto : ownerOdersDto) {
			System.out.println(dto);
		}
	}
	
}
