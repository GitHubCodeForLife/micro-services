package com.programming.techie.web.reactive;

import org.springframework.boot.web.embedded.tomcat.TomcatReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;

public class DemoTomcat {
    public static void main(String[] args) {
        TomcatReactiveWebServerFactory factory = new TomcatReactiveWebServerFactory();

        factory.setPort(8080);

        //set simple get method
        WebServer webServer = factory.getWebServer(new MyHttpHandler());
        webServer.start();

    }
}
