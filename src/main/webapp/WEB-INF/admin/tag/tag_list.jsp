<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>font-awesome/css/font-awesome.css"
	rel="stylesheet">
<!-- Data Tables -->
<link
	href="<%=basePath%>css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="<%=basePath%>css/animate.css" rel="stylesheet">
<link href="<%=basePath%>css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>js/flavr/flavr/css/animate.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>js/flavr/flavr/css/flavr.css" />
<link rel="stylesheet"
	href="<%=basePath%>/js/zTree_v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">

<style>
.btn-circle {
	width: 20px;
	height: 20px;
	border-radius: 10px;
}

.fa {
	position: relative;
	top: -3px;
}
</style>
</head>

<body>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="side-menu">

				<jsp:include page="../header.jsp" flush="true"></jsp:include>

				<li><a href="javascript:void(0)"><i class="fa fa fa-qrcode"></i>
						<span class="nav-label">文章管理</span> <span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="<%=basePath%>admin/article/list">文章列表</a></li>
					</ul></li>
				<li class="active"><a href="javascript:void(0)"><i
						class="fa fa fa-qrcode"></i> <span class="nav-label">标签管理</span> <span
						class="fa arrow"></span> </a>
					<ul class="nav nav-second-level">
						<li class="active"><a href="<%=basePath%>admin/tag/list">标签列表</a>
						</li>
					</ul></li>
				<li><a href="javascript:void(0)"><i class="fa fa fa-qrcode"></i>
						<span class="nav-label">链接管理</span> <span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="<%=basePath%>admin/friend/list">链接列表</a></li>
					</ul></li>
				<li><a href="javascript:void(0)"><i class="fa fa fa-qrcode"></i>
						<span class="nav-label">用户管理</span> <span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="<%=basePath%>admin/list">用户列表</a></li>
					</ul></li>
				<li><a href="javascript:void(0)"><i class="fa fa fa-qrcode"></i>
						<span class="nav-label">栏目管理</span> <span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="<%=basePath%>admin/category/list">栏目列表</a></li>
					</ul></li>
			</ul>

		</div>
		</nav>

		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>标签列表</h2>
					<ol class="breadcrumb">
						<li><a href="<%=basePath%>admin/home">主页</a></li>
						<li><a>标签管理</a></li>
						<li><strong>标签列表</strong></li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content">
								<div class="row">
									<form class="form-inline">
										<div class="col-sm-3">
											<div class="input-group">
												<input type="text" id="keyword" placeholder="请输入标签名称"
													class="input-sm form-control"> <span
													class="input-group-btn">
													<button type="button" class="btn btn-sm btn-primary"
														onclick="search()">搜索</button>
												</span>
											</div>
										</div>
										<button type="button" class="btn btn-sm btn-danger"
											onclick="addTag()">新增标签</button>
									</form>
								</div>

								<!-- 表格数据 -->
								<div id="dataList">
									<c:if test="/admin/tag/list"></c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<jsp:include page="../footer.jsp" flush="true"></jsp:include>

		</div>
	</div>

	</div>

	<!-- 编辑页面 -->
	<div class="modal inmodal" id="editTagModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div id="editTagContent" class="modal-body"></div>
		</div>
	</div>


	<!-- 新增页面 -->
	<div class="modal inmodal" id="addTagModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div id="addTagContent" class="modal-body"></div>
		</div>
	</div>


	<!-- Mainly scripts -->
	<script src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=basePath%>js/hplus.js"></script>
	<script src="<%=basePath%>js/plugins/pace/pace.min.js"></script>
	<script src="<%=basePath%>js/validation.js"></script>
	<script src="<%=basePath%>js/tag/tag.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/flavr/flavr/js/flavr.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/flavr/base.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/zTree_v3/js/jquery.ztree.all-3.5.js"></script>

</body>

</html>
