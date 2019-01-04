package ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import service.MemberJoinService;

public class IdOverlapCheckAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("idOverlapCheckAjax");
		
		StringBuffer result = new StringBuffer("");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String inputId = request.getParameter("check");
		
		/** JSON syntax
		 * {
		 *	"employees":[ "John", "Anna", "Peter" ]
		 * }
		 * */
		
		MemberJoinService memberJoinService = new MemberJoinService();
		boolean checkResult = memberJoinService.idCheck(inputId);
		
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
