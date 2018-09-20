var car_id = $.getUrlParam('id');
new Vue({
    el: '.ifl',
    data: {
        carInfo:{}
    },
    created:function(){
        var self = this;
        $.ajax({
            type:'get',
            url:'/tnc/order/select/'+car_id,
            async: false,
            success:function(result){
                self.carInfo = result;
            },
            error:function(msg){
                alert(msg);
            }
        })
    }
});
new Vue({
    el: '.form-info',
    data: {

    },
    methods: {
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
new Vue({
    el: '.feeul',
    methods: {
        showOtherCost:function(){
            showOtherCost();
        },
        showCoupon:function(){
            showCoupon();
        }
    }
});
new Vue({
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
            countdown(overtime);
            getPhoneCode();
        },
        checkCode:function(){
            checkCode();
        },
        checkPhoneCode:function(){
            checkPhoneCode();
        },
        save:function(){
            save();
        },
        getJson(){
            getJson();
        }
    }
});
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
                $("#xname").val(""),
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
                $("#xname").val(""),
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
                $("#xname").val(""),
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
                        $("#xname").val(""),
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
            $("#xname").val(""),
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
    $("#identitycardInfo").parents(".order-errorbox1").hide(),
        $("#identitycardInfo").html("");
    var flag = !0
        , xcardtype = $(".op").attr("type-id")
        , xidentitycard = $("#xidentitycard").val()
        , defaultValue = $("#xidentitycard").attr("default-value");
    if (xidentitycard && defaultValue != xidentitycard)
        if (xidentitycard.length > 20)
            flag = !1,
                $("#identitycardInfo").html("您输入的证件号码太长！"),
                $("#xidentitycard").val(""),
                $("#identitycardInfo").parents(".order-errorbox1").show(),
                $(function () {
                    $(".order-errorbox1 .zc-close").click(function () {
                        $(this).parents(".order-errorbox1").hide()
                    })
                });
        else if (/^[a-zA-Z0-9]+$/.test(xidentitycard)) {
            if (10 == xcardtype)
                if (1 == checkIDCard(trim(xidentitycard)))
                    $("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                        $("#xidentitycard").val(""),
                        $("#identitycardInfo").parents(".order-errorbox1").show(),
                        $(function () {
                            $(".order-errorbox1 .zc-close").click(function () {
                                $(this).parents(".order-errorbox1").hide()
                            })
                        }),
                        flag = !1;
                else {
                    var date = $("#currentDate").val()
                        ,
                        converted = new Date(parseInt(date.split("-")[0]) - 18, parseInt(date.split("-")[1]) - 1, parseInt(date.split("-")[2]))
                        ,
                        birthday = new Date(parseInt(xidentitycard.substring(6, 10)), parseInt(xidentitycard.substring(10, 12)) - 1, parseInt(xidentitycard.substring(12, 14)));
                    18 == xidentitycard.length && birthday.getTime() - converted.getTime() > 0 && ($("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                        $("#xidentitycard").val(""),
                        $("#identitycardInfo").parents(".order-errorbox1").show(),
                        $(function () {
                            $(".order-errorbox1 .zc-close").click(function () {
                                $(this).parents(".order-errorbox1").hide()
                            })
                        }),
                        flag = !1)
                }
        } else
            $("#identitycardInfo").html("您输入的证件号码有误，请认真核实！"),
                $("#xidentitycard").val(""),
                $("#identitycardInfo").parents(".order-errorbox1").show(),
                $(function () {
                    $(".order-errorbox1 .zc-close").click(function () {
                        $(this).parents(".order-errorbox1").hide()
                    })
                }),
                flag = !1;
    else
        $("#identitycardInfo").html("请输入证件号码！"),
            $("#xidentitycard").val(""),
            $("#identitycardInfo").parents(".order-errorbox1").show(),
            $(function () {
                $(".order-errorbox1 .zc-close").click(function () {
                    $(this).parents(".order-errorbox1").hide()
                })
            }),
            flag = !1;
    return flag
}
// 检查邮箱
function checkMemberEmail() {
    $("#emailInfo").parents(".order-errorbox2").hide(),
        $("#emailInfo").html("");
    var flag = !0
        , xemail = $("#memberEmail").val()
        , defaultValue = $("#memberEmail").attr("default-value");
    return $.trim(xemail) && xemail != defaultValue && (isEmail($.trim(xemail)) ? $.trim(xemail).length > 50 ? (flag = !1,
        $("#emailInfo").html("您输入的邮箱地址过长！"),
            $("#memberEmail").val(""),
        $("#emailInfo").parents(".order-errorbox2").show()) : flag = 1 : (flag = !1,
            $("#emailInfo").html("您输入的邮箱地址有误，请认真核实！"),
        $("#memberEmail").val(""),
            $("#emailInfo").parents(".order-errorbox2").show()
    )),
        flag,
        $(function () {
            $(".order-errorbox2 .zc-close").click(function () {
                $(this).parents(".order-errorbox2").hide()
            })
        })
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
        $(".opcl").find(".blue-downarr").addClass("open"))
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
        if(phonenumber=="17876253431")
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
        $("#mnewTelid").text("新手机号不能为空"),
            $("#mnewTelid").parents(".order-errorboxred").css("display", "block");
    }else{
        if(!myreg.test(phonenumber))
            $("#mnewTelid").text("手机号格式不正确"),
                $("#mnewTelid").parents(".order-errorboxred").css("display", "block");
        else
        if(phonenumber=="17876253431")
            $("#mnewTelid").text("新旧手机号不能相同"),
            $("#mnewTelid").parents(".order-errorboxred").css("display", "block");
        else
            $("#mnewTelid").parents(".order-errorboxred").css("display", "none");
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
    var tel = $('#newTel').val();
    $.ajax({
        type: "GET",
        url: "/api/alisms/"+tel,
        contentType:'application/json',
        dataType:'json',
        success: function(res) {
            if(res.code==0)
                console.log("发送成功")
        },
        error:function (res) {
            console.log("请求出错，错误："+res.msg);
        }
    })
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
    // var code = $("#code").val();
    // send_VerifyCode(code);
    // var phoneCode = $('#phoneCode').val();
    // var phone = $('#newTel').val();
    // verifyPhoneCode(phoneCode,phone);
    // $("#mverifyid").text("手机验证码不能为空"),
    //     $("#mverifyid").parents(".order-errorboxred").css("display", "block");
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
            }
        }
    }
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

