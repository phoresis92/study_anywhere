package action;

import javax.mail.*;
import java.util.Properties;

import service.EmailAuthService;
import service.MemberSetTempService;
import util.SHA256;
import util.TempPass;
import util.Gmail;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import bean.ActionForward;

public class MemberFindPassAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		System.out.println("이메일 인증 입력 id : " + request.getParameter("memberID"));

		String memberID = null;
		if (request.getParameter("memberID") != null) {
			memberID = request.getParameter("memberID");
		}
		if (memberID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디를 입력 해주세요.');");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			script.close();
		}

		// 임시 비밀번호 생성
		String tempPass = new TempPass().getTempPass();

		// 임시 비밀번호를 테이블에 입력
		MemberSetTempService memberSetTempService = new MemberSetTempService();
		boolean result = memberSetTempService.setMemberTemp(memberID, tempPass);

		ActionForward af = null;
		if (result) {
			System.out.println("임시 비밀번호 발급 완료");

			// 이메일 전송
			EmailAuthService eas = new EmailAuthService();

			String host = "http://127.0.0.1:80/InfantCareCenter/";
			String from = "phoresis92@gmail.com";
			String to = eas.getUserEmail(memberID); // 유저의 가입 이메일 가져오기.

			System.out.println("EmailAuthAction 에서 얻어온 이메일의 값 : " + to);

			String subject = "육아지원센터 회원 임시 비밀번호 이메일 입니다.";
			String content = "다음 사용자에 대한 비밀번호 초기화 요청입니다.<br/><br/>" + "사용자 아이디 : " + memberID + "<br/>"
					+ "사용자 임시 비밀번호 : " + tempPass + "<br/><br/>" + "다음 링크에 접속하여 인증을 진행하세요." + "<a href='" + host
					+ "memberInitPass.do?memberID=" + memberID + "&tempPass=" + tempPass + "'>비밀번호 초기화</a>";
			// 클릭하면 기존 비밀번호 -> 임시비밀번호 Update

			Properties p = new Properties();
			p.put("mail.smtp.user", from);
			p.put("mail.smtp.host", "smtp.googlemail.com");
			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");

			try {
				Authenticator auth = new Gmail();
				Session ses = Session.getInstance(p, auth);
				MimeMessage msg = new MimeMessage(ses);
				msg.setSubject(subject);
				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr);
				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO, toAddr);
				msg.setContent(content, "text/html;charset=UTF-8");
				Transport.send(msg);

				af = new ActionForward();
				af.setPath("./changeEmailSended.jsp");
				af.setRedirect(true);

			} catch (Exception e) {
				e.printStackTrace();
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('오류가 발생했습니다.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			}
		} else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}

		return af;
	}

}
