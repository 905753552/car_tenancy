
var ob =new Vue({
    el:".zc_login_box",
    data:{
        tag:true,
        username:'',
        password:'',
        loginUrl:''
    },
    methods:{
        showNormal:function () {
            showNormal();
        },
        showMobile:function () {
            showMobile();
        },
        remenberme:function () {
            doRemenber();
        },
        doLogin:function () {
            doLogin();
        },
        getVerificationCode:function () {
            getVerificationCode();
        },
        subVerifyCode:function () {
            subVerifyCode();
        },
        cancelVerify:function () {
            cancelVerify();
        }

    }
});

    /*点击登录方式tag*/
    function  showNormal() {
        if( !$(this).hasClass("cur")){
            $(".ipt-err").remove();
            $("#logintype").val("normal");
            $("#phone_id").hide();
            $("#normal_id").show();
            $("#mobilelogin").removeClass("cur");
            $("#normallogin").addClass("cur");
            $("#phoneYzmLi").hide();
            $(".pass_switchover").show();
            changeBacktoLongin();
        }
    }
    function showMobile() {
        if (!$(this).hasClass("cur")){
            $(".ipt-err").remove();
            $("#logintype").val("phone");
            $("#normal_id").hide();
            $("#phone_id").show();
            $("#normallogin").removeClass("cur");
            $("#mobilelogin").addClass("cur");
            $(".pass_switchover").hide();
            $("#phoneYzmLi").show();
            $("#imageYzmLi").hide();
    }
}
    /*记住密码*/
    function  doRemenber() {

        if($(".login_checkbox").is(":checked")){
            $(".zc_blyes_kong").css({display: "none"});
            $(".tnc-blyes").css({display: "inline-block"});
        }else{
            $(".tnc-blyes").css({display: "none"});
            $(".zc_blyes_kong").css({display: "inline-block"});
        }

    }
    /*点击登录操作*/
    function doLogin() {
        var flag = checkInput();
        if(flag){
            return;
        }else{
            //登录查询
            // alert("input is ok");
            var loginType = $("#logintype").val();
            $("#loginBt").val("登录中..."),
            $("#loginBt").attr("disabled", "disabled"),
            $(".ipt-err").remove();

            if( loginType =="normal"){
                doNormalLogin();
            }else if(loginType == "phone"){
                doPhoneLogin();
            }

        }
    }
    /*正常账号登录请求*/
    function doNormalLogin() {
        var data={};
        $.ajax({
            type: "post",
            url: ob.loginUrl,
            dataType: "json",
            data: data,
            error: function() {
                showlogintips(-29, 30, "#error_tips", "网络异常，请稍后重试"),
                    $("#loginBt").removeAttr("disabled"),
                    $("#loginBt").val("登录")
            },
            success: function(res) {
                console.log(res);
            },
            fail:function (res) {
                showlogintips(-29, 30, "#error_tips", "网络异常，请稍后重试");
                $("#loginBt").removeAttr("disabled");
                $("#loginBt").val("登录");
            }
        })
    }
    /*短信登录请求*/
    function doPhoneLogin() {
        var data={};
        $.ajax({
            type: "post",
            url: ob.loginUrl,
            dataType: "json",
            data: data,
            error: function() {
                showlogintips(-29, 30, "#error_tips", "网络异常，请稍后重试"),
                    $("#loginBt").removeAttr("disabled"),
                    $("#loginBt").val("登录")
            },
            success: function(res) {
                console.log(res);
            },
            fail:function (res) {
                showlogintips(-29, 30, "#error_tips", "网络异常，请稍后重试");
                $("#loginBt").removeAttr("disabled");
                $("#loginBt").val("登录");
            }
        })
    }
    /*检查输入*/
    function checkInput() {
        var loginType = $("#logintype").val();
        //true正常登录方式  false短信快速登录
        if(loginType == "normal"){
            // console.log("normal");
            return checkNormalType();

        }else{
            // console.log("quick");
            return checkQuickType();
        }
    }
    /* 正常登录方式*/
    function checkNormalType() {
        // 登录名
        if($("#normal_id").val() == ''){
            //提示操作
            showlogintips(-29, 30, "#error_tips", "请输入正确的手机号");
            $('#normal_id').focus();
            return true;
        }
        // 密码
        if($("#xpasstext").val() == ''){
            //提示操作
            showlogintips(19, 30, "#error_tips", "请输入密码");
            $('#xpasstext').focus();
            return true;
        }
        return false;
    }
    /*短信快速登录*/
    function checkQuickType() {
        if($("#phone_id").val() == ''){
            //提示操作
            showlogintips(-29, 30, "#error_tips", "请输入正确的手机号");
            $('#phone_id').focus();
            return true;
        }
        if($("#idtmVal").val() == ''){
            //提示操作
            showlogintips(17, 30, "#error_tips", "请输入手机动态验证码");
            $('#idtmVal').focus();
            return true;
        }
        return false;
    }
    /*输入错误消息提示框*/
    function showlogintips(top, left, tipslocation, tipsinfo) {
        var basehtml = '<div class="input-errlog ipt-err" style="top:' + top + "px;left:" + left + 'px;"><span class="arrowbox">◆<span class="arrow">◆</span></span><span class="zc-retan"></span><i>' + tipsinfo + '</i><span class="zc-close"></span></div>';
        $(tipslocation).html(basehtml);
        $(".ipt-err").show();
        $(".zc-close").click(function() {
            $(this).parent().remove()
        })
    }
    /*获取手机验证前的图片验证码验证*/
    function getVerificationCode() {
        $(".ipt-err").remove();
        var phone = $.trim($("#phone_id").val());
        if(phone == '') {
            showlogintips(-29, 30, "#error_tips", "请输入手机号");
            $("#phone_id").focus();
        }else if(isMobile(phone)){
            $(".boxremove").animate({left: "-362px"}, "slow");
            // alert("isphone");
             changeImageVerifiCode();
        }else{
            showlogintips(-29, 30, "#error_tips", "手机号格式错误，请重新输入");
            $("#phone_id").focus();
        }
    }
    /*验证手机号*/
    function isMobile(val) {
    return /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(16[5-6])|(17[0-9]{1})|(18[0-9]{1})|(19[8-9]{1}))+\d{8})$/.test(val)
}
    /*取消验证*/
    function cancelVerify() {
        changeBacktoLongin();
    }
    /*改变验证码图片*/
    function changeImageVerifiCode() {
        $("#yzmImg_float").attr("src", "/api/captcha/generate?_t="+new Date().getTime());
    }
    /*提交验证码*/
    function subVerifyCode() {
       var code =  $.trim($('#yzm_float').val());
       var phone = $.trim($("#phone_id").val());
       if(code == ''){
           showlogintips(10, 30, "#error_tips_float", "请输入验证码")
       }else{
           send_VerifyCode(code,phone);
       }
    }
    /*请求验证码*/
    function send_VerifyCode(code,phone) {
        //带上phone请求
        $.ajax({
            type: "GET",
            url: "/api/captcha/verify/"+code,
            contentType:'application/json',
            dataType:'json',
            error: function(res) {
                // console.log("network error");
                console.log(res);
                changeBacktoLongin();
                winxintipsDialog("网络问题未获取到，请稍后重试");
            },
            success: function(res) {
                    if(res.code == 200){
                        // console.log(res);
                        // alert("验证通过，请输入短信验证码");
                        changeBacktoLongin();
                        $("#idtmVal").focus();
                        showlogintips(17, 30, "#error_tips", "验证码已发送到您的手机！");
                        //超时重发时间，先默认60秒
                        countdown(60);

                    }else{
                        console.log(res);
                        showlogintips(10, 30, "#error_tips_float", "验证码错误，请重新输入");
                        changeImageVerifiCode();
                    }
                },
            fail:function (res) {
                console.log("fail"+res.toString());
                changeBacktoLongin();
                winxintipsDialog("网络问题未获取到，请稍后重试");
            }
    })
}
    /*返回手机号输入面*/
    function changeBacktoLongin() {
        $(".boxremove").animate({left: "0px"}, "slow");
    }
    /*重发短信验证码倒计时*/
    function countdown(overtime) {
        if (--overtime < 1){
             $("#idtmcount").hide();
             $("#getidtm").show();
            return;
        }

        $("#getidtm").hide();
        $("#idtmcount").val(overtime + "秒后可重发");
        $("#idtmcount").show();
        setTimeout(function() {
            countdown(overtime)
        }, 1e3)
    }

    /*温馨提示对话框*/
    function winxintipsDialog(msg, callback) {
        var dialog = getDialogBody("winxintips", msg);
        $("body").append(dialog);
        // 显示温馨提示模态
        show("winxintips");
        // $(".tempDialog").find(".maskclose").click(function() {
        //         callback()
        //  });

    }

