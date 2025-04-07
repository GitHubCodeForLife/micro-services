package com.programming.techie.proxy;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

class RetryAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before method call");
    }
}
