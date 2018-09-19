package com.dev.main.common.controller.exception;

import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.ResultMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 全局异常处理,最佳实践, 把处于顶层的异常类搁置到代码最尾端
 */
@ControllerAdvice(annotations = {GlobalExceptionResolver.class})
public class CommonExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public ResultMap dataNotFoundExceptionHandler(CommonException e) {
        e.printStackTrace();
        return ResultMap.fail(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMap exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultMap.fail(e.getMessage());
    }

}
