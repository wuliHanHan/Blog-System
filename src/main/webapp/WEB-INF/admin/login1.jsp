<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>	
<head>
<title>博客管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="<%=basePath%>css/styles.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<!-- <link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'> -->
<!--//webfonts-->
<script src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
		/* toastr.options = {
					  "closeButton": true,
					  "debug": false,
					  "progressBar": true,
					  "positionClass": "toast-top-center",
					  "onclick": null,
					  "showDuration": "50",
					  "hideDuration": "100",
					  "timeOut": "1200",
					  "extendedTimeOut": "100",
					  "showEasing": "swing",
					  "hideEasing": "linear",
					  "showMethod": "fadeIn",
					  "hideMethod": "fadeOut"
		} */
		
		// ��¼
		function login(){
			var loginId = $("#userName").val();
			var password = $("#password").val();
			if(isEmpty(userName)){
				toastr.error("");
				//autoCloseAlert("�˻�����Ϊ��",1000);
				return false;
			}
			
			if(isEmpty(password)){
				toastr.error("");
				//autoCloseAlert("���벻��Ϊ��",1000);
				return false;
			}
			$.ajax({
				url : "http://localhost:8080/my-blog/admin/login",
				data: $("#form").serialize(),
				method : "post",
				dataType : "json",
				success : function(data) {
					//alert(data.errorInfo);
					//alert(data.resultCode);
					if (data.resultCode == 'success') {
						window.location.href = "admin/home";
					}else{
						toastr.error(data.errorInfo);
						//autoCloseAlert(data.errorInfo,1000);
						return false;
					}
				}
			}) 
		}
	</script>
<style>
</head>
<body>
<script>
$(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	  
});
</script>
 <!--SIGN UP-->
 <h1>博客后台登录</h1>
<div class="login-form">
	<div class="close"> </div>
		<div class="head-info">
			<label class="lbl-1"> </label>
			<label class="lbl-2"> </label>
			<label class="lbl-3"> </label>
		</div>
			<div class="clear"> </div>
	<div class="avtar">
		<img src="<%=basePath%>img/avtar.png" />
	</div>
			<form id="form" action="/admin/login">
					<input type="text" class="text" value="userName" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'userName';}" >
						<div class="key">
					<input type="password" value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}">
						</div>
			</form>
	<div class="signin">
		<input type="submit" value="登录" onclick="login()">
	</div>
</div>
</body>
</html>