function saveEditPartner(){
	if(validateEditPartner()){
		$.ajax({
	        url : '../friend/edit',
	        data : encodeURI($("#editForm").serialize()),
	        success  : function(data) {
	        	if(data.resultCode == 'success'){
	        		$('#editPartnerModal').modal('hide');
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

function validateEditPartner(){
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

//关闭编辑窗口
function closeEditWindow(){
	$('#editPartnerModal').modal('hide');
}