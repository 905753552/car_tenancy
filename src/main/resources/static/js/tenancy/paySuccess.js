//设置5秒后跳转回首页
$(document).ready(function(){
	var time = 5;
	setTimeout("timeout(" + time + ")",1000);
})
function timeout(time){
	$('.messOuter .costMoney .change').text(time+"秒后");
	time = time - 1;
	if(time<1)
		//跳转路径
		setTimeout("window.location.href='beginReserve.html'",1000);
	setTimeout("timeout(" + time + ")",1000);
}