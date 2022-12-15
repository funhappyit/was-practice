package org.example;

public class RequestLine {
    private final String method;  //GET
    private final String urlPath; //

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        this.urlPath= tokens[1];
    }
}
