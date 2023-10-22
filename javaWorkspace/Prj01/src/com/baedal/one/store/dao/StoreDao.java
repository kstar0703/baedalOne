package com.baedal.one.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.store.vo.StoreVo;

public class StoreDao {

	public List<StoreVo> showStoreInfo(Connection conn, String loginOwnerNo) throws Exception {
	    
		//sql
		String sql = "SELECT STORE_NO,CATEGORY_NO,OWNER_NO,STORE_NAME,STORE_PHONE,STORE_ADDRESS,TO_CHAR(ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE,CLOSE_YN,OPENTIME,CLOSETIME  FROM STORE WHERE OWNER_NO = ?";
		
		//psmt
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginOwnerNo);
		
		//rs
		ResultSet rs = pstmt.executeQuery();
		
		List<StoreVo> list = new ArrayList<StoreVo>();
		while(rs.next()) {
				list.add(new StoreVo(rs.getString("STORE_NO"),
						rs.getString("CATEGORY_NO"),rs.getString("OWNER_NO")
						,rs.getString("STORE_NAME"),rs.getString("STORE_PHONE")
						,rs.getString("STORE_ADDRESS"),rs.getString("ENROLL_DATE"),
						rs.getString("CLOSE_YN"),rs.getString("OPENTIME")
						,rs.getString("CLOSETIME")));
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		
		//결과
		return list;
		
		
		
		
		
		
	}

}
