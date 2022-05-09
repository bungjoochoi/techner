<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
//오늘
Date today = new Date();         
SimpleDateFormat date = new SimpleDateFormat("dd"); 
String aa = date.format(today);
int ia = Integer.parseInt(aa);
if(ia == 31) {
	ia = 1;
} else if(ia > 15) {
	ia = ia-15;
}
aa = ia+"";
if(aa.length()==1){
	aa = "0"+aa;
}
%>

<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="../images/favicon.ico">

	<title></title>

	<!-- Bootstrap core CSS -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="../css/login.css" rel="stylesheet">
	
	<style type="text/css">
	.form-signin-wrap {
	background-image: url("../images/common/bg<%=aa%>.jpg");
	}
	</style>
	
	<!-- Bootstrap core JavaScript -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="../js/jquery/jquery.min.js"></script>
  	<script src="../js/popper.min.js"></script>
 	<script src="../js/bootstrap.min.js"></script>
 	
 	<script type="text/javascript">
 	var cKey = 'serveSocketid';
	function SetCookie(cValue) {
		var todayDate = new Date();
		todayDate.setDate( todayDate.getDate() + 365 );
		document.cookie = cKey + '=' + escape(cValue) + "; path=/; expires=" + todayDate.toGMTString() + ";";
	}
    function deleteCookie(cKey){
        var expireDate = new Date();
        expireDate.setDate(expireDate.getDate() - 1);
        document.cookie = cKey + '=' + "; path=/; expires=" + expireDate.toGMTString() + ";";
    }
    function GetCookie(cKey) {
        var allcookies = document.cookie;
        var cookies = allcookies.split("; ");
        for (var i = 0; i < cookies.length; i++) {
            var keyValues = cookies[i].split("=");
            if (keyValues[0] == cKey) {
                return unescape(keyValues[1]);
            }
        }
        return "";
    }
    function getId(){
    	var idObj = document.getElementById('userid');
    	var rememberObj = document.getElementById('remember');
    	var id = GetCookie(cKey);
    	if(id != ""){
    		idObj.value = id;
    		rememberObj.checked = true;
    	}
    }
    function setId(){
    	var idObj = document.getElementById('userid');
    	var rememberObj = document.getElementById('remember');
    	if(rememberObj.checked){
    		SetCookie(idObj.value);
    	} else {
    		deleteCookie(cKey);
		}
    }
    
 	$(document).ready(function() {
 		getId();

 		sendPW = function(){
			var map = {
					userId: $('#findUserId').val(),
					uname: $('#finduName').val(),
					username: $('#findUserId').val(),
					account_locked: 1,
					last_updated: new Date()
			};

			$.ajax({
				url: '/auth/existUser',
				method: 'POST',
				data: JSON.stringify(map),
				contentType: "application/json; charset=utf-8",
				success: function(response) {
						if(response != '') {
							if(confirm('전송하시겠습니까?')) {
					    		$.ajax({
					    			url: '/auth/sendPassword',
					    			method: 'POST',
					    			data: JSON.stringify(map),
					    			contentType: "application/json; charset=utf-8",
					    			success: function(response) {
						    			alert("Email로 비밀번호를 전송 하였습니다.");
					    				location.reload();
					    			},
					    			error: function(response) {
					    				markingErrorField(response);
					    			}
					    		});
					    	} 
						} else {						
							var message = "<span style='color:red;'>사용자가 존재하지 않습니다.</span>";
				 			$('#message').html(message);
						}
				},
				error: function(response) {
					markingErrorField(response);
				}
			});

 		};
 	});
 	</script>

</head>

<body class="form-signin-wrap">
<c:url value="/authenticate" var="loginProcessingUrl"/>
<form class="form-signin" action="${loginProcessingUrl}" method="post">
		<div>
			<div class="signin-info">
				<h1><span></span></h1>
				<p></p>
			</div>
			<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
	            <div>
					Failed to login.<br>
						Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
						<br>비밀번호 5회 오류 시 비밀번호 변경 후 사용 가능합니다.
	            </div>
	        </c:if>
	        <!-- the configured LogoutConfigurer#logoutSuccessUrl is /login?logout and contains the query param logout -->
	        <c:if test="${param.logout != null}">
	            <div>
	                You have been logged out.
	            </div>
	        </c:if>
			<div class="form-label-group">
				<input type="text" id="userid" name="userid" class="form-control" placeholder="아이디를 입력하세요." required autofocus value="">
				<label for="userid">아이디</label>
			</div>
			
			<div class="form-label-group">
				 <input type="password" id="userPassword" name="userPassword" class="form-control" placeholder="비밀번호를 입력하세요." required value="">
            	<label for="userPassword">비밀번호</label>
			</div>
			<div class="checkbox float-left text-left">
				<label>
					<input type="checkbox" id="remember" value="remember-me"> Remember me
				</label>
			</div>
			<div class="col-4 float-right text-right">
				<button class="btn btn-normal btn-sm row" type="button" data-toggle="modal" data-target="#pwCng">PW 변경</button>
			</div>
			<br><br>
			<button class="btn btn-lg btn-block" type="submit" onclick="setId()">Login</button>
			<p class="copyright">COPYRIGHT 2019 &#169; <br><strong> Techner.Ltd.</strong> ALL RIGHT RESERVED.</p>
		</div>
	</form>
	
	<div class="modal fade" id="pwCng" tabindex="-1" role="dialog" aria-labelledby="pwCngTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document">
      <div class="modal-content" style="max-width:420px">
        <div class="modal-header">
          <h5 class="modal-title" id="pwCngTitle">비밀번호 변경</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
			<div class="form-group">
				<label for="userId" class="col-form-label">사용자 ID</label>
				<input type="text" class="form-control" id="findUserId" required="required" >
			</div>
			<div class="form-group">
				<label for="uName" class="col-form-label">사용자 이름</label>
				<input type="text" class="form-control" id="finduName">
			</div>
			<div class="alert alert-warning" role="alert">
				ID 생성 시 등록하신 E-mail로 비밀번호가 전송됩니다.
			</div>
			<label id="message"></label>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-cancel" data-dismiss="modal">취소</button>
			<button type="button" class="btn btn btn-info" onclick="sendPW()">비밀번호 전송</button>
		</div>
      </div>
    </div>
  </div>

</body>

</html>