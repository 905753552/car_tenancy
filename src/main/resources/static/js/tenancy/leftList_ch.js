$(document).ready(function () {
    var year = new Date().getFullYear();
    var month = new Date().getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var day = new Date().getDate();
    if (day < 10) {
        day = "0" + day;
    }
    var dayTime = year + "-" + month + "-" + day;
    $("input[id='fromDate']").val(dayTime);
    $("input[id='toDate']").val(dayTime);
});

$(function () {
    $("li a").click(function () {
        $(this).parent().siblings('li').children().removeClass('checked');  // 删除其他兄弟元素的样式
        $(this).addClass('checked');                   // 添加当前元素的样式
        return false;                                 //加这句来阻止跳转 可用来调试效果
    });
    $("#ch_num li a").click(function () {
        $(this).parent().parent().parent().removeClass('price1');
        $(this).parent().parent().parent().removeClass('price2');
        $(this).parent().parent().parent().removeClass('price3');
        $(this).parent().parent().parent().removeClass('price4');
        $(this).parent().parent().parent().removeClass('price5');
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
    });
});