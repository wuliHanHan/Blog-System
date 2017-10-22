<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:if test="${pager.totalCount gt 0 and pager.totalPageNum gt 1}">
	<div id="pager" class="row">
	   <input type="hidden" id="page" name="page" value="${pager.page}"/>
	   <div class="col-sm-6">
		    <div class="dataTables_info" role="alert" aria-live="polite" aria-relevant="all">
		     	共${pager.totalCount}条记录&nbsp;${pager.page}/${pager.totalPageNum}
		    </div>
	   </div>
	   <div class="col-sm-6">
		    <div class="dataTables_paginate paging_simple_numbers">
			     <ul class="pagination">
				      <c:if test="${pager.page - 2 gt 2}">
				      		<li class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_previous">
					      		<a href="javascript:;" onclick="toPage(${pager.page - 1})">上一页</a>
					      	</li>
					      	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(1)">1</a>
					      	</li>
					      	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;">...</a>
					      	</li>
		                	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page - 2})">${pager.page - 2}</a>
					      	</li>
					      	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page - 1})">${pager.page - 1}</a>
					      	</li>
	                	</c:if>
	                	
	                	<c:if test="${pager.page == 4}">
		                	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(1)">1</a>
					      	</li>
		                	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page - 2})">${pager.page - 2}</a>
					      	</li>
					      	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page - 1})">${pager.page - 1}</a>
					      	</li>
	                	</c:if>
	                	
	                	<c:if test="${pager.page - 2 lt 2}">
	                		<c:if test="${pager.page == 3}">
	                			<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
						      		<a href="javascript:;" onclick="toPage(1)">1</a>
						      	</li>
	                			<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
						      		<a href="javascript:;" onclick="toPage(2)">2</a>
						      	</li>
	                		</c:if>
	                		<c:if test="${pager.page == 2}">
	                			<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
						      		<a href="javascript:;" onclick="toPage(1)">1</a>
						      	</li>
	                		</c:if>
	                	</c:if>
				      
				      <!-- 当前页 -->
				      <li class="paginate_button active" aria-controls="DataTables_Table_0" tabindex="0">
				      		<a href="javascript:;">${pager.page}</a>
				      </li>
				      
				      <c:if test="${pager.page + 3 lt pager.totalPageNum}">
	                    	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page + 1})">${pager.page + 1}</a>
					      	</li>
					      	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page + 2})">${pager.page + 2}</a>
					      	</li>
		                    <li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;">...</a>
					      	</li>
		                    <li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.totalPageNum})">${pager.totalPageNum}</a>
					      	</li>
		                    <li class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page + 1})">下一页</a>
					      	</li>
	                    </c:if>
	                    <c:if test="${pager.page + 3 == pager.totalPageNum}">
	                    	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page + 1})">${pager.page + 1}</a>
					      	</li>
					      	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page + 2})">${pager.page + 2}</a>
					      	</li>
	                    	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
					      		<a href="javascript:;" onclick="toPage(${pager.page + 3})">${pager.page + 3}</a>
					      	</li>
	                    </c:if>
	                    <c:if test="${pager.page + 3 gt pager.totalPageNum}">
	                    	<c:if test="${pager.page == pager.totalPageNum -1}">
		                    	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
						      		<a href="javascript:;" onclick="toPage(${pager.page + 1})">${pager.page + 1}</a>
						      	</li>
	                    	</c:if>
	                    	<c:if test="${pager.page == pager.totalPageNum -2}">
		                    	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
						      		<a href="javascript:;" onclick="toPage(${pager.page + 1})">${pager.page + 1}</a>
						      	</li>
						      	<li class="paginate_button " aria-controls="DataTables_Table_0" tabindex="0">
						      		<a href="javascript:;" onclick="toPage(${pager.page + 2})">${pager.page + 2}</a>
						      	</li>
	                    	</c:if>
	                    </c:if>
			     </ul>
		    </div>
	   </div>
	</div> 
</c:if>