function checkIDCard(idcard) {
    idcard = idcard.toUpperCase();
    var Y, JYM, S, M, area = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门"
    }, idcard_array = [];
    if (idcard_array = idcard.split(""),
        !area[parseInt(idcard.substr(0, 2))])
        return 1;
    switch (idcard.length) {
        case 15:
            return (parseInt(idcard.substr(6, 2)) + 1900) % 4 == "0" || (parseInt(idcard.substr(6, 2)) + 1900) % 100 == "0" && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == "0" ? ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/ : ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/,
                ereg.test(idcard) ? 0 : 1;
        case 18:
            return parseInt(idcard.substr(6, 4)) % 4 == "0" || parseInt(idcard.substr(6, 4)) % 100 == "0" && parseInt(idcard.substr(6, 4)) % 4 == "0" ? ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/ : ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/,
                ereg.test(idcard) ? (S = 7 * (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) + 9 * (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) + 10 * (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) + 5 * (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) + 8 * (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) + 4 * (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) + 2 * (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) + 1 * parseInt(idcard_array[7]) + 6 * parseInt(idcard_array[8]) + 3 * parseInt(idcard_array[9]),
                    Y = S % 11,
                    M = "F",
                    JYM = "10X98765432",
                    M = JYM.substr(Y, 1),
                    M == idcard_array[17] ? 0 : 1) : 1;
        default:
            return 1
    }
}
$(document).ready(function(){
    $("#coupons li").each(function(i) {
        $(this).click(function() {
                $("#coupons").find("li").eq(i).siblings().removeClass("checked"),
                    $("#coupons").find("li").eq(i).addClass("checked");
            var $nextLi = $(".opcl1").next(".zc_query-condition_list");
            $nextLi.removeClass("show"), $(".opcl1").find(".blue-downarr").removeClass("open");
        })
    })
})