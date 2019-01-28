package bean;

public class Comment {
	
	private int board_num;
	private String member_id;
	private int comment_num;
	private String comment_content;
	private String comment_date;
	
	
	
	public Comment() {
	}
	public Comment(int board_num, String member_id, int comment_num, String comment_content, String comment_date) {
		super();
		this.board_num = board_num;
		this.member_id = member_id;
		this.comment_num = comment_num;
		this.comment_content = comment_content;
		this.comment_date = comment_date;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	
	
	
	
}
