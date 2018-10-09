
//门店市区地址样式
$(function() {
    $("body").click(function(e) {
        var target = $(e.target);
        $("#city").css("display","none");
        $("#city2").css("display","none");
        $("#fromStore").css("display","none");
        $("#toStore").css("display","none");
        $("#fromCity2").removeClass("tnc_border");
        $("#toCity").removeClass("tnc_border");
        $("#fromStoreName").removeClass("tnc_border");
        $("#toStoreName").removeClass("tnc_border");
        if( $("#fromCity2").hasClass("tnc_input_citySD2") && !($("#fromCity2").hasClass("tnc_input_citySD")) ){
            $("#fromCity2").removeClass("tnc_input_citySD2");
            $("#fromCity2").addClass("tnc_input_citySD");
        }
        if( $("#toCity").hasClass("tnc_input_citySD2") && !($("#toCity").hasClass("tnc_input_citySD")) ){
            $("#toCity").removeClass("tnc_input_citySD2");
            $("#toCity").addClass("tnc_input_citySD");
        }
        if( $("#fromStoreName").hasClass("tnc_input_citySD2") && !($("#fromStoreName").hasClass("tnc_input_citySD")) ){
            $("#fromStoreName").removeClass("tnc_input_citySD2");
            $("#fromStoreName").addClass("tnc_input_citySD");
        }
        if( $("#toStoreName").hasClass("tnc_input_citySD2") && !($("#toStoreName").hasClass("tnc_input_citySD")) ){
            $("#toStoreName").removeClass("tnc_input_citySD2");
            $("#toStoreName").addClass("tnc_input_citySD");
        }
        if( target.is('#fromCity2') || target.is('#city *') && !(target.is("#city span")) ){
            $("#fromCity2").addClass("tnc_border");
            $("#fromCity2").removeClass("tnc_input_citySD");
            $("#fromCity2").addClass("tnc_input_citySD2");
            $("#city").css("display", "block");
        }
        if( target.is('#fromStoreName') || target.is('#fromStore *') && !(target.is("#fromStore_Name span"))) {
            $("#fromStoreName").addClass("tnc_border");
            $("#fromStoreName").removeClass("tnc_input_citySD");
            $("#fromStoreName").addClass("tnc_input_citySD2");
            $("#fromStore").css("display", "block");
        }
        if( target.is('#toCity') || target.is('#city2 *') && !(target.is("#city2 span")) ){
            $("#toCity").addClass("tnc_border");
            $("#toCity").removeClass("tnc_input_citySD");
            $("#toCity").addClass("tnc_input_citySD2");
            $("#city2").css("display", "block");
        }
        if( target.is('#toStoreName') || target.is('#toStore *') && !(target.is("#toStore_Name span"))) {
            $("#toStoreName").addClass("tnc_border");
            $("#toStoreName").removeClass("tnc_input_citySD");
            $("#toStoreName").addClass("tnc_input_citySD2");
            $("#toStore").css("display", "block");
        }
    });
});

//门店市级英文字母样式
$(function () {
    //26个英文字符点击事件的颜色切换
    $("#popup_title li").click(function () {
        $(this).siblings().removeClass("cur");
        $(this).addClass("cur");
        //将对应的面板显示出来，并隐藏其他面板
        // console.log($(this).parent().siblings());
        $(this).parent().siblings().children().removeClass("cur");
        if ($(this).val() === 1) {
            $(this).parent().siblings().children().eq(0).addClass("cur");
        } else if ($(this).val() === 2) {
            $(this).parent().siblings().children().eq(1).addClass("cur");
        } else if ($(this).val() === 3) {
            $(this).parent().siblings().children().eq(2).addClass("cur");
        } else if ($(this).val() === 4) {
            $(this).parent().siblings().children().eq(3).addClass("cur");
        } else if ($(this).val() === 5) {
            $(this).parent().siblings().children().eq(4).addClass("cur");
        } else if ($(this).val() === 6) {
            $(this).parent().siblings().children().eq(5).addClass("cur");
        } else if ($(this).val() === 7) {
            $(this).parent().siblings().children().eq(6).addClass("cur");
        }
    });
    //26个英文字符点击事件的颜色切换
    $("#popup_title2 li").click(function () {
        $(this).siblings().removeClass("cur");
        $(this).addClass("cur");
        //将对应的面板显示出来，并隐藏其他面板
        // console.log($(this).parent().siblings());
        $(this).parent().siblings().children().removeClass("cur");
        if ($(this).val() === 1) {
            $(this).parent().siblings().children().eq(0).addClass("cur");
        } else if ($(this).val() === 2) {
            $(this).parent().siblings().children().eq(1).addClass("cur");
        } else if ($(this).val() === 3) {
            $(this).parent().siblings().children().eq(2).addClass("cur");
        } else if ($(this).val() === 4) {
            $(this).parent().siblings().children().eq(3).addClass("cur");
        } else if ($(this).val() === 5) {
            $(this).parent().siblings().children().eq(4).addClass("cur");
        } else if ($(this).val() === 6) {
            $(this).parent().siblings().children().eq(5).addClass("cur");
        } else if ($(this).val() === 7) {
            $(this).parent().siblings().children().eq(6).addClass("cur");
        }
    });
});

