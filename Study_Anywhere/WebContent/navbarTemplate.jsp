<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.warn {
		border : 1px red solid;
	}
</style>
</head>
<body>


    <!-- Navigation -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="index.html">Start Bootstrap</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="about.html">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="services.html">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="contact.html">Contact</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Portfolio
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                <a class="dropdown-item" href="portfolio-1-col.html">1 Column Portfolio</a>
                <a class="dropdown-item" href="portfolio-2-col.html">2 Column Portfolio</a>
                <a class="dropdown-item" href="portfolio-3-col.html">3 Column Portfolio</a>
                <a class="dropdown-item" href="portfolio-4-col.html">4 Column Portfolio</a>
                <a class="dropdown-item" href="portfolio-item.html">Single Portfolio Item</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Blog
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="blog-home-1.html">Blog Home 1</a>
                <a class="dropdown-item" href="blog-home-2.html">Blog Home 2</a>
                <a class="dropdown-item" href="blog-post.html">Blog Post</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Other Pages
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="full-width.html">Full Width Page</a>
                <a class="dropdown-item" href="sidebar.html">Sidebar Page</a>
                <a class="dropdown-item" href="faq.html">FAQ</a>
                <a class="dropdown-item" href="404.html">404</a>
                <a class="dropdown-item" href="pricing.html">Pricing Table</a>
              </div>
            </li>
            

	<style>
	.warn {
		border : 1px red solid;
	}
</style>


        
        <c:choose>
        	<c:when test="${empty loginInfo.getMEMBER_ID()}">
        		<div class="btn-group">
		       		<button class="btn btn-outline-primary" data-toggle="modal" data-target="#loginForm">
						로그인
					</button>
		       		<button class="btn btn-outline-primary" data-toggle="modal" data-target="#joinForm">
						회원가입
		       		</button>
        		</div>
        	</c:when>
			
			<c:when test="${loginInfo.getMEMBER_ID().equals('admin')}">
        		<div class="btn-group">
		       		 <button class="btn btn-outline-info" onclick="location.href='./boardSuspendList.kly'">
	        	    	관리자 모드
	        	    </button>
	        	    <button class="btn btn-outline-danger" onclick="location.href='./memberLogout.do'">
	        	    	로그아웃
	        	    </button>
        		</div>
        	</c:when>
        	
        	<c:otherwise>
	        	<div class="btn-group">
	        	    <button class="btn btn-outline-primary" onclick="location.href='./memberDetail.kly'">
	        	    	마이페이지
	        	    </button>
	        	    <button class="btn btn-outline-danger" onclick="location.href='./memberLogout.do'">
	        	    	로그아웃
	        	    </button>
	        	</div>
        	</c:otherwise>
        </c:choose>
		

        
<script>
	var overlap = 0; // 0  중복체크 안함, 1 했고 중복 안됌
	var passConfirm = 0;

	/* 아이디 중복체크 */
	function idCheck() {
		var id = document.getElementById("joinId");
		
		console.log("idCheck 실행");
		if(id.value == ""){
			console.log("null if문 실행")
			alert('id를 입력해주세요');
			return;
		} else if(id.value.match(/[가-힣ㄱ-ㅎㅏ-ㅣ]/)) {
			alert('아이디는 한글을 제외해 주세요.');
			return;
		}
	
		
 		var req = new XMLHttpRequest();
		req.onreadystatechange = function() {
			if(this.readyState == 4 && this.status == 200) {
				var out = JSON.parse(this.responseText);
				if(out.result == "yes") {
					alert('아이디가 존재합니다.');
				} else {
					alert('사용 가능한 아이디 입니다.');
					overlap = 1; // 중복체크 완료
					var checkbutton = document.getElementById("checkButton");
					checkbutton.className += " disabled";
					id.disabled = true;
					console.log('버튼 비활성화 완료');
					document.getElementById("tempId").value = id.value;
				}
			}
		}
		req.open("GET","./idOverlapCheck.do?check="+id.value, true);
		req.send();
	}
	
	/* 패스워드 일치 알림 */
	function passCheck() {
		var pass1 = $("#pass1").val();
		var pass2 = $("#pass2").val();
		if(pass1 != pass2) {
			$("#passCheckMessage").html("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			$("#pass1").addClass("warn");
			$("#pass2").addClass("warn")
		} else {
			$("#passCheckMessage").html("");
			$("#pass1").removeClass("warn");
			$("#pass2").removeClass("warn");
			 passConfirm = 1;
		}
	}
	
	/* 가입 버튼 */
	function join() {
		var email = $("#inputEmail").val();
		if(overlap==0) {
			alert("id 중복체크를 해주세요.");
			return false;
		} else if(passConfirm==0) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		} else if(email=="") {
			console.log(email);
			alert("이메일을 입력해 주세요.");
			return false;
		} else if(overlap==1 && passConfirm==1) {
			$("#joinFomat").submit();
		}
	}
