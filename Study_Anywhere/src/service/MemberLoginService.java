package service;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.MemberBean;
import dao.MemberDAO;

public class MemberLoginService {

	public MemberBean loginMember(MemberBean mb) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		MemberBean loginInfo = memberDAO.loginMember(mb);

		close(con);
		if(loginInfo==null)
			System.out.println(":: 서비스에서 null을 보냄\n");
		return loginInfo;
	}

}
