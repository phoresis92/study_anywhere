package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;

public class ShowCalendarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ActionForward af = null;
		
		
		String username = request.getParameter("username");
		String roomname = request.getParameter("roomname");
		
		
		request.setAttribute("username", username);
		request.setAttribute("roomname", roomname);
		af = new ActionForward();
		af.setPath("./calendar.jsp");
		return af;
	}

}
