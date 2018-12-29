package com.coreteka.filters;

import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;

public class UserRequestWrapper extends HttpServletRequestWrapper {

    public UserRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private BufferedReader reader;

    public void setReader() throws IOException {
        reader = super.getRequest().getReader();
    }

    public void logRequest() throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        System.out.println(builder.toString());
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return reader;
    }
}


