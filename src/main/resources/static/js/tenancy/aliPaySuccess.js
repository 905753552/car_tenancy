$(document).ready(function () {
    var orderid = $.getUrlParam("out_trade_no");
    updateMyOrder(orderid);
    window.location.href="/tenancy/p/myOrderList";
})
function updateMyOrder(orderid){
    $.ajax({
        type:'get',
        url:'/api/order/pay/'+orderid,
        async: false,
        dataType:'json',
        success:function(res){
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
}