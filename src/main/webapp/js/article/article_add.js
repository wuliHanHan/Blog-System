// 保存文章
function saveArticle(){
    var param = {};
    
    // 收集参数 校验
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
    
    // 简介
    var description = UE.getEditor('editor').getContentTxt().substring(0,500);
    
    // 标签
    var tagId = $(".chosen-select").val();
    //alert(tagId);
    if(!isEmpty(tagId)){
    	var ids = (tagId+"").split("\,");
    	var tagArray = [];
    	for(var i=0;i<ids.length;i++){
    		tagObj = {"id":ids[i]};
    		//alert(tagObj.id);
    		tagArray.push(tagObj);
    	}
    	param["tagList"] = tagArray;
    	console.info(tagArray);
    }else{
    	autoCloseAlert("请输入标签",500);
    	return false;
    }
   // alert('param='+encodeURI(encodeURI(JSON.stringify(param))));
    // 保存文章
    $.ajax({
        type : "POST",
        url : '../article/add',
        data : 'param='+encodeURI(encodeURI(JSON.stringify(param)))+"&content="+encodeURI(encodeURI(content)).replace(/\&/g, "%26").replace(/\+/g, "%2B")+"&description="+encodeURI(encodeURI(description)),
        success  : function(data) {
        	if(data.resultCode != 'success'){
        		autoCloseAlert(data.errorInfo,1000);
				return false;
			}else if(data.resultCode == 'exist'){
        		autoCloseAlert("文章已存在,保存失败",1000);
			}
        	else{
				// 调到列表页
				window.location.href = "../article/list";
			}
		}
    });
}

function cancelSaveArticle(){
	window.location.href = "../article/list";
}