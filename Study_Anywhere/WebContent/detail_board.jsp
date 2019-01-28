<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>


<style type="text/css">

body{

margin-top:30px;
margin-bottom: 30px;

}

</style>

<script>

$(document).ready(function(){
	$.ajax({
		type: "post",
		url: 'commentGetAll.do',
		data:{
			'board_num': '${boardBean.board_num}'
		},
		success:function(data){
			var list = JSON.parse(data);
			for(var i in list){
				
				$('<div></div>').attr('id',list[i].comment_num).appendTo('#comment');
				$('<span></span>').attr('style','color: white;').attr('class','bg-primary').text(list[i].member_id).appendTo('#'+list[i].comment_num+'');
				$('<span></span>').text(' ').appendTo('#'+list[i].comment_num+'');
				$('<span></span>').text(list[i].comment_date).appendTo('#'+list[i].comment_num+'');
				//$('<button class="btn btn-sm btn-info"></button>').text('답글').appendTo('#'+list[i].comment_num+'');
				$('<div></div>').text(list[i].comment_content).attr('class','lead').appendTo('#'+list[i].comment_num+'');
				$('<hr>').appendTo('#'+list[i].comment_num+'');
			}
			$('#commentNum').text(list.length);
		}
	})//ajax end
	 
	
	
	
});//ready end


function del(){
	
	if(confirm('정말 삭제하시겠습니까?')){
		
		$('#delForm').submit();
		
	}else{
		return;		
	}
	
}

function update(){
	
	$('#updateForm').submit();
	
}

function addComment(){
	if($('#content').val() == ''){
		alert('댓글을 입력해주세요');
		return;
	}
	$.ajax({
		type: "post",
		url: './commentAdd.do',
		data:{
			'board_num': '${boardBean.board_num}',
			'username': '${ sessionScope.memberID }',
			'content': $('#content').val()
		},
		success: function(data){
			console.log(JSON.parse(data));
			var obj = JSON.parse(data)
			$('<div></div>').attr('id',obj.comment_num).appendTo('#comment');
			$('<span></span>').attr('style','color: white;').attr('class','bg-primary').text(obj.member_id).appendTo('#'+obj.comment_num+'');
			$('<span></span>').text(' ').appendTo('#'+obj.comment_num+'');
			$('<span></span>').text(obj.comment_date).appendTo('#'+obj.comment_num+'');
			//$('<button class="btn btn-sm btn-info"></button>').text('답글').appendTo('#'+list[i].comment_num+'');
			$('<div></div>').text(obj.comment_content).attr('class','lead').appendTo('#'+obj.comment_num+'');
			$('<hr>').appendTo('#'+obj.comment_num+'');
			
		}
	});//ajax end
	$('#content').val('')
	
}// addComment end

</script>

</head>
<body>


<div class="container">

	<div class="clearfix">
		<button class="btn btn-primary float-left" onclick="location.href='./boardList.do'">목록보기</button>
		<c:if test="${ boardBean.member_id eq sessionScope.memberID }">
		<button class="btn btn-primary float-right" onclick="update()">수정하기</button>
		<span class="float-right">&nbsp;&nbsp;</span>
		<button class="btn btn-primary float-right" onclick="del()">삭제하기</button>
		</c:if>
	</div>
<br>

	<table class="table table-bordered">
	<thead class="thead-light">
	<tr>
 	<th colspan="3" style="font-size: 20px">제목 : ${boardBean.board_subject }</th>
	</tr>
	</thead>
	<tr>
	<td>글번호 : ${boardBean.board_num }</td>
	<td>작성자 : ${ boardBean.member_id }</td>
	<td>작성일 : ${boardBean.board_date }</td>
	</tr>
	<tr>
	<td colspan="3" style="height: 250px">${boardBean.board_content }</td>
	</tr>
	
	</table>


	
	<form method="POST" action="./deleteBoard.do" id="delForm">
		<input type=hidden name="bNum" value="${ boardBean.board_num }">
	</form>



	<br>
	
	<span>댓글수</span><span id="commentNum"></span><span>&nbsp;&nbsp;&nbsp;</span><sapn>조회수</sapn><span>${ boardBean.board_readcount }</span>
	<div id="comment">
		<div class="row">
			<div class="col-10">
				<textarea class="form-control" rows="2" cols="10" id="content" name="content" style="resize:none; " ></textarea>
			</div>
			<div class="col-2">
				<button class="btn btn-primary btn-lg" onclick="addComment()" >등록</button>
			</div>
		</div>
	</div>
	
	
	
</div>
	
	<form method="POST" id="updateForm" action="./boardUpdate.do">
		<input type="hidden" name="board_num" value="${ boardBean.board_num }">
		<input type="hidden" name="board_subject" value="${ boardBean.board_subject }">
		<input type="hidden" name="board_content" value="${ boardBean.board_content }">
		<input type="hidden" name="member_ID" value="${ boardBean.member_id }">
		<input type="hidden" name="roomname" value="${ boardBean.roomname }">
		<input type="hidden" name="upCheck" value="update">
	</form>

</body>
</html>