var orderDetail = new Vue({
    el: '.myPayPage',
    data:{
        order_detail:{}
    },
    created:function(){
    	console.log(123);
        var self = this;
        self.order_detail = JSON.parse(decodeURIComponent(window.location.search.slice(1)));
        console.log(self.order_detail);
    },
    methods:{
        savePay:function(){
            savePay();
		}
    }
});
// var payDetail = new Vue({
//     el: '.payType',
//     data:{
//         order_detail:{
//             days:'',
//             order_detail:{}
//         }
//     },
//     created:function(){
//         var self = this;
//         self.order_detail.order_detail = JSON.parse(decodeURIComponent(window.location.search.slice(1))).self.order_detail;
//         self.order_detail.days = JSON.parse(decodeURIComponent(window.location.search.slice(1))).days;
//     },
// });
function savePay(){
    $.ajax({
        type: "GET",
        url: "/tnc/order/pay/"+orderDetail.order_detail.order_detail.id,
        dataType:'json',
        success: function(res) {
            orderDetail.order_detail.order_detail = res.order;
            var data = encodeURIComponent(JSON.stringify(orderDetail.order_detail));
            window.location.href = '/tenancy/p/paySuccess?'+data;
        },
        error:function (res) {
            console.log("请求出错，错误："+res);
        }
    })
}
$(document).ready(function(){
    $(".paymentTabUl li").each(function(i) {
    $(this).click(function() {
                    if(i==0){
                        $(".paymentTabUl").find("li").eq(1).removeClass("checked"),
						$(".paymentTabUl").find("li").eq(0).addClass("checked")
                    }else{
                        $(".paymentTabUl").find("li").eq(0).removeClass("checked"),
						$(".paymentTabUl").find("li").eq(1).addClass("checked")
                    }
                })
})
})
