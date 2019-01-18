package service;

import static db.JDBCUtil.getConnection;

import static db.JDBCUtil.close;


import java.sql.Connection;
import java.util.ArrayList;

import bean.BoardBean;
import dao.BoardDAO;

public class BoardListService {

	public int getListCount() {
 
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int listCount =0;
		listCount = boardDAO.getListCount();
		close(con);
		return listCount;
	}

	
	public ArrayList<BoardBean> getboardlist(int page, int limit){
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> boardlist = boardDAO.getboardList(page, limit);
		close(con);
		return boardlist;
	}
	
}
