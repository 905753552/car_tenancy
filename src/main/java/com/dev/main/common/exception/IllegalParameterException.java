package com.dev.main.common.exception;

/**
 * 参数错误异常
 */
public class IllegalParameterException extends RuntimeException  {
    public IllegalParameterException(String message) {
        super(message);
    }
}
