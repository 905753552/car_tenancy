$(document).ready(function () {
    $("#changePhoneBtn").click(function () {
        var display = $("#changePhoneForm").toggle().is(':hidden');
        if (display) {
            $("#changePhoneBtn").html("修改")
        } else {
            $("#changePhoneBtn").html("取消修改")
        }
    });
});
/*获取验证码*/
function getVerify_Code() {
    $('#myModal').modal('show');
    // 变换验证码
    changeImageVerifyCode();
}
/*改变验证码图片*/
function changeImageVerifyCode() {
    $("#yzmImg_float").attr("src", "/api/captcha/generate?_t=" + new Date().getTime());
}
/*提交验证码*/
function sub_verify_code() {
    var phone = $("#xcode").val();
    var code = $("#verifyCode").val();
    if (code == ""){
        $(".modal-title").text("验证码不能为空！");
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
function countdown(overtime) {
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
/*提示不符合格式*/
function doShowTips(tip) {
    layer.msg(tip, () => {});
}
// 表单检验
var formCheck = function () {
    //姓名
    if ($("#username").val() == '') {
        doShowTips("请输入真实姓名");
        $('#username').focus();
        return false;
    }
    if($('#card_select option:selected') .val()==0||$("#card_code").val()==''){
        doShowTips("请选择证件并填写");
        $('#card_select').focus();
        return false;
    }

    if($("#changePhoneBtn").text()=="取消修改") {
        if($('#currentPhone').val() != $('#userPhone').val()) {
            doShowTips("请填写原手机号");
            $('#card_select').focus();
            return false;
        }
        var new_phone = $("#newPhone").val();
        if(new_phone=="") {
            doShowTips("请填写新手机号");
            $("#newPhone").focus();
            return false;
        } else {
            if(!checkMobile(new_phone)) {
                doShowTips("手机号格式不对");
                $("#newPhone").focus();
                return false;
            }
        }
        alert("你成功了2");
    }
    var email = $("#email").val();
    if(email == "") {
        doShowTips("请输入电子邮箱");
        $('#email').focus();
        return false;
    }else {
        if(!checkEmail(email)) {
            doShowTips("邮箱格式不正确");
            $('#email').focus();
            return false;
        }
    }
    if ($("#emergencyUserName").val() == '') {
        doShowTips("请输入紧急联系人姓名");
        $('#emergencyUserName').focus();
        return false;
    }
    var emergencyUser_phone = $("#emergencyUserPhone").val().trim();
    if(emergencyUser_phone=="") {
        doShowTips("请填写紧急联系人手机号");
        $("#emergencyUserPhone").focus();
        return false;
    } else {
        if(!checkMobile(emergencyUser_phone)) {
            doShowTips("手机号格式不对");
            $("#emergencyUserPhone").focus();
            return false;
        }
    }
}
/*验证手机*/
function checkMobile(val) {
    return /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(16[5-6])|(17[0-9]{1})|(18[0-9]{1})|(19[8-9]{1}))+\d{8})$/.test(val);
}
/*验证邮箱*/
function checkEmail(val) {
    return /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(val);
}
function strLevel(str, charLength) {
    if (!str || !charLength || str.length < charLength)
        return 0;
    var ls = 0;
    return str.match(/([0-9])+/) && ls++,
    str.match(/([a-zA-Z])+/) && ls++,
    str.match(/[^a-zA-Z0-9]+/) && ls++,
        ls
}

/*简单密码匹配*/
function  pwdSimple(str) {
    return /^(([01268a]{6})|(123((456(7)?)|(123)|(321)|(qwe)|(4qwer)))|(a123((123)|(456)))|(((abc)|(qwe))123)|(1q2w3e(4r)?)|(1?qaz2?wsx)|(654321)|(520520)|(password)|(q1w2e3r4)|(qwerty))$/.test(str)
}
/*计算密码复杂度*/
function strLevel(str, charLength) {
    if (!str || !charLength || str.length < charLength)
        return 0;
    var ls = 0;
    return str.match(/([0-9])+/) && ls++,
    str.match(/([a-zA-Z])+/) && ls++,
    str.match(/[^a-zA-Z0-9]+/) && ls++,
        ls
}
function pswLevel() {
    var xpswValue = $("#xpsw").val();
    var level = strLevel(xpswValue, 6);
    if(level > 0){
        var pwdSimpleValue = pwdSimple(xpswValue);
        if(level >= 3  && !pwdSimpleValue ){
            //密码复杂度为强
            $("#pwdLevelDiv").addClass("safetybg_3 col-md-3 col-sm-2").removeClass("safetybg_1 safetybg_2");
            $("#pwdLevelDiv").attr("class", "zc-safepsw zc-safepsw_3 col-md-3 col-sm-2");
        }else if(2 != level || pwdSimpleValue ){
            //密码复杂度为弱
            $("#pwdLevelDiv").addClass("safetybg_1").removeClass("safetybg_2 safetybg_3");
            $("#pwdLevelDiv").attr("class", "zc-safepsw zc-safepsw_1 col-md-3 col-sm-2");
        }else {
            //密码复杂度为中
            $("#pwdLevelDiv").addClass("safetybg_2").removeClass("safetybg_1 safetybg_3");
            $("#pwdLevelDiv").attr("class", "zc-safepsw zc-safepsw_2 col-md-3 col-sm-2");
        }
    }else{
        //密码复杂度为弱
        $("#pwdLevelDiv").addClass("safetybg_1").removeClass("safetybg_2 safetybg_3");
        $("#pwdLevelDiv").removeClass("safetybg_1 safetybg_2 safetybg_3");
        $("#pwdLevelDiv").attr("class", "zc-safepsw col-md-3 col-sm-2");
    }
}
function currentPwd() {
    var currentPwd = "123456";  //原密码
    var currentInputPwd = $("#current_password").val();  //输入的原密码
    if(currentInputPwd != currentPwd) {
        doShowTips("原密码不正确");
        $('#current_password').focus();
        return false;
    }
}
function equalToPwd() {
    var newPwd = $("#xpsw").val();
    var confirmPwd = $("#confirm_password").val();
    if(newPwd != confirmPwd) {
        doShowTips("两次输入密码不一致");
        $('#confirm_password').focus();
        return false;
    }
}
function checkForm() {
    if ($("#current_password").val() == '') {
        doShowTips("请输入原密码");
        $('#current_password').focus();
        return false;
    }
    if($("#xpsw").val()=="") {
        doShowTips("请输入新密码");
        $('#xpsw').focus();
        return false;
    }
}