<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
  <title>ServeSocket</title>
  
  <script>
		$(document).ready(function(){			
		    $("#redirect-list").click(function() {
		    	location.href = "<c:url value="/company/list"/>";
		    });  
		    
		    $("#redirect-modify").click(function() {
		    	var coId = "${company.coId}";
		    	console.log(coId);
				$.ajax({
					url : '<c:url value="/company/modify/${company.companyCode}" />',
					//contentType : "application/json; charset=UTF-8",
					type : 'GET',
					success : onSuccess
				});
				function onSuccess(res, status, xhr) {
					$('#companyDetailRow').html(res);
					datepickerinit();
				}
		    });

		    datepickerinit = function(){
		    	$('.datepicker').datepicker({
		    		dateFormat: 'yy-mm-dd'
		            
		          });
			} 
		  	
		});
  </script>

  <script type="text/javascript" class="init">

    $(document).ready(function() {
    	
    	$('.datepicker').datepicker({
    		dateFormat: 'yy-mm-dd',
    		showMonthAfterYear:true
            
         });
      
    });

  </script>



</head>

<body>

	<section class="section">
	    <div class="section-sub">
	      <img src="/images/common/oval-1.svg" alt="" class="oval-one">
	      <img src="/images/common/shape-1.svg" alt="" class="shape-three">
	      <img src="/images/common/shape-55.svg" alt="" class="shape-four">
	      <img src="/images/common/shape-56.svg" alt="" class="shape-five">
	      <img src="/images/common/shape-57.svg" alt="" class="shape-six">
	      <img src="/images/common/shape-58.svg" alt="" class="shape-seven">
	      <img src="/images/common/shape-59.svg" alt="" class="shape-eight">
	      <img src="/images/common/shape-60.svg" alt="" class="shape-nine">
	      <img src="/images/common/shape-61.svg" alt="" class="shape-ten">
	      <img src="/images/common/shape-62.svg" alt="" class="shape-eleven">
	
	      <div class="sub-hero-wrap">
	        <p class="sub-hero-text"><span class="core-color">회사</span> 정보<i class="icon icon-question"></i></p>
	        <p class="sub-hero-text2">고객님의 불편사항을 신속히 처리해 드리겠습니다.</p>
	      </div>
	    </div>
    </section>

  <section class="section sub-contents" >
    <div class="container" id="companyDetailRow">

      <section class="detail-form" >
        <form:form modelAttribute="modifyForm">
          <div class="detail-wrap">
            <div class="col-lg-12">
              <div class="row">
                <label class="col-md-2 col-lg-1" for="company">회사코드</label>
                <div class="col-md-4 col-lg-3">
                  <input type="hidden" class="form-control" value="${company.coId}" name ="coId">
                  <input type="text" class="form-control" value="${company.companyCode}" name ="companyCode" readonly="readonly">
                </div>
                <label class="col-md-2 col-lg-1" for="">회사명</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.coName}" readonly="readonly">
                </div>
                <label class="col-md-2 col-lg-1" for="">회사명(En)</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.coNameEN}"  readonly="readonly">
                </div>
                <label class="col-md-2 col-lg-1" for="">회사설명</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.coDesc}" name="coDesc" readonly="readonly">
                </div>
                <label class="col-md-2 col-lg-1" for="">산업분류</label>
                <div class="col-md-4 col-lg-3">
                	<input type="text" class="form-control" value="${company.industry}" name="coDesc" disabled="disabled">
                </div>
                <label class="col-md-2 col-lg-1" for="">업태</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.businessType}" name="businessType" readonly="readonly">
                </div>
                <label class="col-md-2 col-lg-1" for="">업종</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.businessCondition}" name="businessCondition" disabled="disabled">
                </div>
                <label class="col-md-2 col-lg-1" for="">주소</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.address}" name ="address" disabled="disabled">
                </div>
                <label class="col-md-2 col-lg-1" for="">Tel</label>
                <div class="col-md-4 col-lg-3">
					<input type="text" class="form-control" value="${company.tel}" name="tel" disabled="disabled">
                </div>
                <label class="col-md-2 col-lg-1" for="">Fax</label>
                <div class="col-md-4 col-lg-3">
					<input type="text" class="form-control" value="${company.fax}" name ="fax" disabled="disabled">
                </div>
                <label class="col-md-2 col-lg-1" for="">Date Created</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.dateCreated.substring(0, 19)}" readonly="readonly">
                </div>
                <label class="col-md-2 col-lg-1" for="">Last Updated</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="${company.lastUpdated.substring(0, 19)}" readonly="readonly">
                </div>
