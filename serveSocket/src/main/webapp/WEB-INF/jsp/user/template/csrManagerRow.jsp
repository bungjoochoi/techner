<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="data-table-wrap">
          <table id="" class="table dataTable type2 table-striped table-bordered text-center" style="width:100%">
            <thead>
              <tr>
                <th>영역구분</th>
                <th>영역</th>
                <th>담당자</th>
                <th>E-Mail</th>
                <th>전화번호</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td rowspan="${mgCount.get('SAP')+sapList.size()}" class="textAC">SAP</td>
              </tr>   
              <c:forEach items="${sapList}" var="sap">
	              <tr>
	                <td rowspan="${sap.userList.size()+1}" class="textAC">${sap.mName}</td>
	              </tr>
	              <c:forEach items="${sap.userList}" var="user" varStatus="status">
	              	<c:choose>
						<c:when test="${status.index % 2 eq '0'}">
							<tr class="odd">
							<td class="textAC"><a href="<c:url value="/csrManagerList" />">${user.uName}</a></td>
			                <td>${user.email}</td>
			                <td class="textAC">${user.tel}</td>
						</c:when>
						<c:otherwise>
							<tr class="even">
							<td class="textAC"><a href="<c:url value="/csrManagerList" />">${user.uName}</a></td>
			                <td>${user.email}</td>
			                <td class="textAC">${user.tel}</td>
						</c:otherwise>						
					</c:choose>		
	              </c:forEach>
              </c:forEach>
              <tr>
                <td rowspan="${mgCount.get('Non_SAP')+nonsapList.size()+1}" class="textAC">Non-SAP</td>
              </tr>   
              <c:forEach items="${nonsapList}" var="nonsap">
	              <tr>
	                <td rowspan="${nonsap.userList.size()+1}" class="textAC">${nonsap.mName}</td>
	              </tr>
	              <c:forEach items="${nonsap.userList}" var="user" varStatus="status">
	              	<c:choose>
						<c:when test="${status.index %2 eq '0'}">
							<tr class="odd">
							<td class="textAC"><a href="<c:url value="/csrManagerList" />">${user.uName}</a></td>
			                <td>${user.email}</td>
			                <td class="textAC">${user.tel}</td>
						</c:when>
						<c:otherwise>
							<tr class="even">
							<td class="textAC"><a href="<c:url value="/csrManagerList" />">${user.uName}</a></td>
			                <td>${user.email}</td>
			                <td class="textAC">${user.tel}</td>
						</c:otherwise>						
					</c:choose>		
	              </c:forEach>
              </c:forEach>             
            </tbody>
          </table>
        </div>