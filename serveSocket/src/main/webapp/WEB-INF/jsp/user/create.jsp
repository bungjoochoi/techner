<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
  <title>ServeSocket</title>
  
<script>var duplicateCheck = false;</script>
  <script>
		$(document).ready(function(){
			$('#create').click(function() {
				$(".error-message").remove();
				var mobile = $("#mobile").val(); // 현재 입력한 번호
				var mobile1 = mobile.replace(/-/gi, ""); // 입력한 번호에서 - 제외
				var mobile2 = mobile1.replace(/(\s*)/g, ""); // 입력한 번호에서 - 공백 제거

				var tel = $("#tel").val(); // 현재 입력한 번호
				var tel1 = tel.replace(/-/gi, ""); // 입력한 번호에서 - 제외
				var tel2 = tel1.replace(/(\s*)/g, ""); // 입력한 번호에서 - 공백 제거
				
				console.log(tel2);
				console.log("길이 " + tel2.length);
				if(mobile2.length>15 || tel2.length>15) {
					alert('전화번호 및 휴대전화의 길이가 15자를 초과할 수 없습니다.(-,공백제외)');
					return -1;
				}
				
				if(!duplicateCheck){
					alert('아이디 중복확인을 해주세요.');
					return -1;
				}
				var password = $("#password").val();
				var passwordConfirm = $("#passwordConfirm").val();
				if (passwordConfirm =='' || password == ''){
					$('input:checkbox[id="pass"]').prop("checked", true);
				}
				else{
					if (password != passwordConfirm) {
						$('input:checkbox[id="pass"]').prop("checked", false);
					} else {
						$('input:checkbox[id="pass"]').prop("checked", true);
					}
				}

				var createForm = $("form[name=createForm]").serialize();
				$.ajax({
		            url:"<c:url value="/user/create"/>",
		            method: 'POST',
		            data: createForm,
		            success: function (response) {
		            	alert("사용자 정보 등록 완료되었습니다.");
		            	location.href =response;
		            },
		            error: function (response) {
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

		$('#checkId').click(function(){
			duplicateCheck = true;
			
			var checkID = $("#username").val().trim();
			 $.ajax({
		            async: true,
		            type : 'POST',
		            data : checkID,
		            url : "/user/checkID",
		            dataType : "json",
		            contentType: "application/json; charset=UTF-8",
		            success : function(data) {
			            console.log(data);
		                if (data > 0) {
		                    alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
		                } else {
		                    alert("사용가능한 아이디입니다.");
		                }
		            },
		            error : function(error) {
		                alert("error : " + error);
		                console.log(error);
		            }
		        });
			
		});
			
      
      
     	//회사
		$("#company").select2({
			placeholder: "회사명을 검색후 선택하세요",
			//allowClear: true,
			//minimumInputLength: 2,
			ajax: {
				url: '/common/code/companyListAjax',
				processResults: function (data) {
				// Tranforms the top-level key of the response object from 'items' to 'results'
					return {
					  results: data.result
					};
				}
			}
		}); 
		      
		//사용자권한	
		etcSelect('userRole', '', false);
		//$("#userRole").select2();

        etcSelect('userStatus', '', false);
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
        <p class="sub-hero-text"><span class="core-color">사용자</span> 등록<i class="icon icon-question"></i></p>
        <p class="sub-hero-text2">고객님의 불편사항을 신속히 처리해 드리겠습니다.</p>
      </div>
    </div>
    </section>


  <section class="section sub-contents">
    <div class="container">

      <section class="detail-form">
        <form:form modelAttribute="createForm" name="createForm">
          <div class="detail-wrap">
            <div class="col-lg-12">
              <div class="row">
                <label class="col-md-2 col-lg-1" for="company"><span class="required">회사</span></label>
                <div class="col-md-4 col-lg-3">
                  <select class="form-control"  id="company" name="company.coId">
                  </select>
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">사용자 ID</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="username" name="username" value="">
                   <input type="button" id="checkId" value="중복확인">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">사용자명(한글)</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="uName" name="uName" value="">
                </div>
                <label class="col-md-2 col-lg-1" for="">사용자명(En)</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="userNameEN" name ="userNameEN" value="">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">비밀번호</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="password" class="form-control" id="password" name="password" value="">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">비밀번호확인</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" value="">
                  <input type="checkbox" style="display: none" id="pass" name="pass">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">사용자권한</span></label>
                <div class="col-md-4 col-lg-3">
                  <select class="form-control"  id="userRole" name="role">
                  </select>
                </div>
                <label class="col-md-2 col-lg-1" for="">직책</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" name="uTitle" value="">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">사용자 상태</label>
                <div class="col-md-4 col-lg-3">
                    <select class="form-control" id="userStatus" name="status">
                    </select>
                </div>
                <label class="col-md-2 col-lg-1" for="">소속부서명</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" name="dept" value="">
                </div>
                <label class="col-md-2 col-lg-1" for="">담당업무명</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" name="businessDesc" value="">
                </div>
                <label class="col-md-2 col-lg-1"  for=""><span class="required">전화번호</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="tel" name="tel" value="">
                </div>
                <label class="col-md-2 col-lg-1"  for="">FAX번호</label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" name="fax" value="">
                </div>
                <label class="col-md-2 col-lg-1"  for=""><span class="required">휴대전화</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="mobile" name="mobile" value="">
                </div>
                <label class="col-md-2 col-lg-1" for=""><span class="required">E-Mail</span></label>
                <div class="col-md-4 col-lg-3">
                  <input type="text" class="form-control" id="email" name="email" value="">
                </div>
              </div>
            </div>
          </div>

        </form:form>
      </section>

      <div class="detail-btn-wrap">
		<button type="button" class="btn btn-normal" onClick="history.go(-1)">취소</button>        
	    <button type="button" class="btn btn-type1" id="create" type="submit"data-toggle="modal" data-target="#delivery">저장</button>	      	
      </div>
    </div> <!-- /container -->
  </section>

</body>

</html>
