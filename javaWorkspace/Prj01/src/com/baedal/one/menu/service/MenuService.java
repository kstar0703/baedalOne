package com.baedal.one.menu.service;

import java.sql.Connection;

import com.baedal.one.jdbcTemplate.JDBCTemplate;
import com.baedal.one.menu.dao.MenuDao;
import com.baedal.one.menu.vo.MenuVo;


public class MenuService {
	
	//필드
	private final MenuDao menuDao;
	
	//기본 생성자
	public MenuService() {
		menuDao = new MenuDao();
	}
	
	//점주 비밀번호 가져오기
	public String findPwd(String ownerno) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		String nowPwd = menuDao.findPwd(conn, ownerno);

		JDBCTemplate.close(conn);

		return nowPwd;
		
	}
	
	//메뉴등록
	public int addMenu(MenuVo newMenu) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		int result = menuDao.addMenu(conn, newMenu);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else { 
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
		
	}
	
	
	//메뉴 수정
	public int editMenu(MenuVo menuVo) throws Exception {
		System.out.println("--- 메뉴 수정 ---");
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = menuDao.editMenu(conn, menuVo);
		
		//tx
		JDBCTemplate.commit(conn);
		JDBCTemplate.rollback(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	//메뉴 삭제
	public int deleteMenu(MenuVo menuVo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = menuDao.deleteMenu(conn, menuVo);
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
	}

}
