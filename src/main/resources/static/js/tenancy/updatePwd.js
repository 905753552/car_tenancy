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
            $("#pwdLevelDiv").addClass("safetybg_1 col-md-3 col-sm-2").removeClass("safetybg_2 safetybg_3");
            $("#pwdLevelDiv").attr("class", "zc-safepsw zc-safepsw_1 col-md-3 col-sm-2");
        }else {
            //密码复杂度为中
            $("#pwdLevelDiv").addClass("safetybg_2 col-md-3 col-sm-2").removeClass("safetybg_1 safetybg_3");
            $("#pwdLevelDiv").attr("class", "zc-safepsw zc-safepsw_2 col-md-3 col-sm-2");
        }
    }else{
        //密码复杂度为弱
        $("#pwdLevelDiv").addClass("safetybg_1").removeClass("safetybg_2 safetybg_3");
        $("#pwdLevelDiv").removeClass("safetybg_1 safetybg_2 safetybg_3");
        $("#pwdLevelDiv").attr("class", "zc-safepsw col-md-3 col-sm-2");
    }
}
// $(".infoleft_bd a").click(function () {
//     $(this).parent().siblings('dd').children().removeClass('infoleft_a');
//     $(this).parent().parent().siblings('dl').children().children().removeClass('infoleft_a');  // 删除其他兄弟元素的样式
//     $(this).addClass('infoleft_a');  // 添加当前元素的样式
//     return false;                                 //加这句来阻止跳转 可用来调试效果
// });
// $(".coupon_hd span").click(function () {
//     $(this).parent().siblings('div').children().removeClass('coupon_a_stop');  // 删除其他兄弟元素的样式
//     $(this).addClass('coupon_a_stop');  // 添加当前元素的样式
//     return false;                                 //加这句来阻止跳转 可用来调试效果
// });

var paswData = {
    passwords:{
        curPassword:"",
        newPassword:""
    }
}
function savePassword() {
    if(!checkPwdForm()) {
        return false;
    }
    var data = JSON.stringify(paswData.passwords);
    console.log(data);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/customer/changePwd",
        data: data,
        success:function (res) {
            console.log(res);
            if(res.code==0) {
                layer.msg("操作成功",{
                    time:1500
                },function(){
                    window.location.href='/tenancy/p/updatePassword';
                });
            }else {
                handleAjax(res);
            }
        }
    })
}
function checkPwdForm() {
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
    if($("#confirm_password").val()=="") {
        doShowTips("请确认新密码");
        $('#confirm_password').focus();
        return false;
    }else{
        var newPwd = $("#xpsw").val();
        var confirmPwd = $("#confirm_password").val();
        if(newPwd != confirmPwd) {
            doShowTips("两次输入密码不一致");
            $('#confirm_password').focus();
            return false;
        }
    }
    return true;
}
/*提示不符合格式*/
function doShowTips(tip) {
    layer.msg(tip, () => {});
}
//Vue app，信息页
var passwordInfo_app = new Vue({
    el: "#passwordInfo",
    data: paswData,
    methods: {
        savePassword:savePassword,
        checkPwdForm:checkPwdForm,
    },
    created:()=>{
        couponAndPoint();
    }
});

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