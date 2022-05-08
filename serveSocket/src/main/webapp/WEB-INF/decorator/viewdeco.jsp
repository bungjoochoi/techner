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
  

  <link rel="stylesheet" href="/css/reset.css">
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="/css/bootstrap.min.css">

  <!-- dataTables CSS -->
  <link href="/css/dataTables.bootstrap.min.css" rel="stylesheet">
  <link href="/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
  <link href="/css/responsive.bootstrap.min.css" rel="stylesheet">
  <link href="/css/select2.min.css" rel="stylesheet" />
  <link href="/css/bootstrap-datepicker3.min.css" rel="stylesheet">

  

	
  

  <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
  <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->


    <!-- Custom styles for this template -->
  <link href="/css/common.css" rel="stylesheet">
  <link rel="stylesheet" href="/css/all.css">
  <script src="/js/jquery/jquery.min.js"></script>
  <script src="/js/popper.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  
  <script src="/js/datatables.min.js"></script>
  <script src="/js/dataTables.bootstrap.min.js"></script>
  <script src="/js/dataTables.fixedHeader.min.js"></script>
  <script src="/js/dataTables.responsive.min.js"></script>
  <script src="/js/responsive.bootstrap.min.js"></script>

  <script src="/js/bootstrap-datepicker.min.js"></script>
  <script src="/js/bootstrap-datepicker.kr.min.js"></script>
  <script type="text/javascript" src="/plugins/jquery.repeater.js"></script>
  <script type="text/javascript" src="/plugins/bootstrap-tagsinput.js"></script>


  <script src="/js/select2.min.js"></script>
  <script src="/js/comm.js"></script>
  <script src="/js/common.js"></script>
	
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

<%@include file="/WEB-INF/decorator/footer.jsp"%>  

</body>
</html>

