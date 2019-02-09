package ajax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bean.BoardBean;
import service.BoardSearchService;

public class SearchBoardAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String constraint = request.getParameter("constraint");
		String key = request.getParameter("key");
		
		System.out.println(constraint);
		System.out.println(key);
		
		BoardSearchService bss = new BoardSearchService();
		
		ArrayList<BoardBean> list = null;
		
		if(constraint.equals("both")) {
			
			list = bss.searchBoth(key);
			
			
		}else if(constraint.equals("title")) {
			
			list = bss.searchTitle(key);
			
			
		}else if(constraint.equals("content")) {
			
			list = bss.searchContent(key);
			
		}
		
		JSONArray arr = new JSONArray();
		for(BoardBean board : list) {
			JSONObject obj = new JSONObject();
			obj.put("member_id", board.getMember_id());
			obj.put("board_num", board.getBoard_num());
			obj.put("board_subject", board.getBoard_subject());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			obj.put("board_date", sdf.format(board.getBoard_date()));
			obj.put("board_readcount", board.getBoard_readcount());
			obj.put("roomname", board.getRoomname());
			obj.put("comment_count", board.getComment_count());
			arr.add(obj);
		}
		
		return arr.toJSONString();
		
	}
	
}
