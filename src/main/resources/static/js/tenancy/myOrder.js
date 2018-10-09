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
        },
        cancelOrder:function(){
            cancelOrder();
        },
        gotoOrderCenter:function(){
            gotoOrderCenter();
        },
        updateOrder:function(){
            updateOrder();
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
            if(res.code == 0){
                self.order_detail.order_detail = res.order;
                if (self.order_detail.order_detail.status == 0) {
                    var create = new Date(self.order_detail.order_detail.gmtCreate),
                        now = new Date();
                     create.setHours(create.getHours() + 1);
                    //create.setMinutes(create.getMinutes() + 1);
                    var hours = accAdd(create.getTime(), (-1) * now.getTime());
                    hours = accDiv(hours, 3600000);
                    if (hours <= 0) {
                        // 自动取消订单
                        autoCancel(self);
                    }
                }
                console.log("获取订单成功");
            }else{
                handleAjax(res);
            }
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
function cancelOrder(){
    layer.confirm('确定要取消订单？', function(index){
        $.ajax({
            type: "GET",
            url: "/api/order/cancel/"+orderDetail.order_detail.order_detail.id,
            dataType:'json',
            success: function(res) {
                if(res.code == 0){
                    layer.close(index);
                    //取消订单要删除id的cookie
                    $.cookie("id","",{expires:-1});
                    layer.msg(res.msg,{time:1200},function(){
                        orderDetail.order_detail.order_detail = orderDetail.order_detail.order_detail.id;
                        var data = {
                            order_detail:orderDetail.order_detail,
                            index:'myOrder'
                        }
                        var order = encodeURIComponent(JSON.stringify(data));
                        window.location.href="/tenancy/p/myOrder?"+order;
                    });
                }
                else{
                    handleAjax(res);
                }
            }
        })
    });
}
function gotoOrderCenter(){
    window.location.href = '/tenancy/p/myOrderList';
}
function updateOrder(){
    // 将订单id存放在cookie中
    $.cookie('id', JSON.stringify(orderDetail.order_detail.order_detail.id));
    window.location.href = '/tenancy/p/headList';
}
function autoCancel(self){
    $.ajax({
        type: "GET",
        url: "/api/order/cancel/"+orderDetail.order_detail.order_detail.id,
        dataType:'json',
        async:false,
        success: function(res) {
            if(res.code == 0){
                //取消订单要删除id的cookie
                self.order_detail.order_detail.status = 3;
                $.cookie("id","",{expires:-1});
            }
            else{
                handleAjax(res);
            }
        }
    })
}
// 加法运算
function accAdd(arg1,arg2){
    var r1,r2,m;
    try{
        r1=arg1.toString().split(".")[1].length
    }catch(e){
        r1=0} try{
        r2=arg2.toString().split(".")[1].length}catch(e){r2=0} m=Math.pow(10,Math.max(r1,r2))
    return (arg1*m+arg2*m)/m
}
// 除法运算
function accDiv(arg1,arg2){
    var t1=0,t2=0,r1,r2;
    try{
        t1=arg1.toString().split(".")[1].length}catch(e){
    }try{
        t2=arg2.toString().split(".")[1].length}catch(e){}
    with(Math){
        r1=Number(arg1.toString().replace(".",""))
        r2=Number(arg2.toString().replace(".",""))
        return (r1/r2)*pow(10,t2-t1);
    }
}

