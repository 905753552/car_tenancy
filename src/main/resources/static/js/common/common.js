//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null) return unescape(r[2]);
    return null;
};
T.p = url;
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

//重写alert
window.alert = function(msg, callback) {
    parent.layer.alert(msg, function(index) {
        parent.layer.close(index);
        if(typeof(callback) === "function") {
            callback("ok");
        }
    });
}


//重写confirm式样框
window.confirm = function(msg, callback) {
    parent.layer.confirm(msg, {
            btn: ['确定', '取消']
        },
        function() { //确定事件
            if(typeof(callback) === "function") {
                callback("ok");
            }
            layer.closeAll('dialog');
        });
}
