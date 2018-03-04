function saveEditManager(){
	if(validateEditManager()){
		$.ajax({
	        url : 'http://localhost:8080/my-blog/admin/edit',
	        data : encodeURI($("#editForm").serialize()),
	        success  : function(data) {
	        	if(data.resultCode == 'success'){
	        		$('#editManagerModal').modal('hide');
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

function validateEditManager(){
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

//关闭编辑窗口
function closeEditWindow(){
	$('#editManagerModal').modal('hide');
}