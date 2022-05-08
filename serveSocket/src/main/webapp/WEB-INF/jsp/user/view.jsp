<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <title>ServeSocket</title>
  <script>
	 window.onpopstate = function(event) {  //뒤로가기 이벤트를 캐치합니다.

		 // history.back();   // pushState로 인하여 페이지가 하나 더 생성되기 떄문에 한번에 뒤로가기 위해서 뒤로가기를 한번 더 해줍니다.
	 };
  </script>
  <script>  
  	 function isEmpty(str){
	      if(typeof str == "undefined" || str == null || str == "")
	          return true;
	      else
	          return false ;
	  }


		$(document).ready(function(){
			
			$('#modifyInfo').click(function() {
				$(".error-message").remove();
				var password = $("#password").val();
				var passwordConfirm = $("#passwordConfirm").val();

				var mobile = $("#mobile").val(); // 현재 입력한 번호
				var mobile1 = mobile.replace(/-/gi, ""); // 입력한 번호에서 - 제외
				var mobile2 = mobile1.replace(/(\s*)/g, ""); // 입력한 번호에서 - 공백 제거

				var tel = $("#tel").val(); // 현재 입력한 번호
				var tel1 = tel.replace(/-/gi, ""); // 입력한 번호에서 - 제외
				var tel2 = tel1.replace(/(\s*)/g, ""); // 입력한 번호에서 - 공백 제거

				if(mobile2.length>15 || tel2.length>15) {
					alert('전화번호 및 휴대전화의 길이가 15자를 초과할 수 없습니다.(-,공백제외)');
					return -1;
				}
				

				if (isEmpty(password) || isEmpty(passwordConfirm)){
					$('input:checkbox[id="pass"]').prop("checked", true);
					// name 값 바꾸고 넘기기.. passwordConfirm, password
					$('#password').attr("name","password1");
					$('#passwordConfirm').attr("name","passwordConfirm1");
				} else {
					
					if (password != passwordConfirm) {
						$('input:checkbox[id="pass"]').prop("checked", false);
					} else {
						$('input:checkbox[id="pass"]').prop("checked", true);
					}
				}
				
				var modifyForm = $("form[name=modifyForm]").serializeArray();
				if(confirm('사용자 정보를 수정하시겠습니까?')) {
					$.ajax({
			            url:"<c:url value="/user/modify"/>",
			            method: 'POST',
			            data: modifyForm,
			            success: function (response) {
			            	alert("사용자 정보 수정 완료되었습니다.");
			            	location.href =response;
			            },
			            error: function (response) {
				            console.log("실패");
				            console.log(response);
			                var errors = response.responseJSON.errors;
			                
			                var field;
			                var message;
			                for(var i=0; i<errors.length; i++){
			                	field = errors[i].field;
			                	message= errors[i].defaultMessage;
			                	$('#password').attr("name","password");
								$('#passwordConfirm').attr("name","passwordConfirm");
			                	$('#'+field).after('<span class="error-message  text-danger">'+message+'</span>');
			                }
			            }
			        });
				}
				
			});		
			
			$('#cancleBtn').click(function() {
				location.href = "<c:url value="/user/list"/>";
			});		
			
		
		});
		
  </script>

  <script type="text/javascript" class="init">

	$(document).ready(function() {	
		
		var selectedModule = {};

		$('.datepicker').datepicker({
		  format: 'yyyy.mm.dd',
		  //todayBtn: "linked",
		  //clearBtn: true,
		  language: "kr",
		  autoclose: true,
		  todayHighlight: true,
		  toggleActive: true,
		  orientation: 'bottom auto',
		});
		
     	//회사
		$("#company").select2({
			placeholder: "회사명을 검색후 선택하세요",
			//allowClear: true,
			//minimumInputLength: 2,
			ajax: {
				url: '/user/companySelectorListAjax',
				processResults: function (data) {
				// Tranforms the top-level key of the response object from 'items' to 'results'
					return {
					  results: data.result
					};
				}
			}
		}); 
		
		var userRole = ${myInfo.role};

		//사용자권한		
		etcSelect('userRole', userRole, false, '${sessionScope.__USER__.role}');
		$("#userRole").select2();
	    
        var userStatus = ${myInfo.status};
        etcSelect('userStatus', userStatus, false);
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
        <p class="sub-hero-text"><span class="core-color">사용자</span> 정보<i class="icon icon-question"></i></p>
        <p class="sub-hero-text2">고객님의 불편사항을 신속히 처리해 드리겠습니다.</p>
      </div>
    </div>
    </section>


  <section class="section sub-contents">
    <div class="container">

      <section class="detail-form">
        <form:form modelAttribute="modifyForm" name="modifyForm">
          <div class="detail-wrap">
            <div class="col-lg-12">
              <div class="row">
                <label class="col-md-2 col-lg-1" for="company"><span class="required">회사</span></label>
                <div class="col-md-4 col-lg-3">
                <c:choose>
                	<c:when test="${sessionScope.__USER__.role eq '5' || sessionScope.__USER__.role eq '6'}">
                		<select class="form-control"  id="company" name="company.coId">
	                  		<option value="${myInfo.company.coId}" selected="selected">${myInfo.company.coName}</option>  
	                 	</select>
                	</c:when>
                	<c:otherwise>
	                	<select class="form-control"  id="company" disabled="disabled">
	                  		<option value="${myInfo.company.coId}" selected="selected">${myInfo.company.coName}</option>  
	                 	</select>
	                 	<input type="hidden" value="${myInfo.company.coId}" name="company.coId"/>
                	</c:otherwise>
                </c:choose>
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">사용자 ID</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" name="username" value="${myInfo.username}" readonly="readOnly">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">사용자명(한글)</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="uName" name="uName" value="${myInfo.uName}">
                </div>
                <label class="col-md-2 col-lg-1" for="">사용자명(En)</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" name ="userNameEN" value="${myInfo.userNameEN}">
                </div>
               
               
                <c:choose>
                 <c:when test="${sessionScope.__USER__.role eq '6' || sessionScope.__USER__.userId eq myInfo.userId}">
                  
	                <label class="col-md-2 col-lg-1" for=""><span class="required">비밀번호</span></label>
	                <div class="col-md-4 col-lg-3">
	                  <input type="password" id="password" name="password" class="form-control" value="">
	                </div>
	                <label class="col-md-2 col-lg-1" for=""><span class="required">비밀번호확인</span></label>
	                <div class="col-md-4 col-lg-3">
	                  <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" value="">
                 	  <input type="checkbox" style="display: none" id="pass" name="pass">
	                </div>
                 </c:when>
                 <c:otherwise>
                  <input type="checkbox" style="display: none" id="pass" name="pass">
                  </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${sessionScope.__USER__.role eq '5' || sessionScope.__USER__.role eq '6'}">
                        <label class="col-md-2 col-lg-1" for=""><span class="required">사용자권한</span></label>
                        <div class="col-md-4 col-lg-3">  
                            <select class="form-control"  id="userRole" name="role">
                                <%--<option value="0" selected="selected">${myInfo.company.coName}</option>  --%>
                            </select>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" value="${myInfo.role}" name="role"/>
                    </c:otherwise>
                </c:choose>
                <label class="col-md-2 col-lg-1" for="">직책</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" name="uTitle" value="${myInfo.getuTitle()}">
                </div>
                <c:if test="${sessionScope.__USER__.role eq '3' || sessionScope.__USER__.role eq '5' || sessionScope.__USER__.role eq '6'}">
                    <label class="col-md-2 col-lg-1" for=""><span class="required">사용자 상태</span></label>
                    <div class="col-md-4 col-lg-3">
                        <select class="form-control" id="userStatus" name="status">
                        </select>
                    </div>
                </c:if>
                <label class="col-md-2 col-lg-1" for="">소속부서명</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="dept" name="dept" value="${myInfo.getDept()}">
                </div>
                <input type="hidden" class="form-control" id="userId"  name="userId" value="${myInfo.userId}">
                <label class="col-md-2 col-lg-1" for="">담당업무명</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="businessDesc" name="businessDesc" value="${myInfo.getBusinessDesc()}">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">전화번호</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="tel" name="tel" value="${myInfo.getTel()}">
                </div>
                <label class="col-md-2 col-lg-1" for="">FAX번호</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="fax" name="fax" value="${myInfo.getFax()}">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">휴대전화</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="mobile" name="mobile" value="${myInfo.getMobile()}">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">E-Mail</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="email" name="email" value="${myInfo.getEmail()}">
                </div>
              </div>
            </div>
          </div>
        </form:form>
      </section>
      <div class="detail-btn-wrap">
      	<c:choose>
	      	<c:when test="${myInfo.userId eq sessionScope.__USER__.userId 
		      	|| sessionScope.__USER__.role eq '5' 
		      	|| sessionScope.__USER__.role eq '6' 
		      	|| (sessionScope.__USER__.role eq '3' && myInfo.company.coId eq sessionScope.__USER__.company.coId)}">
	        	<button type="button" class="btn btn-normal" onclick="javascript:history.back()">취소</button>        
	        	<button type="button" class="btn btn-type1" id="modifyInfo" type="submit"data-toggle="modal" data-target="#delivery">저장</button>
	      	</c:when>
	      	<c:otherwise>
	      		<button type="button" class="btn btn-normal" onclick="javascript:history.back()">목록으로</button>        
	      	</c:otherwise>
      	</c:choose>
      </div>
    </div> <!-- /container -->
  </section>

  
  

</body>

</html>
