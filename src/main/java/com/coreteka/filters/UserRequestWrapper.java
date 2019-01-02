package com.coreteka.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

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

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new StringReader(body));
    }
}


