package com.baedal.one.owneroders.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.baedal.one.owneroders.dto.OwnerOdersVo;
import com.baedal.one.owneroders.service.OwnerOdersService;
import com.baedal.one.owneroders.dto.OwnerCartListDTO;
public class OwnerOdersController {
	
	public final String STORENO= "1";
	private final OwnerOdersService service;
	private final Scanner sc;
	public OwnerCartListDTO dto;
	public OwnerOdersController() {
		dto = new OwnerCartListDTO();
		service = new OwnerOdersService();
		sc = new Scanner(System.in);
	}
	
	public OwnerCartListDTO showOders() 
	{
		try {
			dto.setStoreName(findStoreName());
			System.out.println("======"+dto.getStoreName()+"<간편 주문 목록 조회>======");
			List<OwnerOdersVo> voList;
			voList = service.OwnerOderList(STORENO);
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			int i = 1;
			for(OwnerOdersVo vo : voList) {
				map.put(i, vo.getCartNo());
				System.out.print(i +". ");
				System.out.print(vo.getCartNo() +" ");
				System.out.print(vo.getMenuName() +" ");
				int otherQuantity = Integer.parseInt(vo.getTotalQuantity())-1;
				if(otherQuantity >0) {
					System.out.print("외 ");
					System.out.print(otherQuantity);
					System.out.print("개 ");					
				}else {
					System.out.print("\t");
				}
				System.out.print("/ ");
				System.out.print(vo.getTotalPrice());
				System.out.print("원 / ");
				System.out.println(vo.getOderDate());
				i++;
			}
			System.out.print("상세목록을 보고싶은 내역을 입력하세요 : ");
			int input = sc.nextInt();
			String nowCartNo = map.get(input);
			dto.setOrderDate(voList.get(input-1).getTotalPrice());
			dto.setTotalPrice(voList.get(input-1).getOderDate());
			
//			System.out.println(voList.get(input-1).getTotalPrice());
//			System.out.println(voList.get(input-1).getOderDate());
			oderDetails(nowCartNo,dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return dto;

	}
	//매장 이름 찾기
	private String findStoreName() {
		OwnerCartListDTO cartListDto =null;
		try {
			cartListDto = service.findStoreName(STORENO);
			if(cartListDto==null) {
				throw new Exception();
			}
		} catch (Exception e) {
			
		}
		String storeName = cartListDto.getStoreName();
		return storeName;
	}
	
	//상세내역 보기
	private void oderDetails(String nowCartNo, OwnerCartListDTO dto) {
		System.out.println("----------"+dto.getStoreName()+"<주문 상세 내역>-------------");
		
		try {
			List<OwnerCartListDTO> voList = service.oderDetails(nowCartNo);
			
			for(OwnerCartListDTO vo : voList) {
				
			}
		} catch (Exception e) {
			System.out.println("주문 상세 내역 불러오기 실패");
			e.printStackTrace();
		}
		
	}
	
	
}