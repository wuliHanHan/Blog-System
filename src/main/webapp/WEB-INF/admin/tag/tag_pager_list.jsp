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
			<th>标签名称</th>
			<th>操作</th>
		</tr>
	</thead>
	<div>
		<tbody>
			<c:forEach items="${tagList}" var="tag">
				<tr class="gradeX">
					<td>${tag.id}</td>
					<td>${tag.tagName}</td>
					<td>
						<button class="btn btn-primary btn-xs" type="button" title="编辑"
							onclick="editTag(${tag.id})">编辑</button>
						<button class="btn btn-primary btn-xs" type="button" title="删除"
							onclick="deleteTag(${tag.id})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
</table>


<jsp:include page="../pager.jsp" flush="true"></jsp:include>