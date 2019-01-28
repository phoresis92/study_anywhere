package dao;

import static db.JDBCUtil.*;

import java.sql.*;
import java.util.ArrayList;

import bean.MemberBean;

public class MemberDAO {
	// singleton
	private static MemberDAO memberDAO;
	private MemberDAO() {
		
	}
	
	// MemberDAO 인스턴스 생성 메소드
	public static MemberDAO getInstance() {
		if(memberDAO==null) {
			memberDAO = new MemberDAO();
		}
		
		return memberDAO;
	}
	
	// db 설정용 필드
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// db 연결 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	public int joinMember(MemberBean mb) {
		//String sql = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_EMAIL, MEMBER_DATE) VALUES(?,?,?, SYSDATE)";
		String sql = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_EMAIL) VALUES(?,?,?)";
		int result = 0;
		
		System.out.println(":: MemberDAO ::");
		System.out.println("id :" + mb.getMEMBER_ID());
		System.out.println("pass :" + mb.getMEMBER_PW());
		System.out.println("email :" + mb.getMEMBER_EMAIL());
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMEMBER_ID());
			pstmt.setString(2, mb.getMEMBER_PW());
			pstmt.setString(3, mb.getMEMBER_EMAIL());
			
			result = pstmt.executeUpdate();
						
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

	/** 이메일 인증 완료 검증 메소드 */
	public boolean getUserEmailChecked(String memberID) {
		String sql = "SELECT MEMBER_CHECKED FROM MEMBER WHERE MEMBER_ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt(1) == 1)
					return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false; // 데이터베이스 오류
	}

	
	public boolean idCheck(String inputId) {
		String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ?";
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString(1));
				result = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public String getUserEmail(String memberID) {
		String sql = "SELECT MEMBER_EMAIL FROM MEMBER WHERE MEMBER_ID = ?";
		String result = null;
		System.out.println("MemberDAO 로 넘어온 memberID의 값 : "+memberID);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("이메일 결과 값이 존재");
				result = rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result; // 데이터베이스 오류
	}
	
	
	public int setUserEmailChecked(String memberID) {
		String sql = "UPDATE MEMBER SET MEMBER_CHECKED = 1 WHERE MEMBER_ID = ?";
		System.out.println("setUserEmailChecked에 넘어온 유저 아이디 : "+memberID);
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			result = pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	public MemberBean loginMember(MemberBean mb) {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMEMBER_ID());
			rs = pstmt.executeQuery();
			System.out.println("::DB에서 아이디 값 : "+mb.getMEMBER_ID());
			System.out.println("로그인 시도\n");
			if(rs.next()) {
				System.out.println("쿼리 결과 존재");
				if(rs.getString("MEMBER_PW").equals(mb.getMEMBER_PW())) {
					System.out.println("로그인 성공");
					mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
					mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
					mb.setMEMBER_TEMPPASS(rs.getString("MEMBER_TEMPPASS"));
					mb.setMEMBER_SETTEMP(rs.getInt("MEMBER_SETTEMP"));
					mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
					mb.setMEMBER_CHECKED(rs.getInt("MEMBER_CHECKED"));
					mb.setMEMBER_DATE(rs.getDate("MEMBER_DATE"));
					mb.setMEMBER_SUSPENED(rs.getDate("MEMBER_SUSPENDED"));
				} else {
					System.out.println("비밀번호 틀림");
					mb = null;
				}
			} else {
				mb = null;
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return mb;
	}

	
	public int relieveSuspendBoard(String mem_ID) { // 멤버 정지 해제
		String sql = "UPDATE MEMBER SET MEMBER_SUSPENDED = NULL WHERE MEMBER_ID=?";
		int updateResult = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_ID);
			updateResult = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("relieveMember 오류" + e);
		} finally {
			close(pstmt);
		}
		return updateResult;
	}

	
	public int setTempMember(String memberID, String tempPass) {
		String sql = "UPDATE MEMBER SET MEMBER_TEMPPASS = ? WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tempPass);
			pstmt.setString(2, memberID);
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	public int modifyMember(String memberID, String tempPass) {
		String sql1 = "UPDATE MEMBER SET MEMBER_SETTEMP = 1 WHERE MEMBER_ID = ?";
		String sql2 = "UPDATE MEMBER SET MEMBER_PW = ? WHERE MEMBER_ID = ?";
		int result1 = 0;
		int result2 = 0;
		try {
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, memberID);
			result1 = pstmt.executeUpdate();

			if(result1 == 1) {
					System.out.println("수정 단계");
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, tempPass);
					pstmt.setString(2, memberID);
					result2 = pstmt.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return result2;
	}
	
	/** 마이페이지에서 정보 변경시 사용하는 메소드 */
	public int modifyMember(MemberBean mb, String currentPassword, String changePassword) {
		String confirm = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ?";
		String sql = "UPDATE MEMBER SET MEMBER_PW = ?, MEMBER_SETTEMP = 0 WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(confirm);
			pstmt.setString(1, mb.getMEMBER_ID());
			rs = pstmt.executeQuery();

			// 해당 아이디가 존재
			if(rs.next()) {
				System.out.println("아이디가 존재");
				// 입력 비밀번호가 일치
				if(rs.getString(1).equals(currentPassword)) {
					System.out.println("수정 단계");
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, changePassword);
					pstmt.setString(2, mb.getMEMBER_ID());
					result = pstmt.executeUpdate();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	
	public boolean tempPassCheck(String temppass, String memberID) {
		
		String sql = "SELECT MEMBER_TEMPPASS FROM MEMBER WHERE MEMBER_ID = ?";
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(temppass)) {
					System.out.println("rs.getString: "+rs.getString(1));
					result = true;
				}
				System.out.println(result);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public int dropMember(MemberBean mb, String inputPassword) {
		String confirm = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ?";
		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(confirm);
			pstmt.setString(1, mb.getMEMBER_ID());
			rs = pstmt.executeQuery();

			// 해당 아이디가 존재
			if(rs.next()) {
				// 입력 비밀번호가 일치
				if(rs.getString(1).equals(inputPassword)) {
					System.out.println("일단 true로 삭제 시도 까진 온다.");
					System.out.println("삭제할 아이디는 : "+mb.getMEMBER_ID());
					
					pstmt = con.prepareStatement(sql);
					System.out.println("pstmt = con.prepareStatement(sql); 시도");
					pstmt.setString(1, mb.getMEMBER_ID());
					System.out.println("pstmt.setString(1, mb.getMEMBER_ID());");
					
					result = pstmt.executeUpdate();
					System.out.println("result = pstmt.executeUpdate();");
					System.out.println(":: 삭제 결과");
					System.out.println("result : "+result);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	
}