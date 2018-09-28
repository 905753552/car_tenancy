const param = {
    order:{},
    base_info:{
        car_info:{},
        returnDate:'',
        returnTime:'',
        returnStore:'',
        returnCity:'',
        getDate:'',
        getTime:'',
        getStore:'',
        getCity:'',
        days:'',
        base_price:'',
        service_price:'',
        total_base_price:'',
        total_service_price:'',
        discount_total_base:'',
        discount_total_service:'',
        other_cost:'',
        prepare_cost:20,
        foreign_land_cost:0,
        foreign_store_cost:0,
        overtime_cost:'',
        overtime_count:'',
        deposit:'',
        order_price_sum:'',
        description:'',
        coupon:0,
        order_detail:{
            // 姓名
            name:'',
            // 证件类型 1-身份证 2-台湾居民来往大陆通行证 3-港澳居民来往内地通行
            credentialsType:'',
            // 证件号码
            credentialsNumber:'',
            // 邮箱
            email:'',
            // 手机号
            phone:'',
            // 总价 = (下单时)订单价格 + 其它费用
            totalAmount:'',
            // (下单时)订单价格=天数*(基础价 + 服务费)*折扣-优惠券面值
            orderAmount:'',
            // 租赁费用 天数*基础价
            baseAmount:'',
            // 服务费用 天数*服务费
            serviceAmount:'',
            // 其它费用(租赁过程中产生的额外收费)
            otherAmount:'',
            // 优惠券 外键
            couponId:'',
            // 备注，用于说明扣费项及其它特殊状况
            description:'',
            // 折扣, 0<折扣<=1, 默认为1
            discount:'',
            // 已收押金
            deposit:'',
            // 退还押金
            returnDeposit:'',
            // 是否已退押 0-未退 1-已退
            isDepositReturned:'',
            // 套餐名
            packageName:'',
            // 取车门店
            getStoreId:'',
            // 还车门店
            returnStoreId:'',
            // 开始时间
            startDate:'',
            // 应还时间
            returnDate:'',
            // 实际归还时间
            realReturnDate:'',
            // 支付时间
            payTime:new Date(),
            // 状态：0-提交订单 1-失效 2-已支付 3-用户取消（退款） 4-完成
            status:'',
            // 是否删除 1-删除
            isDeleted:'',
            // 创建时间
            gmtCreate:new Date(),
            // 修改时间
            gmtModified:new Date(),
            // 车item 外键
            carItemId:'',
        }
    }
}
var orderDetail = new Vue({
    el: '.orderDetails',
    data: {
        // 价格方案
        priceSchemeInfo:{},
        // 车辆信息
        carInfo:{},
        // 订单显示参数
        baseInfo:{},
        // 优惠券信息
        coupons:{},
        // 顾客信息
        customerInfo:{},
        // 手机号副本
        phoneCopy:''
    },
    created:function(){
        var self = this;
        param.order = JSON.parse(decodeURIComponent(window.location.search.slice(1)));
        self.getCarInfo();
        self.getPSchemeAndPName();
        self.getCustomerInfo();
    },
    methods:{
        setBaseParam:function(){
            setBaseParam(this);
        },
        getCarInfo:function(){
            getCarInfo(this);
        },
        getPSchemeAndPName:function(){
            getPSchemeAndPName(this);
        },
        getCustomerInfo:function(){
            getCustomerInfo(this);
        },
        showOtherCost:function(){
            showOtherCost();
        },
        showCoupon:function(){
            showCoupon();
        },
        submitOrder:function(){
            submitOrder();
        },
        checkMemberFormName:function() {
            checkMemberFormName();
        },
        checkMemberIdentitycard:function() {
            checkMemberIdentitycard();
        },
        checkMemberEmail:function() {
            checkMemberEmail();
        }
    }
});
var modifyModel = new Vue({
    el: '#modifyModel',
    methods: {
        changeImageVerifyCode:function(){
            changeImageVerifyCode();
        },
        checkTelphone:function(){
            checkOriginalTel();
        },
        checkNewTel:function(){
            checkNewTel();
        },
        getPhoneCode:function(){
            var overtime = 60;
            if(checkNewTel())
                if(getPhoneCode())
                    countdown(overtime);
        },
        checkCode:function(){
            checkCode();
        },
        checkPhoneCode:function(){
            checkPhoneCode();
        },
        save:function(){
            save();
        }
    }
});
//-------------页面初始化--------------start
function getCarInfo(self){
    $.ajax({
        type:'get',
        url:'/api/order/car/'+param.order.carId,
        async: false,
        dataType:'json',
        success:function(result){
            if(result.code==0) {
                self.carInfo = result.car;
                param.base_info.car_info = result.car;
                console.log("获取车辆信息成功");
            }
            else
                console.log("获取车辆信息失败");
            // console.log(result);
        },
        error:function(msg){
            console.log(msg);
        }
    })
}
function getPSchemeAndPName(self){
    $.ajax({
        type:'get',
        url:'/api/order/pSchemeAndPName/'+param.order.priceId,
        async: false,
        dataType:'json',
        success:function(result){
            if(result.code==0) {
                self.priceSchemeInfo = result.priceScheme;
                param.base_info.order_detail.packageName = result.packageScheme.name;
                self.setBaseParam();
                console.log("获取价格方案成功");
            }
            else
                console.log("获取价格方案失败");
            // console.log(result);
        },
        error:function(msg){
            console.log(msg);
        }
    })
}
//获取用户信息
function getCustomerInfo(self){
    $.ajax({
        type:'get',
        url:'/api/order/customer',
        //async: false,
        dataType:'json',
        success:function(result){
            if(result.code==0) {
                self.coupons = result.list;
                self.customerInfo = result.customer;
                self.phoneCopy = result.customer.phone.substr(0,3)+'*****'+result.customer.phone.substr(7,11);
                for(var i=0;i<result.list.length;i++){
                    self.coupons[i].beginDate = result.list[i].beginDate.substr(0,10);
                    self.coupons[i].endDate = result.list[i].endDate.substr(0,10);
                }
                console.log("获取用户优惠券成功");
            }
            else
                console.log("获取用户优惠券失败");
            // console.log(result);
        },
        error:function(msg){
            console.log(msg);
        }
    })
}
//订单页面显示参数设置
function setBaseParam(self){
    param.base_info.getDate = param.order.getCarDate;
    param.base_info.getTime = param.order.getCarTime;
    param.base_info.getStore = param.order.getCarPlace;
    param.base_info.returnDate = param.order.returnCarDate;
    param.base_info.returnTime = param.order.returnCarTime;
    param.base_info.returnStore = param.order.returnCarPlace;
    param.base_info.days = param.order.days;
    param.base_info.base_price = self.priceSchemeInfo.basePrice;
    param.base_info.service_price = self.priceSchemeInfo.servicePrice;
    param.base_info.total_base_price = self.priceSchemeInfo.basePrice * param.order.days;
    param.base_info.total_service_price = self.priceSchemeInfo.servicePrice * param.order.days;
    param.base_info.discount_total_base = param.base_info.total_base_price * self.priceSchemeInfo.discount;
    param.base_info.discount_total_service = param.base_info.total_service_price * self.priceSchemeInfo.discount;
    //计算异地费用
    foreginCost();
    //计算超时服务费
    overtimeCost(self.priceSchemeInfo.baseHourPrice);
    param.base_info.deposit = self.priceSchemeInfo.deposit;
    //计算其他费用
    otherCost();
    param.base_info.order_price_sum = param.base_info.discount_total_base
                                       + param.base_info.discount_total_service
                                       + param.base_info.other_cost
                                       - param.base_info.coupon;
    self.baseInfo = param.base_info;
}
//-------------页面初始化--------------end
// 订单页面基本参数设置
function setOrderDetails(){
    if(orderDetail.customerInfo.email==''||orderDetail.customerInfo.idCard==''){
        param.base_info.order_detail.name = $("#xname").val();
        // 证件类型 1-身份证 2-台湾居民来往大陆通行证 3-港澳居民来往内地通行
        param.base_info.order_detail.credentialsType = $('#xcardtype').val();
        // 证件号码
        param.base_info.order_detail.credentialsNumber = $('#xidentitycard').val();
        // 邮箱
        param.base_info.order_detail.email = $('#memberEmail').val();
    }

        param.base_info.order_detail.name = orderDetail.customerInfo.name;
        // 证件号码
        param.base_info.order_detail.credentialsNumber = orderDetail.customerInfo.idCard;
        // 证件类型 1-身份证 2-台湾居民来往大陆通行证 3-港澳居民来往内地通行
        param.base_info.order_detail.credentialsType = 1;
        // 邮箱
        param.base_info.order_detail.email = orderDetail.customerInfo.email;
        // 手机号
        param.base_info.order_detail.phone = orderDetail.customerInfo.phone;
        // (下单时)订单价格=天数*(基础价 + 服务费)*折扣-优惠券面值
        param.base_info.order_detail.orderAmount = param.base_info.discount_total_base
            + param.base_info.discount_total_service
            - param.base_info.coupon;
        // 其它费用(租赁过程中产生的额外收费)
        param.base_info.order_detail.otherAmount = param.base_info.other_cost;
        // 总价 = (下单时)订单价格 + 其它费用
        param.base_info.order_detail.totalAmount = param.base_info.order_detail.orderAmount
                                                 + param.base_info.order_detail.otherAmount;
        // 租赁费用 天数*基础价
        param.base_info.order_detail.baseAmount = param.base_info.total_base_price;
        // 服务费用 天数*服务费
        param.base_info.order_detail.serviceAmount = param.base_info.total_service_price;
        // 备注，用于说明扣费项及其它特殊状况
        param.base_info.order_detail.description = param.base_info.description;
        // 折扣, 0<折扣<=1, 默认为1
        param.base_info.order_detail.discount = orderDetail.priceSchemeInfo.discount;
        // 已收押金
        param.base_info.order_detail.deposit = 0;
        // 退还押金
        param.base_info.order_detail.returnDeposit = 0;
        // 是否已退押 0-未退 1-已退
        param.base_info.order_detail.isDepositReturned = 0;
        // 取车门店
        param.base_info.order_detail.getStoreId = param.order.getCarPlaceId;
        // 还车门店
        param.base_info.order_detail.returnStoreId = param.order.returnCarPlaceId;
        // 开始时间
        param.base_info.order_detail.startDate = new Date((param.base_info.getDate + ' ' + param.base_info.getTime).replace(/-/g,"/"));
        // 应还时间
        param.base_info.order_detail.returnDate = new Date((param.base_info.returnDate + ' ' + param.base_info.returnTime).replace(/-/g,"/"));
        // 实际还车时间
        param.base_info.order_detail.realReturnDate = param.base_info.order_detail.returnDate;
        // 状态：0-提交订单 1-失效 2-已支付 3-用户取消（退款） 4-完成
        param.base_info.order_detail.status = 0;
        // 是否删除 1-删除
        param.base_info.order_detail.isDeleted = 0;
        // 车item 外键
        param.base_info.order_detail.carItemId = param.order.carId;
}
//获取取车地点
function getCarAddress(){
    var getcaraddress;
    $.ajax({
        type:'get',
        url:'/api/order/storeAddress/'+param.order.getCarPlaceId,
        async: false,
        dataType:'json',
        success:function(result){
            if(result.code==0) {
                param.base_info.getCity = result.storeAddress.city.name;
                getcaraddress =  result.storeAddress;
            }
            else
                console.log("获取信息失败");
        },
        error:function(msg){
            console.log(msg);
        }
    })
    return getcaraddress;
}
//获取还车地点
function returnCarAddress(){
    var returncaraddress;
    $.ajax({
        type:'get',
        url:'/api/order/storeAddress/'+param.order.returnCarPlaceId,
        async: false,
        dataType:'json',
        success:function(result){
            if(result.code==0) {
                param.base_info.returnCity = result.storeAddress.city.name;
                returncaraddress = result.storeAddress;
            }
            else
                console.log("获取信息失败");
        },
        error:function(msg){
            console.log(msg);
        }
    })
    return returncaraddress;
}
//异地或异店还车费计算
function foreginCost(){
    var getcaraddress = getCarAddress();
    var returncaraddress = returnCarAddress();
    //判断取车地点和还车地点同不同省
    if(getcaraddress.provinceId==returncaraddress.provinceId){
        //判断取车地点和还车地点同不同市
        if (getcaraddress.cityId==returncaraddress.cityId) {
            //判断取车地点和还车地点同不同店
            if (param.order.getCarPlaceId!=param.order.returnCarPlaceId) {
                param.base_info.foreign_store_cost = 200;
                return;
            }
        }else{
            param.base_info.foreign_land_cost = 1000;
            return;
        }
    }else{
        param.base_info.foreign_land_cost = 2000;
        return;
    }

}
//计算超时服务费
function overtimeCost(baseHourPrice){
    var get_time = new Date(param.base_info.getTime+':00 10/1/2018'),
        return_time = new Date(param.base_info.returnTime+':00 10/1/2018');
    var hours = ( return_time.getTime() - get_time.getTime() ) / 3600000;
    if(hours>4){
        param.base_info.overtime_count = hours;
        param.base_info.overtime_cost = 0;
    }else{
        param.base_info.overtime_cost = hours * baseHourPrice;
    }
}
//计算其他费用
function otherCost(){
    param.base_info.other_cost = param.base_info.prepare_cost
                                  + param.base_info.overtime_cost
                                  + param.base_info.foreign_store_cost
                                  + param.base_info.foreign_land_cost;
    param.base_info.description = '车辆整备费:20';
    if(param.base_info.overtime_cost>0){
        param.base_info.description.concat(';超时服务费:' + param.base_info.overtime_cost)
    }
    if(param.base_info.foreign_store_cost>0){
        param.base_info.description.concat(';异店还车费:' + param.base_info.foreign_store_cost)
    }
    if(param.base_info.foreign_land_cost>0){
        param.base_info.description.concat(';异地还车费:' + param.base_info.foreign_land_cost)
    }
}
// 检查姓名
function checkMemberFormName() {
    $("#nameInfo").parents(".order-errorbox").hide(),
        $("#nameInfo").html("");
    var flag = !0
        , xname = $("#xname").val()
        , defaultValue = $("#xname").attr("default-value");
    if (xname && $.trim(xname) && xname != defaultValue) {
        if (isInteger(trim(xname)))
            $("#nameInfo").html("姓名不能是数字！"),
                // $("#xname").val(""),
                $("#nameInfo").parents(".order-errorbox").show(),
                $(function () {
                    $(".order-errorbox .zc-close").click(function () {
                        $(this).parents(".order-errorbox").hide()
                    }),
                        $(".order-errorbox").focus(function () {
                            $(this).hide()
                        })
                }),
                flag = !1;
        else if (getBytesLength(xname) > 30)
            $("#nameInfo").html("姓名长度不能超过30个字符！"),
                //$("#xname").val(""),
                $("#nameInfo").parents(".order-errorbox").show(),
                $(function () {
                    $(".order-errorbox .zc-close").click(function () {
                        $(this).parents(".order-errorbox").hide()
                    }),
                        $(".order-errorbox").focus(function () {
                            $(this).hide()
                        })
                }),
                flag = !1;
        else if (checkStr(trim(xname)))
            $("#nameInfo").html("不能含有特殊字符！"),
                //$("#xname").val(""),
                $("#nameInfo").parents(".order-errorbox").show(),
                $(function () {
                    $(".order-errorbox .zc-close").click(function () {
                        $(this).parents(".order-errorbox").hide()
                    }),
                        $(".order-errorbox").focus(function () {
                            $(this).hide()
                        })
                }),
                flag = !1;
        else
            for (var j = 0; j < trim(xname).length; j++)
                if (" " == $.trim(xname).substring(j, j + 1)) {
                    $("#nameInfo").html("姓名中不能有空格！"),
                        //$("#xname").val(""),
                        $("#nameInfo").parents(".order-errorbox").show(),
                        $(function () {
                            $(".order-errorbox .zc-close").click(function () {
                                $(this).parents(".order-errorbox").hide()
                            }),
                                $(".order-errorbox").focus(function () {
                                    $(this).hide()
                                })
                        }),
                        flag = !1;
                    break
                }
    } else
        $("#nameInfo").html("请输入真实姓名！"),
            //$("#xname").val(""),
            $("#nameInfo").parents(".order-errorbox").show(),
            $(function () {
                $(".order-errorbox .zc-close").click(function () {
                    $(this).parents(".order-errorbox").hide()
                }),
                    $(".order-errorbox").focus(function () {
                        $(this).hide()
                    })
            }),
            flag = !1;
    return flag
}
// 检查证件号码
function checkMemberIdentitycard() {
    var xidentitycard = $('#xidentitycard').val(),xcardtype = $("#xcardtype").val();
    // 身份证验证
    var idCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    // 台湾通行证验证
    var TaiWan1 = /^[0-9]{8}$/,TaiWan2 = /^[0-9]{10}$/;
    // 港澳通行证验证
    var HKMacao = /^[HMhm]{1}([0-9]{10}|[0-9]{8})$/;
    // 外籍护照
    var foreign = /^[a-zA-Z]{3}\d{12}$/;
    if(xidentitycard =='')
        $("#identitycardInfo").html("请输入证件号码！"),
           // $("#xidentitycard").val(""),
            $("#identitycardInfo").parents(".order-errorbox1").show(),
            $(function () {
                            $(".order-errorbox1 .zc-close").click(function () {
                                $(this).parents(".order-errorbox1").hide()
                            })
                        });
    else {
        switch (xcardtype) {
            case '1':
                if (idCard.test(xidentitycard))
                    $("#identitycardInfo").parents(".order-errorbox1").hide();
                else {
                    $("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                      //  $("#xidentitycard").val(""),
                        $("#identitycardInfo").parents(".order-errorbox1").show(),
                        $(function () {
                            $(".order-errorbox1 .zc-close").click(function () {
                                $(this).parents(".order-errorbox1").hide()
                            })
                        });
                }
                break;
            case '2':
                if (TaiWan1.test(xidentitycard) || TaiWan2.test(xidentitycard))
                    $("#identitycardInfo").parents(".order-errorbox1").hide();
                else {
                    $("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                       // $("#xidentitycard").val(""),
                        $("#identitycardInfo").parents(".order-errorbox1").show(),
                        $(function () {
                            $(".order-errorbox1 .zc-close").click(function () {
                                $(this).parents(".order-errorbox1").hide()
                            })
                        });
                }
                break;
            case '3':
                if (HKMacao.test(xidentitycard))
                    $("#identitycardInfo").parents(".order-errorbox1").hide();
                else {
                    $("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                       // $("#xidentitycard").val(""),
                        $("#identitycardInfo").parents(".order-errorbox1").show(),
                        $(function () {
                            $(".order-errorbox1 .zc-close").click(function () {
                                $(this).parents(".order-errorbox1").hide()
                            })
                        });
                }
                break;
            case '4':
                if (foreign.test(xidentitycard))
                    $("#identitycardInfo").parents(".order-errorbox1").hide();
                else {
                    $("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                       // $("#xidentitycard").val(""),
                        $("#identitycardInfo").parents(".order-errorbox1").show(),
                        $(function () {
                            $(".order-errorbox1 .zc-close").click(function () {
                                $(this).parents(".order-errorbox1").hide()
                            })
                        });
                }
                break;

        }
    }
}
// 检查邮箱
function checkMemberEmail() {
    var email = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
    var memberEmail = $('#memberEmail').val();
    if(memberEmail =='')
        $("#emailInfo").html("请输入邮箱地址！"),
           // $("#memberEmail").val(""),
            $("#emailInfo").parents(".order-errorbox2").show(),
            $(function () {
                $(".order-errorbox2 .zc-close").click(function () {
                    $(this).parents(".order-errorbox2").hide()
                })
            });
    else {
        if (email.test(memberEmail))
            $("#emailInfo").parents(".order-errorbox2").hide();
        else {
            $("#emailInfo").html("您输入的邮箱地址有误，请认真核实！"),
               // $("#memberEmail").val(""),
                $("#emailInfo").parents(".order-errorbox2").show(),
                $(function () {
                    $(".order-errorbox2 .zc-close").click(function () {
                        $(this).parents(".order-errorbox2").hide()
                    })
                });
        }
    }
}

/*验证手机*/
function checkMobile(val) {
    return /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(16[5-6])|(17[0-9]{1})|(18[0-9]{1})|(19[8-9]{1}))+\d{8})$/.test(val);

}
/*改变验证码图片*/
function changeImageVerifyCode() {
    $("#kaptchaImage").attr("src", "/api/captcha/generate?_t="+new Date().getTime());
}
// 显示其他费用
function showOtherCost() {
    var $nextLi = $(".opcl").next(".bluelist");
    $nextLi.hasClass("show") ? ($nextLi.removeClass("show"),
        $(".opcl").find(".blue-downarr").removeClass("open")) : ($nextLi.addClass("show"),
        $(".opcl").find(".blue-downarr").addClass("open"));
}
// 显示优惠券
function showCoupon() {
    var $nextLi = $(".opcl1").next(".zc_query-condition_list");
    $nextLi.hasClass("show") ? ($nextLi.removeClass("show"),
        $(".opcl1").find(".blue-downarr").removeClass("open")) : ($nextLi.addClass("show"),
        $(".opcl1").find(".blue-downarr").addClass("open"))
}
// 检查原手机号码
function checkOriginalTel(){
    var phonenumber = $("#originalTel").val();
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if(phonenumber==''){
        $("#moldTelid").text("原手机号不能为空"),
            $("#moldTelid").parents(".order-errorboxred").css("display", "block");
    }else{
        if(!myreg.test(phonenumber))
            $("#moldTelid").text("手机号格式不正确"),
                $("#moldTelid").parents(".order-errorboxred").css("display", "block");
        else
        if(phonenumber==orderDetail.customerInfo.phone)
            $("#moldTelid").parents(".order-errorboxred").css("display", "none");
        else
            $("#moldTelid").text("请输入原手机号"),
                $("#moldTelid").parents(".order-errorboxred").css("display", "block");
    }

}
// 检查新手机号码
function checkNewTel(){
    var phonenumber = $("#newTel").val();
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if(phonenumber==''){
        $("#mnewTelid").text("新手机号不能为空");
            $("#mnewTelid").parents(".order-errorboxred").css("display", "block");
            return false;
    }else{
        if(!myreg.test(phonenumber)) {
            $("#mnewTelid").text("手机号格式不正确");
                $("#mnewTelid").parents(".order-errorboxred").css("display", "block");
            return false;
        }
        else
        if(phonenumber==orderDetail.customerInfo.phone) {
            $("#mnewTelid").text("新旧手机号不能相同");
                $("#mnewTelid").parents(".order-errorboxred").css("display", "block");
            return false;
        }
        else {
            $("#mnewTelid").parents(".order-errorboxred").css("display", "none");
            return true;
        }
    }

}
// 检查图片验证码
function checkCode(){
    var code = $("#code").val();
    if(code==''){
        $("#myzm").text("验证码不能为空"),
            $("#myzm").parents(".order-errorboxred").css("display", "block");
    }else{
        $("#myzm").parents(".order-errorboxred").css("display", "none");
    }

}
// 检查手机验证码
function checkPhoneCode(){
    var code = $("#phoneCode").val();
    if(code==''){
        $("#mverifyid").text("手机验证码不能为空"),
            $("#mverifyid").parents(".order-errorboxred").css("display", "block");
    }else{
        $("#mverifyid").parents(".order-errorboxred").css("display", "none");
    }

}
//获取手机验证码
function getPhoneCode(){
    var tel = $('#newTel').val(),flag = true;
        $.ajax({
            type: "GET",
            url: "/api/alisms/" + tel,
            contentType: 'application/json',
            dataType: 'json',
            success: function (res) {
                if (res.code == 0)
                    flag = true,
                    console.log("发送成功")
            },
            error: function (res) {
                console.log("请求出错，错误：" + res.msg);
            }
        });
    return flag;
}
/*获取验证码倒计时*/
function countdown(overtime) {
    if (--overtime < 1){
        $("#getCode").text("获取验证码");
        $('#getCode').removeAttr("disabled");
        return;
    }
    $('#getCode').attr('disabled',"true");
    $("#getCode").text(overtime + "秒可重发");
    setTimeout(function() {
        countdown(overtime)
    }, 1e3);

}
//保存手机号修改
function save(){
    var code = $("#code").val()
    ,phoneCode = $('#phoneCode').val()
    ,phone = $('#newTel').val();
    //判断原手机号码
    checkOriginalTel();
    //判断新手机号码
    checkNewTel();
    //判断图片验证码
    checkCode();
    //判断手机验证码
    checkPhoneCode();
    //判断是否可发送验证 验证码
    if($("#moldTelid").parents(".order-errorboxred").css("display")=='none'
        &&$("#mnewTelid").parents(".order-errorboxred").css("display")=='none'
        &&$("#myzm").parents(".order-errorboxred").css("display")=='none'
        &&$("#mverifyid").parents(".order-errorboxred").css("display")=='none'){
        //首先验证图片验证码
        send_VerifyCode(code);
        //如果图片验证码正确，则判断手机验证码
        if($("#myzm").parents(".order-errorboxred").css("display")=='none'){
            verifyPhoneCode(phoneCode,phone);
            //判断手机验证码
            if($("#mverifyid").parents(".order-errorboxred").css("display")=='none'){
                //这里写修改手机号码的函数
                updatePhoneInfo();
            }
        }
    }
}
// 订单修改个人信息
function updateCustomerInfo(){
    var name = $("#xname").val(),
        idCard = $('#xidentitycard').val(),
        email = $('#memberEmail').val();
    $.ajax({
        type:'get',
        url:'/api/order/updCusInfo',
        data:{
            name:name,
            idCard:idCard,
            email:email
        },
        async: false,
        dataType:'json',
        success:function(result){
            if(result.code==0) {
                orderDetail.customerInfo.name = name;
                orderDetail.customerInfo.email = email;
                orderDetail.customerInfo.idCard = idCard;
                console.log("修改顾客信息成功");
            }
            else
                console.log("修改顾客信息失败");
            // console.log(result);
        },
        error:function(msg){
            console.log(msg);
        }
    })

}
// 订单修改手机号码
function updatePhoneInfo(){
    var phone = $("#newTel").val();
    $.ajax({
        type:'get',
        url:'/api/order/updCusInfo',
        data:{
            phone:phone
        },
        async: false,
        dataType:'json',
        success:function(result){
            if(result.code==0) {
                orderDetail.phoneCopy = phone.substr(0,3)+'*****'+phone.substr(7,11);
                orderDetail.customerInfo.phone = phone;
                $('#modifyModel').modal('hide');
                layer.msg("修改成功",{time:2000});
                console.log("修改手机号成功");
            }
            else
                console.log("修改手机号失败");
            // console.log(result);
        },
        error:function(msg){
            console.log(msg);
        }
    })

}
//验证手机验证码是否正确
function verifyPhoneCode(phoneCode,phone){
    $.ajax({
        type: "GET",
        url: "/api/alisms/verify/"+phone+"/"+phoneCode,
        contentType:'application/json',
        dataType:'json',
        async:false,
        success: function(res) {
            if(res.code!=0)
            //提示手机验证码出错
                $("#mverifyid").text("手机验证码错误"),
                    $("#mverifyid").parents(".order-errorboxred").css("display", "block");
        },
        error:function (res) {
            console.log("请求出错，错误："+res);
        }
    })
}
//验证图片验证码是否正确
function send_VerifyCode(code){
    $.ajax({
        type: "GET",
        url: "/api/captcha/verify/"+code,
        contentType:'application/json',
        dataType:'json',
        async:false,
        success: function(res) {
            if(res.code!=0)
                //提示验证码出错
                $("#myzm").text("验证码错误"),
                    $("#myzm").parents(".order-errorboxred").css("display", "block"),
                    //改变图片验证码
                    changeImageVerifyCode();
        },
        error:function (res) {
            console.log("请求出错，错误："+res);
        }
    })
}
// 提交订单
function submitOrder(){
    var hasForm = $('.memberInfo').css('display');

    if(typeof hasForm != 'undefined'){
        checkMemberFormName();
        if ($("#nameInfo").parents(".order-errorbox").css('display') == 'none') {
            checkMemberIdentitycard();
            if ($("#identitycardInfo").parents(".order-errorbox1").css('display') == 'none') {
                checkMemberEmail();
                if ($("#emailInfo").parents(".order-errorbox2").css('display') == 'none') {
                    if(orderDetail.customerInfo.email==''||orderDetail.customerInfo.idCard==''){
                        updateCustomerInfo();
                    }
                    setOrderDetails();
                    console.log(param.base_info.order_detail)
                    $.ajax({
                        type: "GET",
                        url: "/api/order/submitOrder",
                        data:param.base_info.order_detail,
                        contentType:'application/json',
                        dataType:'json',
                        async:false,
                        success: function(res) {
                            param.base_info.order_detail = res.order.id;
                            var data = {
                                order_detail:param.base_info,
                                index:'beginReserve'
                            }
                            var order = encodeURIComponent(JSON.stringify(data));
                            window.location.href = '/tenancy/p/myOrder?'+order;
                        },
                        error:function (res) {
                            console.log("请求出错，错误："+res);
                        }
                    })
                } else {
                    console.log(1);
                    scrollUp();
                }
            } else {
                console.log(2);
                scrollUp();
            }
        } else {
            scrollUp();
        }
    }else{
        setOrderDetails();
        console.log(param.base_info.order_detail);
        $.ajax({
            type: "GET",
            url: "/api/order/submitOrder",
            data:param.base_info.order_detail,
            contentType:'application/json',
            dataType:'json',
            async:false,
            success: function(res) {
                param.base_info.order_detail = res.order.id;
                var data = {
                    order_detail:param.base_info,
                    index:'beginReserve'
                }
                var order = encodeURIComponent(JSON.stringify(data));
                window.location.href = '/tenancy/p/myOrder?'+order;
            },
            error:function (res) {
                console.log("请求出错，错误："+res);
            }
        })
    }
}
// 页面滚动到个人信息位置
function scrollUp(){
    //得到box这个div层的offset，包含两个值，top和left
    var scroll_offset = $('.memberInfo').offset();
    $("body,html").animate({
        scrollTop:scroll_offset.top //让body的scrollTop等于pos的top，就实现了滚动
    })
}
function trim(str) {
    var strReturn;
    return strReturn = leftTrim(str),
        strReturn = rightTrim(strReturn)
}

function leftTrim(strValue) {
    if (!strValue)
        return "";
    var re = /^\s*/;
    return strValue.replace(re, "")
}

function rightTrim(strValue) {
    if (!strValue)
        return "";
    var re = /\s*$/;
    return strValue.replace(re, "")
}

function isInteger(strValue) {
    return regExpTest(strValue, /\d+/)
}

function isEmail(val) {
    var pass = 0;
    if (window.RegExp) {
        var tempS = "a";
        new RegExp(tempS).test(tempS) && (pass = 1)
    }
    if (!pass)
        return val.indexOf(".") > 2 && val.indexOf("@") > 0;
    var r1 = new RegExp("(@.*@)|(\\.\\.)|(@\\.)|(^\\.)")
        ,
        r2 = new RegExp("^[a-zA-Z0-9\\.\\!\\#\\$\\%\\&\\??\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\~]*[a-zA-Z0-9\\!\\#\\$\\%\\&\\??\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\~]\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3})(\\]?)$");
    return !r1.test(val) && r2.test(val)
}

function regExpTest(source, re) {
    var result = !1;
    return !!source && (source == re.exec(source) && (result = !0),
        result)
}

function getBytesLength(str) {
    return str.replace(/[^\x00-\xff]/g, "xx").length
}

function checkStr(str) {
    for (var flag = !1, checkString = "`~!@#$%^&*()+-=[]{}\\|;':\",.。，/<>?", j = 0; j < checkString.length; j++)
        if (-1 != str.indexOf(checkString.substring(j, j + 1))) {
            flag = !0;
            break
        }
    return flag
}
$(document).ready(function(){
    $("#coupons li").each(function(i) {
        $(this).click(function() {
            $("#coupons").find("li").eq(i).siblings().removeClass("checked");
            $("#coupons").find("li").eq(i).addClass("checked");
            var count = $("#coupons").find("li").eq(i).find(".moneyamount").text().substr(1);
            var id = $("#coupons").find("li").eq(i).find(".moneyid").text().substr(0);
            param.base_info.order_detail.couponId = id;
            param.base_info.coupon = count;
            self.baseInfo = param.base_info;
            param.base_info.order_price_sum = param.base_info.discount_total_base
                + param.base_info.discount_total_service
                + param.base_info.other_cost
                - param.base_info.coupon;
            var $nextLi = $(".opcl1").next(".zc_query-condition_list");
            $nextLi.removeClass("show"), $(".opcl1").find(".blue-downarr").removeClass("open");
        })
    })
})
