<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th>Id</th>
			<th>名称</th>
			<th>站点地址</th>
			<th>描述</th>
			<th>排序</th>
			<th>操作</th>
		</tr>
	</thead>
	<div>
		<tbody>
			<c:forEach items="${partnerList}" var="partner">
				<tr class="gradeX">
					<td>${partner.id}</td>
					<td>${partner.siteName}</td>
					<td>${partner.siteUrl}</td>
					<td>${partner.siteDesc}</td>
					<td>${partner.sort}</td>
					<td>
						<button class="btn btn-primary btn-xs" type="button" title="编辑"
							onclick="editPartner(${partner.id})">编辑</button>
						<button class="btn btn-primary btn-xs" type="button" title="删除"
							onclick="deletePartner(${partner.id})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
</table>

<jsp:include page="../pager.jsp" flush="true"></jsp:include>