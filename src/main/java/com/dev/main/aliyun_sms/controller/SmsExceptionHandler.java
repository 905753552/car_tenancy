package com.dev.main.aliyun_sms.controller;

import com.aliyuncs.exceptions.ClientException;
import com.dev.main.common.controller.exception.CommonExceptionHandler;
import com.dev.main.common.util.ResultMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SmsExceptionHandler extends CommonExceptionHandler {

    @ExceptionHandler({ClientException.class})
    public ResultMap clientException(ClientException e) {
        e.printStackTrace();
        return ResultMap.fail();
    }

    @ExceptionHandler({InterruptedException.class})
    public ResultMap interruptedException(InterruptedException e) {
        e.printStackTrace();
        return ResultMap.fail();
    }


}
