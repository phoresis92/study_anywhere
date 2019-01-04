package service;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.MemberBean;
import dao.MemberDAO;

public class MemberSetTempService {

	public boolean setMemberTemp(String memberID, String tempPass) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean modifyResult = false;
		
		int result = memberDAO.setTempMember(memberID, tempPass);
		
		if(result != 0) {
			modifyResult = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return modifyResult;
	}
	
	
}
