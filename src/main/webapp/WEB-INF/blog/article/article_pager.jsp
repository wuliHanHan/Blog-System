<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<c:if test="${articleId != null}">
	<input type="hidden" id="lastId" name="lastId" value="${articleId}">
</c:if>
<c:forEach items="${articleList}" var="article">
						<article class="excerpt excerpt-one">
						<header>
							<a class="cat label label-important" href="<%=basePath%>category/${article.aliasName}" data-original-title="" title="">${article.categoryName}
								<i class="label-arrow"></i>
							</a>
							<h2><a target="_blank" href="<%=basePath%>article/html/${article.id}" title="${article.title}">${article.title}</a></h2>
						</header>
							
						<p class="text-muted time">作者：${article.author}&nbsp;&nbsp;&nbsp;&nbsp; 发布于 ${article.createTime}</p>
						
						<p class="focus">
							<a target="_blank" href="<%=basePath%>article/html/${article.id}" class="thumbnail">
							<span class="item">
								<span class="thumb-span">
									<%-- <c:if test="${article.imageUrl !=null}">
										<!--<img data-original="<%=basePath%>uploads/${article.imageUrl}" class="thumb" src="<%=basePath%>uploads/loading.jpg" style="display: inline;">-->
										<img data-original="http://7xljkx.com1.z0.glb.clouddn.com/uploads/${article.imageUrl}" class="thumb" src="http://7xljkx.com1.z0.glb.clouddn.com/uploads/loading.jpg" style="display: inline;">
									</c:if> --%>
									<%-- <c:if test="${article.imageUrl == null}">
										<!--<img data-original="<%=basePath%>uploads/${article.imageUrl}" class="thumb" src="<%=basePath%>uploads/loading.jpg" style="display: inline;">-->
										<img data-original="http://7xljkx.com1.z0.glb.clouddn.com/uploads/${article.imageUrl}" class="thumb" src="http://7xljkx.com1.z0.glb.clouddn.com/uploads/loading.jpg" style="display: inline;">
									</c:if> --%>
								</span>
							</span>
							</a>
						</p>
						<p class="note">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${fn:substring(article.description, 0, 150)}...
						</p>
						<p class="text-muted views">
							<span class="post-views">阅读(${article.showCount})</span>
							<span class="post-tags">标签：
								<c:forEach items="${article.tagList}" var="tag" varStatus="status">
									<a href="javascript:void(0)" onclick="goTag('${tag.tagName}')" rel="tag" data-original-title="" title="">${tag.tagName}</a> 
									<c:if test="${fn:length(article.tagList) > (status.index+1)}">
										/
									</c:if>
								</c:forEach>
							</span>
						</p>
						
						</article>
						
						</c:forEach>	