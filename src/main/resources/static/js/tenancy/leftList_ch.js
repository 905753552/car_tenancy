$(document).ready(function () {
    var OneDay = 86400000;
    var date = new Date().getTime() / OneDay;
    var fromDate = formatDate(new Date(date + 1) * OneDay);
    var toDate = formatDate(new Date(date + 2) * OneDay);
    $("#fromDate").val(fromDate);
    $("#toDate").val(toDate);
});

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

$(function () {
    // 类型checked切换
    $("#ch_type li a").click(function () {
        $(this).parent().siblings('li').children().removeClass('checked');  // 删除其他兄弟元素的样式
        $(this).addClass('checked');                   // 添加当前元素的样式
        return false;                                 //加这句来阻止跳转 可用来调试效果
    });

    // $("#ch_price li a").click(function () {
    //     $(this).parent().siblings('li').children().removeClass('checked');  // 删除其他兄弟元素的样式
    //     $(this).addClass('checked');                   // 添加当前元素的样式
    //     return false;                                 //加这句来阻止跳转 可用来调试效果
    // });

    //价格checked切换
    $("#ch_num li a").click(function () {
        $(this).parent().parent().parent().removeClass('price1');
        $(this).parent().parent().parent().removeClass('price2');
        $(this).parent().parent().parent().removeClass('price3');
        $(this).parent().parent().parent().removeClass('price4');
        $(this).parent().parent().parent().removeClass('price5');
        $(this).parent().siblings('li').children().removeClass('checked');
        $(this).addClass('checked');
        if ($(this).hasClass('checked') && $(this).parent().hasClass('bar1')) {
            $(this).parent().parent().parent().addClass('price1');
        } else if ($(this).hasClass('checked') && $(this).parent().hasClass('bar2')) {
            $(this).parent().parent().parent().addClass('price2');
        } else if ($(this).hasClass('checked') && $(this).parent().hasClass('bar3')) {
            $(this).parent().parent().parent().addClass('price3');
        } else if ($(this).hasClass('checked') && $(this).parent().hasClass('bar4')) {
            $(this).parent().parent().parent().addClass('price4');
        } else if ($(this).hasClass('checked') && $(this).parent().hasClass('bar5')) {
            $(this).parent().parent().parent().addClass('price5');
        } else {
            return false;                             //加这句来阻止跳转 可用来调试效果
        }
        return false;
    });
    // 品牌checked切换
    $("#ch_brand li a").click(function () {
        $(this).parent().siblings('li').children().removeClass('checked');  // 删除其他兄弟元素的样式
        $(this).addClass('checked');                   // 添加当前元素的样式
        return false;                                 //加这句来阻止跳转 可用来调试效果
    });

});

