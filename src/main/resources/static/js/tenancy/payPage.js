var orderDetail = new Vue({
    el: '.mytotal',
    data:{
        order_detail:{}
    },
    created:function(){
        var self = this;
        self.order_detail = JSON.parse(decodeURIComponent(window.location.search.slice(1)));
        console.log(self.order_detail);
        self.initialize();
    },
    methods:{
        savePay:function(){
            console.log(123);
            savePay();
		},
        initialize:function(){
            initialize(this);
        },
        targetTo:function(){
            targetTo();
        }
    }
});
// var payDetail = new Vue({
//     el: '.payType',
//     data:{
//         order_detail:{}
//     },
//     created:function(){
//         var self = this;
//         self.order_detail = JSON.parse(decodeURIComponent(window.location.search.slice(1)));
//         console.log(self.order_detail);
//         initialize(self);
//     },
//     methods:{
//         initialize:function(){
//             initialize(this);
//         }
//     }
// });
// function targetTo(){
//     orderDetail.order_detail.order_detail = orderDetail.order_detail.order_detail.id;
//     var data = encodeURIComponent(JSON.stringify(orderDetail.order_detail));
//     window.location.href="/tenancy/p/myOrder?"+data;
// }
function savePay(){
    if($(".paymentTabUl").find("#p_alipay").hasClass("checked")
        ||$(".paymentTabUl").find("#WECHAT").hasClass("checked")) {
        $.ajax({
            type: "GET",
            url: "/api/order/pay/" + orderDetail.order_detail.order_detail.id,
            dataType: 'json',
            success: function (res) {
                if (res.code == 0) {
                    // 支付完成要删除id的cookie
                    $.cookie("id", "", {expires: -1});
                    orderDetail.order_detail.order_detail = res.order;
                    var data = {
                        car_info: orderDetail.order_detail.car_info,
                        order_detail: orderDetail.order_detail.order_detail,
                        car_number: res.carItem.number,
                        days: orderDetail.order_detail.days
                    }
                    var order = encodeURIComponent(JSON.stringify(data));
                    window.location.href = '/tenancy/p/paySuccess?' + order;
                } else {
                    handleAjax(res);
                }
            }
        })
    }else{
        layer.msg("请选择一种支付方式！",{time:1200},function(){});
    }
}
function initialize(self){
    console.log(self.order_detail.order_detail);
    $.ajax({
        type: "GET",
        url: "/api/order/getOrder/"+self.order_detail.order_detail,
        dataType:'json',
        success: function(res) {
            if(res.code == 0){
                self.order_detail.order_detail = res.order;
                console.log(self.order_detail);
                console.log("获取订单成功");
            }else{
                handleAjax(res);
            }
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
$("#orderDetail").click(function(){
        orderDetail.order_detail.order_detail = orderDetail.order_detail.order_detail.id;
        var data = {
            order_detail:orderDetail.order_detail,
            index:'payPage'
        }
        var order = encodeURIComponent(JSON.stringify(data));
        this.href="/tenancy/p/myOrder?"+order;
    })
})
