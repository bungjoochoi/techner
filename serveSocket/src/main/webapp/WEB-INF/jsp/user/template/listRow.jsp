<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
					<td>${user.mobile}</td>
					<td>${user.email}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
