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
		
		ActionForward af = null;
		
		//HttpSession session = request.getSession();
		String board_Num = request.getParameter("bnum");
		System.out.println(board_Num); 
		
		BoardReadService boardReadService = new BoardReadService();
		int result = boardReadService.readCount(Integer.parseInt(board_Num));
		
		if(result == 1) {
			System.out.println("count success");
		}else {
			System.out.println("count fail");
		}
		
		BoardBean boardBean = boardReadService.getBoard(Integer.parseInt(board_Num));
		
		
		request.setAttribute("boardBean", boardBean);
		af = new ActionForward();
		af.setPath("./detail_board.jsp");
		return af;
	}

}
