var paySuccess = new Vue({
    el: '.messOuter',
    data:{
        order_detail:{},
        car_number:{},
        car_info:{},
        days:''
    },
    created:function(){
        var self = this;
        self.order_detail = JSON.parse(decodeURIComponent(window.location.search.slice(1))).order_detail;
        self.car_number = JSON.parse(decodeURIComponent(window.location.search.slice(1))).car_number;
        self.car_info = JSON.parse(decodeURIComponent(window.location.search.slice(1))).car_info;
        self.days = JSON.parse(decodeURIComponent(window.location.search.slice(1))).days;
        console.log(self.order_detail);
        console.log(self.car_number);
        console.log(self.car_info);
        console.log(self.days);

    }
});
//设置5秒后跳转回首页
$(document).ready(function(){
	var time = 5;
	setTimeout("timeout(" + time + ")",1000);
})
function timeout(time){
	$('.messOuter .costMoney .change').text(time+"秒后");
	if(time<1)
		//跳转路径
		setTimeout("javascript:window.location.href='/tenancy/p/headList'",1000);
	else{
        time = time - 1;
            setTimeout("timeout(" + time + ")",1000);
	}
}
