package com.baedal.one.menu.service;

import java.sql.Connection;
import java.util.List;

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
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = menuDao.editMenu(conn, menuVo);
		
		//tx
        if (result == 1) {
            JDBCTemplate.commit(conn);
        } else {
            JDBCTemplate.rollback(conn);
        }
       
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
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
	
	//삭제한 메뉴 복구
	public int rollbackDeleteMenu(MenuVo menuVo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = menuDao.rollbackDeleteMenu(conn, menuVo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
	
	//메뉴 전체 리스트 
	public List<MenuVo> menuList(String storeNo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		List<MenuVo> voList = menuDao.menuList(storeNo, conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	
	//메뉴 삭제 리스트
	public List<MenuVo> deleteMenuList(String storeNo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		List<MenuVo> voDeleteList = menuDao.deleteMenuList(storeNo, conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return voDeleteList;
		
	}

	
	

//	//메뉴 삭제
//	public int deleteMenu(MenuVo menuVo) throws Exception {
//		//conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		//DAO
//		int result = menuDao.deleteMenu(conn, menuVo);
//		
//		//tx
//		if(result == 1 && menuVo.getDeleteYn().equals("N")) {
//			//삭제가 성공하고, delete_yn이 "Y"로 설정되었을 때
//			menuVo.setDeleteYn("N");
//			menuVo.setDeleteYn("Y");
//			
//			
//			int rollbackResult = menuDao.rollbackMenu(conn, menuVo);
//			
//			if(rollbackResult == 1) {
//				JDBCTemplate.commit(conn);
//			} else {
//				JDBCTemplate.rollback(conn);
//			}
//		} else {
//			JDBCTemplate.rollback(conn);
//		}
//		
//		//close
//		JDBCTemplate.close(conn);
//		
//		return result;
//		
//	}

}
