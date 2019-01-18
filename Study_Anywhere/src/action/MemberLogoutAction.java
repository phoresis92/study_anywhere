package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		//session 객체의 정보 가져오기(가입정보)
		HttpSession session = request.getSession();
		MemberBean mb = (MemberBean) session.getAttribute("loginInfo");
				
		ActionForward af = null;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		if(mb!=null) {
			session.invalidate();			
			// 성공 했으면 다시 메인 페이지로
			af = new ActionForward();
			af.setRedirect(true);
			af.setPath("./index.jsp");
		} else {
			out.println("<script>");
			out.println("alert('로그인하지 않았습니다.');");
			out.println("location.href='./index.jsp';");
			out.println("</script>");
		}
		
		return af;
	}

}

