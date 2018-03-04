<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script src="<%=basePath%>js/partner/partner_edit.js"></script>

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

.input-upload {
	position: relative;
}

.input-upload input[type="file"] {
	position: absolute;
	left: 0px;
	top: 0px;
	width: 72px;
	height: 35px;
	opacity: .0;
	filter: alpha(opacity = 00);
}
</style>

<form id="editForm" method="post" class="form-horizontal">
	<fieldset>
		<legend>编辑链接</legend>
		<input type="hidden" name="id" value="${friend.id}">
		<div class="form-group">
			<label class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="siteName"
					name="siteName" maxlength="35" value="${friend.siteName}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">网址</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="siteUrl" name="siteUrl"
					maxlength="35" value="${friend.siteUrl}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="siteDesc"
					name="siteDesc" maxlength="35" value="${friend.siteDesc}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">排序</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="sort" name="sort"
					value="${friend.sort}">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-4 col-sm-offset-4">
				<button class="btn btn-primary" type="button"
					onclick="saveEditPartner()">保存</button>
				<button class="btn btn-white" type="button"
					onclick="closeEditWindow()">取消</button>
			</div>
		</div>
	</fieldset>
</form>
