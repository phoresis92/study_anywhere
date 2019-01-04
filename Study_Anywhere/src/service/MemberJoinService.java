package service;

import static db.JDBCUtil.*;

import java.sql.*;

import bean.MemberBean;
import dao.MemberDAO;

public class MemberJoinService {

	/** 회원가입 */
	public int joinMember(MemberBean mb) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		int result = memberDAO.joinMember(mb);

		
		// 가입 결과에 따른 커밋, 롤백
		if(result!=0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	
	/** id 중복체크 */
	public boolean idCheck(String inputId) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean result = memberDAO.idCheck(inputId);
		
		close(con);
		return result;
	}

}
