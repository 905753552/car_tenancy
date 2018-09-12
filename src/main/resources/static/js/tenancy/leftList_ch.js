$(document).ready(function () {
    var year = new Date().getFullYear();
    var month = new Date().getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var day = new Date().getDate();
    var day2 = day + 1;
    if (day < 10) {
        day = "0" + day;
    }
    if (day2 < 10) {
        day = "0" + day;
    }
    var dayTime1 = year + "-" + month + "-" + day;
    var dayTime2 = year + "-" + month + "-" + day2;
    $("input[id='fromDate']").val(dayTime1);
    $("input[id='toDate']").val(dayTime2);
});

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