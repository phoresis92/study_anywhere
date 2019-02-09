package ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ajax.Ajax;
import bean.Comment;
import service.CommentService;

public class CommentDropAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String cnum = request.getParameter("cnum");
		
		CommentService cs = new CommentService();
		int result = cs.dropComment(Integer.parseInt(cnum));
		
		if(result == 1) {
			System.out.println("success drop comment");
		}else {
			System.out.println("fail drop comment");
		}
		
		
		return result+"";
	}

}
