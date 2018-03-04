function saveAddManager(){
	if(validateAddManager()){
		$.ajax({
	        url : 'http://localhost:8080/my-blog/admin/add',
	        data : encodeURI($("#addForm").serialize()),
	        success  : function(data) {
	        	if(data.resultCode == 'success'){
	        		$('#addManagerModal').modal('hide');
	            	loadManagerList();
	            	autoCloseAlert(data.errorInfo,1000);
	        	}else if(data.resultCode == 'exist'){
	        		autoCloseAlert("用户已存在,保存失败",1000);
				}else{
	        		autoCloseAlert(data.errorInfo,1000);
	        	}
			}
	    });
	}
}

// 校验新增banner输入框
function validateAddManager(){
	var userName = $("#userName").val();
	if(!isEmpty(userName)){
		if(isSpecialSymbols(userName)){
			autoCloseAlert("用户名不能包含特殊符号",1000);
			return false;
		}
	}else{
		autoCloseAlert("用户名不能为空",1000);
		return false;
	}
	
	var password = $("#password").val();
	if(!isEmpty(password)){
		if(isSpecialSymbols(password)){
			autoCloseAlert("密码不能包含特殊符号",1000);
			return false;
		}
	}else{
		autoCloseAlert("密码不能为空",1000);
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
	$('#addManagerModal').modal('hide');
}