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
	return (localhostPaht + projectName + "/");
}

//保存文章
function editArticle(){
	var param = {};

	// 收集参数 校验
	param["id"] = $("#id").val();

	var categoryId = $("#categoryId").val();
	if(categoryId == '-1'){
		autoCloseAlert("请选择栏目",500);
		return false;
	}
	param["categoryId"] = categoryId;

	var title = $("#title").val();
	if(isEmpty(title)){
		autoCloseAlert("请输入标题",500);
		return false;
	}
	param["title"] = title;

	var arr = [];
	arr.push(UE.getEditor('editor').getContent());
	var content = arr.join("\n");
	console.info(content);

	var description = UE.getEditor('editor').getContentTxt().substring(0,500);

	// 标签
	var tagId = $(".chosen-select").val();
	if(!isEmpty(tagId)){
		var ids = (tagId+"").split("\,");
		var tagArray = [];
		for(var i=0;i<ids.length;i++){
			tagObj = {id:ids[i]};
			tagArray.push(tagObj);
		}
		param["tagList"] = tagArray;
		console.info(tagArray);
	}else{
		autoCloseAlert("请输入标签",500);
		return false;
	}

	// 保存文章
	$.ajax({
		type : "POST",
		url :  getRootPath()+'admin/article/edit',
		data : 'param='+encodeURI(encodeURI(JSON.stringify(param)))+"&content="+encodeURI(encodeURI(content)).replace(/\&/g, "%26").replace(/\+/g, "%2B")+"&description="+encodeURI(encodeURI(description)),
		success  : function(data) {
			if(data.resultCode != 'success'){
				autoCloseAlert(data.errorInfo,1000);
				return false;
			}
			else if(data.resultCode == 'exist'){
				autoCloseAlert("文章已存在,保存失败",1000);
			}
			else{
				// 调到列表页
				window.location.href = getRootPath()+ "admin/article/list";
			}
		}
	});
}

function cancleEditArticle(){
	window.location.href = getRootPath()+ "admin/article/list";
}