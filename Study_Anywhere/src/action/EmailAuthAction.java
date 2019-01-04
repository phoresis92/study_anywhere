package action;

import javax.mail.*;
import java.util.Properties;

import service.EmailAuthService;
import util.SHA256;
import util.Gmail;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import bean.ActionForward;

public class EmailAuthAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();

		System.out.println("이메일 인증, session id : " + session.getAttribute("memberID"));
		
		String memberID= null;
		if(session.getAttribute("memberID") != null) {
			memberID = (String) session.getAttribute("memberID");
			System.out.println("EmailAuthAction에서 memberID에 세션 값 저장 : "+memberID);
		}
		if(memberID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요');");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			script.close();
		}
		
		// 이메일 인증 여부 확인
		EmailAuthService eas = new EmailAuthService();
		boolean emailChecked = eas.getUserEmailChecked(memberID);
		System.out.println(emailChecked);
		
		if (emailChecked == true) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 인증된 회원 입니다.');");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			script.close();
		}
		
		String host = "http://127.0.0.1:80/InfantCareCenter/";
		String from = "phoresis92@gmail.com";
		String to = eas.getUserEmail(memberID); // 유저의 가입 이메일 가져오기.
		
		System.out.println("EmailAuthAction 에서 얻어온 이메일의 값 : "+to);
		
		String subject = "육아지원센터 회원 인증 이메일 입니다.";
		String content = "다음 링크에 접속하여 인증을 진행하세요."+
					"<a href='" + host + "emailCheckedAction.do?code=" + new SHA256().getSHA256(to) + "'>이메일 인증하기</a>"; 
		
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
		
		
		ActionForward af = null;
		
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
			af.setPath("./emailSended.jsp");
			af.setRedirect(true);
		
		} catch(Exception e) {
			e.printStackTrace();
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
