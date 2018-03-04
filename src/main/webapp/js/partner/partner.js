$(function(){
	// 加载菜单列表
	loadPartnerList();
})


// 跳转分页
function toPage(page){
	$("#page").val(page);
	loadPartnerList();		
}

// 加载菜单列表
function loadPartnerList(){
	// 收集参数
	var param = buildParam();
	
	var page = $("#page").val();
	if(isEmpty(page) || page == 0){
		page = 1;
	}
	
	// 查询列表
	$.ajax({
        url : '../friend/load',
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
		param["siteName"] = encodeURI(encodeURI(keyword));
	}
	return JSON.stringify(param);
}

// 搜索
function search(){
	loadPartnerList();
}

// 删除栏目
function deletePartner(id){
	$.ajax({
        url : '../friend/delete',
        data : 'id='+id,
        success  : function(data) {
        	if(data.resultCode == 'success'){
        		autoCloseAlert(data.errorInfo,1000);
        		loadPartnerList();
        	}else{
        		autoCloseAlert(data.errorInfo,1000);
        	}
		}
    });
}

// 跳转栏目编辑页
function editPartner(id){
	$.ajax({
        url : '../friend/editJump',
        data : 'id='+id,
        success  : function(data) {
        	$('#editPartnerContent').html(data);
        	$('#editPartnerModal').modal('show');
        	$('#editPartnerModal').addClass('animated');
        	$('#editPartnerModal').addClass('flipInY');
		}
    });
}

// 跳转新增页面
function addPartner(){
	$.ajax({
        url : '../friend/addJump',
        success  : function(data) {
        	$('#addPartnerContent').html(data);
        	$('#addPartnerModal').modal('show');
        	$('#addPartnerModal').addClass('animated');
        	$('#addPartnerModal').addClass('bounceInLeft');
		}
    });
}