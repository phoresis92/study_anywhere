package ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import service.CalendarService;

public class AddEventAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String jstr = request.getParameter("event");
		JSONParser jparser = new JSONParser();
		JSONObject jobj = (JSONObject) jparser.parse(jstr);
		
		String title = (String) jobj.get("title");
		String start = (String) jobj.get("start");
		String end = (String) jobj.get("end");
		
		
		System.out.println(title);
		System.out.println(start);
		System.out.println(end);
		

		CalendarService cv = new CalendarService();
		int result = cv.addEvent(title,start,end);
		
		
		return result+"";
	}

}
