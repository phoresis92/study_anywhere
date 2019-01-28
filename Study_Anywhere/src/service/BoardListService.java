package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import bean.BoardBean;
import bean.Comment;
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

	
/*	public ArrayList<BoardBean> getboardlist(int page, int limit){
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> boardlist = boardDAO.getboardList(page, limit);
		close(con);
		return boardlist;
	}*/
	
	public ArrayList<BoardBean> getBoardList(String roomname){
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		ArrayList<BoardBean> list = boardDAO.getBoardList(roomname);
		for(BoardBean board : list) {
			CommentService cs = new CommentService();
			int count = cs.getCommentCount(board.getBoard_num());
			board.setComment_count(count);
		}
		
		close(con);
		
		return list;
	}
	
}
