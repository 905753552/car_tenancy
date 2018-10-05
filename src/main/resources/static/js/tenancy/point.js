/*左侧优惠券和积分数*/
function couponAndPoint() {
    $.ajax({
        type:"get",
        url: "/api/customer/countCouponAndPoint",
        success:function (res) {
            if(res.code==0) {
                couponData.usablePoint = res.point;
                couponData.pid = res.pid;
                $("#pointNum").text("("+res.point+"分)");
                $("#couponCount").text("("+res.count+"张)");
                //document.getElementById(("info_h2")).innerText="我的可用积分 | "+res.point+"分";
                $("#info_h2").text("积分商城 | 可用积分 "+res.point+"分")
            } else {
                handleAjax(res);
            }
        }
    })
}

var couponData = {
    coupons:[
        { amount: '10', point: '80'},
        { amount: '20', point: '150'},
        { amount: '50', point: '300'},
        { amount: '80', point: '600'},
        { amount: '100',point: '800'},
    ],
    usablePoint:"",
    pid:"",
}
/**兑换优惠券*/
function exchange(pointValue, amountValue) {
    $.ajax({
        type:"get",
        url:"/api/customer/exchange",
        data:{
            usablePoint: couponData.usablePoint,
            pid: couponData.pid,
            pointValue: pointValue,
            amount: amountValue,
        },
        success:function(res){
            if(res.code==0) {
                layer.msg("兑换成功",function () {
                    window.location.href = "/tenancy/p/pointMall";
                })
            }  else {
                handleAjax(res);
            }
        }
    })
}

var couponInfo_app = new Vue({
    el: "#pointMall",
    data: couponData,
    methods:{
        exchange: exchange,
    },
    created: ()=>{
        couponAndPoint();
    },
});

/*左侧优惠券和积分数*/
function usablePoint_couponAndPoint() {
    $.ajax({
        type:"get",
        url: "/api/customer/countCouponAndPoint",
        success:function (res) {
            if(res.code==0) {
                $("#pointNum").text("("+res.point+"分)");

                if(res.point===0) {
                    $("#noNullPoint").remove();
                }else {
                    $("#nullPoint").remove();
                }
                $("#couponCount").text("("+res.count+"张)");
                $("#info_h2").text("积分商城 | 可用积分 "+res.point+"分")
            } else {
                handleAjax(res);
            }
        }
    })
}

Vue.filter('positiveNum', function (value) {
    if(value>0) {
        value = "+"+value;
    }else {
        value = -value;
        value = "- "+value;
    }
    return value;
})

/**获取积分记录*/
function getPointLog() {
    $.ajax({
        type:"get",
        url:"/api/customer/getPointLog",
        success:function(res){
            if(res.code==0) {
                pointLogData.pointLogs = res.pointLogs;
            }  else {
                handleAjax(res);
            }
        }
    })
}
var pointLogData = {
    pointLogs:[],
    pid:"",
}

var pointLogInfo_app = new Vue({
    el: "#pointLog",
    data: pointLogData,
    created: ()=>{
        usablePoint_couponAndPoint();
        getPointLog();
    },
});