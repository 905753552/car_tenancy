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
                console.log("--------------");
                // 支付完成要删除id的cookie
                $.cookie("id", "", {expires: -1});
            } else {
                handleAjax(res);
            }
        }
    })
}