$(".fhNav").hover(function(){},function(){
	$(".bottomLine").css("width",parseFloat($(".selectedNav").eq(0).width())+"px");
	$(".bottomLine").css("left",parseFloat($(".selectedNav").eq(0)[0].offsetLeft+50)+"px");
})
$(".nav li").hover(function(){
	$(".bottomLine").css("width",parseFloat($(this).width())+"px");
	$(".bottomLine").css("left",parseFloat($(this)[0].offsetLeft+40)+"px");
});