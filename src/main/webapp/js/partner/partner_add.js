function saveAddPartner(){
	if(validateAddPartner()){
		$.ajax({
	        url : '../friend/add',
	        data : encodeURI($("#addForm").serialize()),
	        success  : function(data) {
	        	if(data.resultCode == 'success'){
	        		$('#addPartnerModal').modal('hide');
	            	loadPartnerList();
	            	autoCloseAlert(data.errorInfo,1000);
	        	}else if(data.resultCode == 'exist'){
	        		autoCloseAlert("链接已存在,保存失败",1000);
	        	}
	        	else{
	        		autoCloseAlert(data.errorInfo,1000);
	        	}
			}
	    });
	}
}

// 校验新增banner输入框
function validateAddPartner(){
	var siteName = $("#siteName").val();
	if(!isEmpty(siteName)){
		if(isSpecialSymbols(siteName)){
			autoCloseAlert("合作伙伴名称不能包含特殊符号",1000);
			return false;
		}
	}else{
		autoCloseAlert("合作伙伴名称不能为空",1000);
		return false;
	}
	
	var siteUrl = $("#siteUrl").val();
	if(isEmpty(siteUrl)){
		autoCloseAlert("合作伙伴地址不能为空",1000);
		return false;
	}
	
	var siteDesc = $("#siteDesc").val();
	if(isEmpty(siteDesc)){
		autoCloseAlert("网站描述不能为空",1000);
		return false;
	}
	
	var sort = $("#sort").val();
	if(isEmpty(sort)){
		autoCloseAlert("排序不能为空",1000);
		return false;
	}
	
	return true;
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
	return (localhostPaht + projectName + "/uploads/");
}

//关闭新增窗口
function closeAddWindow(){
	$('#addPartnerModal').modal('hide');
}