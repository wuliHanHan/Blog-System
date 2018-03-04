<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" id="main-css" href="<%=basePath%>xiu/style.css"
	type="text/css" media="all">
<link
	href="<%=basePath%>js/ueditor1_4_3/third-party/SyntaxHighlighter/shCoreDefault.min.css"
	rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="baidu-site-verification" content="emEdenaBVA">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>${article.title}</title>
<meta name="description"
	content="${fn:substring(article.description, 0,60)}" />
<meta name="keywords" content="" <c:forEach items="${article.tagList}" var="tag">${tag.tagName} </c:forEach>${article.title} "/>
<style type="text/css">
img.wp-smiley, img.emoji {
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
		<input class="degfy_search_input" name="s" type="text"
			placeholder="输入关键字" value="">
	</form>
	<header class="header">
	<div class="logo_right">
		<span class="glyphicon glyphicon-search degfy_search"></span>
	</div>
	<div class="logo_left"></div>
	<ul class="nav">

		<li
			class="menu-item menu-item-type-custom menu-item-object-custom menu-item-home menu-item-61">
			<a href="<%=basePath%>"> <span class="glyphicon glyphicon-home"></span>首页
		</a>
		</li>
		<c:forEach items="${categoryList}" var="category">
			<li
				class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-61  
		<c:if test="${article.categoryId == category.id}">
			current_page_item 
		</c:if>
	">
				<a href="<%=basePath%>category/${category.aliasName}"> <span
					class="glyphicon glyphicon-heart-empty"></span>${category.categoryName}
			</a>
			</li>
		</c:forEach>
		<li
			class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-61">
			<a href="<%=basePath%>login"><span
				class="glyphicon glyphicon-heart-empty"></span>登录 </a>
		</li>
	</ul>
	<div class="widget_head"></div>
	</header>
	<div class="content-wrap">
		<div class="content">
			<header class="article-header">
			<h1 class="article-title">
				<a href="<%=basePath%>article/html/${article.id}">${article.title}</a>
			</h1>
			<ul class="article-meta">
				<li>作者：${article.author}&nbsp;&nbsp;&nbsp;&nbsp;发布于${article.createTime}</li>
				<li>分类：<a href="<%=basePath%>category/${article.aliasName}"
					rel="category tag" data-original-title="" title="">${article.categoryName}</a></li>

				<li></li>
			</ul>
			</header>
			<article class="article-content"> ${article.content}
			<p class="post-copyright">
				未经允许不得转载：<a href="<%=basePath%>"></a>>><a
					href="<%=basePath%>article/html/${article.id}">${article.title}</a>
			</p>
			</article>

			<div class="article-tags">
				标签：
				<c:forEach items="${article.tagList}" var="tag">
					<a title="" href="<%=basePath%>tag/${tag.tagName}">${tag.tagName}</a>
				</c:forEach>
			</div>

			<nav class="article-nav"> <c:if test="${beforeArticle != null}">
				<span class="article-nav-prev">上一篇<br> <a
					href="<%=basePath%>article/html/${beforeArticle.id}" rel="prev">${beforeArticle.title}</a></span>
			</c:if> <c:if test="${nextArticle != null}">
				<span class="article-nav-next">下一篇<br> <a
					href="<%=basePath%>article/html/${nextArticle.id}" rel="next">${nextArticle.title}</a></span>
			</c:if> </nav>


			<!--高速版-->
			<div id="SOHUCS"></div>

			<%-- <c:if test="${relationList != null && fn:length(relationList) > 0}">
				<div class="relates"><h3 class="title"><strong>相关推荐</strong></h3>
				<ul>
					<c:forEach items="${relationList}" var="relation">
						<li>
							<a target="_blank" href="<%=basePath%>article/html/${relation.id}">
								<!--<img data-original="" class="thumb" src="<%=basePath%>uploads/${relation.imageUrl}" style="display: inline;">-->
								<img data-original="" class="thumb" src="http://7xljkx.com1.z0.glb.clouddn.com/uploads/${relation.imageUrl}" style="display: inline;">
								${relation.title}
							</a>
						</li>
					</c:forEach>
				</ul>
				</div>	
			</c:if> --%>

		</div>
	</div>
	<aside class="sidebar">

	<div class="widget widget_searchbox affix-top" style="top: 0px;">
		<form method="get" class="search-form"
			action="<%=basePath%>article/searchJump">
			<input class="form-control" name="title" type="text"
				placeholder="输入关键字如：暂时不能用" value=""> <input class="btn"
				type="submit" value="搜一下">
		</form>
	</div>

	<div class="widget widget_tags">
		<h3 class="title">
			<strong>热门标签</strong>
		</h3>
		<ul class="widget_tags_inner">
			<c:forEach items="${tagList}" var="tag">
				<li><a title="" href="<%=basePath%>tag/${tag.tagName}">${tag.tagName}</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="widget widget_text">
		<h3 class="title">
			<strong>友情链接</strong>
		</h3>
		<div class="textwidget">
			<ul class="widget_tags_inner">
				<c:forEach items="${friendList}" var="friend">
					<li><a href="http://${friend.siteUrl}" target="_blank"
						class="fr_link">${friend.siteName}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>

	</div>
	</aside> <jsp:include page="../footer.jsp" flush="true"></jsp:include> </section>

	<div id="back_hidden"></div>
	<script type="text/javascript"
		src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
	<script
		src="//cdn.bootcss.com/jquery.lazyload/1.9.1/jquery.lazyload.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/ueditor1_4_3/third-party/SyntaxHighlighter/shCore.min.js"></script>
</body>

<script>
	$(function() {
		SyntaxHighlighter.highlight();

		window.changyan.api.config({
			appid : 'cyrXo6KDF',
			conf : 'prod_4489406abc9bd36460f352f93cee965f'
		});

		var _hmt = _hmt || [];
		var hm = document.createElement("script");
		hm.src = "//hm.baidu.com/hm.js?790c0d88ebfcc979bcb6d95993bffb1d";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);

	});

	function goTag(tagName) {
		//window.location.href = "http://coriger.com/tag/"+encodeURI(encodeURI(tagName))
		window.location.href = getRootPath() + "article/"
				+ encodeURI(encodeURI(tagName))
	}

	function getRootPath() {
		//获取当前网址，如： http://localhost:8080/GameFngine/share/meun.jsp
		var curWwwPath = window.document.location.href;
		//获取主机地址之后的目录，如： GameFngine/meun.jsp
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		//获取主机地址，如： http://localhost:8080
		var localhostPaht = curWwwPath.substring(0, pos);
		//获取带"/"的项目名，如：/GameFngine
		var projectName = pathName.substring(0,
				pathName.substr(1).indexOf('/') + 1);
		return (localhostPaht + projectName + "/");
	}
</script>

</html>