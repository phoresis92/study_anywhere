package dao;

import static db.JDBCUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import bean.BoardBean;
import bean.Comment;


public class BoardDAO {
	DataSource ds;
	ResultSet rs = null;
	Connection con;
	PreparedStatement pstmt = null;

	private static BoardDAO BoardDAO;

	public static BoardDAO getInstance() {
		if (BoardDAO == null) {
			BoardDAO = new BoardDAO();
		}
		return BoardDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int getListCount() {
		int listCount = 0;
		String sql = "SELECT COUNT(*) FROM board";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	
	public ArrayList<BoardBean> getBoardList(String roomname) {
		String sql = "select * from board where roomname = ? order by board_num desc";

		System.out.println(roomname);

		ArrayList<BoardBean> boardList = new ArrayList<BoardBean>();
		BoardBean boardBean = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roomname);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				boardBean = new BoardBean();
				boardBean.setMember_id(rs.getString("member_id"));
				boardBean.setBoard_num(rs.getInt("board_num"));
				boardBean.setBoard_subject(rs.getString("board_subject"));
				boardBean.setBoard_content(rs.getString("board_content"));
				boardBean.setBoard_date(rs.getDate("board_date"));
				//boardBean.setBOARD_VIDEO_FILE(rs.getString("BOARD_FILE"));
				//boardBean.setBOARD_VIDEO_URL(rs.getString("BOARD_URL"));
				boardBean.setBoard_readcount(rs.getInt("board_readcount"));
				boardBean.setRoomname(rs.getString("roomname"));
			//	boardBean.setBOARD_LIKECOUNT(rs.getInt("BOARD_LIKECOUNT"));
			//	boardBean.setBOARD_REPORTCOUNT(rs.getInt("BOARD_REPORTCOUNT"));
			//	boardBean.setBOARD_TAG(rs.getString("BOARD_TAG"));
			//	boardBean.setBOARD_CATEGORY(rs.getString("BOARD_CATEGORY"));
			//	boardBean.setBOARD_BLIND(rs.getInt("BOARD_BLIND"));
			//	boardBean.setBOARD_YOUTUBE_ID(rs.getString("BOARD_YOUTUBE_ID"));
				boardList.add(boardBean);
			}
		} catch (Exception e) {
			System.out.println("pagelist 오류" + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return boardList;

	}
	
	
	public int readCount(int bNum) {
		int result = 0;
		String sql = "update board set board_readcount = board_readcount+1 where board_num = ? ;";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public BoardBean getBoard(int bNum) {
		
		String sql = "select * from board where board_num = ? ;";
		
		BoardBean boardBean = new BoardBean();
		
		try {
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bNum);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			boardBean.setMember_id(rs.getString("member_id"));
			boardBean.setBoard_num(rs.getInt("board_num"));
			boardBean.setBoard_subject(rs.getString("board_subject"));
			boardBean.setBoard_content(rs.getString("board_content"));
			boardBean.setBoard_date(rs.getDate("board_date"));
			boardBean.setBoard_readcount(rs.getInt("board_readcount"));
			boardBean.setRoomname(rs.getString("roomname"));
			
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardBean;
		
	}
	
	public int boardDelete(int bNum) {
		
		int result = 0;
		String sql = "delete from board where board_num = ? ;";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
		
	}
	
	
	public int boardWrite(BoardBean boardBean) {
		
		int result = 0;
		String sql = "insert into board (board_subject, board_content, member_ID, roomname) values( ?, ?, ?, ? );";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardBean.getBoard_subject());
			pstmt.setString(2, boardBean.getBoard_content());
			pstmt.setString(3, boardBean.getMember_id());
			pstmt.setString(4, boardBean.getRoomname());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
		
	}
	
	public int boardUpdate(BoardBean boardBean) {
		
		int result = 0;
		String sql = "update board set board_subject = ? , board_content = ? , board_date = now() where board_num = ? ;";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardBean.getBoard_subject());
			pstmt.setString(2, boardBean.getBoard_content());
			pstmt.setInt(3, boardBean.getBoard_num());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
		
	}

