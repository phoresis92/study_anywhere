package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardListAction;
import action.EmailAuthAction;
import action.EmailCheckedAction;
import action.MemberFindPassAction;
import action.MemberInitPassAction;
import action.MemberJoinAction;
import action.MemberLoginAction;
import action.MemberLogoutAction;
import action.MemberModifyAction;
import action.ShowCalendarAction;
import ajax.AddEventAjax;
import ajax.Ajax;
import ajax.GetAllEventAjax;
import ajax.IdOverlapCheckAjax;
import ajax.ModifyEventAjax;
import ajax.RemoveEventAjax;
import ajax.tempPassCheckAjax;
import bean.ActionForward;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String RequestURI = request.getRequestURI();  
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		System.out.println("RequestURI : " + RequestURI);
		System.out.println("RequestURI : " + contextPath);
		System.out.println("command : " + command);
		System.out.println();

		Action action = null;
		Ajax ajax = null;
		ActionForward forward = null;
		String responseText = null;

		//===============================================================================
			// 회원가입_상단 바 
		if (command.equals("/memberJoin.do")) {
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//아이디 중복 체크 ajax
		} else if (command.equals("/idOverlapCheck.do")) {
			ajax = new IdOverlapCheckAjax();
			try {
				responseText = ajax.getJSON(request, response); // JSON
				response.getWriter().write(responseText);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 임시 비밀번호 체크 ajax
		} else if (command.equals("/tempPassCheck.do")) {
			ajax = new tempPassCheckAjax();
			try {
				responseText = ajax.getJSON(request, response); // JSON
				response.getWriter().write(responseText);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 회원 가입 시, 이메일 인증 보내기
		} else if (command.equals("/emailAuthAction.do")) {
			action = new EmailAuthAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 받은 이메일 인증작업
		} else if (command.equals("/emailCheckedAction.do")) {
			action = new EmailCheckedAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/memberLogin.do")) {
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//로그아웃
		} else if (command.equals("/memberLogout.do")) {
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//비밀번호 찾기
		} else if (command.equals("/memberFindPass.do")) {
			action = new MemberFindPassAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 임시비밀번호 처리
		} else if (command.equals("/memberInitPass.do")) { //
			action = new MemberInitPassAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 임시비밀번호 입력후 본인 비밀번호로 변경
		}  else if (command.equals("/memberInfoRivision.do")) {
			action = new MemberModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//===========================================================================
			// 게시판
		} else if (command.equals("/boardList.do")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 게시물 조회
		}else if (command.equals("/boardRead.do")) {
			//action = new BoardReadAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 달력 =======================================================================
		}else if(command.equals("/addEvent.do")) {
			ajax = new AddEventAjax();
			try {
				responseText = ajax.getJSON(request, response); // JSON
				response.getWriter().write(responseText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/removeEvent.do")) {
			ajax = new RemoveEventAjax();
			try {
				responseText = ajax.getJSON(request, response); // JSON
				response.getWriter().write(responseText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/modifyEvent.do")) {
			ajax = new ModifyEventAjax();
			try {
				responseText = ajax.getJSON(request, response); // JSON
				response.getWriter().write(responseText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/getAllEvent.do")) {
			ajax = new GetAllEventAjax();
			try {
				responseText = ajax.getJSON(request, response); // JSON
				response.getWriter().write(responseText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/showCalendar.do")) {
			action = new ShowCalendarAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 게시물 조회
		}

		/** 2. ActionForward 인스턴스에 따른 forwarding */
		if (forward != null) {
			if (forward.isRedirect()) {
				System.out.println(forward.isRedirect());
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
