package action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;
import service.MemberLoginService;
import util.SHA256;
import util.SHAHash256;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// request 객체의 정보 가져오기(가입정보)
		String id = request.getParameter("loginId");
		String pwd = request.getParameter("loginPwd");
		String MEM_ID = request.getParameter("loginId");

		System.out.println("-- MemberLoginAction");
		System.out.println("id : " + id);
		System.out.println("pwd : " + pwd);
		System.out.println();

		// 현재시간
		Date nowDate = new Date();
		System.out.println("nowDate" + nowDate);

		// 가입정보를 하나의 객체로 합침
		MemberBean mb = new MemberBean();

		mb.setMEMBER_ID(id);
		mb.setMEMBER_PW(pwd);

		MemberLoginService mls = new MemberLoginService();

		MemberBean loginInfo = mls.loginMember(mb);
		
		// 로그인 후처리를 위한 변수
		ActionForward af = null;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		// 1) 로그인 정보를 불러오지 못했을 경우(로그인 실패)
		if (loginInfo == null) {
			out.println("<script>");
			out.println("alert('로그인에 실패했습니다.');");
			out.println("location.href='./index.html';");
			out.println("</script>");
			out.close();
			return af;
		}
		
		System.out.println("id : " + loginInfo.getMEMBER_ID());
		System.out.println("pw : " + loginInfo.getMEMBER_PW());
		System.out.println("email : " + loginInfo.getMEMBER_EMAIL());
		System.out.println("pwcheck : " + loginInfo.getMEMBER_SETTEMP());
		System.out.println("emailcheck : " + loginInfo.getMEMBER_CHECKED());
		System.out.println("date : " + loginInfo.getMEMBER_DATE());
		System.out.println("suspened : " + loginInfo.getMEMBER_SUSPENED());
		
		
		
		
		
		
		// 정지 회원을 위한 데이터 불러오기
		Date Member_suspendDate = loginInfo.getMEMBER_SUSPENED();
		System.out.println("Member_suspendDate" + Member_suspendDate);
		
		// 2) 정지된 회원이 로그인 할 경우 
//		if (loginInfo.getMEMBER_SUSPENED() != null) {
//			if ( Member_suspendDate.compareTo(nowDate) == -1 || Member_suspendDate.compareTo(nowDate) == 0) { // || Member_suspendDate.compareTo(nowDate) == 0 1
//				MemberSuspendRelieveService memberSuspendRelieveService = new MemberSuspendRelieveService();
//				boolean deleteResult = memberSuspendRelieveService.RelieveSuspendBoard(MEM_ID);
//				
//				HttpSession session = request.getSession();
//				session.setAttribute("loginInfo", loginInfo);
//				af = new ActionForward();
//				// 성공 했으면 다시 메인 페이지로
//				af.setPath("./index.jsp");
//			} else {
//				out.println("<script>");
//				System.out.println("loginInfo.getMEMBER_SUSPENED() " + loginInfo.getMEMBER_SUSPENED());
//				out.println("alert('"+loginInfo.getMEMBER_SUSPENED()+"일까지 정지된 회원입니다.');");
//				out.println("location.href='./index.jsp';");
//				out.println("</script>");
//				out.close();
//			}
//		}
		// 3) 임시 비밀번호로 로그인할 경우 비밀번호 변경 페이지로 이동
		if (loginInfo.getMEMBER_SETTEMP() == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
			session.setAttribute("memberID", loginInfo.getMEMBER_ID());
			af = new ActionForward();
			af.setPath("./changeFromTemp.jsp");
			af.setRedirect(true);
		}
		// 4) 이메일 인증을 수행하지 않았을 경우
		else if (loginInfo.getMEMBER_CHECKED() == 0) {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
			session.setAttribute("memberID", loginInfo.getMEMBER_ID());
			af = new ActionForward();
			af.setPath("./emailSendConfirm.jsp");
			af.setRedirect(true);
		}
		// 로그인 성공
		else if (loginInfo != null) {
			HttpSession session = request.getSession();
			// 세션에 로그인 정보 저장
			session.setAttribute("loginInfo", loginInfo);
			
			SHAHash256 hash = new SHAHash256();
			
			
			session.setAttribute("hash", hash.sha256(loginInfo.getMEMBER_PW()+loginInfo.getMEMBER_EMAIL()));
			af = new ActionForward();
			// 성공 했으면 다시 메인 페이지로
			//af.setPath("http://127.0.0.1:3000?memID="+loginInfo.getMEMBER_ID()+"&hash="+hash.sha256(loginInfo.getMEMBER_PW()+loginInfo.getMEMBER_EMAIL()));
			af.setPath("./toNode.jsp");
		}
		return af;
	}

}
