    var ob = new Vue({
        el:'.form_bg',
        data:{},
        methods:{
            getVerifyCode(){
                getVerifyCode();
            },
            sub_verify_code(){
                sub_verify_code();
            },
            doRegister(){
                doRegister();
            },
            inputPsw(){
                pswLevel();
            }
        }
    });
    /*获取验证码*/
    function getVerifyCode() {
        if( $("#xphone").val() == "" ||  $("#xphone").val() == null){
            $("#xphone").focus();
            layer.msg('电话不能空', () => {});
            return ;
        }
        if (!checkMobile($("#xphone").val())){
            $("#xphone").focus();
            layer.msg('手机号码格式错误',() => {});
            return ;
        }else{
            // console.log("success");
            $('#myModal').modal('show');
            // 变换验证码
            changeImageVerifyCode();
        }

    }
        /*改变验证码图片*/
    function changeImageVerifyCode() {
        $("#yzmImg_float").attr("src", "/api/captcha/generate?_t="+new Date().getTime());
    }
    /*验证手机*/
    function checkMobile(val) {
        return /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(16[5-6])|(17[0-9]{1})|(18[0-9]{1})|(19[8-9]{1}))+\d{8})$/.test(val);

    }
    /*提交验证码*/
    function sub_verify_code() {
        var phone = $("#xcode").val();
        var code = $("#verifyCode").val();
        if (code == ""){
            // showTips();
            // console.log("code is null");
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
                    $("#getYzmBtn").attr("disabled","true");
                    doCheckPhoneRepeat();
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
    /*
    * 查重手机
    * */
    function doCheckPhoneRepeat() {
        var phone = $("#xphone").val();
        $.ajax({
            type: "GET",
            data:{phone:phone},
            url: "/api/user/checkRepeat",
            success: function(res) {
                if(res.code == 0){
                    doSendPhoneCode();
                    // console.log("发送验证");
                    //超时重发时间，先默认60秒
                    countdown(60);
                }else {
                    layer.msg(res.msg);
                }
            }
        })
    }
    /**
     * 发送短信验证码
     * */
    function doSendPhoneCode() {
        var phone = $("#xphone").val();
        $.ajax({
            url:'/api/alisms/'+phone,
            success:function (res) {
                if(res.code==0){
                    layer.msg("发送成功！");
                }else{
                    layer.msg("发送失败，请一分钟后再试！");
                }
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
    /*点击注册按钮*/
    function doRegister() {
        var flag = doCheck();
        if(!flag){
            return;
        }else{
            doSubmit();
        }

    }
    /*检查表单*/
    function doCheck() {
        if($("#xname").val() == ""){
            $("#xname").focus();
            doShowTips("用户名不能为空");
            return false;
        }else if(isNumber($("#xname").val())){
            $("#xname").focus();
            doShowTips("用户名不能为数字");
            return false;
        }
       // console.log(isMobile($("#xphone").val()));
        if($("#xphone").val() == ""){
            $("#xphone").focus();
            doShowTips("手机号码不能为空");
            return false;
        }else if(!isMobile($("#xphone").val())){
            $("#xphone").focus();
            doShowTips("手机号码格式错误");
            return false;
        }
        if($("#xcode").val() == ""){
            $("#xcode").focus();
            doShowTips("验证码不能为空");
            return false;
        }
        if($("#xpsw").val() == ""){
            $("#xpsw").focus();
            doShowTips("密码不能为空");
            return false;
        }else if($("#xpsw").val().length <6 || $("#xpsw").val().length >18){
            $("#xpsw").focus();
            doShowTips("密码长度必须在6-18字符");
            return false;
        }else if(getPswLevel()<1){
            $("#xpsw").focus();
            doShowTips("密码不符合规则！");
            return false;
        }

        return true;
    }
    /*提交表单*/
    function doSubmit() {

        var name = $("#xname").val(),
            phone = $("#xphone").val(),
            code = $("#xcode").val(),
            psw =  $("#xpsw").val();
            // coupon = $("#xcoupon").val();

        var data = {
            tnc:{
                name:name,
                phone:phone,
                password:psw
            },
            code:code,
            // coupon:coupon
        }
        // console.log(data);
        $.ajax({
            type:"POST",
            url: "/api/user/register",
            data:JSON.stringify(data),
            // dataType:"json",
            contentType:"application/text",
            success:function (res) {
                if(res.code == 0){
                    // alert("success");
                    window.location.href = "/index";
                }else {
                    layer.msg(res.msg);
                }
            }
        });
    }
    /*提示不符合格式*/
    function doShowTips(tip) {
        layer.msg(tip, () => {});
    }

    /*验证是否为数字*/
    function isNumber(val) {
        return regExpTest(val, /\d+/);
    }
    function regExpTest(source, re) {
        var result = !1;
        return !!source && (source == re.exec(source) && (result = !0),
            result)
    }
    /*获取密码复杂度*/
    function getPswLevel() {
        var value = $("#xpsw").val();
        var level = strLevel(value, 6);
        var pwdSimpleValue = pwdSimple(value);
        if(pwdSimpleValue){
            level =1;
        }
        return level;
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

    /*验证手机号*/
    function isMobile(val) {
        return /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(16[5-6])|(17[0-9]{1})|(18[0-9]{1})|(19[8-9]{1}))+\d{8})$/.test(val);
    }
