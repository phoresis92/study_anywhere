<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bean.MemberBean" %>
<%@ page import="java.io.PrintWriter" %>
<%
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
			script.println("location.href='emailSendConfirm.jsp'");
			script.println("</script>");
			script.close();
		}
	}
	

%>
<!DOCTYPE html>
<html>
<head>
  <title>My Page</title>
  <meta charset="utf-8">
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
        .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
        /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      height: 400px;
      .row.content {height: auto;} 
    }

	.warn {
		border : 1px red solid;
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
 

		<li><a href="./myPage.jsp"><span class="glyphicon glyphicon-log-in"></span> My page</a></li>
        <li><a href="./memberLogout.do"><span class="glyphicon glyphicon-log-out"></span> Sign out</a>

      </ul>
    </div>
  </div>
</nav>


<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
      <h2>Study_Anywhere</h2>
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#section1">내정보 변경</a></li>
        <li><a href="./myPageDrop.jsp">회원탈퇴</a></li>
        <!-- <li><a href="#section2">Friends</a></li>
        <li><a href="#section3">Family</a></li>
        <li><a href="#section3">Photos</a></li> -->
      </ul><br><!-- 
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search Blog..">
        <span class="input-group-btn">
          <button class="btn btn-default" type="button">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
      </div> -->
    </div>

    <div class="col-sm-9">
      
      <div class="col-md-8 col-lg-9">
	            <div class="container">
	                <h2>비밀번호 변경</h2>
	                <form class="form mt-5" action="memberInfoRivision.do" id="memberInfoRivision" method="post">
	                    <div class="form-group">
	                       <div class="row mb-3">
	                            <div class="col-sm-3" style="text-align: center;">
	                                <label><h5>아이디</h5></label>
	                            </div>
	                            <div class="col-sm-9">
	                                <input class="form-control" type="text" readonly="readonly" value="${loginInfo.getMEMBER_ID()}"/>
	                           </div>
	                        </div>

	                        <div class="row mb-3">
	                            <div class="col-sm-3" style="text-align: center;">
	                                <label><h5>현재 비밀번호</h5></label>
	                            </div>
	                            <div class="col-sm-9">
	                                <input class="form-control" name="currentPassword" id="currentPW" type="password" placeholder="현재 비밀번호를 입력해 주세요."/>
	                           </div>
	                        </div>

	                        <div class="row mb-3">
	                            <div class="col-sm-3" style="text-align: center;">
	                                <label><h5>바꿀 비밀번호</h5></label>
	                            </div>
	                            <div class="col-sm-9">
	                                <input class="form-control" name="changePassword" id="passw1" onkeyup="passCheck()" type="password" placeholder="바꿀 비밀번호를 입력해 주세요."/>
	                           </div>
	                        </div>

	                        <div class="row mb-3">
	                            <div class="col-sm-3" style="text-align: center;">
	                                <label><h5>비밀번호 확인</h5></label>
	                            </div>
	                            <div class="col-sm-9">
	                                <input class="form-control" type="password" id="passw2" onkeyup="passCheck()" placeholder="바꿀 비밀번호를 한번 더 입력해 주세요."/>
	                           </div>
	                        </div>

	                        <div class="row mb-3">
	                            <div class="col-sm-3" style="text-align: center;">
	                                <label><h5>이메일</h5></label>
	                            </div>
	                            <div class="col-sm-9">
	                                <input class="form-control" type="text" readonly="readonly" value="${loginInfo.getMEMBER_EMAIL()}"/>
	                           </div>
	                        </div>

	                        <div class="row text-right">
	                            <div class="col">
	                            	<p style="color:red;" id="passCheckMessage2"></p>
	                                <button id="finalcheck" class="btn btn-primary" type="button" onclick="changePW()">비밀 번호 변경</button>
	                            </div>
	                        </div>
	                    </div>
	                </form>
	            </div>
	        </div>
      
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>KITRI 디지털 컨버젼스 28기</p>
</footer>

   <script>
   var passConfirmed = 0;
   
	/* 패스워드 일치 알림 */
	function passCheck() {
		var pass1 = $("#passw1").val();
		var pass2 = $("#passw2").val();
		if(pass1 != pass2) {
			$("#passCheckMessage2").html("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			$("#passw1").addClass("warn");
			$("#passw2").addClass("warn");
			$('#finalcheck').attr('disabled','disabled');
		} else {
			$("#passCheckMessage2").html("");
			$("#passw1").removeClass("warn");
			$("#passw2").removeClass("warn");
			 passConfirmed = 1;
			$('#finalcheck').removeAttr('disabled');
		}
	}
	
	function changePW() {
		if($("#currentPW").val() == ""){
			alert("현재 비밀번호를 입력해주세요.");
			return false;
		}else if($("#passw1").val() == '' || $("#passw2").val() == ''){
			alert('바꾸실 비밀번호를 입력해주세요')
			return;
		}else if(passConfirmed==0) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		} else {
			$("#memberInfoRivision").submit();
		}
	}
   </script>

</body>
</html>
