<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>ServeSocket</title>

<script type="text/javascript">
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
					format : 'yyyy.mm.dd',
					//todayBtn: "linked",
					//clearBtn: true,
					language : "kr",
					autoclose : true,
					todayHighlight : true,
					toggleActive : true,
					orientation : 'bottom auto',
				});
				
				var a = window.history;
				console.log(a);
				
				var userSearch = "${userSearch}";
				if( userSearch != null ){
					$("#searchType").val('${userSearch.searchType}').prop('selected', 'selected');
					$("#searchKeyword").val('${userSearch.searchKeyword}');
				}

				$("#company").select2({
					placeholder : "회사명을 검색후 선택하세요",
					multiple : true,
					ajax : {
						url : '/user/companySelectorListAjax',
						processResults : function(data) {
							return {
								results : data.result
							};
						}
					}
				});

				
				var sendMap = {};
				var map = {};
				var multiParam = [];
				$("#company").on("select2:select", function(event) {
					map = {};
					multiParam = [];
					$(event.currentTarget).find("option:selected").each(
							function(i, selected) {
								var mvalue = $(selected).text();
								map.cName = mvalue;
								multiParam.push(map);
								map = {};
							});
					sendMap.multiparam = multiParam;

					$.ajax({
						url : '<c:url value="/user/listSearchCompany" />',
						contentType : "application/json; charset=UTF-8",
						data : JSON.stringify(sendMap),
						type : 'POST',
						success : onSuccess
					});
					function onSuccess(res, status, xhr) {
						$('#userListRow').html(res);
					}
				});

				$("#company").on("select2:unselect", function(event) {
					map = {};
					multiParam = [];
					$(event.currentTarget).find("option:selected").each(
							function(i, selected) {
								var mvalue = $(selected).text();
								map.cName = mvalue;
								multiParam.push(map);
								map = {};
							});
					sendMap.multiparam = multiParam;

					$.ajax({
						url : '<c:url value="/user/listSearchCompany" />',
						contentType : "application/json; charset=UTF-8",
						data : JSON.stringify(sendMap),
						type : 'POST',
						success : onSuccess
					});
					function onSuccess(res, status, xhr) {
						$('#userListRow').html(res);
					}
				});
				
				$("#search").select2();
				
				$("#createUser").on("click", function(){
					location.href = "<c:url value="/user/create"/>";
				});
				
				$('#searchUserBtn').click(function() {
					var searchCondition = $("#searchType option:selected").val();
					console.log(searchCondition);
					var searchForm = $("#searchForm");
					searchForm.attr({
						"action" : "<c:url value="/user/listAjax"/>",
						"method" : "GET"
					}).submit();
				});	
				
				$("#searchType").change(function() {
			        var selected = $("#searchType").children("option:selected").text();
			        $("#searchType").children("option:selected").attr("selected", "selected");
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
					<span class="core-color">사용자</span> 현황<i class="icon icon-question"></i>
				</p>
				<p class="sub-hero-text2">고객님의 불편사항을 신속히 처리해 드리겠습니다.</p>
			</div>
		</div>
	</section>

	<section class="section sub-contents">
		<div class="container">
			<div class="search-form">
				<form:form modelAttribute="searchForm">
					<div class="search-wrap">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="company">회사선택</label>
									<select class="form-control" id="company" name="coId" multiple="multiple">
										<c:choose>
											<c:when test="${not empty companyList}">
												<c:forEach items="${companyList}" var="company">
													<option value="${company.get('id')}"
													selected="selected">${company.get('text')}</option>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<option value="${userList[0].company.coId}"
											selected="selected">${userList[0].company.coName}</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="search">검색조건</label> 
									<select class="form-control" id="searchType" name="searchType">
										<option value="" disabled="disabled" selected="selected">검색조건</option>
										<option value="0">이   름</option>
										<option value="1">직   책</option>
										<option value="2">연락처</option>
										<option value="3">메   일</option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="searchValue">이름</label> 
									<input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력해 주세요." 	value="">
								</div>
							</div>
						</div>
					</div>

					<div class="search-btn-wrap">
						<div class="form-group">
							<button type="submit" class="btn btn-search" id="searchUserBtn">
								<i class="icon icon-search"></i>조회
							</button>
						</div>
					</div>

				</form:form>
			</div>

			<div class="data-table-wrap" id="userListRow">				
				<div class="data-table-wrap">
					<table id="example"
						class="table table-striped table-bordered text-center"
						style="width: 100%">
						<thead>
							<tr>
								<th>No.</th>
								<th>회사</th>
								<th>사용자명(한글)</th>
								<th>직책</th>
								<th>사무실전화</th>
								<th>E-Mail</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="user">
								<tr>
									<td>${user.userId}</td>
									<td>${user.company.coName}</td>
									<td><a href="<c:url value="/user/view/${user.userId}" />" >${user.uName}</a></td>
									<td>${user.uTitle}</td>
									<td>${user.tel}</td>
									<td>${user.email}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<c:if test="${sessionScope.__USER__.role eq '5' || sessionScope.__USER__.role eq '6'}">
				<div class="text-right">
			        <button type="button" class="btn btn-normal" id="createUser">사용자 생성</button>
			    </div>
			</c:if>

		</div>
		<!-- /container -->
	</section>


</body>

</html>
