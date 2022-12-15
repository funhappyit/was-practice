package org.example;

import org.example.CustomWebApplicationServer;

import java.io.IOException;

// GET /calculate/operand1=1&operator=*&operand2=55
public class Main {
    public static void main(String[] args) throws IOException {
        new CustomWebApplicationServer(8090).start();
    }
}
