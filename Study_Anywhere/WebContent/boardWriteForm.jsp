<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<style type="text/css">

body{
	margin-top: 20px;
}

</style>

<script type="text/javascript">

function check(){
	var title = $('#title').val();
	var content = $('#content').val();
	
	if(title == '' || content == ''){
		alert('공백을 입력해주세요');
		return;
	}else{
		$('#write').attr('action','./boardWrite.do')
		$('#write').submit();
	}
	
}

function update(){
	var title = $('#title').val();
	var content = $('#content').val();
	
	if(title == '' || content == ''){
		alert('공백을 입력해주세요');
		return;
	}else{
		$('#write').attr('action','./boardUpdate.do')
		$('#write').submit();
	}
}

</script>

</head>
<body>
<div class="container">
<c:choose>
	<c:when test="${ boardBean.board_num != null }">
		<h2>글 수정하기</h2>
	</c:when>
	<c:otherwise>
	  <h2>글 작성하기</h2>
	</c:otherwise>
</c:choose>

  <form action="./boardWrite.do" method="POST" id="write">
  	  <label for="title">글제목 : </label>
  	  <input id="title" name="title" style="width: 500px" value="${ boardBean.board_subject }"></input>
    <div class="form-group">
      <label for="content">내용작성 : </label>
      <textarea class="form-control" rows="5" id="content" name="content" style="resize:none; height: 500px;" >${ boardBean.board_content }</textarea>
    </div>
    <input type="hidden" name="num" value="${ boardBean.board_num }">
    <input type="hidden" name="roomname" value="${ boardBean.roomname }">
    <input type="hidden" name="member_id" value="${ boardBean.member_id }">
  </form>
  <p>저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시글은 이용약관 및 관련법률에 의해 제재를 받으실 수 있습니다.</p>
  
  <c:choose>
    <c:when test="${ boardBean.board_num != null }">
    <button class="btn btn-primary float-right" onclick="update()">글수정</button>
    </c:when>
    <c:otherwise>
    <button class="btn btn-primary float-right" onclick="check()">글쓰기</button>
    </c:otherwise>
  </c:choose>
    <span class="float-right">&nbsp;&nbsp;</span>
    <button class="btn btn-primary float-right" onclick="history.back()">뒤로가기</button>
</div>


</body>
</html>