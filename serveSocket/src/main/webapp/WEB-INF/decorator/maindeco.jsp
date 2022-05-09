<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <meta name="generator" content="">
  <title>ServeSocket</title>


<%@include file="/WEB-INF/decorator/common.jsp"%>


	
	<script >
	
		function main(){ 
		    location.href="/main";
		}
		
		$(document).ready(function(){
		    $(".header-logo").click(function() {
		    	location.href="<c:url value="/main"/>";
		    });
		    $("#my-info").click(function() {
		    	location.href="<c:url value="/user/myInfo"/>";
		    });  
		    $("#logout").click(function() {
		    	location.href="<c:url value="/logout"/>";
		    });  
		});
	
	</script>
<sitemesh:write property='head' />
</head>
<body> 

<%@include file="/WEB-INF/decorator/header.jsp"%>
    
	<sitemesh:write property='body' />
<%--
<%@include file="/WEB-INF/decorator/footer.jsp"%>  
--%>
</body>
</html>



