var Psw = new Vue({
    el:".find_body",
    data:{},
    methods:{
        submitPhone:function () {
            submitPhone();
        },
        sub_verify_code:function () {
            sub_verify_code();
        },
        getVerifyCode:function () {
            getVerifyCode();
        },
        submitCode:function () {
            submitCode();
        },
        inputPsw:function () {
            pswLevel();
        },
        resetPsw:function () {
            resetPsw();
        }
    }
});

var submitPhone = function () {
    var phone = $("#xphone").val();

    if(phone == ""){
        $("#xphone").focus();
        doShowTips("手机号码不能为空");
        return false;
    }else if(!isMobile(phone)){
        $("#xphone").focus();
        doShowTips("手机号码格式错误");
        return false;
    }
    showStep(2);
}
var showStep = function(s){
    if(s==1){
        $(".pro1").show();
        $(".pro2").hide();
        $(".pro3").hide();
        $(".pro4").hide();
        $(".zc-findbox li").eq(0).addClass("passed");
    }else if(s==2){
        $(".pro1").hide();
        $(".pro2").show();
        $(".pro3").hide();
        $(".pro4").hide();
        $(".zc-findbox li").eq(1).addClass("passed");
    }else if(s==3){
        $(".pro1").hide();
        $(".pro2").hide();
        $(".pro3").show();
        $(".pro4").hide();
        $(".zc-findbox li").eq(2).addClass("passed");
    }else if(s==4){
        $(".pro1").hide();
        $(".pro2").hide();
        $(".pro3").hide();
        $(".pro4").show();
        $(".zc-findbox li").eq(3).addClass("passed");
    }
}
/*提示不符合格式*/
var doShowTips =function(tip) {
    layer.msg(tip, () => {});
}

/*验证手机号*/
var isMobile= function(val) {
    return /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(16[5-6])|(17[0-9]{1})|(18[0-9]{1})|(19[8-9]{1}))+\d{8})$/.test(val);
}
var changeImageVerifyCode = function () {
    $("#yzmImg_float").attr("src", "/api/captcha/generate?_t="+new Date().getTime());
}
var sub_verify_code = function () {
        var phone = $("#xcode").val();
        var code = $("#verifyCode").val();
        if (code == ""){
            // showTips();
            console.log("code is null");
            $(".modal-title").text("验证码不能为空！");
        }else{
            send_VerifyCode(code,phone);
        }

}
/*发送验证码*/
var send_VerifyCode =function(code,phone) {
    //带上phone请求
    $.ajax({
        type: "GET",
        url: "/api/captcha/verify/"+code,
        contentType:'application/json',
        dataType:'json',
        error: function(res) {
            console.log("network error");
            // alert("网络问题未获取到，请稍后重试");
            $(".modal-title").text("网络问题未获取到，请稍后重试");
        },
        success: function(res) {
            if(res.code == 200){
                console.log(res);
                // alert("验证通过，请输入短信验证码");
                $("#getYzmBtn").attr("disabled","true");
                //超时重发时间，先默认60秒
                countdown(60);
                $('#myModal').modal('hide');

            }else{
                console.log(res);
                $(".modal-title").text("验证码错误，请重新输入");
                changeImageVerifyCode();
            }
        },
        fail:function (res) {
            console.log("fail"+res.toString());
            // alert("网络问题未获取到，请稍后重试");
            $(".modal-title").text("网络问题未获取到，请稍后重试");
        }
    })
}
/*重发短信验证码倒计时*/
var countdown = function (overtime) {
        if (--overtime < 1){
            $("#getidtm").show();
            $("#getYzmBtn").removeAttr("disabled");
            $("#getYzmBtn").text("获取手机动态码");
            return;
        }
        $("#getYzmBtn").text(overtime + "秒后可重发");

        setTimeout(function() {
            countdown(overtime)
        }, 1e3)
}

var getVerifyCode =function(){
    $('#myModal').modal('show');
    // 变换验证码
    changeImageVerifyCode();
}

var submitCode = function () {
   var phone = $("#xphone").val(),
       code = $("#xcode").val();
   if(code == ""){
       $("#xcode").focus();
       doShowTips("验证码不能为空");
       return;
   }
    showStep(3);
   /* $.ajax({
        url: "/login1",
        data:{},
        success:function (res) {
            // if(res.code == 200){
            //     alert("success");
            //     // window.location.href = "/index";
            // // }
            console.log(res);
            showStep(3);
        }
    });*/

}

var pswLevel =function() {
    var xpswValue = $("#xpsw").val();
    var level = strLevel(xpswValue, 6);
    if(level > 0){
        var pwdSimpleValue = pwdSimple(xpswValue);
        if(level >= 3  && !pwdSimpleValue ){
            //密码复杂度为强
            $("#pwdLevelDiv").addClass("safetybg_3").removeClass("safetybg_1 safetybg_2");
            $("#pwdLevelDiv").attr("class", "zc-safepsw zc-safepsw_3")
            console.log("safetybg_3");
        }else if(2 != level || pwdSimpleValue ){
            //密码复杂度为弱
            $("#pwdLevelDiv").addClass("safetybg_1").removeClass("safetybg_2 safetybg_3");
            $("#pwdLevelDiv").attr("class", "zc-safepsw zc-safepsw_1");
        }else {
            //密码复杂度为中
            $("#pwdLevelDiv").addClass("safetybg_2").removeClass("safetybg_1 safetybg_3");
            $("#pwdLevelDiv").attr("class", "zc-safepsw zc-safepsw_2")
        }
    }else{
        //密码复杂度为弱
        $("#pwdLevelDiv").addClass("safetybg_1").removeClass("safetybg_2 safetybg_3");
        $("#pwdLevelDiv").removeClass("safetybg_1 safetybg_2 safetybg_3");
        $("#pwdLevelDiv").attr("class", "zc-safepsw");
    }
}
/*计算密码复杂度*/
var strLevel=function(str, charLength) {
    if (!str || !charLength || str.length < charLength)
        return 0;
    var ls = 0;
    return str.match(/([0-9])+/) && ls++,
    str.match(/([a-zA-Z])+/) && ls++,
    str.match(/[^a-zA-Z0-9]+/) && ls++,
        ls
}
/*简单密码匹配*/
var pwdSimple = function(str) {
    return /^(([01268a]{6})|(123((456(7)?)|(123)|(321)|(qwe)|(4qwer)))|(a123((123)|(456)))|(((abc)|(qwe))123)|(1q2w3e(4r)?)|(1?qaz2?wsx)|(654321)|(520520)|(password)|(q1w2e3r4)|(qwerty))$/.test(str)
}
var resetPsw = function () {
    var psw = $("#xpsw").val();
    var psw1= $("#xpsw1").val();
    if(psw == ""){
        $("#xpsw").focus();
        doShowTips("密码不能为空");
    }else if(pwdSimple(psw)){
        $("#xpsw").focus()
        doShowTips("密码不能太简单");
    }else if(psw!=psw1){
        $("#xpsw1").focus();
        doShowTips("密码不一致");
    }else{
        doResetPsw();
    }

}
var doResetPsw =function () {
    var psw = $("#xpsw").val();
    var psw1= $("#xpsw1").val();
    showStep(4);
   /* $.ajax({
        url:'',
        data:{},
        success:function (res) {
            console.log(res);
            showStep(4);
        }
    })*/
}