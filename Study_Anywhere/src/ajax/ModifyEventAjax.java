package ajax;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ajax.Ajax;
import service.CalendarService;

public class ModifyEventAjax implements Ajax{

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String jstr = request.getParameter("event");
		JSONParser jparser = new JSONParser();
		JSONObject jobj = (JSONObject) jparser.parse(jstr);
		System.out.println(jobj);
		String calnum;
		try {
			calnum = (String) jobj.get("calnum");
		}catch(Exception e) {
			calnum = Integer.toString((int) jobj.get("calnum"));
		}
		String title = (String) jobj.get("title");
		String start = (String) jobj.get("start");
		String end = (String) jobj.get("end");
		
		System.out.println(calnum);
		System.out.println(title);
		System.out.println(start);
		System.out.println(end);
		

		CalendarService cv = new CalendarService();
		boolean result = cv.modifyEvent(calnum, title, start, end);
		
		String str = "";
		if(result) {
			str = "good";
		}else {
			str = "bad";
		}
		
		
		return str;
		
	}

	
	
}
