var orderDetail = new Vue({
    el: '#orderDetails',
    data:{
        order_detail:{},
        description:{}
    },
    created:function(){
        var self = this;
        self.order_detail = JSON.parse(decodeURIComponent(window.location.search.slice(1)));
        console.log(self.order_detail);
        self.description = readDesc(self.order_detail.description);
    },
    methods:{
        goToPay:function(){
            goToPay();
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
function goToPay(){
    var data = {
        total_amount:orderDetail.order_detail.order_detail.totalAmount,
        days:orderDetail.order_detail.days,
        name:orderDetail.order_detail.order_detail.name,
        car_brand:orderDetail.order_detail.
        car_series:
        carid:
        orderid:
        payTime:

    }
    var data = encodeURIComponent(JSON.stringify(orderDetail.order_detail));
    window.location.href = '/tenancy/p/payPage?'+data;
}

