package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.PortUnreachableException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);
    public CustomWebApplicationServer(int port) {
        this.port = port;
    }
    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("started {} port"+port);

            Socket clientSocket;
            logger.info("waiting for client");
            while((clientSocket = serverSocket.accept())!=null){
                logger.info("connected client connected");
                /*
                    사용자 요청이 들어올 때마다 Thread를 새로 생성하여 사용자 요청을 처리하도록 한다.
                    thread는 생성됄때마다 독립적인 stack 메모리 공간을 할당 받는다
                    서버가 다운됄수도 있다. thread를 고정됀 갯수만큼 할당
                */
                executorService.execute(new ClientRequestHandler(clientSocket));
               // new Thread(new ClientRequestHandler(clientSocket)).start();
            };
        }
    }

}
