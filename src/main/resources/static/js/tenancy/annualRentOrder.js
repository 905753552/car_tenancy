$(document).click(function(){
    //选择租金支付方式
    $(".b-warp input[type='radio']").click(function() {
        $(".b-warp input[type='radio']").removeAttr("checked"),
        $(this).attr("checked", "checked"),
        $(this).parent().addClass("ltwcur").siblings().removeClass("ltwcur")
    })
    //选择优惠券
    // $("#coupons li").each(function(i) {
    //     $(this).click(function() {
    //         $("#coupons").find("li").eq(i).siblings().removeClass("checked"),
    //             $("#coupons").find("li").eq(i).addClass("checked");
    //     })
    // })
})