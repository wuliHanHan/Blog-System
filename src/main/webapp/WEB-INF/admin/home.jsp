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
<!-- Morris -->
<link href="<%=basePath%>css/plugins/morris/morris-0.4.3.min.css"
	rel="stylesheet">
<!-- Gritter -->
<link href="<%=basePath%>js/plugins/gritter/jquery.gritter.css"
	rel="stylesheet">
<link href="<%=basePath%>css/animate.css" rel="stylesheet">
<link href="<%=basePath%>css/style.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/toastr/toastr.min.css"
	rel="stylesheet">

<style>
.wel-text {
	height: 813px;
	background: #000 url(<%=basePath%>img/home.jpg) center 100px no-repeat;
}
</style>
</head>

<body>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="side-menu">

				<jsp:include page="header.jsp" flush="true"></jsp:include>

				<li><a href="javascript:void(0)"> <i
						class="fa fa fa-volume-up"></i> <span class="nav-label">文章管理</span>
						<span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="<%=basePath%>admin/article/list">文章列表</a></li>
					</ul></li>
				<li><a href="javascript:void(0)"> <i
						class="fa fa fa-qrcode"></i> <span class="nav-label">标签管理</span> <span
						class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="<%=basePath%>admin/tag/list">标签列表</a></li>
					</ul></li>
				<li><a href="javascript:void(0)"> <i
						class="fa fa fa-qrcode"></i> <span class="nav-label">链接管理</span>
						<span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="<%=basePath%>admin/partner/list">链接列表</a></li>
					</ul></li>
				<li><a href="javascript:void(0)"> <i
						class="fa fa fa-qrcode"></i> <span class="nav-label">用户管理</span>
						<span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="<%=basePath%>admin/list">用户列表</a></li>
					</ul></li>
			</ul>

		</div>
		</nav>

		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
				<ul class="nav navbar-top-links navbar-right">
					<li><span class="m-r-sm text-muted welcome-message"><a
							href="javascript:void(0)"><i class="fa fa-home"></i></a>欢迎使用管理后台</span>
					</li>
					<li><a href="<%=basePath%>out"> <i class="fa fa-sign-out"></i>
							退出
					</a></li>
				</ul>

				</nav>
			</div>

			<jsp:include page="footer.jsp" flush="true"></jsp:include>

		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="<%=basePath%>js/hplus.js"></script>
	<script src="<%=basePath%>js/plugins/pace/pace.min.js"></script>
	<script src="<%=basePath%>js/plugins/toastr/toastr.min.js"></script>

	<script>
		toastr.options = {
					  "closeButton": true,
					  "debug": false,
					  "progressBar": true,
					  "positionClass": "toast-bottom-right",
					  "onclick": null,
					  "showDuration": "50",
					  "hideDuration": "100",
					  "timeOut": "1200",
					  "extendedTimeOut": "100",
					  "showEasing": "swing",
					  "hideEasing": "linear",
					  "showMethod": "fadeIn",
					  "hideMethod": "fadeOut"
		}
		
		/* $().ready(function(){
			toastr.info("登录成功");
		}) */
	</script>
</body>

</html>