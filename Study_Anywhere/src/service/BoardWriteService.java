package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;

import bean.BoardBean;
import dao.BoardDAO;

public class BoardWriteService {

	public int boardWrite(BoardBean boardBean) {
		
		int result = 0;
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		result = boardDAO.boardWrite(boardBean);
		
		close(con);
		return result;
		
	}
	
}
