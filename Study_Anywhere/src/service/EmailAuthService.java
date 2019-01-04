package service;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.MemberBean;
import dao.MemberDAO;

public class EmailAuthService {

	public boolean getUserEmailChecked(String memberID) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean result = memberDAO.getUserEmailChecked(memberID);

		close(con);
		return result;
	}

	public String getUserEmail(String memberID) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		String memberEmail = memberDAO.getUserEmail(memberID);

		close(con);
		return memberEmail;
	}

	public boolean setUserEmailChecked(String memberID) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		int setResult = memberDAO.setUserEmailChecked(memberID);
		boolean result = false;
		if(setResult!=0) {
			commit(con);
			result = true;
		} else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
