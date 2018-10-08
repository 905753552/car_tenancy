package com.dev.main.aspects;

import com.dev.main.tenancy.service.ICarService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Component
public class CarAssessTimes {
    @Autowired
    private ICarService iCarService;
    @AfterReturning(value = "execution(* com.dev.main.tenancy.service.ICarService.TncCardetail(..))")
    public void addCarAssessTimes(JoinPoint joinPoint){

        Object[] aid = joinPoint.getArgs();
        //System.out.println((long) aid[0]);
        iCarService.addCarHot((long) aid[0]);

    }

}

