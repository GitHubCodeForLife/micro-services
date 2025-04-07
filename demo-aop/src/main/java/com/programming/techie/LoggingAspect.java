package com.programming.techie;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.programming.techie.MyService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method execution");
    }
}