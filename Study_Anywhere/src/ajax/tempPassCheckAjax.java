package ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberModifyService;

public class tempPassCheckAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("tempPassCheckAjax");
		
		StringBuffer result = new StringBuffer("");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String temppass = request.getParameter("temppass");
		String memberID = request.getParameter("memberID");
		System.out.println(temppass);
		
		/** JSON syntax
		 * {
		 *	"employees":[ "John", "Anna", "Peter" ]
		 * }
		 * */
		
		MemberModifyService membermodifyService = new MemberModifyService();
		boolean checkResult = membermodifyService.tempPassCheck(temppass, memberID);
		
		result.append("{\"result\":");
		
		if(checkResult) {
			result.append("\"yes\"");
		} else {
			result.append("\"no\"");
		}
		result.append("}");
		
		return result.toString();
	}

}
