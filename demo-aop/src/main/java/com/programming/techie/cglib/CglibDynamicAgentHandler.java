package com.programming.techie.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDynamicAgentHandler implements MethodInterceptor {

    public Object getInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("proxy do: ");
        return proxy.invokeSuper(obj, args);
    }

    //CGLIB proxy isn't require interface.
    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        CglibDynamicAgentHandler cglibDynamicAgent = new CglibDynamicAgentHandler();
        RealObject instance = (RealObject) cglibDynamicAgent.getInstance(realObject);
        instance.doSomething();
    }
}

