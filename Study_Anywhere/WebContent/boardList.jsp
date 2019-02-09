<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.BoardBean"%>
<%@ page import="bean.PageInfo"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
  
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>게시판</title>

<style type="text/css">

body{
	margin: 20px;
}

tr:hover{

cursor: pointer;
color: green;

}

</style>

<script>

function detail(j){
	/* location.href="./boardDetail.do?bnum="+j; */
	
	$('<form></form>').attr('action',"./boardDetail.do").attr('method', 'POST').attr('id','detail').appendTo('#body');
	$('<input></input>').attr('type','hidden').attr('value',j).attr('name','bnum').appendTo('#detail');
	$('#detail').submit();
}


function search(){
	var key = $('#key').val();
	if(key == ''){
		alert('검색어를 입력해주세요');
		return;
	}
	
	var constraint = $('#constraint').val();
	
	$.ajax({
		type:"POST",
		url: './boardSearch.do',
		data:{
			'key': key,
			'constraint': constraint
		},
		success:function(data){
			console.log(data);
			var list = eval(data);
			//var list = JSON.parse(data);
			console.log(list);
			
			$('#tbody').empty();
			
			for(var i in list){
				console.log(i)
				
				$('<tr></tr>').attr('onclick','detail('+list[i].board_num+')').attr('id',list[i].board_num).appendTo('#tbody');
				$('<td></td>').text(list[i].board_num).appendTo('#'+list[i].board_num+'');
				$('<td></td>').text(list[i].board_subject).appendTo('#'+list[i].board_num+'');
				$('<td></td>').text(list[i].member_id).appendTo('#'+list[i].board_num+'');
				$('<td></td>').text(list[i].board_date).appendTo('#'+list[i].board_num+'');
				$('<td></td>').text(list[i].board_readcount).appendTo('#'+list[i].board_num+'');
				$('<td></td>').text(list[i].comment_count).appendTo('#'+list[i].board_num+'');
				
				
			}
			
			
		}
	});
	
}

</script>

</head>

<body style="overflow-x:hidden" id="body">



<h2 style="text-align: center">${ requestScope.roomname } 게시판</h2>
<div class="container-fluid">
  <div class="table-responsive">            
  <table class="table table-bordered table-striped table-hover ">
    <thead class="thead-dark">
      <tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>댓글수</th>
			<!-- <th>좋아요</th> -->
			</tr>
    </thead>
    <tbody id="tbody">
    		<c:choose>
    		<c:when test="${ fn:length(boardlist) == 0 }"></c:when>
    		<c:otherwise>
			<c:forEach var="board" begin="${ pageInfo.startNum }" end="${ pageInfo.endNum }"  items="${boardlist}" varStatus="status">
				
				<tr onclick="detail(${board.board_num})">
				<td>${board.board_num}</td>
				<td>${board.board_subject}</td>
				<td>${board.member_id}</td>
				<td>${board.board_date}</td>
				<td>${board.board_readcount}</td>
				<td>${board.comment_count }</td>
				</tr>

			</c:forEach>
			</c:otherwise>
			</c:choose>
    </tbody>
  </table>
  </div>
</div>


	<div class="row">
	<div class="col-4"></div>
	<input class="col-3" type="text" id="key">
	<select id="constraint">
		<!-- <option value="both">제목+내용</option> -->
		<option value="title">제목</option>
		<option value="content">내용</option>
	</select>
	<button class="btn btn-primary col-1" onclick="search()">검색</button>
	<button class="btn btn-primary col-1" onclick="location.href='./boardWriteForm.jsp'" >글쓰기</button>
	
	
	</div>



	<!--리스트 페이지의 몸통부분 -->
	<div class="main">

			<div>
			
			<ul class="pagination justify-content-center">
			
				<c:choose>
					<c:when test="${ pageInfo.page eq 1 }">
					
					<li class="page-item disabled"><a class="page-link" href="#">1</a></li>
					
					</c:when>
					
					<c:when test="${ pageInfo.page eq 0 }">
					
					<li class="page-item disabled"><a class="page-link" href="#">1</a></li>
					
					</c:when>
				
					<c:otherwise>
					
					<li class="page-item"><a class="page-link" href="boardList.do?page=1">1</a></li>
					
					</c:otherwise>
				</c:choose>
			
			
				<c:choose>
					<c:when test="${ pageInfo.page <= 1 }">
										
					<li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
					
					</c:when>
					
					<c:otherwise>
					
					<li class="page-item"><a class="page-link" href="boardList.do?page=${ pageInfo.page -1 }">이전</a></li>
					
					</c:otherwise>
					
				</c:choose>
			
			
			
				<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
				
					<c:choose>
					<c:when test="${i eq pageInfo.page }">
					
					<li class="page-item active"><a class="page-link" href="boardList.do?page=${ i }">${ i }</a></li>
					
					</c:when>
					
					<c:otherwise>
					
					<li class="page-item"><a class="page-link" href="boardList.do?page=${ i }">${ i }</a></li>
					
					</c:otherwise>
					
					</c:choose>
				</c:forEach>
			
			
			
			
				<c:choose>
				
					<c:when test="${ pageInfo.page >= pageInfo.totalPage }">
					
					<li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
					
					</c:when>
					
					<c:otherwise>
					
					<li class="page-item"><a class="page-link" href="boardList.do?page=${ pageInfo.page +1 }">다음</a></li>
	
					</c:otherwise>
				
				</c:choose>
				
			


				
				<c:choose>
				
					<c:when test="${ pageInfo.page eq pageInfo.totalPage }">
					
					<li class="page-item disabled"><a class="page-link" href="#">${ pageInfo.totalPage }</a></li>
					
					</c:when>
					
					<c:otherwise>
					
					<li class="page-item"><a class="page-link" href="boardList.do?page=${ pageInfo.totalPage }">${ pageInfo.totalPage }</a></li>
					
					</c:otherwise>
					
				</c:choose>
				
			</ul> <!-- Paging end -->


			</div>
		</div> <!-- main paging div end -->
	
	



</body>
</html>