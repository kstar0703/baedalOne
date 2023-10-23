package com.baedal.one.store.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.store.dao.StoreDao;
import com.baedal.one.store.dto.StoreCategoryDto;
import com.baedal.one.store.vo.StoreVo;

public class StoreService {
	
	private	StoreDao dao;
	
	public StoreService() {
		dao = new StoreDao();
	}
	
	
	/**
	 * 매장 정보 출력(점주)
	 * 수정 변경 및 삭제 재활용 가능
	 */
	public List<StoreVo> showStoreInfo(String loginOwnerNo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		List<StoreVo> list = dao.showStoreInfo(conn,loginOwnerNo);
		
		//close
		conn.close();
			
		return list;
	}


	/**
	 * 가게 선택 (점주)
	 * 수정 변경 및 삭제 재활용 가능
	 */
	public StoreVo chooseStore(StoreVo storeVo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		StoreVo vo = dao.chooseStore(conn,storeVo); 
		
		//close
		conn.close();
		
		return vo;
	}

	/**
	 * 카테고리 목록 출력 - 매장등록시 사용 , 구매자 검색 할때 사용
	 */
	
	public List<StoreCategoryDto> showCategory() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao 
		List<StoreCategoryDto> list = dao.showCategory(conn);
		
		//close
		conn.close();
		
		
		return list;
	}
	

	/**
	 * 매장 등록 추가
	 */
	
	public int registerStore(StoreVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = dao.registerStore(vo,conn);
		
		//tx
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		conn.close();
		
		return result;
	}


	/**
	 * 매장 수정
	 * @param vo
	 * 매장 이름 수정  ,카테고리 전화번호 수정, 매장주소 수정, 매장 영업시간 수정
	 * 
	 */
	public int changeStoreInfo(StoreVo vo, StoreVo changeVo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		
		int result = dao.changetStoreInfo(conn,vo,changeVo);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.close(conn);
		}
		
		// close
		conn.close();
		
		return result;
	}


	/**
	 * 매장 폐업 
	 * @throws Exception 
	 */
	public int shoutDownStore(StoreVo vo, String password) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		
		int result = dao.shoutDownStore(conn,vo,password);
		
		// tx
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.close(conn);
		}
		
		// close
		conn.close();
		
		return result;
	}

}
