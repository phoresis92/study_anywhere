package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;

import bean.BoardBean;
import dao.BoardDAO;

public class BoardUpdateService {

	public int boardUpdate(BoardBean boardBean) {
		
		 
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int result = boardDAO.boardUpdate(boardBean);
		close(con);
		return result;
	}
	
}
