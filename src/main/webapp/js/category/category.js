$(function(){
	// 加载管理员列表
	loadCategoryList();
})


// 跳转分页
function toPage(page){
	$("#page").val(page);
	loadCategoryList();		
}

// 加载管理员列表
function loadCategoryList(){
	// 收集参数
	var param = buildParam();
	
	var page = $("#page").val();
	if(isEmpty(page) || page == 0){
		page = 1;
	}
	
	// 查询列表
	$.ajax({
        url : 'http://localhost:8080/my-blog/admin/category/load',
        data : 'page='+page+"&param="+param,
        type : "post",
        success  : function(data) {
        	$("#dataList").html(data);
		}
    });
	
}

// 收集参数
function buildParam(){
	var param = {};
	var keyword = $("#keyword").val();
	if(!isEmpty(keyword)){
		param["categoryName"] = encodeURI(encodeURI(keyword));
	}
	return JSON.stringify(param);
}

// 搜索
function search(){
	loadCategoryList();
}

// 删除
function deleteCategory(id){
	$.ajax({
        url : '../category/delete',
        data : 'id='+id,
        success  : function(data) {
        	if(data.resultCode == 'success'){
        		autoCloseAlert(data.errorInfo,1000);
        		loadCategoryList();
        	}else{
        		autoCloseAlert(data.errorInfo,1000);
        	}
		}
    });
}

// 跳转编辑页
function editCategory(id){
	$.ajax({
        url : '../category/editJump/'+id,
        success  : function(data) {
        	$('#editCategoryContent').html(data);
        	$('#editCategoryModal').modal('show');
        	$('#editCategoryModal').addClass('animated');
        	$('#editCategoryModal').addClass('flipInY');
		}
    });
}

// 关闭编辑管理员窗口
function closeEditWindow(){
	$('#editCategoryModal').modal('hide');
}

// 关闭新增管理员窗口
function closeAddWindow(){
	$('#addCategoryModal').modal('hide');
}


// 编辑管理员
function saveEditCategory(){
	if(validateEditCategory()){
		$.ajax({
	        url : '../category/edit',
	        data : encodeURI($("#editForm").serialize()),
	        success  : function(data) {
	        	if(data.resultCode == 'success'){
	        		$('#editCategoryModal').modal('hide');
	            	loadCategoryList();
	            	autoCloseAlert(data.errorInfo,1000);
	        	}else if(data.resultCode == 'exist'){
	        		autoCloseAlert("栏目已存在,保存失败",1000);
	        	}
	        	else{
	        		autoCloseAlert(data.errorInfo,1000);
	        	}
			}
	    });
	}
}

// 新增管理员
function saveAddCategory(){
	if(validateAddCategory()){
		$.ajax({
	        url : '../category/add',
	        data : encodeURI($("#addForm").serialize()),
	        success  : function(data) {
	        	if(data.resultCode == 'success'){
	        		$('#addCategoryModal').modal('hide');
	            	loadCategoryList();
	            	autoCloseAlert(data.errorInfo,1000);
	        	}else if(data.resultCode == 'exist'){
	        		autoCloseAlert("栏目已存在,保存失败",1000);
	        	}
	        	else{
	        		autoCloseAlert(data.errorInfo,1000);
	        	}
			}
	    });
	}
}

// 校验新增管理员输入框
function validateAddCategory(){
	var categoryName = $("#categoryName").val();
	if(!isEmpty(categoryName)){
		if(isSpecialSymbols(categoryName)){
			autoCloseAlert("栏目不能包含特殊符号",1000);
			return false;
		}
	}else{
		autoCloseAlert("栏目不能为空",1000);
		return false;
	}
	var aliasName = $("#aliasName").val();
	if(!isEmpty(aliasName)){
		if(isSpecialSymbols(aliasName)){
			autoCloseAlert("别名不能包含特殊符号",1000);
			return false;
		}
	}else{
		autoCloseAlert("别名不能为空",1000);
		return false;
	}
	return true;
}

// 校验编辑管理员输入框
function validateEditCategory(){
	var categoryName = $("#categoryName").val();
	if(!isEmpty(categoryName)){
		if(isSpecialSymbols(categoryName)){
			autoCloseAlert("栏目不能包含特殊符号",1000);
			return false;
		}
	}else{
		autoCloseAlert("栏目不能为空",1000);
		return false;
	}
	var aliasName = $("#aliasName").val();
	if(!isEmpty(aliasName)){
		if(isSpecialSymbols(aliasName)){
			autoCloseAlert("别名不能包含特殊符号",1000);
			return false;
		}
	}else{
		autoCloseAlert("别名不能为空",1000);
		return false;
	}
	return true;
}

// 跳转新增管理员页面
function addCategory(){
	$.ajax({
        url : '../category/addJump',
        success  : function(data) {
        	$('#addCategoryContent').html(data);
        	$('#addCategoryModal').modal('show');
        	$('#addCategoryModal').addClass('animated');
        	$('#addCategoryModal').addClass('bounceInLeft');
		}
    });
}