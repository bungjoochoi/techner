<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <meta name="description" content="">
  <meta name="author" content="">
  <meta name="generator" content="">

  <title>ServeSocket</title>

  
 <script type="text/javascript" class="init">

    $(document).ready(function() {
    	//https://datatables.net/
    	//table위에 10개씩보기 / 검색하는 필드 띄우는 function
		var table = $('#example').DataTable( {
    	        responsive: true,
    	        language : lang_kor,
    	        } );

    	new $.fn.dataTable.FixedHeader( table );
    	
      $('.datepicker').datepicker({
    	 dateFormat: 'yy-mm-dd',
        //todayBtn: "linked",
        //clearBtn: true,
        language: "kr",
        autoclose: true,
        todayHighlight: true,
        toggleActive: true,
        orientation: 'bottom auto',
      });
      
      var coSearch = '${coSearch}';
      console.log(coSearch);
      if( coSearch != '' ){
    	  var type = '${coSearch.searchType}';
    	  console.log(type);
    	  $("#searchType").val(type).attr('selected', 'selected');
    	  
    	  if( type == 0 ){
    		  var keyword = '${coSearch.searchKeyword}';
    		  $('#searchKeyword').val(keyword);
    	  }
    	  else if( type == 1 ){
    		    var code = '<select class="form-control" id="company" name="coId" multiple="multiple"></select>';
    		    $("#searchValue").after(code);
    		    $('#searchKeyword').remove();
    		   //var coList = 
    			<c:if test='${not empty coSearch.coId}'>
	    		   	<c:forEach items='${companyList}' var='co'>
	    		   		$('#company').append('<option value="'+'${co.coId}'+'" selected="selected">'+'${co.coName}'+'</option>');
	    		    </c:forEach>
    		    </c:if>
	        	$("#company").select2({
	    			placeholder: "회사명을 검색후 선택하세요",
	    			multiple: true,
	    			ajax: {
	    				url: '/user/companySelectorListAjax',
	    				processResults: function (data) {
	    					return {
	    					  results: data.result
	    					};
	    				}
	    			}
	    		});
    	  }
    	  else if(  type == 2 ){
    		  var code = '<select class="form-control" id="companyRole" name="searchKeyword"></select>';
    		  var roleVal = '${coSearch.searchKeyword}';
    		  var roleTxt = '${searchBack.roleName}';
    		  $('#searchKeyword').remove();
    		  console.log(roleVal);
    		  console.log(roleTxt);    		  
    		  $("#searchValue").after(code);
    		  $('#companyRole').append('<option value="'+roleVal+'" selected="selected">'+roleTxt+'</option>');
	        	$("#companyRole").select2({
	    			placeholder: "회사역할을 선택하세요.",
	    			//multiple: true,
	    			ajax: {
	    				url: '/common/code/companyRoleSelect',
	    				processResults: function (data) {
	    					return {
	    					  results: data.result
	    					};
	    				}
	    			}
	    		});
    	  }
    	  else if(  type == 3 ){
    		  var code = '<select class="form-control" id="industry" name="searchKeyword"></select>';
    		  var induVal = '${coSearch.searchKeyword}';
    		  var induTxt = '${searchBack.industry}';
    		  $('#searchKeyword').remove();
    		  console.log(induVal);
    		  console.log(induTxt);    		  
    		  $("#searchValue").after(code);
    		  $('#industry').append('<option value="'+induVal+'" selected="selected">'+induTxt+'</option>')
	          $("#industry").select2({
	        		placeholder: "산업분류를 선택하세요.",
	    			//multiple: true,
	    			ajax: {
	    				url: '/common/code/industrySelect',
	    				processResults: function (data) {
	    					return {
	    					  results: data.result
	    					};
	    				}
	    			}
	    		});
    	  }
    	  
    	  
      }
      saveFlag=true;
		      
		$("#createCompany").on("click", function(){
			if(confirm('생성하시겠습니까?')) {
				saveFlag=false;
				$.ajax({
					url : '<c:url value="/company/create" />',
					//contentType : "application/json; charset=UTF-8",
					type : 'GET',
					success : onSuccess,
					error: function (response) {
		                console.log(response);
	                	saveFlag=true;
	                	$(".error-message").remove();
	                    markingErrorField(response);
	                } 
				});
			}
			function onSuccess(res, status, xhr) {
				$('#companyDetailRow').html(res);
			}
		});
		
		$('#searchBtn').click(function() {
			var searchCondition = $("#searchType option:selected").val();
			console.log(searchCondition);
			var searchForm = $("#searchForm");
			
			searchForm.attr({
				"action" : "<c:url value="/company/searchCompanyListAjax"/>",
				"method" : "GET"
			}).submit();
		});	
		
		//var $code = $('<input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력해 주세요.">');
		
		$("#searchType").change(function() {
	        var selected = $("#searchType").children("option:selected").val();
	        $("#searchType").children("option:selected").attr("selected", "selected");
	        console.log(selected);
	        if( selected == 0 ){//회사코드
	        	var code = '<input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력해 주세요.">';
	        	$('#company').select2('destroy');
	        	$('#company').remove();
	        	$('#companyRole').select2('destroy');
	        	$('#companyRole').remove();
	        	$('#industry').select2('destroy');
	        	$('#industry').remove();
	        	$("#searchValue").after(code);
	        };
	        
	        if( selected == 1 ){//회사명
	        	var code = '<select class="form-control" id="company" name="coId" multiple="multiple"></select>';
	        	$('#searchKeyword').remove();
	        	$('#industry').select2('destroy');
	        	$('#industry').remove();
	        	$('#companyRole').select2('destroy');
	        	$('#companyRole').remove();
	        	$("#searchValue").after(code);
	        	$("#company").select2({
	    			placeholder: "회사명을 검색후 선택하세요",
	    			multiple: true,
	    			ajax: {
	    				url: '/user/companySelectorListAjax',
	    				processResults: function (data) {
	    					return {
	    					  results: data.result
	    					};
	    				}
	    			}
	    		});
	        };
	        if( selected == 2 ){//회사역할
	        	$('#company').select2('destroy');
	        	$('#company').remove();
	        	$('#industry').select2('destroy');
	        	$('#industry').remove();
	        	$('#searchKeyword').remove();
	        	var code = '<select class="form-control" id="companyRole" name="searchKeyword"></select>';
	        	$("#searchValue").after(code);
	        	$("#companyRole").select2({
	    			placeholder: "회사역할을 선택하세요.",
	    			//multiple: true,
	    			ajax: {
	    				url: '/common/code/companyRoleSelect',
	    				processResults: function (data) {
	    					return {
	    					  results: data.result
	    					};
	    				}
	    			}
	    		});
	        };
	        if( selected == 3 ){//산업분류
	        	$('#company').select2('destroy');
	        	$('#company').remove();
	        	$('#searchKeyword').remove();
	        	$('#companyRole').select2('destroy');
	        	$('#companyRole').remove();
	        	var code = '<select class="form-control" id="industry" name="searchKeyword"></select>';
	        	$("#searchValue").after(code);
	        	$("#industry").select2({
	    			placeholder: "산업분류를 선택하세요.",
	    			//multiple: true,
	    			ajax: {
	    				url: '/common/code/industrySelect',
	    				processResults: function (data) {
	    					return {
	    					  results: data.result
	    					};
	    				}
	    			}
	    		});
	        };      
	        
	    });

   
    });

  </script>
  
  

