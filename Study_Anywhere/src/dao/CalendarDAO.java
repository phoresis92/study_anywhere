package dao;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CalendarDAO {
	
	private static CalendarDAO calendarDAO;
	private CalendarDAO() {
		
	}
	
	// MemberDAO 인스턴스 생성 메소드
	public static CalendarDAO getInstance() {
		if(calendarDAO==null) {
			calendarDAO = new CalendarDAO();
		}
		
		return calendarDAO;
	}
	
	// db 설정용 필드
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// db 연결 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	public int addEvent(String title, String start, String end, String roomname, String username) {
		String sql = "insert into calendar(roomname, member_id, caldate1, caldate2, calevent, calcode) values (?,?, ? , ? , ?, 2);";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roomname);
			pstmt.setString(2, username);
			pstmt.setString(3, start);
			pstmt.setString(4, end);
			pstmt.setString(5, title);
			
			result = pstmt.executeUpdate();
			
			if (result == 1) {
				System.out.println("입력 성공");
				commit(con);
			}else {
				System.out.println("입력실패");
				rollback(con);
			}

			
			sql = "select max(calnum) from calendar;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
						
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO에서의 에러 메세지"+e.getMessage());
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int removeEvent(String calnum) {
		String sql = "delete from calendar where calnum = ? ;";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(calnum));
			
			result = pstmt.executeUpdate();
			
			if (result == 1) {
				System.out.println("삭제 성공");
				commit(con);
			}else {
				System.out.println("삭제 실패");
				rollback(con);
			}
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO에서의 에러 메세지"+e.getMessage());
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int modifyEvent(String calnum, String title, String start, String end) {
		String sql = "update calendar set calevent = ? , caldate1 = ? , caldate2= ? where calnum = ? ;";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, start);
			pstmt.setString(3, end);
			pstmt.setInt(4, Integer.parseInt(calnum));
			
			result = pstmt.executeUpdate();
			
			if (result == 1) {
				System.out.println("수정 성공");
				commit(con);
			}else {
				System.out.println("수정 실패");
				rollback(con);
			}
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO에서의 에러 메세지"+e.getMessage());
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public JSONArray getAllEvent(String roomname) {
		String sql = "select calnum, caldate1, caldate2, calevent, member_id from calendar where roomname = ?;";
		int result = 0;
		
		JSONArray jarr = new JSONArray();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roomname);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				JSONObject data = new JSONObject();
				data.put("calnum", rs.getInt(1));
				data.put("start", rs.getString(2));
				data.put("end", rs.getString(3));
				data.put("title", rs.getString(4));
				data.put("username", rs.getString(5));
				jarr.add(data);
			}
			
			commit(con);
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("MemberDAO에서의 에러 메세지"+e.getMessage());
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return jarr;
	}
	

}
