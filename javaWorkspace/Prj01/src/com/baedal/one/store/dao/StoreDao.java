package com.baedal.one.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.Main;
import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.store.dto.StoreCategoryDto;
import com.baedal.one.store.vo.StoreVo;


public class StoreDao {

	
	/**
	 * 매장 정보 출력(점주)
	 * 수정 변경 및 삭제 재활용 가능
	 */
	public List<StoreVo> showOwnerStore(Connection conn, String loginOwnerNo) throws Exception {
	    
		//sql
		String sql = "SELECT S.STORE_NO,S.CATEGORY_NO,S.OWNER_NO,S.STORE_NAME,S.STORE_PHONE,S.STORE_ADDRESS,TO_CHAR(ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE,S.CLOSE_YN,S.OPENTIME,S.CLOSETIME,C.CATEGORY_NAME FROM STORE S JOIN STORE_CATEGORY C ON S.CATEGORY_NO = C.CATEGORY_NO WHERE OWNER_NO = ? AND S.CLOSE_YN = 'N'";
		
		//psmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginOwnerNo);
		
		//rs
		ResultSet rs = pstmt.executeQuery();
		
		List<StoreVo> list = new ArrayList<StoreVo>();
		while(rs.next()) {
				list.add(new StoreVo(
						rs.getString("STORE_NO"),
						rs.getString("CATEGORY_NO"),rs.getString("OWNER_NO")
						,rs.getString("STORE_NAME"),rs.getString("STORE_PHONE")
						,rs.getString("STORE_ADDRESS"),rs.getString("ENROLL_DATE"),
						rs.getString("CLOSE_YN"),rs.getString("OPENTIME")
						,rs.getString("CLOSETIME"),rs.getString("CATEGORY_NAME")));
				
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		
		//결과
		return list;
	
	}
	
	/**
	 * 가게 선택 (점주)
	 * @retunrn Storevo
	 * 수정 변경 및 삭제 재활용 가능
	 */

//	public StoreVo chooseStore(Connection conn, StoreVo storeVo) throws Exception {
//		//sql
//		String sql = "SELECT S.STORE_NO,S.CATEGORY_NO,S.OWNER_NO,S.STORE_NAME,S.STORE_PHONE,S.STORE_ADDRESS,TO_CHAR(ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE,S.CLOSE_YN,S.OPENTIME,S.CLOSETIME,C.CATEGORY_NAME FROM STORE S JOIN STORE_CATEGORY C ON S.CATEGORY_NO = C.CATEGORY_NO WHERE STORE_NO = '?'";
//		
//		//pstmt
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, storeVo.getStoreNO());
//		
//		//rs
//		ResultSet rs = pstmt.executeQuery();
//		StoreVo vo = null;
//		
//		if(rs.next()) {
//				vo = new StoreVo(rs.getString("STORE_NO"),rs.getString("CATEGORY_NO"),rs.getString("OWNER_NO"),
//						rs.getString("STORE_NAME"),rs.getString("STORE_PHONE")
//						,rs.getString("STORE_ADDRESS"),rs.getString("ENROLL_DATE"),
//						rs.getString("CLOSE_YN"),rs.getString("OPENTIME")
//						,rs.getString("CLOSETIME"),rs.getString("CATEGORY_NAME"));
//		}
//		
//		JDBCTemplate.close(pstmt);
//		JDBCTemplate.close(rs);
//		
//		
//		
//		return vo;
//	}
	
	/**
	 * 카테고리 목록 출력 - 매장등록시 사용 , 구매자 검색 할때 사용
	 */

