package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import bean.Comment;
import dao.BoardDAO;

public class CommentService {

	public int addComment(Comment comm) {
		
		int result = 0;
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		result = boardDAO.addComment(comm);
		
		close(con);
		return result;
		
	}
	
	
	public int getCommentNum() {
		
		int result = 0;
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		result = boardDAO.getCommentNum();
		
		close(con);
		return result;
		
	}
	
	public Comment getCommentObj(int comment_num) {
		
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		Comment comm = boardDAO.getCommentObj(comment_num);
		
		close(con);
		return comm;
		
	}
	
	public ArrayList<Comment> getAllComment(int board_num) {
		
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<Comment> list = boardDAO.getAllComment(board_num);
		
		close(con);
		return list;
		
	}
	
	public int getCommentCount(int board_num) {
		
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int count = boardDAO.getCommentCount(board_num);
		
		close(con);
		return count;
		
	}
	
	
}
