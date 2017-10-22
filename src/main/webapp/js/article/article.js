$(function(){
	//alert(1);
	// 加载文章列表
	loadArticleList();
});





// 加载文章列表
function loadArticleList(){
	//alert(2);
	// 收集参数
	var param = buildParam();
	//alert(param);
	var page = $("#page").val();
	if(isEmpty(page) || page == 0){
		page = 1;
	}
	//alert("page" + page);
	//alert(4);
	// 查询列表
	/*var Xhr = null;
	if (window.XMLHttpRequest) {
		Xhr = new XMLHttpRequest();
	} else {
		Xhr = new ActiveXObject("Microsoft.XMLHttp");
	}
	alert(5);
	Xhr.onreadystatechange = function() {
		if (Xhr.readyState == 4 && Xhr.status == 200) {
			var txt = Xhr.responseText();
			alert(10);
		}
	}
	Xhr.open("get", "../article/load", true);
	Xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	Xhr.send("page=" + page + "&param=" + param);
*/
	
	
	$.ajax({
        url : 'http://localhost:8080/my-blog/admin/article/load',
        data : 'page='+page+"&param="+param,
        type : "post",
        //dataType : "json",
        success  : function(data) {
        	//alert(data);
        	$("#dataList").html(data);
		}
    });
	
}


// 收集参数
function buildParam(){
	//alert(3);
	var param = {};
	var keyword = $("#keyword").val();
	if(!isEmpty(keyword)){
		param["title"] = encodeURI(encodeURI(keyword));
	}
	
	var categoryId = $("#categoryId").val();
	if(!isEmpty(categoryId) && categoryId != '-1'){
		param["categoryId"] = categoryId;
	}
	
	var tagId = $("#tagId").val();
	if(!isEmpty(tagId) && tagId != '-1'){
		param["tagId"] = tagId;
	}
	return JSON.stringify(param);
}


// 搜索
function search(){
	loadArticleList();
}

// 新增文章  跳转新页
function addArticle(){
	window.location.href = "../article/addJump";
}

// 删除文章
function deleteArticle(id){
	$.ajax({
        url : '../article/delete',
        data : 'id='+id,
        success  : function(data) {
        	if(data.resultCode == 'success'){
        		autoCloseAlert(data.errorInfo,1000);
        		loadArticleList();
        	}else{
        		autoCloseAlert(data.errorInfo,1000);
        	}
		}
    });
}

function htmlArticle(id){
	$.ajax({
        url : '../article/static/'+id,
        success  : function(data) {
        	if(data.resultCode == 'success'){
        		autoCloseAlert(data.errorInfo,1000);
        		loadArticleList();
        	}else{
        		autoCloseAlert(data.errorInfo,1000);
        	}
		}
    });
}

// 静态化全部文章
function htmlAllArticle(){
	$("#htmlAll").hide();
	$.ajax({
        url : '../article/staticAll',
        success  : function(data) {
        	if(data.resultCode == 'success'){
        		autoCloseAlert(data.errorInfo,1000);
        		loadArticleList();
        	}else{
        		autoCloseAlert(data.errorInfo,1000);
        	}
        	$("#htmlAll").show();
		}
    });
}

// 编辑文章
function editArticle(id){
	window.location.href = "../article/editJump/"+id;
}

function toPage(page){
	$("#page").val(page);
	loadArticleList();		
}

//0:可用  1：不可用 
function updateStatue(id,flag){
	$.ajax({
        url : '../article/updateStatue',
        data : 'id='+id+'&statue='+flag, 
        success  : function(data) {
        	if(data.resultCode == 'success'){
        		autoCloseAlert(data.errorInfo,1000);
        		loadArticleList();
        	}else{
        		autoCloseAlert(data.errorInfo,1000);
        	}
		}
    });
}