	public List<StoreCategoryDto> showCategory(Connection conn) throws Exception {
		//sql
		String sql = "SELECT * FROM STORE_CATEGORY";
		
		//pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		//rs
		ResultSet rs = pstmt.executeQuery();
		
		
		List<StoreCategoryDto> list = new ArrayList<StoreCategoryDto>();
		while(rs.next()) {
			list.add(new StoreCategoryDto(rs.getString("CATEGORY_NO"),rs.getString("CATEGORY_NAME")));
		}
	
		//close	
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return list;
	}
	
	
	/**
	 * 매장 등록 추가
	 */
	public int registerStore(StoreVo vo, Connection conn) throws Exception {
		
		//sql
		String sql = "INSERT INTO STORE (STORE_NO,CATEGORY_NO,OWNER_NO,STORE_NAME,STORE_PHONE,STORE_ADDRESS,OPENTIME,CLOSETIME) VALUES (SEQ_STORE.NEXTVAL,?,?,?,?,?,?,?)";
		
		//pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCategoryNO());
		pstmt.setString(2, vo.getOwnerNo());
		pstmt.setString(3, vo.getStoreName());
		pstmt.setString(4, vo.getStorePhone());
		pstmt.setString(5, vo.getStoreADDRESS());
		pstmt.setString(6, vo.getOpenTime());
		pstmt.setString(7, vo.getCloseTime());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	/**
	 * 매장 수정
	 * @param vo
	 * 매장 이름 수정  ,카테고리 전화번호 수정, 매장주소 수정, 매장 영업시간 수정
	 * @throws Exception 
	 * 
	 */

	public int changetStoreInfo(Connection conn, StoreVo vo, StoreVo changeVo) throws Exception {
		
		//sql
		String sql ="UPDATE STORE SET STORE_NAME = ?, STORE_PHONE = ?, STORE_ADDRESS = ? , OPENTIME = ? , CLOSETIME = ? WHERE STORE_NO = ?";
		
		//pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		
		// 이게 문제가 될까 -- ##
		// 점주 넘
		pstmt.setString(6,vo.getStoreNO());
		
		
		// 이름조건 확인
		if(changeVo.getStoreName().length()==0) {
			pstmt.setString(1, vo.getStoreName());
		}else {
			pstmt.setString(1, changeVo.getStoreName());
		}
		
		// 전번
		if(changeVo.getStorePhone().length() ==0) {
			pstmt.setString(2, vo.getStorePhone());
		}else {
			pstmt.setString(2, changeVo.getStorePhone());
		}
		
		// 주소
		if(changeVo.getStoreADDRESS().length() ==0) {
			pstmt.setString(3, vo.getStoreADDRESS());
		}else {
			pstmt.setString(3, changeVo.getStoreADDRESS());
		} 
		
		// 오픈타임
		if(changeVo.getOpenTime().length() ==0) {
			pstmt.setString(4, vo.getOpenTime());
		}else {
			pstmt.setString(4, changeVo.getOpenTime());
		}
		
		// 마감타임
		if(changeVo.getCloseTime().length() ==0) {
			pstmt.setString(5, vo.getCloseTime());
		}else {
			pstmt.setString(5, changeVo.getCloseTime());
		}
		
		

		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
		
		
		
		
	}
	
	/**
	 * 폐점 하기
	 */

	public int shutDownStore(Connection conn, StoreVo vo, String password) throws Exception {

		//sql
		String sql = "UPDATE STORE SET CLOSE_YN = 'Y' WHERE STORE_NO = ? AND CLOSE_YN ='N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 변수 변경 Main.Owner
		if(!password.endsWith(Main.loginOwner.getOwnerPwd())) {
			throw new Exception("비밀번호가 다릅니다.");
		}else {
			pstmt.setString(1, vo.getStoreNO());
		}
		
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	/**
	 *  전체 매장 조회
	 *  매장 선택 메소드 재활용
	 * @throws Exception 
	 * 
	 */

	public List<StoreVo> showAllStore(Connection conn) throws Exception {
		
		//sql
				String sql = "SELECT S.STORE_NO,S.CATEGORY_NO,S.OWNER_NO,S.STORE_NAME,S.STORE_PHONE,S.STORE_ADDRESS,TO_CHAR(ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE,S.CLOSE_YN,S.OPENTIME,S.CLOSETIME,C.CATEGORY_NAME FROM STORE S JOIN STORE_CATEGORY C ON S.CATEGORY_NO = C.CATEGORY_NO ";
				
				//psmt
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				
				//rs
				ResultSet rs = pstmt.executeQuery();
				
				List<StoreVo> list = new ArrayList<StoreVo>();
				while(rs.next()) {
						list.add(new StoreVo(
								rs.getString("STORE_NO"),
								rs.getString("CATEGORY_NO"),rs.getString("OWNER_NO")
								,rs.getString("STORE_NAME"),rs.getString("STORE_PHONE")
								,rs.getString("STORE_ADDRESS"),rs.getString("ENROLL_DATE"),
								rs.getString("CLOSE_YN"),rs.getString("OPENTIME")
								,rs.getString("CLOSETIME"),rs.getString("CATEGORY_NAME")));
						
				}
				
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rs);
				
				
				//결과
				return list;
	}
	/**
	 * 매장 조회
	 * 카테고리 검색 조회
	 * 매장 선택 메소드 재활용
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<StoreVo> showCategoryStore(Connection conn, String categoryNum) throws Exception {
		//sql
		String sql = "SELECT S.STORE_NO,S.CATEGORY_NO,S.OWNER_NO,S.STORE_NAME,S.STORE_PHONE,S.STORE_ADDRESS,TO_CHAR(ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE,S.CLOSE_YN,S.OPENTIME,S.CLOSETIME,C.CATEGORY_NAME FROM STORE S JOIN STORE_CATEGORY C ON S.CATEGORY_NO = C.CATEGORY_NO WHERE C.CATEGORY_NO = ?";
		
		//pstmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,categoryNum );
		
		//rs
		ResultSet rs = pstmt.executeQuery();
		
		List<StoreVo> list = new ArrayList<StoreVo>();
		while(rs.next()) {
				list.add(new StoreVo(
						rs.getString("STORE_NO"),
						rs.getString("CATEGORY_NO"),rs.getString("OWNER_NO")
						,rs.getString("STORE_NAME"),rs.getString("STORE_PHONE")
						,rs.getString("STORE_ADDRESS"),rs.getString("ENROLL_DATE"),
						rs.getString("CLOSE_YN"),rs.getString("OPENTIME")
						,rs.getString("CLOSETIME"),rs.getString("CATEGORY_NAME")));
				
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		
		return list;
	}
	
	
}
