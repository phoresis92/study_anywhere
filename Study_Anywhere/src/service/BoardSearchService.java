package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import bean.BoardBean;
import dao.BoardDAO;

public class BoardSearchService {

	public ArrayList<BoardBean> searchBoth (String key) {
		
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> list = boardDAO.searchBoth(key);
		System.out.println(list);
		for(BoardBean board : list) {
			int board_num = board.getBoard_num();
			
			int comment_count = boardDAO.getCommentCount(board_num);
			board.setComment_count(comment_count);
			
		}
		
		close(con);
		return list;
		
	}
	
	public ArrayList<BoardBean> searchTitle (String key) {
		
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> list = boardDAO.searchTitle(key);
		System.out.println(list);
		for(BoardBean board : list) {
			int board_num = board.getBoard_num();
			
			int comment_count = boardDAO.getCommentCount(board_num);
			board.setComment_count(comment_count);
			
		}
		
		close(con);
		return list;
		
	}
	
	public ArrayList<BoardBean> searchContent (String key) {
		
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> list = boardDAO.searchContent(key);
		System.out.println(list);
		for(BoardBean board : list) {
			int board_num = board.getBoard_num();
			
			int comment_count = boardDAO.getCommentCount(board_num);
			board.setComment_count(comment_count);
			
		}
		
		close(con);
		return list;
		
	}
	
}
