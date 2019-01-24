package ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import service.CalendarService;

public class GetAllEventAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CalendarService cs = new CalendarService();		
		String roomname = null;
		
		if(request.getParameter("roomname") != null) {
			
			roomname = request.getParameter("roomname");


			JSONArray result = cs.getAllEvent(roomname);
			
			return result.toString();
		}else {
			
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("memberID");
			
			String[] arr = cs.getRoomname(username);
			
			JSONArray result = cs.getAllEvent(arr[0]);
			
			return result.toString();
		}
		

		
		

	}

	
	
}
