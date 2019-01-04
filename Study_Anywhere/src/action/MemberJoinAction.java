package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;
import service.MemberJoinService;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//request 객체의 정보 가져오기(가입정보)
		String id = request.getParameter("memberID");
		String pwd = request.getParameter("MEMBER_PW");
		String email = request.getParameter("MEMBER_EMAIL");
		
		System.out.println("-- MemberJoinAction");
		System.out.println("id :"+id);
		System.out.println("pwd :"+pwd);
		System.out.println("email :"+email);
		System.out.println();
		
		// 가입정보를 하나의 객체로 합침 
		MemberBean mb = new MemberBean();
		
		mb.setMEMBER_ID(id);
		mb.setMEMBER_PW(pwd);
		mb.setMEMBER_EMAIL(email);

		// 서비스에서 가입
		MemberJoinService mjs = new MemberJoinService();
		int result = mjs.joinMember(mb);
		
		ActionForward af = null;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		if(result!=0) {
			HttpSession session = request.getSession();
			session.setAttribute("memberID", mb.getMEMBER_ID());
			
			System.out.println("가입 완료, session id : " + session.getAttribute("memberID"));
			
			af = new ActionForward();
			af.setPath("./emailAuthAction.do");
	
			af.setRedirect(true);
		} else {
			out.println("<script>");
			out.println("alert('가입에 실패했습니다.')");
			out.println("location.href='./index.jsp';");
			out.println("</script>");
		}
		
		return af;
	}

}