</script>

          </ul>
        </div>
      </div>
    </nav>
    
    
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
                                    <div class="container">
                                       <a href="./index.jsp"><img src="./images/kly.png" style="display:block; height: 300px" /></a>
                                    </div>
                                    
                                    <h5><label>아이디</label></h5>
                                    <input class="form-control" name="loginId" type="text" id="id" />
                                    <h5><label>비밀번호</label></h5>
                                    <input class="form-control" name="loginPwd" type="password" id="pwd" />
                                    <a data-toggle="modal" data-target="#MissingForm"><u>혹시 비밀번호를 잊어버리셨나요?</u></a>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary" type="submit">로그인</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- 회원가입(modal) -->
        <div class="modal" id="joinForm">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">회원가입</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <form action="memberJoin.do" method="post" id="joinFomat">
                        <div class="form-group">
                            <div class="modal-body">
                                
                                <div class="row">
									<div class="col-sm">
		                                <h5><label>아이디</label></h5>
		                            	<div class="row">
											<div class="col-sm-9">
		                                		<input class="form-control" name="MEMBER_ID" type="text" maxlength="5" id="joinId" placeholder="아이디를 입력해 주세요." />
		                                		<input class="form-control" name="memberID" type="hidden" maxlength="5" id="tempId" placeholder="아이디를 입력해 주세요." />
											</div>
									
											<div class="col-sm-3">
												<button type="button" class="btn btn-info" id="checkButton" onclick="idCheck()">중복체크</button> <!-- 중복체크 완료시 disable -->
											</div>
										</div>
									</div>
                                </div>

								<div class="row mt-2 mb-2">
									
									<div class="col-sm">
										<label><h5>비밀번호</h5></label>
										<input class="form-control" name="MEMBER_PW" type="password" id="pass1" onkeyup="passCheck()" placeholder="비밀번호를 입력해 주세요."/>
									</div>
									
									<div class="col-sm">
										<label><h5>비밀번호 확인</h5></label>
										<input class="form-control" type="password" id="pass2" onkeyup="passCheck()" placeholder="한번 더  입력해 주세요."/>
									</div>
									
                                </div>
                                
                                <label><h5>이메일</h5></label>
                                <input class="form-control" name="MEMBER_EMAIL" type="email" id="inputEmail" placeholder="이메일을 입력해 주세요."/>
                                    
                            </div>
							<div class="modal-footer">
								<p style="color:red;" id="passCheckMessage"></p>
                                <button type="button" class="btn btn-primary" onclick="join()">가입</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    	<!-- 회원 비밀번호 찾기(modal) -->
	<form action="memberFindPass.do" method="post">
	<div class="modal" id="MissingForm">
		<div class="modal-dialog  modal-lg">
			<div class="modal-content">
                   <div class="modal-header">
                       <h4 class="modal-title">비밀번호 찾기</h4>
                       <button type="button" class="close" data-dismiss="modal">&times;</button>
                   </div>
				
				<div class="modal-body">
					현재 아이디를 입력하시면, 가입 정보에 기입된 이메일로 안내 메일을 발송해 드립니다.
						<input class="form-control" type="text" name="memberID" placeholder="아이디를 입력해주세요.">
				</div>
				
				<div class="modal-footer">
					<p style="color:red;" id="passCheckMessage"></p>
					<button class="btn btn-info" type="submit">이메일 전송</button>
	
				</div>
			</div>
		</div>
	</div>
	</form>
    
</body>
</html>