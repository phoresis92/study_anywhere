package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;
import service.MemberModifyService;

public class MemberInitPassAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//session 객체의 정보 가져오기(가입정보)
		HttpSession session = request.getSession();
		
		String memberID = request.getParameter("memberID");
		String tempPass = request.getParameter("tempPass");
		
		MemberModifyService memberModifyService = new MemberModifyService();
		boolean result = memberModifyService.modifyMember(memberID, tempPass);
		
		ActionForward af = null;
		PrintWriter out = response.getWriter();

		if(result) {
			System.out.println("수정 완료");
			out.println("<script>");
			out.println("alert('비밀번호 초기화 완료');");
			out.println("location.href='index.jsp';");
			out.println("</script>");
			out.close();
		} else {
			out.println("<script>");
			out.println("alert('정보가 수정되지 않았습니다.');");
			out.println("location.href='index.jsp';");
			out.println("</script>");
			out.close();
		}
		return af;
	}

}

