package ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bean.BoardBean;
import service.BoardListService;

public class GetBoardFiveAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		BoardListService boardListService = new BoardListService();

		String roomname = request.getParameter("roomname");
		System.out.println("GetBoardFiveAjax"+roomname);
		
		ArrayList<BoardBean> list = boardListService.getBoardList(roomname);
		
		JSONArray arr = new JSONArray();
		int limit = 5;
		if(list.size() < 5) {
			limit = list.size();
		}
		
		for(int i = 0 ; i < limit ; i++) {
			JSONObject obj = new JSONObject();
			obj.put("member_id", list.get(i).getMember_id());
			obj.put("board_num", list.get(i).getBoard_num());
			obj.put("board_subject", list.get(i).getBoard_subject());
			obj.put("board_date", list.get(i).getBoard_date());
			obj.put("board_readcount", list.get(i).getBoard_readcount());
			obj.put("roomname", list.get(i).getRoomname());
			obj.put("comment_count", list.get(i).getComment_count());
			arr.add(obj);
		}
		
		return arr.toJSONString();
		
	}

}
