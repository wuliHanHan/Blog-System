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
			<th>用户名</th>
			<th>密码</th>
			<th>操作</th>
		</tr>
	</thead>
	<div>
		<tbody>
			<c:forEach items="${managerList}" var="manager">
				<tr class="gradeX">
					<td>${manager.id}</td>
					<td>${manager.userName}</td>
					<td>${manager.password}</td>
					<td>
						<button class="btn btn-primary btn-xs" type="button" title="编辑"
							onclick="editManager(${manager.id})">编辑</button>
						<button class="btn btn-primary btn-xs" type="button" title="删除"
							onclick="deleteManager(${manager.id})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
</table>


<jsp:include page="pager.jsp" flush="true"></jsp:include>