package com.programming.techie;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan
@Configuration(proxyBeanMethods = false)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoApp.class);

        // Get MyService bean
        MyService myService = context.getBean(MyService.class);

        // Call the method which will trigger the aspect
        myService.doSomething();

        context.close();

    }
}
