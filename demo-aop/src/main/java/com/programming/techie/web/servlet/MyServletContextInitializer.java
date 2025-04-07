package com.programming.techie.web.servlet;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.web.servlet.DispatcherServlet;

class MyServletContextInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("my.init.param", "my.value");
        System.out.println("ServletContext initialized");
        // You can also register your own servlets, filters, and listeners here
        servletContext.addServlet("myServletContext", new HelloServlet())
        .addMapping("/hello");


    }
}
