<%@ page language="java" import="java.util.*, java.net.URLEncoder, com.ziqiang.model.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html><head>
<link rel="stylesheet" id="main-css" href="<%=basePath%>xiu/style.css" type="text/css" media="all">
<%-- <meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="baidu-site-verification" content="emEdenaBVA">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>乱七八糟</title>
<meta name="description" content="coriger 夏末冬初  
<c:forEach items="${tagList}" var="tag">${tag.tagName} </c:forEach>" />
<meta name="keywords" content="coriger 夏末冬初  
<c:forEach items="${categoryList}" var="category">${category.categoryName} </c:forEach>
"/> --%>
<style type="text/css">
	img.wp-smiley,
	img.emoji {
		display: inline !important;
		border: none !important;
		box-shadow: none !important;
		height: 1em !important;
		width: 1em !important;
		margin: 0 .07em !important;
		vertical-align: -0.1em !important;
		background: none !important;
		padding: 0 !important;
	}
</style>


</head>

<body id="contain" class="home blog ui-c3">
<section class="container">
<form method="get" class="degfy_search_box">
        <input class="degfy_search_input" name="s" type="text" placeholder="输入关键字" value="">
</form>
<header class="header">
    <div class="logo_right"><span class="glyphicon glyphicon-search degfy_search"></span></div>
    <div class="logo_left"></div>
	<ul class="nav">

<li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-61">
	<a href="">
		<span class="glyphicon glyphicon-home"></span>首页
	</a>
</li>

<c:forEach items="${categoryList}" var="category">
	<li class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-61">
	<a href="<%=basePath%>category/${category.aliasName}">
		<span class="glyphicon glyphicon-${category.iconClass}"></span>${category.categoryName}
	</a></li>
</c:forEach>

</ul>		<div class="widget_head">
			</div>
</header>
<div class="content-wrap">
	<div class="content" id="content">
						<h3 class="title"><strong>最新发布</strong></h3>
						<input type="hidden" id="categoryId" value="-1"/>
						
				</div>
			</div>
<aside class="sidebar">

<!-- <div class="widget widget_searchbox affix-top" style="top: 0px;">
    <form method="get" class="search-form" action="http://www.coriger.com">
        <input class="form-control" name="s" type="text" placeholder="输入关键字如：暂时不能用" value="">
        <input class="btn" type="button" value="搜一下">
    </form>
</div> -->

<div class="widget widget_tags">
   <h3 class="title"><strong>热门标签</strong></h3> 
   <ul class="widget_tags_inner"> 
   	<c:forEach items="${tagList}" var="tag">
   		<li><a title="" href="javascript:;" >${tag.tagName}</a></li> 
   	</c:forEach>
   </ul> 
  </div> 
  <div class="widget widget_text"> 
   <h3 class="title"><strong>友情链接</strong></h3> 
   <div class="textwidget"> 
    <ul class="widget_tags_inner"> 
    	<c:forEach items="${friendList}" var="friend">
   			<li><a href="http://${friend.siteUrl}" target="_blank" class="fr_link">${friend.siteName}</a></li> 
   		</c:forEach>
    </ul> 
   </div>
  </div>
		
</div></aside>
		
	<%-- <jsp:include page="footer.jsp" flush="true"></jsp:include> --%>
		
</section>

<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/article/article_list.js"></script>
<!-- <script src="//cdn.bootcss.com/jquery.lazyload/1.9.1/jquery.lazyload.js"></script> -->

</body>

<!-- <script>

var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?790c0d88ebfcc979bcb6d95993bffb1d";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();

</script> -->

</html>