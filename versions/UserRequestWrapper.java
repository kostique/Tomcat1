package com.coreteka.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class UserRequestWrapper extends HttpServletRequestWrapper {

    private String body;

    UserRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    void logRequest() throws IOException {

        BufferedReader bufferedReader = super.getRequest().getReader();
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
            builder.append(System.lineSeparator());
        }
        body = builder.toString();
    }

    void printRequestToConsole(){
        System.out.println();
        System.out.println();
        System.out.println(super.getMethod() + "  " +  super.getRequestURL());
        System.out.println(super.getRequest().getProtocol());
        System.out.println("Content-Type: " + super.getContentType());
        System.out.println(body);
        System.out.println();
    }

    @Override
    public BufferedReader getReader(){
        return new BufferedReader(new StringReader(body));
    }
}