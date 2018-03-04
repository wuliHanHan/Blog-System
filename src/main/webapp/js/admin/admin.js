$(function(){
	//alert(1);
	// 加载文章列表
	loadManagerList();
});





//加载文章列表
function loadManagerList(){
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
		url : 'http://localhost:8080/my-blog/admin/load',
		data : 'page='+page+"&param="+param,
		type : "post",

		success  : function(data) {
			//alert(data);
			$("#dataList").html(data);
		}
	});

}


//收集参数
function buildParam(){
	var param = {};
	var keyword = $("#keyword").val();
	if(!isEmpty(keyword)){
		param["userName"] = encodeURI(encodeURI(keyword));
	}
	return JSON.stringify(param);
}


//搜索
function search(){
	loadManagerList();
}

//新增文章  跳转新页
function addManager(){
	$.ajax({
        url : 'http://localhost:8080/my-blog/admin/addJump',
        success  : function(data) {
        	$('#addManagerContent').html(data);
        	$('#addManagerModal').modal('show');
        	$('#addManagerModal').addClass('animated');
        	$('#addManagerModal').addClass('bounceInLeft');
		}
    });
}

function deleteManager(id){
	$.ajax({
		url : 'http://localhost:8080/my-blog/admin/delete',
		data : 'id='+id,
		success  : function(data) {
			if(data.resultCode == 'success'){
				autoCloseAlert(data.errorInfo,1000);
				loadManagerList();
			}else{
				autoCloseAlert(data.errorInfo,1000);
			}
		}
	});
}

//编辑文章
function editManager(id){
	$.ajax({
        url : 'http://localhost:8080/my-blog/admin/editJump',
        data : 'id='+id,
        success  : function(data) {
        	$('#editManagerContent').html(data);
        	$('#editManagerModal').modal('show');
        	$('#editManagerModal').addClass('animated');
        	$('#editManagerModal').addClass('flipInY');
		}
    });
}

function toPage(page){
	$("#page").val(page);
	loadManagerList();		
}
