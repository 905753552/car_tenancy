
$(function () {
    //取车
    $("#fromCity2").focus(function () {
        $("#city").css("display", "block");
    });
    $("#city").blur(function () {
        $(this).css("display", "none");
    });
    $("#fromStoreName").click(function () {
        $("#fromStore").css("display", "block");
    });
    $("#fromStore").hover(function () {
        $(this).toggle();
    });



    //还车
    $("#toCity").focus(function () {
        $("#city2").css("display", "block");
    });
    $("#city2").blur(function () {
        $(this).css("display", "none");
    });
    $("#toStoreName").focus(function () {
        $("#toStore").css("display", "block");
    });
    $("#toStore").blur(function () {
        $(this).css("display", "none");
    });
});

$(function () {
    $("#fromStoreName").focus(function () {
        $("#store1").css("display", "block");
    });
    $("#fromStoreName").blur(function () {
        $("#store1").css("display", "none");
    });
});

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
        }
    });
});

// $(function () {
//     $("#popup_content li div span").on({
//         mouseover: function () {
//             $(this).addClass("cur-city");
//         },
//         mouseout: function () {
//             $(this).removeClass("cur-city");
//         },
//         click: function () {
//             $("#fromCity2").val($(this).text().trim());
//             $("#city").css("display", "none");
//             $("#fromCityId").val($(this).find("input").attr("cid"));
//             areaLoad($(this).find("input").attr("cid"));
//         }
//     });
//     $("#popup_content2 li div span").on({
//         mouseover: function () {
//             $(this).addClass("cur-city");
//         },
//         mouseout: function () {
//             $(this).removeClass("cur-city");
//         },
//         click: function () {
//             $("#toCity").val($(this).text().trim());
//             $("#city2").css("display", "none");
//             $("#toCityId").val($(this).find("input").attr("cid"));
//             areaLoad($(this).find("input").attr("cid"));
//         }
//     });
//     $("#store1 div span").on({
//         mouseover: function () {
//             $(this).addClass("cur-city");
//         },
//         mouseout: function () {
//             $(this).removeClass("cur-city");
//         },
//         click: function () {
//             console.log($(this).find("input").attr("aid"));
//         }
//     });
//     // $("#store2 div span").on({
//     //     mouseover: function () {
//     //         $(this).addClass("cur-city");
//     //     },
//     //     mouseout: function () {
//     //         $(this).removeClass("cur-city");
//     //     },
//     //     click: function () {
//     //         $("#toCity").val($(this).text().trim());
//     //         $("#city2").css("display", "none");
//     //         $("#toCityId").val($(this).find("input").attr("cid"));
//     //         areaLoad($(this).find("input").attr("cid"));
//     //     }
//     // });
// });

function choose() {
    $("#popup_content li div span").click(function () {
            $("#fromCity2").val($(this).text().trim());
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

//加载省级地址
function addrLoad () {
    $.ajax({
        type: "get",
        url: "/api/storeAddress/list",
        success: function (res) {
            carInfoTab_app.city = res.data;
            console.log(carInfoTab_app.city);
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
            console.log(res.data);
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