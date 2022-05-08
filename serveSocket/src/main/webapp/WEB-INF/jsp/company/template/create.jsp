<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>

  <script type="text/javascript" class="init">

    $(document).ready(function() {

    	var now = new Date();
        now.setDate(now.getDate() + 1);
        var year= now.getFullYear();
        var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
        var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
        var toDay = year + "-" + mon + "-" + day;
        $("#dueDateS").val(toDay);
        $("#dueDateE").val(toDay);
        
        $('.datepicker').datepicker({
       	  format: 'yyyy-mm-dd',
          //todayBtn: "linked",
          //clearBtn: true,
          language: "kr",
          autoclose: true,
          todayHighlight: true,
          toggleActive: true,
          orientation: 'bottom auto',
        });
        saveFlag=true;
		$('#modifyBtn').click(function() {
			var modifyForm = $("form[name=modifyForm]").serialize();
			saveFlag=false;
			$.ajax({
	            url:"<c:url value="/company/create"/>",
	            method: 'POST',
	            data: modifyForm,
	            success: function (response) {
	            	location.href =response;
	            },
	            error: function (response) {
                	saveFlag=true;
                	$(".error-message").remove();
	                var errors = response.responseJSON.errors;
	                var field;
	                var message;
	                for(var i=0; i<errors.length; i++){
	                	field = errors[i].field;
	                	message= errors[i].defaultMessage;
	                	$('#'+field).after('<span class="error-message  text-danger">'+message+'</span>');
	                }
	            }
	        });
		});	
		
	    $("#redirect-list").click(function() {
	    	location.href = "<c:url value="/company/list"/>";
	    });

	    
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
		$("#roleId").select2({
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


		
      
    });

  </script>

</head>
<body>

	 <section class="detail-form">
        <form:form modelAttribute="modifyForm" name="modifyForm">
          <div class="detail-wrap">
            <div class="col-lg-12">
              <div class="row">
                <label class="col-md-2 col-lg-1" for="company"><span class="required">회사코드</span></label>
                <div class="col-md-4 col-lg-3">
                <c:choose>
                <c:when test="${not empty company.coId}">
                	<input type="hidden" class="form-control" value="${company.coId}" name ="coId">
                </c:when>
                <c:otherwise>
                	<input type="hidden" class="form-control" value="0" name ="coId">
                </c:otherwise>
                </c:choose>                  
                  <input type="text" class="form-control" value="${company.companyCode}" id="companyCode" name ="companyCode">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">회사명</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.coName}" id="coName" name="coName">
                </div>
                <label class="col-md-2 col-lg-1" for="">회사명(En)</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.coNameEN}" id="coNameEN" name="coNameEN">
                </div>
                <label class="col-md-2 col-lg-1" for="">회사설명</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.coDesc}" id="coDesc" name="coDesc">
                </div>
                <label class="col-md-2 col-lg-1" for="">산업분류</label>
                <div class="col-md-4 col-lg-3">
	                <select class="form-control"  id="industry" name="industryId">
	                </select>
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">회사역할</span></label>
                <div class="col-md-4 col-lg-3">
	                <select class="form-control"  id="roleId" name="roleId">
	                </select>
                </div>
                <label class="col-md-2 col-lg-1" for="">업태</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.businessType}" name="businessType">
                </div>
                <label class="col-md-2 col-lg-1" for="">업종</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.businessCondition}" name="businessCondition">
                </div>
                <label class="col-md-2 col-lg-1" for="">주소</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.address}" name ="address">
                </div>
                <label class="col-md-2 col-lg-1" for="">Tel</label>
                <div class="col-md-4 col-lg-3">
					<input type="text" class="form-control" value="${company.tel}" name="tel">
                </div>
                <label class="col-md-2 col-lg-1" for="">Fax</label>
                <div class="col-md-4 col-lg-3">
					<input type="text" class="form-control" value="${company.fax}" name ="fax">
                </div>
                <label class="col-md-2 col-lg-1" for="">Date Created</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.dateCreated}">
                </div>
                <label class="col-md-2 col-lg-1" for="">Last Updated</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.lastUpdated}">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">서비스 유무</span></label>
                <div class="col-md-4 col-lg-3">
                  <select class="form-control" id="isServiced" name="isServiced">
                  <c:choose>
	                  	<c:when test="${company.isServiced ne true}">
	                  	<option value="0" selected="selected">off</option>
	                  	<option value="1">on</option>
	                  	</c:when>                  	
	                  	<c:otherwise>
	                  	<option value="0">off</option>
	                  	<option value="1" selected="selected">on</option>
	                  	</c:otherwise>
                  </c:choose>

                  </select>
                </div>
                 <label class="col-md-2 col-lg-1" for="">월 계약공수</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="" id="contractMM" name="contractMM">
                </div>
                 <label class="col-md-2 col-lg-1" for="">계약기간</label>
                <div class="col-md-4 col-lg-3">
                    <input style="width:45%;display:inline;" type="text" class="form-control datepicker" id="dueDateS"name="dueDateS"><span class="input-group-addon">~</span>
                    <input style="width:45%;display:inline;" type="text" class="form-control datepicker" id="dueDateE"name="dueDateE">
                </div>
                 <label class="col-md-2 col-lg-1" for="">계약상태</label>
                <div class="col-md-4 col-lg-3">
<!--                   <input type="text" class="form-control" value="" id="contractStatus" name="contractStatus"> -->
                   <select class="form-control" id="contractStatus" name="contractStatus">
	                  	<option value="1" selected="selected">계약중</option>
	                  	<option value="0">만료</option>

                  </select>
                </div>
                
                <label class="col-md-2 col-lg-1" for="">구성원</label>
                <c:forEach items="${company.userList}" var="employee" varStatus="index">
	                <div class="col-md-4 col-lg-3">
		                <a href="<c:url value='/userDetail/${employee.userId}'/>" class="form-control">${employee.uName}</a>          
	                </div>
	                <c:if test="${!index.last}">
	                	<label class="col-md-2 col-lg-1" for=""></label>
	                </c:if>
                </c:forEach>           
              	</div>
            </div>
          </div>

        </form:form>
        </section>

      <div class="detail-btn-wrap">
        <button type="button" class="btn btn-normal" id="redirect-list">목록으로</button>
        <c:if test="${sessionScope.__USER__.role eq '6'}">
        	<button type="button" class="btn btn-type1" id="modifyBtn" type="submit"data-toggle="modal" data-target="#delivery">저장</button>
        </c:if>
      </div>
</body>
