package com.dev.main.common.statics;

/**
 * 业务请求状态码
 */
public interface StatusCode {

    static final Integer SUCCESS = 200; // 成功

    static final Integer NO_LOGIN = 401; // 未授权

    static final Integer NOT_UNAUTHORIZED = 403; // 无权限

    static final Integer ERROR = 500; // 服务器错误
}
