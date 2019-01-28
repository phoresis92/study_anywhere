package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardUpdateService;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ActionForward actionForward = null;
		
		if(request.getParameter("upCheck") == null) {
			//수정완료 
			BoardBean boardBean = new BoardBean();
			boardBean.setBoard_num(Integer.parseInt(request.getParameter("num")));
			boardBean.setBoard_subject(request.getParameter("title"));
			boardBean.setBoard_content(request.getParameter("content"));
			boardBean.setMember_id(request.getParameter("member_ID"));
			boardBean.setRoomname(request.getParameter("roomname"));
			
			BoardUpdateService bus = new BoardUpdateService();
			int result = bus.boardUpdate(boardBean);
			
			if(result == 1) {
				System.out.println("update success");
			}else {
				System.out.println("update fail");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('오류로 인해 게시물 삭제를 실패했습니다.');");
				out.println("location.href='./boardList.do';");
				out.println("</script>");
				out.close();
				return actionForward;
				
			}
			
			
		}else {
			//수정폼으로 보내기
			
			BoardBean boardBean = new BoardBean();
			boardBean.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
			boardBean.setBoard_subject(request.getParameter("board_subject"));
			boardBean.setBoard_content(request.getParameter("board_content"));
			boardBean.setMember_id(request.getParameter("member_ID"));
			boardBean.setRoomname(request.getParameter("roomname"));
			
			request.setAttribute("boardBean", boardBean);
			actionForward = new ActionForward();
			actionForward.setPath("./boardWriteForm.jsp");
			return actionForward;
			
		}
		
		
		
		actionForward = new ActionForward();
		actionForward.setRedirect(true);
		actionForward.setPath("./boardList.do");
		return actionForward;
	}

	
	
}
