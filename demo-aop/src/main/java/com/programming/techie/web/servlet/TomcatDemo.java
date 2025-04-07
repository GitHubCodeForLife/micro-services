package com.programming.techie.web.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

public class TomcatDemo {
    public static void main(String[] args) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        // Set the port for Tomcat
        factory.setPort(8080);

        //set simple get method
        WebServer webServer = factory.getWebServer(new MyServletContextInitializer());
        webServer.start();

    }
}

