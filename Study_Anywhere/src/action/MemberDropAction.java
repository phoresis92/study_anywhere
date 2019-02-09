package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;
import service.MemberDropService;

public class MemberDropAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//session 객체의 정보 가져오기(가입정보)
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		// 사용자의 현재 로그인 정보
		MemberBean mb = (MemberBean) session.getAttribute("loginInfo");
		
		// 사용자가 입력한 비밀번호
		String inputPassword = (String) request.getParameter("memberPW");
		
		
		
		ActionForward af = null;
		if(!mb.getMEMBER_PW().equals(inputPassword)) {
			out.println("<script>");
			out.println("alert('비밀번호를 확인해 주세요.');");
			out.println("location.href='./index.jsp';");
			out.println("</script>");
			out.close();
			return af;
		}
		
		
		/** 사용자 입력정보 확인 */
		System.out.println("id : "+mb.getMEMBER_ID());
		System.out.println("pw : "+inputPassword);
		System.out.println();
		
		MemberDropService mds = new MemberDropService();
		boolean result = mds.dropMember(mb, inputPassword); 
		if(result) {
			session.invalidate();
			out.println("<script>");
			out.println("alert('회원탈퇴 성공하였습니다.');");
			out.println("location.href='./index.jsp';");
			out.println("</script>");
			out.close();
			return af;
		} else {
			out.println("<script>");
			out.println("alert('탈퇴가 취소 되었습니다.');");
			out.println("location.href='./index.jsp';");
			out.println("</script>");
			out.close();
			return af;
		}
		
				
	}

}

