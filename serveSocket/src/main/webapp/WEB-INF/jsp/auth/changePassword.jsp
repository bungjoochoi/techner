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
if(ia > 15){
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

	<title>비밀번호 변경</title>

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
 	$(document).ready(function() {
 		console.log('${myInfo.userId}');
 		
 		setCookie = function(){
 			console.log("닫기");
 			var now = new Date();
	        var year= now.getFullYear();
	        var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	        var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	        var expireDay = (now.getDate()+1)>9 ? ''+(now.getDate()+1) : '0'+(now.getDate()+1);
	        var date = year + '-' + mon + '-' + day;
	        var expireDate = year + '-' + mon + '-' + expireDay;
	        document.cookie = 'dailyPWClose'+ ${myInfo.userId} + "="  + date + "; path=/; expires=" + expireDate + ";";
	        
	        this.close();
 		};
 		
 		changePW = function(){
 			console.log("변경");
 			var password = $('#password').val();
 			var checkPassword = $('#checkPassword').val();
 			
	 		var check1 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,12}$/.test(password);   //영문,숫자 8~12자리 체크
	 		
	 		if(!check1) {
	 			var message = "<span style='color:red;'>비밀번호 양식이 맞지 않습니다.</span>";
	 			$('#message').html(message);
	 			return false;
	 		} else if(password != checkPassword) {
	 			var message = "<span style='color:red;'>비밀번호가 일치하지 않습니다.</span>";
	 			$('#message').html(message);
	 			return false;
	 		} else {
	 			var secUser = {
	 					id: ${myInfo.userId},
	 					password: password,
	 					last_updated: new Date()
	 			}
	 			
	 			if(confirm('변경하시겠습니까?')) {
	    			$.ajax({
	    				url: '/auth/changePassword',
	    				method: 'POST',
	    				data: JSON.stringify(secUser),
	    				contentType: "application/json; charset=utf-8",
	    				success: function(response) {
	    					window.close();
	    				},
	    				error: function(response) {
	    					markingErrorField(response);
	    				}
	    			});
	    		}
	 			
	 		}
 		};
 		


 	});
 	
 	</script>

</head>

<body class="form-signin-wrap">
<form class="form-signin" action="/" method="post">
		<div>
			<div class="signin-info">
				<h1><span>ServeSocket</sup></span></h1>
				<p>비밀번호를 변경한 지, 90일 이상 지났습니다. <br>비밀번호를 변경해 주시기 바랍니다. <br>(8~12자리의 숫자+영문+특수문자 조합)</p>
			</div>

			<div class="form-label-group">
				<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요." required autofocus value="">
				<label for="password">비밀번호</label>
			</div>
			
			<div class="form-label-group">
				 <input type="password" id="checkPassword" name="checkPassword" class="form-control" placeholder="한번더 입력하세요." required value="">
            	<label for="checkPassword">비밀번호 확인</label>
			</div>
			<div class="float-right text-right">
				<label id="message"></label>
				<label>
					<a href="#" onclick="setCookie()">다음에 변경하기</a>
				</label>
			</div>
			<br><br>
			<button class="btn btn-lg btn-block" type="button" onclick="changePW()">변경</button>
			<p class="copyright">COPYRIGHT 2019 &#169; <br><strong> BizTechPartners.Ltd.</strong> ALL RIGHT RESERVED.</p>
		</div>
	</form>

</body>

</html>