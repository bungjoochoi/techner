<%@ page language ="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style>
.l-content-group {
    position: relative;
    left: 0;
    z-index: 75;
    padding: 10px 15px;
    transition: left .1s ease-out;
}
.l-content-body.error {
    padding: 10px 30px;
}
.l-content-body.error h3 {
    font-size: 24px;
    line-height: 24px;
    color: #222;
    font-weight: 500;
    letter-spacing: 0;
    margin: 20px 0;
}
.l-content-body.error h3 span {
    background: no-repeat left;
    width: 69px;
    height: 70px;
    display: inline-block;
    vertical-align: middle;
    margin-right: 12px;
}
.l-content-body.error h1 {
    margin-bottom: 15px;
    font-size: 18px;
    line-height: 18px;
    color: #222;
    font-weight: 500;
    letter-spacing: 0;
}
.l-content-body.error p {
    margin-bottom: 0;
    font-size: 14px;
    line-height: 20px;
    color: #444;
}

</style>
<script type="text/javascript">
</script>
</head>
<body>
  <!-- error start -->
    <div class="l-content-group">
        <div class="l-content-body error">
        	<h3><span><img src="<c:url value="../images/img_alert.png"/>" alt="error icon" /></span>Access Error!</h3>
            <h1>접속이 제한되어 있는 페이지 입니다.</h1>
		    <p>권한이 없거나 부적합한 경로로 접속하였습니다.</p><br>
		    <p class=".btn"><button type="button" class="pink" onclick="javscript:history.back();">이전</button></p>
          </div>
    </div>
  <!-- error end -->

</body>

<%-- <body id="LblockBody" class="error2">
<div id="" class="LblockError2">
	<div>
		<h1>이용에 불편을 드려 죄송합니다.</h1>
  		<p>오류가 발생했습니다.</p>
  		<p>ErrorMessage : <%=message %></p>
       	 <p class=".btn"><button type="button" class="pink" onclick="javscript:history.back();">이전</button></p>
	</div>
</body> --%>
</html>
