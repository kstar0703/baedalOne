package com.baedal.one.menu.mian;

import java.util.Scanner;

import com.baedal.one.menu.controller.MenuController;
import com.baedal.one.owner.vo.OwnerVo;

public class MenuMain {
	
	public static String storeNo = "1";	//매장번호 
	public static final Scanner SC = new Scanner(System.in); 

	public static void main(String[] args) {
		
		
		System.out.println("== 메뉴(매장) 관리 ==");
		
		//객체
		MenuController menuController = new MenuController();
		menuController.findPwd(storeNo);
			
	}

}
