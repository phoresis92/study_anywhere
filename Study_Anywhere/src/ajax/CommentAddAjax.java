package ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import bean.Comment;
import service.CommentService;

public class CommentAddAjax implements Ajax {

	@Override
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String board_num = request.getParameter("board_num");
		String username = request.getParameter("username");
		String content = request.getParameter("content");

		

		Comment comm = new Comment();
		comm.setMember_id(username);
		comm.setBoard_num(Integer.parseInt(board_num));
		comm.setComment_content(content);
		
		CommentService cs = new CommentService();
		int comment_num = cs.getCommentNum();

		int flag = cs.addComment(comm);
		if(flag == 1) {
			System.out.println("success add comment");
		}else {
			System.out.println("fail to add comment");
		}
		
		comm = cs.getCommentObj(comment_num);
		
		JSONObject obj = new JSONObject();
		obj.put("board_num", comm.getBoard_num());
		obj.put("member_id", comm.getMember_id());
		obj.put("comment_num", comm.getComment_num());
		obj.put("comment_content", comm.getComment_content());
		obj.put("comment_date", comm.getComment_date());
		
		
		
		return obj.toJSONString();
	}

}
