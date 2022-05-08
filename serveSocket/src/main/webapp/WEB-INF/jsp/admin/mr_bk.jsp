<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>ServeSocket</title>
  

<script type="text/javascript" > 
	//https://datatables.net/
	//table위에 10개씩보기 / 검색하는 필드 띄우는 function
	$(document).ready(function() {
		var table = $('#example').DataTable({
			responsive : true,
			language : lang_kor,
		});
	
		new $.fn.dataTable.FixedHeader(table);
	});
</script>
  

  <script type="text/javascript" class="init">

	$(document).ready(function() {
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
			multiple : true,
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
	      
	  	$('#searchUserBtn').click(function() {
	  		var searchCondition = $("#searchType option:selected").val();
	  		console.log(searchCondition);
	  		var searchForm = $("#searchForm");
	  		
	  		searchForm.attr({
	  			"action" : "<c:url value="/searchUserAjax"/>",
	  			"method" : "POST"
	  		}).submit();
	  	});	
	  	
	  	$("#searchType").change(function() {
	          var selected = $("#searchType").children("option:selected").text();
	          $("#searchType").children("option:selected").attr("selected", "selected");
	      });
	  	
	    //영역구분, 영역	    	
	    commSelect('moduleGroup',true);
	
	  	$("#moduleGroup").change(function(){  
	  		$('#module').find('option').each(function() {
	  			$(this).remove();
	  		});
	  		var selectVal = $( this ).val();
	  		if(selectVal != ''){
	  			commSelect('module', true);	
	  		}
	  	});
	  	
		//사용자권한		
		makeSelect('userRole', userRole, true);
        //$("#userRole").select2({
		//	placeholder: "사용자 권한을 선택하세요."
	    //});
      
    });
    
 	

  </script>
    <script type="text/javascript">
    $(function() {
      $(document).ready(function() {

        $(".add-btn").click(function(){
          // clone
          $.trClone = $(this).closest(".table-wrapper").find(".edit-table tr:last").clone().html();
          $.newTr = $("<tr>"+$.trClone+"</tr>");

          // append
          $(this).closest(".table-wrapper").find(".edit-table").append($.newTr).find("input").prop("readonly", false);

        });
        $(".edit-btn").click(function(){
          $(this).closest(".table-wrapper").find("input").prop("readonly", false);
        });
        $(".save-btn").click(function(){
          $(this).closest(".table-wrapper").find("input").prop("readonly", true);
        });
      });
      $(document).on("click",".del-btn",function(){         
          $(this).closest("tr").remove();
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
				<p class="sub-hero-text">
					<span class="core-color">ServeSocket</span> 유지보수
				</p>
			</div>
		</div>
	</section>
	<section class="section sub-contents">
    <div class="container">
        
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
          <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">사용자 유지보수</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">회사 유지보수</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">코드값 유지보수</a>
        </li>
      </ul>
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
          <div class="search-form">
            <form:form modelAttribute="searchForm">
              <div class="search-wrap">
                <div class="row">                
                
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="company">회사</label>
                      <select class="form-control" id="company" name="company.coId">
                      </select>
                    </div>
                  </div>

                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="modulegroup">영역구분</label>
                      <select class="form-control" id="moduleGroup" name="modulegroup">
                      </select>
                    </div>
                  </div>

                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="module">영역</label>
                      <select class="form-control" id="module" name="module">
                        </select>
                    </div>
                  </div>

                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="csrStatus">이름</label>
                      <input type="text" class="form-control" id="uName" name="uName" placeholder="이름을 입력하세요." value="">
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="csrStatus">전화번호</label>
                      <input type="text" class="form-control" id="tel" name="tel" placeholder="전화번호를 입력하세요" value="">
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="csrStatus">휴대전화</label>
                      <input type="text" class="form-control" id="mobile" name="mobile" placeholder="휴대전화번호를 입력하세요." value="">
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="csrStatus">사용자권한</label>
		                <select class="form-control"  id="userRole" name="role">
	                 	</select>
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="csrStatus">사용자상태</label>
	                    <select class="form-control" id="userStatus" name="status">
	        				<option value="1" >재직</option>
	        				<option value="2">퇴직</option>
	        				<option value="3">사용중지</option>
	      				</select>
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="csrStatus">E-mail</label>
                      <input type="text" class="form-control" id="email" name="email" placeholder="E-mail을 입력하세요." value="">
                    </div>
                  </div>
                </div>
              </div>

              <div class="search-btn-wrap">
                <div class="form-group">
                  <button type="submit" class="btn btn-search"><i class="icon icon-search"></i>조회</button>
                </div>
              </div>

            </form:form>

          </div>

          <div class="data-table-wrap">
            <table id="example" class="table table-striped table-bordered text-center display" width="100%">
                <thead>
                    <tr>
                        <th>회사</th>
                        <th>사용자 ID</th>
                        <th>이름</th>
                        <th>이름(영어)</th>
                        <th>사용자권한</th>
                        <%--
                        <th>승인자</th>
                        <th>영역구분</th>
                        <th>영역</th>
                        <th>담당자</th>
                        <th>직책</th>
                        <th>사용자상태</th>
                        <th>소속부서명</th>
                        <th>담당업무명</th>
                        <th>전화번호</th>
                        <th>Fax번호</th>
                        <th>휴대전화</th>
                        <th>E-mail</th>
                        --%>
                        <th>SM KPI 관리자</th>
                        <th>CTS 승인자</th>
                        <%--<th width="18%">Start date</th> --%>
                    </tr>
                </thead>
                <tbody>                  
                  <c:forEach items="${userList}" var="user"> 
	                  	<tr>
		                    <td>${user.company.coName}</td>
		                    <td>${user.username}</td>
		                    <td>${user.uName}</td>
		                    <td>${user.userNameEN}</td>
		                    <td>${user.roleName}</td>
		                    <%--
		                    <td>${user.approver.uName}</td>
		                    <td>${user.userModule[0].moduleGroup.groupName}</td>
		                    <td>${user.userModule[0].module.mName}</td>
		                    <td>${user.respondent.uName}</td>
		                    <td>${user.uTitle}</td>
		                    <c:choose>
		                    	<c:when test="${user.status eq 1}">
		                    		<td>재직</td>
		                    	</c:when>
		                    	<c:when test="${user.status eq 2}">
		                    		<td>퇴직</td>
		                    	</c:when>
		                    	<c:otherwise>
		                    		<td>사용중지</td>
		                    	</c:otherwise>
		                    </c:choose>		                    
		                    <td>${user.businessDesc}</td>
		                    <td>${user.tel}</td>
		                    <td>${user.fax}</td>
		                    <td>${user.mobile}</td>
		                    <td>${user.email}</td>
		                     --%>
		                    <td>${user.isAdminKpi}</td>
		                    <td>${user.isAdminCts}</td>
	                  </tr>
                  </c:forEach>
                </tbody>
            </table>
          </div>
        </div>
        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
			<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	          <div class="search-form">
	            <form:form modelAttribute="searchForm">
	              <div class="search-wrap">
	                <div class="row">                
	                
	                  <div class="col-md-4">
	                    <div class="form-group">
	                      <label for="company">회사코드</label>
	                      <select class="form-control" id="company" name="company.coId">
	                      </select>
	                    </div>
	                  </div>
	
	                  <div class="col-md-4">
	                    <div class="form-group">
	                      <label for="modulegroup">산업분류</label>
	                      <select class="form-control" id="moduleGroup" name="modulegroup">
	                      </select>
	                    </div>
	                  </div>
	
	                  <div class="col-md-4">
	                    <div class="form-group">
	                      <label for="module">계약상태</label>
	                      <select class="form-control" id="module" name="module">
	                        </select>
	                    </div>
	                  </div>
	
	                  <div class="col-md-4">
	                    <div class="form-group">
	                      <label for="csrStatus">회사명</label>
	                      <input type="text" class="form-control" id="uName" name="uName" placeholder="이름을 입력하세요." value="">
	                    </div>
	                  </div>
	                  
	                  <div class="col-md-4">
	                    <div class="form-group">
	                      <label for="csrStatus">회사역할</label>
	                      <input type="text" class="form-control" id="tel" name="tel" placeholder="전화번호를 입력하세요" value="">
	                    </div>
	                  </div>
	                  
	                  <div class="col-md-4">
	                    <div class="form-group">
	                      <label for="csrStatus">회사설명</label>
	                      <input type="text" class="form-control" id="mobile" name="mobile" placeholder="휴대전화번호를 입력하세요." value="">
	                    </div>
	                  </div>
	                  
	                  <div class="col-md-4">
	                    <div class="form-group">
	                      <label for="csrStatus">월투입공수</label>
			                <select class="form-control"  id="userRole" name="role">
		                 	</select>
	                    </div>
	                  </div>
	
	              <div class="search-btn-wrap">
	                <div class="form-group">
	                  <button type="submit" class="btn btn-search"><i class="icon icon-search"></i>조회</button>
	                </div>
	              </div>
	
	            </form:form>
	
	          </div>
	
	          <div class="data-table-wrap">
	            <table id="example" class="table table-striped table-bordered text-center display" width="100%">
	                <thead>
	                    <tr>
	                        <th>회사코드</th>
	                        <th>회사명</th>
	                        <th>회사명(En)</th>
	                        <th>회사설명</th>
	                        <th>Order Idx</th>
	                        <th>산업분류</th>
	                        <th>업태</th>
	                        <th>업종</th>
	                        <th>주소</th>
	                        <th>Tel</th>
	                    </tr>
	                </thead>
	                <tbody>                  

	                  <tr>
		                    <td></td>
		                    <td></td>
		                    <td></td>
		                    <td></td>
		                    <td></td>
	                  </tr>

	                </tbody>
	            </table>
	          </div>
	        </div>        
        </div>
        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
        	코드값 유지보수
        </div>
      </div>

        


    </div> <!-- /container -->
  </section>

</body>
</html>