	//Comment
	public int addComment(Comment comm) {
		
		int result = 0;
		String sql = "insert into board_comment(board_num, member_id, comment_con) values( ? , ? , ? );";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comm.getBoard_num());
			pstmt.setString(2, comm.getMember_id());
			pstmt.setString(3, comm.getComment_content());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
		
	}
	
	public int getCommentNum() {
		
		int result = 0;
		String sql = "select max(comment_num)+1 from board_comment";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
		
	}
	
	public Comment getCommentObj(int comment_num) {
		
		Comment comm = null;
		String sql = "select * from board_comment where comment_num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				comm = new Comment();
				comm.setBoard_num(rs.getInt("board_num"));
				comm.setMember_id(rs.getString("member_id"));
				comm.setComment_num(rs.getInt("comment_num"));
				comm.setComment_content(rs.getString("comment_con"));
				comm.setComment_date(rs.getString("comment_date"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return comm;
		
	}
	
public ArrayList<Comment> getAllComment(int board_num) {
		
		ArrayList<Comment> list = new ArrayList<>();
		String sql = "select * from board_comment where board_num = ? order by comment_date;";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Comment comm = new Comment();
				comm.setBoard_num(rs.getInt("board_num"));
				comm.setMember_id(rs.getString("member_id"));
				comm.setComment_num(rs.getInt("comment_num"));
				comm.setComment_content(rs.getString("comment_con"));
				comm.setComment_date(rs.getString("comment_date"));
				list.add(comm);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
		
	}


public int getCommentCount(int board_num) {
	
	int result = 0;
	String sql = "select count(*) from board_comment where board_num = ? ";
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, board_num);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			result = rs.getInt("count(*)");
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close(rs);
		close(pstmt);
	}
	return result;
	
}
	
	
	public ArrayList<BoardBean> searchBoth(String key) {
		
		ArrayList<BoardBean> list = new ArrayList<>();
		String sql = "select * from board where board_subject || board_content like ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				BoardBean boardBean = new BoardBean();
				boardBean.setMember_id(rs.getString("MEMBER_ID"));
				boardBean.setBoard_num(rs.getInt("BOARD_NUM"));
				boardBean.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				boardBean.setBoard_content(rs.getString("BOARD_CONTENT"));
				boardBean.setBoard_date(rs.getDate("BOARD_DATE"));
				boardBean.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				boardBean.setRoomname(rs.getString("roomname"));
				list.add(boardBean);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
		
	}
	

public ArrayList<BoardBean> searchTitle(String key) {
	
	ArrayList<BoardBean> list = new ArrayList<>();
	String sql = "select * from board where board_subject like ? ";
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+key+"%");
		rs = pstmt.executeQuery();
		
		
		while(rs.next()) {
			
			BoardBean boardBean = new BoardBean();
			boardBean.setMember_id(rs.getString("member_id"));
			boardBean.setBoard_num(rs.getInt("board_num"));
			boardBean.setBoard_subject(rs.getString("board_subject"));
			boardBean.setBoard_content(rs.getString("board_content"));
			boardBean.setBoard_date(rs.getDate("board_date"));
			boardBean.setBoard_readcount(rs.getInt("board_readcount"));
			boardBean.setRoomname(rs.getString("roomname"));
			list.add(boardBean);
			
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close(rs);
		close(pstmt);
	}
	return list;
	
}
	
	public ArrayList<BoardBean> searchContent(String key) {
		
		ArrayList<BoardBean> list = new ArrayList<>();
		String sql = "select * from board where board_content like ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				BoardBean boardBean = new BoardBean();
				boardBean.setMember_id(rs.getString("member_id"));
				boardBean.setBoard_num(rs.getInt("board_num"));
				boardBean.setBoard_subject(rs.getString("board_subject"));
				boardBean.setBoard_content(rs.getString("board_content"));
				boardBean.setBoard_date(rs.getDate("board_date"));
				boardBean.setBoard_readcount(rs.getInt("board_readcount"));
				boardBean.setRoomname(rs.getString("roomname"));
				list.add(boardBean);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
		
	}
	

public int dropComment(int cnum) {
	int result = 0;
	String sql = "delete from board_comment where comment_num = ?";
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cnum);
		result = pstmt.executeUpdate();
		
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close(rs);
		close(pstmt);
	}
	return result;
	
}


}