</head>

<body>
<section class="section">
    <div class="section-sub">
      <img src="../images/common/oval-1.svg" alt="" class="oval-one">
      <img src="../images/common/shape-1.svg" alt="" class="shape-three">
      <img src="../images/common/shape-55.svg" alt="" class="shape-four">
      <img src="../images/common/shape-56.svg" alt="" class="shape-five">
      <img src="../images/common/shape-57.svg" alt="" class="shape-six">
      <img src="../images/common/shape-58.svg" alt="" class="shape-seven">
      <img src="../images/common/shape-59.svg" alt="" class="shape-eight">
      <img src="../images/common/shape-60.svg" alt="" class="shape-nine">
      <img src="../images/common/shape-61.svg" alt="" class="shape-ten">
      <img src="../images/common/shape-62.svg" alt="" class="shape-eleven">

      <div class="sub-hero-wrap">
        <p class="sub-hero-text"><span class="core-color">회사</span>  정보<i class="icon icon-question"></i></p>
        <p class="sub-hero-text2">고객님의 불편사항을 신속히 처리해 드리겠습니다.</p>
      </div>
    </div>
  </section>
  
  <section class="section sub-contents">
    <div class="container" id="companyDetailRow">
        
        <c:if test="${sessionScope.__USER__.company.roleId eq '1'}" >
        <div class="search-form">
          <form:form modelAttribute="searchForm">
            <div class="search-wrap">
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group">
                    <label for="search">검색조건</label> 
					<select class="form-control" id="searchType" name="searchType">
						<option value="0">회사코드</option>
						<option value="1">회  사  명</option>
						<option value="2">회사역할</option>
						<option value="3">산업분류</option>
					</select>
                  </div>
                </div>

                <div class="col-md-6">
                  <div class="form-group" id="searchDiv">
                    <label for="searchValue" id="searchValue">검색어</label>
                    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력해 주세요.">
                  </div>
                </div>
              </div>
            </div>

            <div class="search-btn-wrap">
              <div class="form-group">
                <button type="submit" id="searchBtn" class="btn btn-search"><i class="icon icon-search"></i>조회</button>
              </div>
            </div>

          </form:form>
        </div>
		</c:if>
        <div class="data-table-wrap">
          <table id="example" class="table table-striped table-bordered text-center" style="width:100%">
            <thead>
              <tr>
                <th>회사코드</th>
				<th>회사명</th>
				<th>회사설명</th>
				<th>회사역할(Role)</th>
				<th>산업분류</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${companyList}" var="list">
	 			<tr>
		 			<td><a href="<c:url value="/company/view/${list.companyCode}" />">${list.companyCode}</a></td>
					<td>${list.coName}</td>
					<td>${list.coDesc}</td>
					<td>${list.roleName}</td>
					<td>${list.industry}</td>
				</tr>
			</c:forEach>
            </tbody>
          </table>
        </div>
		<c:if test="${sessionScope.__USER__.role eq '6'}">
			<div class="text-right">
		        <button type="button" class="btn btn-normal" id="createCompany">회사 생성</button>
		    </div>
		</c:if>
    </div> <!-- /container -->
  </section>

</body>

</html>
