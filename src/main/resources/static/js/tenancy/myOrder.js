var orderDetail = new Vue({
    el: '#orderDetails',
    data:{
        order_detail:{},
        description:{},
        index:''
    },
    created:function(){
        var self = this;
        self.order_detail = JSON.parse(decodeURIComponent(window.location.search.slice(1))).order_detail;
        self.index = JSON.parse(decodeURIComponent(window.location.search.slice(1))).index;
        self.description = readDesc(self.order_detail.description);
        self.initialize();
        self.showModel();
    },
    methods:{
        goToPay:function(){
            goToPay();
        },
        initialize:function(){
            initialize(this);
        },
        showModel:function(){
            showModel(this);
        }
    }
});

$(document).ready(function() {
        var reqtype = $("#reqtype").val();
        reqtype && "" != reqtype && $(".order-success-alertbox").show(),
        $(".order-success-alertbox .close,.order-success-alertbox .btn-know").click(function() {
            $(".order-success-alertbox").hide()
        }),
        $(".onsttone").click(function() {
            var sttwrap = $(".sttwrap");
            "none" == sttwrap.css("display") ? (sttwrap.css("display", "block"),
            $(this).find(".down").show(),
            $(this).find(".up").hide()) : (sttwrap.css("display", "none"),
            $(this).find(".down").hide(),
            $(this).find(".up").show())
        }),
		$(".config-btn").click(function() {
			$(".cffade").show(),
			$(".configbox").show()
		}),
		$(".cfclose").click(function() {
			$(".configbox").hide(),
			$(".cffade").hide()
		})
    })
    $('#orderCancel').click(function(){
        layer.confirm('确定要取消订单？', function(index){
            $.ajax({
                type: "GET",
                url: "/api/order/cancel/"+orderDetail.order_detail.order_detail.id,
                dataType:'json',
                success: function(res) {
                    $('#orderDetails .colorBlue').text("已取消");
                },
                error:function (res) {
                    console.log("请求出错，错误："+res);
                }
            })
        });

    })
function readDesc(text){
    var data={},KeyAndName;
    var item = text.split(";");
    for(var i=0;i<item.length;i++) {
        KeyAndName = item[i].split(":");
        data[KeyAndName[0]] = KeyAndName[1];
    }
    console.log(data);
    return data;
}
function showModel(self){
    if(self.index == 'beginReserve'){
        $('.order-success-alertbox').css('display','block');
    }else{
        $('.order-success-alertbox').css('display','none');
    }
}
function initialize(self){
    $.ajax({
        type: "GET",
        url: "/api/order/getOrder/"+self.order_detail.order_detail,
        dataType:'json',
        success: function(res) {
            self.order_detail.order_detail = res.order;
            console.log("获取订单成功");
        },
        error:function (res) {
            console.log("请求出错，错误："+res);
        }
    })
}
function goToPay(){
    // var data = {
    //     total_amount:orderDetail.order_detail.order_detail.totalAmount,
    //     days:orderDetail.order_detail.days,
    //     name:orderDetail.order_detail.order_detail.name,
    //     car_brand:orderDetail.order_detail.car_info.tncBrand.name,
    //     car_series:orderDetail.order_detail.car_info.series,
    //     carid:orderDetail.order_detail.car_info.id,
    //     orderid:orderDetail.order_detail.order_detail.id,
    //     payTime:orderDetail.order_detail.order_detail.payTime
    //
    // }
    orderDetail.order_detail.order_detail = orderDetail.order_detail.order_detail.id;
    var data = orderDetail.order_detail;
    var order_data = encodeURIComponent(JSON.stringify(data));
    window.location.href = '/tenancy/p/payPage?'+order_data;
}

