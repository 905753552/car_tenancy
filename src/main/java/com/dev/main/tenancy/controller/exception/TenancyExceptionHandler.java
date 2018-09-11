package com.dev.main.tenancy.controller.exception;

import com.dev.main.common.controller.exception.CommonExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(annotations = TenancyExceptionResolver.class)
public class TenancyExceptionHandler extends CommonExceptionHandler {

}
