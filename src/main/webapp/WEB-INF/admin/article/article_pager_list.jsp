<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<table class="table table-striped table-bordered table-hover dataTables-example">
	                                    <thead>
	                                        <tr>
	                                            <th>Id</th>
	                                            <th>标题</th>
	                                            <th>栏目</th>
	                                            <th>状态</th>
	                                            <th>创建时间</th>
	                                            <th>操作</th>
	                                        </tr>
	                                    </thead>
	                                    <div>
	                                    <tbody>
	                                    	<c:forEach items="${articleList}" var="article">
	                                    		<tr class="gradeX">
		                                            <td>${article.id}</td>
		                                            <td>
		                                            	<a target="_blank" href="<%=basePath%>article/html/${article.id}">
		                                            		${article.title}
		                                            	</a>
		                                            </td>
 													<td>
 														<a target="_blank" href="<%=basePath%>category/${article.aliasName}">
		                                            		${article.categoryName}
		                                            	</a>
 													</td>
 													<td>
 														<c:if test="${article.statue == 0}">正常</c:if>
		                                            	<c:if test="${article.statue == 1}">不可用</c:if>
		                                            </td>
		                                            <td>${article.createTime}</td>
		                                            <td>
		                                            	<button class="btn btn-primary btn-xs" type="button" title="静态化" onclick="htmlArticle(${article.id})">
		                                            		html
                                    					</button>
			                                            <button class="btn btn-primary btn-xs" type="button" title="编辑" onclick="editArticle(${article.id})">
		                                            		编辑
                                    					</button>
                                    					
                                    						<c:if test="${article.statue == 0}">
	                                    						<button class="btn btn-info btn-xs" type="button"  title="关闭" onclick="updateStatue(${article.id},1)">
				                                            		关闭
		                                    					</button>
		                                    				</c:if>
		                                    				<c:if test="${article.statue == 1}">
		                                    						<button class="btn btn-info btn-xs" type="button"  title="开启" onclick="updateStatue(${article.id},0)">
					                                            		开启
			                                    					</button>
		                                    				</c:if>
	                                    					
		                                            </td>
		                                        </tr>
	                                    	</c:forEach>
	                                    </tbody>
</table>


<jsp:include page="../pager.jsp" flush="true"></jsp:include>