<%--                 <c:if test="${company.roleId ne 3 }"> --%>
                <c:if test="${sessionScope.__USER__.company.roleId ne 3}">
	                <label class="col-md-2 col-lg-1" for="">월 계약공수</label>
	                <div class="col-md-4 col-lg-3">
	                  <input type="text" class="form-control" value="${company.contractMM}" id="contractMM" name="contractMM" readonly="readonly">
	                </div>
	                 <label class="col-md-2 col-lg-1" for="">계약기간</label>
	                <div class="col-md-4 col-lg-3">
	                    <input style="width:45%;display:inline;" value="${company.dueDateS}" type="text" class="form-control" id="dueDateS"name="dueDateS" readonly="readonly">~
	                    <input style="width:45%;display:inline;" value="${company.dueDateE}" type="text" class="form-control" id="dueDateE"name="dueDateE" readonly="readonly">
	                </div>
	                <label class="col-md-2 col-lg-1" for="">계약상태</label>
                
	                <div class="col-md-4 col-lg-3">
	                		<c:choose>
	                			<c:when test="${company.contractStatus eq '1'}">
	                				<input type="text" class="form-control" value="계약중" id="" name="" readonly="readonly">
	                			</c:when>
	                			<c:when test="${company.contractStatus eq '0'}">
	                				<input type="text" class="form-control" value="만료" id="" name="" readonly="readonly">
	                			</c:when>
	                			<c:otherwise>
	                				<input type="text" class="form-control" value="" id="" name="" readonly="readonly">
	                			</c:otherwise>
	                		</c:choose>
	<!--                   </select> -->
	                </div>
                </c:if>
                <label class="col-md-2 col-lg-1" for="">서비스 유무</label>
                <div class="col-md-4 col-lg-3">
                  <select class="form-control" id="isServiced" name="isServiced" disabled="disabled">
                  <c:choose>
	                  	<c:when test="${company.isServiced ne 1}">
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
                <label class="col-md-2 col-lg-1" for=""></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value="" readonly="readonly">
                </div>
                <label class="col-md-2 col-lg-1" for=""></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" value=""  readonly="readonly">
                </div>
                <label class="col-md-2 col-lg-1" for="">구성원</label>
                <c:forEach items="${company.userList}" var="employee" varStatus="index">
	                <div class="col-md-4 col-lg-3">
		                <a href="<c:url value='/user/view/${employee.userId}'/>" class="form-control">${employee.uName}</a>
		                          
	                </div>
	                <c:if test="${!index.last}">
	                	<label class="col-md-2 col-lg-1" for=""></label>
	                </c:if>
                </c:forEach>
                	<c:if test="${not empty company.userList}">
                		<label class="col-md-2 col-lg-1" for=""></label>
                	</c:if>
	                <div class="col-md-4 col-lg-3">
	                <c:if test="${sessionScope.__USER__.role eq '6'}">
	                	<a href="<c:url value='/user/create'/>" class="btn btn-type1">구성원 추가</a>  
	                </c:if>    
	                </div>     
              	</div>
            </div>
          </div>

        </form:form>
      </section>

      <div class="detail-btn-wrap">
        <button type="button" class="btn btn-normal" onclick="javscript:history.back()">목록으로</button>
        <c:if test="${sessionScope.__USER__.role eq '6'}">
        	<button type="button" class="btn btn-normal" id="redirect-modify">회사수정</button>
        </c:if>
      </div>

    </div> <!-- /container -->
  </section>
</body>

</html>
