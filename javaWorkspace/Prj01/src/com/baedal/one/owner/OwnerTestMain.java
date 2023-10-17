package com.baedal.one.owner;

import com.baedal.one.owner.controller.OwnerController;
import com.baedal.one.owner.vo.OwnerVo;

public class OwnerTestMain {
	
	public static OwnerVo LoginOwner;
	
	public static void main(String[] args) {
		OwnerController ow = new OwnerController();
		while(true) {
		ow.selectMenu();
		}
		
	}

}
