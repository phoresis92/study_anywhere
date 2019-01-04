<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로딩중입니다</title>
<script type="text/javascript">
	window.onload = function() {
		document.form1.submit();
	};
</script>
</head>
<body>
	<form action="http://localhost:3000/identify" name="form1" method="post">
		<input type="hidden" name="mem_ID" value="${sessionScope.loginInfo.MEMBER_ID}">
		<input type="hidden" name="mem_Hash" value="${sessionScope.hash}">
	</form>
</body>
</html>