//选择门店地址后的val赋值及样式修改
function choose() {
    $("#popup_content li div span").click(function () {
            $("#fromCity2").val($(this).text().trim());
            $("#fromStoreName").val("请选择取车门店");
            $("#toCity").val($(this).text().trim());
            $("#toStoreName").val("请选择取车门店");
            $("#city").css("display", "none");
            $("#fromCityId").val($(this).find("input").attr("cid"));
            areaLoad($(this).find("input").attr("cid"));
    });
    $("#popup_content2 li div span").click(function () {
        $("#toCity").val($(this).text().trim());
        $("#city2").css("display", "none");
        $("#toCityId").val($(this).find("input").attr("cid"));
        areaLoad($(this).find("input").attr("cid"));
    });
    $("#fromStore div span").click(function () {
        storeLoad($(this).find("input").attr("aid"));
        $("#fromStore_Name span").removeClass("cur-city");
        $(this).addClass("cur-city");
        $(this).siblings("span").removeClass("cur-city");
        $("#fromStore_Name").css("display","block");
        $("#fromStore_Detail").css("display","none");
    });
    $("#toStore div span").click(function () {
        storeLoad($(this).find("input").attr("aid"));
        $("#toStore_Name span").removeClass("cur-city");
        $(this).addClass("cur-city");
        $(this).siblings("span").removeClass("cur-city");
        $("#toStore_Name").css("display","block");
        $("#toStore_Detail").css("display","none");
    });
}

//加载市级地址
function addrLoad () {
    $.ajax({
        type: "get",
        url: "/api/storeAddress/list",
        success: function (res) {
            carInfoTab_app.city = res.data;
        }
    })
}

//加载区级地址
var areaLoad = function (cid) {
    $.ajax({
        type: "get",
        url: "/api/storeAddress/list2",
        data:{
            id:cid
        },
        success: function (res) {
            console.log(res.data);      //已拿到相应区地址
            carInfoTab_app.area = res.data;
        }
    })
};

//加载门店
var storeLoad = function (sid) {
    $.ajax({
        type: "get",
        url: "/api/storeAddress/list3",
        data:{
            id:sid
        },
        success: function (res) {
            console.log(res.data);      //拿到相应门店信息
            carInfoTab_app.storeName = res.data;
        }
    })
};

// $(function () {
//     //选择时间时图标切换
//     $("#fromHourMinute").focus(function () {
//         $(this).removeClass("tnc_input_citySD");
//         $(this).addClass("tnc_input_citySD2");
//         $(this).blur(function () {
//             $(this).removeClass("tnc_input_citySD2");
//             $(this).addClass("tnc_input_citySD");
//         });
//     });
//     $("#toHourMinute").focus(function () {
//         $(this).removeClass("tnc_input_citySD");
//         $(this).addClass("tnc_input_citySD2");
//         $(this).blur(function () {
//             $(this).removeClass("tnc_input_citySD2");
//             $(this).addClass("tnc_input_citySD");
//         });
//     });
// });