package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import service.BoardDeleteService;

public class BoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ActionForward actionForward = null;

		String bNum = request.getParameter("bNum");
		
		BoardDeleteService bds = new BoardDeleteService();
		int result = bds.boardDelete(Integer.parseInt(bNum));
		
		if(result==1) {
			System.out.println("delete success");
		}else {
			System.out.println("delete fail");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('오류로 인해 게시물 삭제를 실패했습니다.');");
			out.println("location.href='./boardList.do';");
			out.println("</script>");
			out.close();
			return actionForward;
		}
		
		actionForward = new ActionForward();
		actionForward.setRedirect(true);
		actionForward.setPath("./boardList.do");
		return actionForward;
	}
}
