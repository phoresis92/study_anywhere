<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.BoardBean"%>
<%-- <%@ page import="bean.CommentBean"%> --%>
<%@ page import="bean.PageInfo"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) request.getAttribute("boardlist");
	//페이징 정보 가져오기
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int totalPage = pageInfo.getTotalPage();
	int countList = pageInfo.getCountList();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>게시판</title>
</head>

<body>

	<!--리스트 페이지의 몸통부분 -->
	<div class="main" style="height: 1080px; width: 1920px">


			<table>
			<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
			<!-- <th>좋아요</th> -->
			</tr>
			<c:forEach var="board" items="${boardlist}" varStatus="status">
				
				<tr>
				<td>${board.board_num}</td>
				<td><a href="./board${ board.board_num }">${board.board_subject}</a></td>
				<td>${board.member_id}</td>
				<td>${board.board_date}</td>
				<td>${board.board_readcount}</td>
				</tr>

			</c:forEach>
			</table>
			
			<h1>nowPage: ${ pageInfo.page }</h1>
			<h1>startPage: ${ pageInfo.startPage }</h1>
			<h1>endPage: ${pageInfo.endPage }</h1>
			<h1>totalPage: ${pageInfo.totalPage }</h1>

			<div>
				<%
					if (nowPage <= 1) {
				%>
				<button type="button">이전</button>
				<%
					} else {
				%>
				<button type="button"
					onclick="location='boardList.do?page=<%=nowPage - 1%>'">이전</button>
				<%
					}
				%>
				<!-- <div > -->
					<%
					for (int a = startPage; a <= endPage; a++) {
						if (a == nowPage) {
				%>
					<button type="button"><%=a%></button>
					<%
					} else {
				%>
					<button type="button"
						onclick="location='boardList.do?page=<%=a%>'"><%=a%></button>
					<%
					}
				%>
					<%
					}
				%>
				<!-- </div> -->
				<%
					if (nowPage >= totalPage) {
				%>
				<button type="button">다음</button>
				<%
					} else {
				%>
				<button type="button" 
					onclick="location='boardList.do?page=<%=nowPage + 1%>'">다음</button>
				<%
					}
				%>
				<c:choose>
					<c:when test="${ pageInfo.page eq 1 }">
				<div>
					<button >처음페이지</button>
					<button onclick="location='boardList.do?page=${pageInfo.totalPage}'">마지막페이지</button>
				</div>
					</c:when>
				
					<c:when test="${ pageInfo.page eq pageInfo.totalPage }">
				<div>
					<button onclick="location='boardList.do?page=1'">처음페이지</button>
					<button>마지막페이지</button>
				</div>
					</c:when>
					<c:otherwise>
				<div>
					<button onclick="location='boardList.do?page=1'">처음페이지</button>
					<button onclick="location='boardList.do?page=${pageInfo.totalPage}'">마지막페이지</button>
				</div>
					</c:otherwise>
				</c:choose>


			</div>
		</div>
	



	<script>
		function alertSelectCategory() {
			var whatCategory = document.getElementById("selectedCategory");
			if (whatCategory.value == "select") {
				alert('게시판을 선택하세요.');
				document.getElementById("category").focus();
				return false;
			} else {
				return true;
			}
		}
	</script>

</body>
</html>