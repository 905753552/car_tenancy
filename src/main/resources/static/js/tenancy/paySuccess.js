//设置5秒后跳转回首页
$(document).ready(function(){
	var time = 5;
	setTimeout("timeout(" + time + ")",1000);
})
function timeout(time){
	$('.messOuter .costMoney .change').text(time+"秒后");
	if(time<1)
		//跳转路径
		setTimeout("javascript:window.location.href='/tenancy/p/beginReserve'",1000);
	else{
        time = time - 1;
            setTimeout("timeout(" + time + ")",1000);
	}
}