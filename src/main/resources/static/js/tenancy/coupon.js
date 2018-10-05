/*左侧优惠券和积分数*/
function couponAndPoint() {
    $.ajax({
        type:"get",
        url: "/api/customer/countCouponAndPoint",
        success:function (res) {
            if(res.code==0) {
                console.log(res);
                document.getElementById("pointNum").innerText="("+res.point+"分)";
                document.getElementById("couponCount").innerText="("+res.count+"张)";
            } else {
                handleAjax(res);
            }
        }
    })
}
function coupon_findCoupon() {
    $.ajax({
        type:"get",
        url: "/api/customer/findCoupon",
        success:function (res) {
            if(res.code==0) {
                couponData.coupons = res.coupons;
            } else {
                handleAjax(res);
            }
        }
    })
}

function selectCoupon(select) {
    if(select==0) {
        $(".coupon_used").css("display", "none");
        $(".coupon_overdue").css("display", "none");
        $(".coupon_unuse").css("display", "inline");
        //$($("#coupon_a_unuse").parent().siblings().children().siblings("span")[0]).removeClass("coupon_a_stop");  // 删除其他兄弟元素的样
        console.log($(this));
        $("#coupon_a_all").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_used").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_overdue").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_unuse").addClass('coupon_a_stop');  // 添加当前元素的样式
    }else if(select==1) {
        $(".coupon_unuse").css("display", "none");
        $(".coupon_overdue").css("display", "none");
        $(".coupon_used").css("display", "inline");
        //$($("#coupon_a_unuse").parent().siblings().children().siblings("span")[0]).removeClass("coupon_a_stop");  // 删除其他兄弟元素的样
        $("#coupon_a_all").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_unuse").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_overdue").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_used").addClass('coupon_a_stop');
    }else if(select==2) {
        $(".coupon_unuse").css("display", "none");
        $(".coupon_used").css("display", "none");
        $(".coupon_overdue").css("display", "inline");
        //$($("#coupon_a_unuse").parent().siblings().children().siblings("span")[0]).removeClass("coupon_a_stop");  // 删除其他兄弟元素的样
        $("#coupon_a_all").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_unuse").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_used").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_overdue").addClass('coupon_a_stop');
    }else{
        $(".coupon_unuse").css("display", "inline");
        $(".coupon_used").css("display", "inline");
        $(".coupon_overdue").css("display", "inline");
        $("#coupon_a_overdue").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_unuse").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_used").removeClass('coupon_a_stop');  // 移除当前元素的样式
        $("#coupon_a_all").addClass('coupon_a_stop');
    }
}

var couponData = {
    coupons:[]
}

Vue.filter('monent', function (value) {
    value = value.substring(0,11);
    return value;
})

var couponInfo_app = new Vue({
    el: "#couponInfo",
    data: couponData,
    methods:{
        selectCoupon:selectCoupon,
    },
    created: ()=>{
        coupon_findCoupon();
        couponAndPoint();
    },
});