$(function(){
		var timeout;
		$(".menu-list li").hover(function(){
			$(".menu-list li").find("dl").hide();
			$(".menu-list li").removeClass("act");
			$(this).find("dl").show();
			$(this).addClass("act");
			clearTimeout(timeout);
		},function(){
			timeout = setTimeout(function(){$(".menu-list li dl").hide();$(".menu-list li").removeClass("act");},100);
		});
		$(".search-box input").val("");
		//搜索框
		$(".search-box input").focus(function(){
			if($(this).val()==""||$(this).val()==" "){
				$(this).parents(".search-box").addClass("focus01");
				 $(this).siblings(".search-ico").stop().animate({left:"210px"},500);
			}
		});
		$(".search-box input").blur(function(){
			if($(this).val()==""||$(this).val()==" "){
				$(this).parents(".search-box").removeClass("focus01");
				$(this).siblings(".search-ico").stop().animate({left:"110px"},500);
			}
		});
		//
		$(".community-left").click(function(){
			if(!$(this).hasClass("unable")){
				$(".community-list li").each(function(i){
					if($(this).hasClass("cur")){
						//alert(i);
						var ml = parseInt($(".community-list").css("margin-left"))+240;
						$(".community-list li").removeClass("cur");
						$(".community-list li").eq(i-1).addClass("cur");
						$(".community-list").stop().animate({marginLeft:ml+"px"},100);
						$(".community-right").removeClass("unable");
						if(i==1){
							$(".community-left").addClass("unable");
						}
						return false;
					}
				})
			}
		});
		$(".community-right").click(function(){
			if(!$(this).hasClass("unable")){
				$(".community-list li").each(function(i){
					var length =$(".community-list li").length;
					//alert(i+" "+ length);
					if($(this).hasClass("cur")){
						var ml = parseInt($(".community-list").css("margin-left"))-240;
						$(".community-list li").removeClass("cur");
						$(".community-list li").eq(i+1).addClass("cur");
						$(".community-list").stop().animate({marginLeft:ml+"px"},100);
						$(".community-left").removeClass("unable");
						if(i==length-2){
							$(".community-right").addClass("unable");
						}
						return false;
					}
				})
			}
		});



})