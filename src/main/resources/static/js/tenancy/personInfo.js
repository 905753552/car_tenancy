var tempphone;
/*省市区三级联动*/
function changeAddress(level){
    vData.customer.tncAddress.area.id = 0;
    if(level == 0) {
        vData.customer.tncAddress.city.id = 0;
        // 加载市
        if(vData.customer.tncAddress.province.id!=0) {
            loadAddress(vData.customer.tncAddress.province.id, 1);
        }
    } else if (level == 1) {
        if(vData.customer.tncAddress.city.id!=0) {
            loadAddress(vData.customer.tncAddress.city.id, 2);
        }
    }
}
/**保存用户信息操作*/
function saveInfo() {
    if(!formCheck()) {
        return false;
    }
    vData.customer.phone = tempphone;
    vData.customer.gender = $("input[type='radio']:checked").val();
    var data = JSON.stringify(vData.customer);
    console.log(data);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/customer/change",
        data: data,
        success:function (res) {
            console.log(res);
            if(res.code==0) {
                layer.msg("操作成功",{
                    time:1500
                },function(){
                    window.location.href='/tenancy/p/personInfo';
                });
            }else {
                handleAjax(res);
            }
        }
    })
}
/**用户信息字段*/
var vData = {
    customer:{
        id:"",
        name:"",
        phone:"",
        gender:"",
        idCard:"",
        email:"",
        tncAddress:{
            id:"",
            province:{id:"",name:""},
            city:{id:"",name:""},
            area:{id:"",name:""},
            detail:"",
        },
        emergencyName:"",
        emergencyPhone:"",
        password:"",
    },
    provinces:[],
    citys:[],
    areas:[]
}
/**显示用户信息*/
var findInfo = function() {
    $.ajax({
        type: "get",
        url: "/api/customer/find",
        success:function(res) {
            if(res.code == 0) {
                vData.customer = res.data;
                if(res.data.gender==1) {
                    $("#man").attr("checked", true);
                }else {
                    $("#lady").attr("checked", true);
                }
                tempphone = vData.customer.phone;
                vData.customer.phone = tempphone.substring(0,3)+"****"+tempphone.substring(7);
                if(res.data.tncAddress!=null) {
                    var detailAddress = res.data.tncAddress.detail;
                    var addressProvinceId = res.data.tncAddress.province.id;
                    var addressCityId = res.data.tncAddress.city.id;
                    loadAddress(addressProvinceId, 1);
                    loadAddress(addressCityId, 2);
                    $("#detail").attr('value', detailAddress);
                }
            } else {
                handleAjax(res);
            }
        }
    })
};
/**加载省市区地址*/
function loadAddress(aid, level) {
    $.ajax({
        type: "get",
        url: "/api/customer/address",
        data: {
            aid : aid,
            level : level
        },
        success:function (res) {
            if(res.code == 0) {
                if(level===0) {
                    vData.provinces = res.data;
                }else if(level===1) {
                    vData.citys = res.data;
                }else {
                    vData.areas = res.data;
                }
            }else {
                handleAjax(res);
            }
        }
    })
}
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
            if(res.code == 0){
                // alert("验证通过，请输入短信验证码");
                $("#getYzmBtn").attr("disabled","true");
                //超时重发时间，先默认60秒
                countdown(60);
                $('#myModal').modal('hide');

            }else{
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
 function formCheck(){
    //姓名
    if ($("#name").val() == '') {
        doShowTips("请输入真实姓名");
        $('#name').focus();
        return false;
    }
    if($("#card_code").val()==''){
        doShowTips("请填写证件");
        $('#card_select').focus();
        return false;
    }
    if($("#changePhoneBtn").text()=="取消修改") {
        if($('#currentPhone').val() != tempphone) {
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
        tempphone = new_phone;
    }
    /*var email = $("#email").val();
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
    }*/
     var emergencyUser_phone = $("#emergencyUserPhone").val().trim();
     if(emergencyUser_phone!="") {
         if(!checkMobile(emergencyUser_phone)) {
             doShowTips("手机号格式不对");
             $("#emergencyUserPhone").focus();
             return false;
         }
     }
    return true;
}
/*验证手机*/
function checkMobile(val) {
    return /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(16[5-6])|(17[0-9]{1})|(18[0-9]{1})|(19[8-9]{1}))+\d{8})$/.test(val);
}
/*验证邮箱
function checkEmail(val) {
    return /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(val);
}*/
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
//Vue app，信息页
var customerInfo_app = new Vue({
    el: "#customerInfo",
    data: vData,
    methods: {
        changeAddress: changeAddress,
        saveInfo:saveInfo,
        formCheck:formCheck,
    },
    created: ()=>{
        findInfo();
        loadAddress(0, 0);
        couponAndPoint();
    }
});

