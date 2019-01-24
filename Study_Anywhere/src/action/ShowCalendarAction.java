package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import service.CalendarService;

public class ShowCalendarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ActionForward af = null;
		
		
		HttpSession session = request.getSession();
		
		String username = (String)session.getAttribute("memberID");
		
		CalendarService cs = new CalendarService();
		String result[] = cs.getRoomname(username);
		
		
		
		
		request.setAttribute("username", username);
		request.setAttribute("roomname", result[0]);
		request.setAttribute("chief", result[1]);
		af = new ActionForward();
		af.setPath("./calendar.jsp");
		return af;
	}

}
