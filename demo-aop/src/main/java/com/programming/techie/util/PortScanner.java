package com.programming.techie.util;

import java.net.*;

public class PortScanner {
    public static void main(String[] args) {
        String ip = "103.57.114.146";

        for (int port = 1; port <= 20000; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ip, port), 10);
                socket.close();
                System.out.println("Port " + port + " is open");
            } catch (Exception ex) {
                // System.out.println("Port " + port + " is closed");
            }
        }
    }
}
