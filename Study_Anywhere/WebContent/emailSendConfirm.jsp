<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bean.MemberBean" %>
<%@ page import="java.io.PrintWriter" %>
<%-- <%
	/* 로그인 사용자만 접속 가능 */
	MemberBean loginInfo = (MemberBean) session.getAttribute("loginInfo");

	if(loginInfo == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인 하셔야 이용하실 수 있는 서비스 입니다.')");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
	} else {
		if(loginInfo.getMEMBER_CHECKED()==0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일 인증 후 이용해 주세요.')");
			script.println("location.href='index.jsp'");
			script.println("</script>");
			script.close();
		}
	}
	

%> --%>

<!DOCTYPE html>
<html>
<head>
  <title>Study Anywhere</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }

    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
    
    .container{
    	padding: 130px;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="./">Study Anywhere</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    
<!--       <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Gallery</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
 -->      <ul class="nav navbar-nav navbar-right">
 

		<c:choose>
        	<c:when test="${empty loginInfo.getMEMBER_ID()}">
		<li><a data-toggle="modal" data-target="#loginForm"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
        <li><a data-toggle="modal" data-target="#joinForm"><span class="glyphicon glyphicon-user"></span> Sign up</a>
        	</c:when>
        	
        	<c:otherwise>
		<li><a href="./memberDetail.do"><span class="glyphicon glyphicon-log-in"></span> My page</a></li>
        <li><a href="./memberLogout.do"><span class="glyphicon glyphicon-log-out"></span> Sign out</a>
        	</c:otherwise>
        </c:choose>

      </ul>
    </div>
  </div>
</nav>


	<div class="container" style="max-width:560px">
		<div class="alert alert-warning mt-4" role="alert">
			이메일 주소 인증을 하셔야 이용 가능합니다. 인증 메일을 받지 못하셨나요?
		</div><br>
		<a href="emailAuthAction.do" class="btn btn-primary">인증 메일 다시 받기</a>	
	</div>


<footer class="container-fluid text-center">
  <p>KITRI 디지털 컨버젼스 28기</p>
</footer>

	<!-- 로그인(modal) -->
	<div class="modal" id="loginForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">로그인</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<form action="memberLogin.do" method="post">
					<div class="form-group">
						<div class="modal-body">
							<h5>
								<label>아이디</label>
							</h5>
							<input class="form-control" name="loginId" type="text" id="id" />
							<h5>
								<label>비밀번호</label>
							</h5>
							<input class="form-control" name="loginPwd" type="password"
								id="pwd" /> <a data-toggle="modal" data-target="#MissingForm"><u>혹시 비밀번호를 잊어버리셨나요?</u></a>
						</div>
						<div class="modal-footer">
							<button class="btn btn-dark" type="submit">로그인</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>



	<!-- 회원가입(modal) -->
	<div class="modal" id="joinForm">
		<div class="modal-dialog">
			<div class="modal-content" style="padding: 50px">
				<div class="modal-header">
					<h4 class="modal-title">회원가입</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form action="memberJoin.do" method="post" id="joinFomat">
					<div class="form-group">
						<div class="modal-body">

							<div class="row">
								<div class="col-sm">
									<h5>
										<label>아이디</label>
									</h5>
									<div class="row">
										<div class="col-sm-9">
											<input class="form-control" name="MEMBER_ID" type="text"
												maxlength="10" id="joinId" placeholder="아이디를 입력해 주세요." /> <input
												class="form-control" name="memberID" type="hidden"
												maxlength="10" id="tempId" placeholder="아이디를 입력해 주세요." />
										</div>

										<div class="col-sm-3">
											<button type="button" class="btn btn-dark" id="checkButton"
												onclick="idCheck()">중복체크</button>
											<!-- 중복체크 완료시 disable -->
										</div>
									</div>
								</div>
							</div>

							<div class="row mt-2 mb-2">

								<div class="col-sm">
									<label><h5>비밀번호</h5></label> <input class="form-control"
										name="MEMBER_PW" type="password" id="pass1"
										onkeyup="passCheck()" placeholder="비밀번호를 입력해 주세요." />
								</div>

								<div class="col-sm">
									<label><h5>비밀번호 확인</h5></label> <input class="form-control"
										type="password" id="pass2" onkeyup="passCheck()"
										placeholder="한번 더  입력해 주세요." />
								</div>

							</div>

							<label><h5>이메일</h5></label> <input class="form-control"
								name="MEMBER_EMAIL" type="email" id="inputEmail"
								placeholder="이메일을 입력해 주세요." />

						</div>
						<div class="modal-footer">
							<p style="color: red;" id="passCheckMessage"></p>
							<button type="button" class="btn btn-dark" onclick="join()">가입</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<form action="memberFindPass.do" method="post">
		<div class="modal" id="MissingForm">
			<div class="modal-dialog  modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">비밀번호 찾기</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div class="modal-body">
						현재 아이디를 입력하시면, 가입 정보에 기입된 이메일로 안내 메일을 발송해 드립니다. <input
							class="form-control" type="text" name="memberID"
							placeholder="아이디를 입력해주세요.">
					</div>

					<div class="modal-footer">
						<p style="color: red;" id="passCheckMessage"></p>
						<button class="btn btn-dark" type="submit">이메일 전송</button>

					</div>
				</div>
			</div>
		</div>
	</form>

</body>
</html>
