package ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bean.Comment;
import service.CommentService;

public class commentGetAllAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String board_num = request.getParameter("board_num");
		
		CommentService cs = new CommentService();
		ArrayList<Comment> list = cs.getAllComment(Integer.parseInt(board_num));
		
		
		JSONArray arr = new JSONArray();
		for(Comment comm : list) {
			JSONObject obj = new JSONObject();
			obj.put("board_num", comm.getBoard_num());
			obj.put("member_id", comm.getMember_id());
			obj.put("comment_num", comm.getComment_num());
			obj.put("comment_content", comm.getComment_content());
			obj.put("comment_date", comm.getComment_date());
			arr.add(obj);
		}
		
		return arr.toJSONString();
	}

}
