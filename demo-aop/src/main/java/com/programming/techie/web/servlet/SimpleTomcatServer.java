package com.programming.techie.web.servlet;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class SimpleTomcatServer {

    public static void main(String[] args) throws Exception {
        // Create a Tomcat instance
        Tomcat tomcat = new Tomcat();
        String appBase = ".";
        tomcat.getHost().setAppBase(appBase);

        // Set the Tomcat base directory (where temporary files and logs are stored)
        tomcat.setBaseDir("temp");

        // Set the HTTP port (default is 8080)
        tomcat.setPort(8181);

        // Create a new context (similar to a web application context)
        Context context = tomcat.addContext("", null);

        // Add a simple servlet to handle HTTP requests
        Tomcat.addServlet(context, "helloServlet", new HelloServlet());

        // Map the servlet to the "/" URL pattern
        context.addServletMappingDecoded("/", "helloServlet");

        // Start the Tomcat server
        tomcat.getConnector();
        tomcat.start();

        // Wait for requests (block the main thread)
        tomcat.getServer().await();
    }


}