/*温馨提示框*/
 function getDialogBody(flag, msg) {
    var msgColor = "";
    var title = "温馨提示", dialogId = "notifyDialog";
    msgColor = "fontBlue";
    if(flag == "error"){
        msgColor = "fontRed",
        title = "错误信息",
        dialogId = "errorDialog"
    }else if("progress" == flag ){
        dialogId = "progressDialog"
    }else if("winxintips" == flag){
        dialogId = "winxintips";
        msgColor = "";
        closeId = dialogId;
    }
    var dialog = "<div  id='" + dialogId + "' class='tempDialog d_none popup_block' mask-data='#?w=350'><div class='orderDialog' style='width: 350px;min-height: 150px;'>";
    return dialog += '<h1 class="f18h1 pc_masklayer_b ac">' + title + '</h1><p class="msg_p p10 ac f14 ' + msgColor + '" style="word-wrap: break-word;word-break: normal;">' + msg + '</p><p class="ac p10"><input maskclose id="colseDialogBT" name="" type="button" value="关闭" class="btn_bluewauto white f14w w80 maskclose"></p>',
         dialog += "</div></div>";

}
    /*温馨提示框显示*/
 function show(popID) {
    var popObj = $("#" + popID)
        , popURL = popObj.attr("mask-data")
        , query = popURL.split("?")
        , dim = query[1].split("&")
        , popWidth = dim[0].split("=")[1];
    popObj.children(".maskclose") || popObj.prepend("<a href=\"javascript:close('" + popID + '\')" class="close maskclose">&nbsp;</a>'),
        popObj.fadeIn().css({
            width: Number(popWidth)
        });
    var popMargTop = ($("#" + popID).height() + 80) / 2
        , popMargLeft = ($("#" + popID).width() + 80) / 2;
    popObj.css({
        "margin-top": -popMargTop,
        "margin-left": -popMargLeft
    }),
        $("body").append('<div id="fade" class="fade_' + popID + '"></div>'),
        $("#fade").css({
            filter: "alpha(opacity=80)"
        }).fadeIn(),
        $("#colseDialogBT").click(function() {
            close(popID)
        })
}
    /*关闭模态框*/
     function close(id) {
        if (id) {
            var layerId = ".fade_" + id;
            $(layerId + ", .popup_block").fadeOut(function() {
                $(layerId).remove()
            })
        } else
            $("#fade , .popup_block").fadeOut(function() {
                $("#fade").remove()
            })
    }