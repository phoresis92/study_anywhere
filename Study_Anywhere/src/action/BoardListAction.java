package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.BoardBean;
import bean.PageInfo;
import service.BoardListService;
import service.CalendarService;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");

		ActionForward actionForward = null;
		BoardListService boardListService = new BoardListService();
		
		
		
		HttpSession session = request.getSession();
		
		String username = (String)session.getAttribute("memberID");
		
		
		CalendarService cs = new CalendarService();
		String result[] = cs.getRoomname(username);
		
		String roomname = result[0];
		
		request.setAttribute("username", username);
		request.setAttribute("roomname", result[0]);
		request.setAttribute("chief", result[1]);
		
		
		ArrayList<BoardBean> list = boardListService.getBoardList(roomname);
		
		
		//==========================================================
		
		
		int page = 1;
		// page 파라미터 값 검사
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		
		int countList = 10; //페이지당 게시물 수
		int countPage = 10; //페이지 수
		
		
		int totalCount = list.size(); // 총 게시물 수
		int totalPage = totalCount / countList; // 총 페이지 수;
		if(totalCount%countList>0) { totalPage++; }
		if(totalPage < page) { page = totalPage; }
		
		
		int startPage = ((page-1)  / countPage ) * countPage + 1; 
		int endPage = startPage + countPage - 1;
		if (endPage > totalPage) { endPage = totalPage; }
		
		//==========================================================
		
		int startNum = (page-1) * countList +1;
		int endNum = page * countList;
		
		//ArrayList<BoardBean> boardList = boardListService.getboardlist(startNum , endNum);
		

		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setCountList(countList);
		pageInfo.setStartNum(startNum-1);
		pageInfo.setEndNum(endNum-1);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("boardlist", list);
		//request.setAttribute("commentlist", commentList);
		actionForward = new ActionForward();
		actionForward.setPath("boardList.jsp");
		return actionForward;
	}
}
