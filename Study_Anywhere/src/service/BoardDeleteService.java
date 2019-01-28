package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;

import dao.BoardDAO;

public class BoardDeleteService {

	public int boardDelete(int bNum) {
		int result = 0;
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		result = boardDAO.boardDelete(bNum);
		
		close(con);
		return result;
	}
	
}
