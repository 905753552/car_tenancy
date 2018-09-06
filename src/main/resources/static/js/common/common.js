/**
 */


/**
 * 返回
 * @param code
 */
function handleAjax(data) {
    if (data.code == 401) {
        window.location.href = '/login';
    } else {
        layer.alert(data.msg);
    }
}
