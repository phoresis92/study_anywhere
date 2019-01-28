package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardWriteService;
import service.CalendarService;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ActionForward actionForward = null;
		
		
		HttpSession session = request.getSession();
		
		String username = (String)session.getAttribute("memberID");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		
		CalendarService cs = new CalendarService();
		String arr[] = cs.getRoomname(username);
		
		String roomname = arr[0];
		
		
		BoardBean boardBean = new BoardBean();
		boardBean.setBoard_content(content);
		boardBean.setBoard_subject(title);
		boardBean.setMember_id(username);
		boardBean.setRoomname(roomname);
		
		
		BoardWriteService bws = new BoardWriteService();
		int result = bws.boardWrite(boardBean);
		
		if(result == 1) {
			System.out.println("success write");
		}else {
			System.out.println("fail to write");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('오류로 인해 게시물 작성에 실패했습니다.');");
			out.println("location.href='./boardList.do';");
			out.println("</script>");
			out.close();
			return actionForward;
			
			
		}
		
		
		actionForward = new ActionForward();
		actionForward.setPath("boardList.do?page=1");
		return actionForward;
	}

}
