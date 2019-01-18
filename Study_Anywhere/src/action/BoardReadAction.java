package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardReadService;

public class BoardReadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		BoardBean boardBean = new BoardBean();
		
		//HttpSession session = request.getSession();
		String board_Num = request.getParameter("board_Num");
		
		BoardReadService boardReadService = new BoardReadService();
		
		return null;
	}

}
