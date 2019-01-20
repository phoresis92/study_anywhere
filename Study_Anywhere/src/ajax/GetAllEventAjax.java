package ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import service.CalendarService;

public class GetAllEventAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String roomname = request.getParameter("roomname");
		
		CalendarService cv = new CalendarService();
		JSONArray result = cv.getAllEvent(roomname);
		
		return result.toString();
	}

	
	
}
