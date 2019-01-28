package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;

import bean.BoardBean;
import dao.BoardDAO;

public class BoardReadService {

	public int readCount(int bNum){
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int result = boardDAO.readCount(bNum);
		
		
		close(con);
		
		return result;
	}
	
	public BoardBean getBoard(int bNum) {
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		BoardBean boardBean = boardDAO.getBoard(bNum);
		
		close(con);
		
		return boardBean;
	}
	
	
}
