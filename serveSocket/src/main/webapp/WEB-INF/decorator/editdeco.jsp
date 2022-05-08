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
  <link rel="stylesheet" href="/css/all.css">
  <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
  <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
  <!--[if lt IE 9]>
    <script type="text/javascript" src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script type="text/javascript" src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]
  <link href="/css/all.css" rel="stylesheet">
    -->

   <link type="text/css" href="/plugins/ckeditor/editor.css" rel="stylesheet">
    <link rel="stylesheet" href="/plugins/plupload/jquery.ui.plupload/css/jquery.ui.plupload.css" rel="stylesheet">
  <link href="/css/fontawesome.css" rel="stylesheet">
  <link href="/css/regular.css" rel="stylesheet">
  
	<link href="/css/jquery-ui/jquery-ui.css" type="text/css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <!-- <link href="/assets/css/common.css" rel="stylesheet">  -->
    <link href="/plugins/bootstrap-tagsinput.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/css/ui.jqgrid.min.css">


  <script type="text/javascript" src="/js/jquery/jquery.min.js"></script>
  <script type="text/javascript" src="/js/popper.min.js"></script>
  <script type="text/javascript" src="/js/bootstrap.min.js"></script>

  <script type="text/javascript" src="/js/datatables.min.js"></script>
  <script type="text/javascript" src="/js/dataTables.bootstrap.min.js"></script>
  <script type="text/javascript" src="/js/dataTables.fixedHeader.min.js"></script>
  <script type="text/javascript" src="/js/dataTables.responsive.min.js"></script>
  <script type="text/javascript" src="/js/responsive.bootstrap.min.js"></script>

  <script type="text/javascript" src="/js/bootstrap-datepicker.min.js"></script>
  <script type="text/javascript" src="/js/bootstrap-datepicker.kr.min.js"></script>
  
 <script type="text/javascript" src="/js/jquery/jquery-ui.min.js"></script>
 
 

 
  <%--
	얘때문에 datepicker가 안먹음 
  --%> 
  

  <script type="text/javascript" src="/plugins/ckeditor/ckeditor.js"></script> 
  <script type="text/javascript" src="/plugins/ckeditor/lang/ko.js"></script>
  <script type="text/javascript" src="/plugins/ckeditor/styles.js"></script>
  <script type="text/javascript" src="/plugins/plupload/plupload.full.min.js"></script>
  <script type="text/javascript" src="/plugins/plupload/jquery.ui.plupload/jquery.ui.plupload.js"></script>
  <!-- activate Slovak translation -->
  <script type="text/javascript" src="/plugins/plupload/i18n/ko.js"></script>
  <script type="text/javascript" src="/plugins/ckeditor/adapters/jquery.js"></script>
  


  <script type="text/javascript" src="/plugins/jquery.repeater.js"></script>
  <script type="text/javascript" src="/plugins/bootstrap-tagsinput.js"></script>


<script type="text/javascript" src="/plugins/bootstrap-slider.js"></script>

  <script  type="text/javascript" src="/js/select2.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/jquery.jqgrid.min.js"></script>
  <script  type="text/javascript" src="/js/comm.js"></script>
  <script  type="text/javascript" src="/js/common.js"></script>


	<script type="text/javascript">

	var ckeditor_config = {
			resize_enabled : false, // 에디터 크기를 조절하지 않음
			enterMode : CKEDITOR.ENTER_BR , // 엔터키를 <br> 로 적용함.
			shiftEnterMode : CKEDITOR.ENTER_P ,  // 쉬프트 +  엔터를 <p> 로 적용함.
			toolbarCanCollapse : true , 
			removePlugins : "elementspath", // DOM 출력하지 않음
			filebrowserImageUploadUrl: '/upload-drag-drop'		,
			filebrowserBrowseUrl : '/uploadEditorImage',
			image_previewText : ' ',
			height: '350px',
			toolbarGroups  : [
        		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },	           
        		{ name: 'insert'},
        		'/',
        		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align' ] },
        		{ name: 'styles' },
        		{ name: 'colors' }	                		
        	]
		};


	var ckeditor_view_config = {
			readOnly : true,
			resize_enabled : true, // 에디터 크기를 조절하지 않음

			toolbarCanCollapse : false,
			toolbarStartupExpanded : false,
			extraPlugins : 'autogrow',
			autoGrow_onStartup : true,
			autoGrow_bottomSpace : 50,
			
			enterMode : CKEDITOR.ENTER_BR , // 엔터키를 <br> 로 적용함.
			shiftEnterMode : CKEDITOR.ENTER_P ,  // 쉬프트 +  엔터를 <p> 로 적용함.
			toolbarCanCollapse : true , 
			removePlugins : "elementspath", // DOM 출력하지 않음
			filebrowserImageUploadUrl: '/upload-drag-drop'		,
			filebrowserBrowseUrl : '/uploadEditorImage',
			image_previewText : ' ',
			height: '350px',
			toolbarGroups  : [
	    		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },	           
	    		{ name: 'insert'},
	    		'/',
	    		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
	    		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align' ] },
	    		{ name: 'styles' },
	    		{ name: 'colors' }	                		
	    	]
		};
		
	
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

