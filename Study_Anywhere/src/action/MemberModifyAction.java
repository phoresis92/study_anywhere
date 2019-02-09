package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;
import service.MemberModifyService;

public class MemberModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//session 객체의 정보 가져오기(가입정보)
		HttpSession session = request.getSession();
		
		//======================================
		String url = "54.180.100.17";
		//======================================
		
		// 사용자의 현재 로그인 정보
		MemberBean mb = (MemberBean) session.getAttribute("loginInfo");
		
		// 사용자가 입력한 비밀번호
		String currentPassword = (String) request.getParameter("currentPassword");
		String changePassword = (String) request.getParameter("changePassword");
		
		MemberModifyService mms = new MemberModifyService();
		boolean result = mms.modifyMember(mb, currentPassword,changePassword); 

		ActionForward af = null;
		PrintWriter out = response.getWriter();
		if(result) {
			System.out.println("수정 완료");
			out.println("<script>");
			out.println("alert('정보수정이 완료되었습니다.');");
			out.println("location.href='index.jsp';");
			out.println("</script>");
			out.close();
		} else {
			out.println("<script>");
			out.println("alert('현재 비밀번호를 확인해주세요.');");
			out.println("location.href='myPage.jsp';");
			out.println("</script>");
			out.close();
		}
				
		return af;
	}

}

