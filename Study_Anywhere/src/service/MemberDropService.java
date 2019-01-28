package service;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.MemberBean;
import dao.MemberDAO;

public class MemberDropService {

	public boolean dropMember(MemberBean mb, String inputPassword) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);

		boolean dropResult = false;
		
		int result = memberDAO.dropMember(mb, inputPassword);
		
		System.out.println(":: dropService ::");
		System.out.println("dropMember result : "+result);
		System.out.println();
		
		if(result != 0) {
			dropResult = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return dropResult;
	}

}
