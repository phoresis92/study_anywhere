package dao;

import static db.JDBCUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import bean.BoardBean;


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
		String sql = "SELECT COUNT(*) FROM BOARD";
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

	public ArrayList<BoardBean> getboardList(int start, int end) {
		String sql = "select * from(SELECT @RNUM := @RNUM + 1 AS ROWNUM, t.* FROM ( SELECT * FROM BOARD ORDER BY BOARD_DATE DESC) t, ( SELECT @RNUM := 0 ) R) t2 where t2.rownum between ? and ?;";


		ArrayList<BoardBean> boardList = new ArrayList<BoardBean>();
		BoardBean boardBean = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				boardBean = new BoardBean();
				boardBean.setMember_id(rs.getString("MEMBER_ID"));
				boardBean.setBoard_num(rs.getInt("BOARD_NUM"));
				boardBean.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				boardBean.setBoard_content(rs.getString("BOARD_CONTENT"));
				boardBean.setBoard_date(rs.getDate("BOARD_DATE"));
				//boardBean.setBOARD_VIDEO_FILE(rs.getString("BOARD_FILE"));
				//boardBean.setBOARD_VIDEO_URL(rs.getString("BOARD_URL"));
				boardBean.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
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
		} finally {
			close(pstmt);
			close(rs);
		}

		return boardList;

	}
	

}