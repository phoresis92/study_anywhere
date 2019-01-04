package service;

import static db.JDBCUtil.close;
import static db.JDBCUtil.commit;
import static db.JDBCUtil.getConnection;
import static db.JDBCUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberSuspendRelieveService {

	public boolean RelieveSuspendBoard(String mem_ID) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean relieveResult = false;
		
		int relieveCount = memberDAO.relieveSuspendBoard(mem_ID);
		
		if(relieveCount > 0) {
			commit(con);
			relieveResult = true;
		}else {
			rollback(con);
			relieveResult = false;
		}
		close(con);
		return relieveResult;
	}

}
