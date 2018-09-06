// ajax全局配置
$.ajaxSetup({
    dataType: "json",
    cache: false,
    error: function (xhr, msg, exception) {
        if (xhr.status == "403")
            layer.msg('请求出错 : [' + xhr.status + '] 无访问权限');
        else if (xhr.status == "404")
            layer.msg('请求出错 : [' + xhr.status + '] 访问的资源不存在');
        else if (xhr.status == "500")
            layer.msg('请求出错 : [500] 服务器错误');
        else
            layer.msg('请求出错 :' + xhr.responseText);
    }
});

/**
 * 处理ajax请求状态码
 * @param data 返回结果对象
 */
function handleAjax(data) {
    if (data.code == 401) { // 未登录
        window.location.href = '/login';
    } else {
        layer.msg(data.msg);
    }
}
