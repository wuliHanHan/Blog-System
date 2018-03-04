<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>js/tag/tag.js"></script>

<style>
fieldset {
	border: 1px solid #1ab394;
	padding: 10px;
}

legend {
	display: block;
	width: auto;
	padding: 0;
	margin-bottom: 0;
	font-size: 14px;
	color: #333;
	border: none;
}
</style>

<form id="editForm" method="post" class="form-horizontal">
	<fieldset>
		<legend>编辑标签</legend>
		<input type="hidden" name="id" id="id" value="${tag.id}">
		<div class="form-group">
			<label class="col-sm-2 control-label">标签</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="tagName" name="tagName"
					value="${tag.tagName}" maxlength="20">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-4 col-sm-offset-4">
				<button class="btn btn-primary" type="button"
					onclick="saveEditTag()">保存</button>
				<button class="btn btn-white" type="button"
					onclick="closeEditWindow()">取消</button>
			</div>
		</div>
	</fieldset>
</form>
