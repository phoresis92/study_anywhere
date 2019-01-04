package action;

import javax.mail.*;
import java.util.Properties;

import service.EmailAuthService;
import util.SHA256;
import util.Gmail;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import bean.ActionForward;

public class EmailCheckedAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		/** 인증 값 가져오기 */
		String code = null;
		if(request.getParameter("code")!=null) {
			code = request.getParameter("code");
		}
		
		/** 로그인 처리 */
		String memberID= null;
		if(session.getAttribute("memberID") != null) {
			memberID = (String) session.getAttribute("memberID");
			System.out.println("EmailAuthAction에서 memberID에 세션 값 저장 : "+memberID);
		} 
		
		/** 비 로그인 접속 시 처리 */
		if(memberID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요');");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			script.close();
		}
		
		EmailAuthService eas = new EmailAuthService();
		String memberEmail = eas.getUserEmail(memberID);
		
		/** 이메일 인증 */
		boolean isRight = (new SHA256().getSHA256(memberEmail).equals(code)) ? true : false;
		
		if (isRight) {
			boolean setResult =  eas.setUserEmailChecked(memberID);
			if(setResult) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('인증에 성공했습니다.');");
				script.println("location.href = 'index.jsp';");
				script.println("</script>");
				script.close();
			} else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('정상적 인증코드이나, 데이터 베이스 오류가 있습니다./n 잠시 후 다시 시도해주세요.');");
				script.println("location.href = 'index.jsp';");
				script.println("</script>");
				script.close();
			}
			
		} else {
			session.setAttribute("memberID", memberID);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 코드 입니다.');");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			script.close();
		}

		return null;
	}

}
