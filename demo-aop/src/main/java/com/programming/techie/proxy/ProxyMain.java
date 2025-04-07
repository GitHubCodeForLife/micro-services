package com.programming.techie.proxy;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;


public class ProxyMain {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new Dog());
        factory.addInterface(Animal.class);
        factory.addAdvice(new RetryAdvice());


        Animal pojo = (Animal) factory.getProxy();
        // this is a method call on the proxy!
        pojo.makeSound();
    }
}

