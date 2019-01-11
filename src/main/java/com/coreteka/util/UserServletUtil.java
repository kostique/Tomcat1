package com.coreteka.util;

import com.coreteka.entities.User;
import com.coreteka.exceptions.InvalidResourcePathVariable;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServletUtil {

    private ObjectMapper mapper = new ObjectMapper();
    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public User getUserFromRequest(HttpServletRequest request) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String data = builder.toString();
        User requestUser;
        requestUser = mapper.readValue(data, User.class);
        return requestUser;
    }

    public void sendResponseMessage(HttpServletResponse response, int sc, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setStatus(sc);
        PrintWriter out = response.getWriter();
        out.print(message);
        out.flush();
    }

    public void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        String res = mapper.writeValueAsString(obj);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }

    public String getPathVariable(HttpServletRequest request) {
        //Take away the first pathInfo character which is "/"
        String pathVariable = request.getPathInfo().substring(1);
        if (pathVariable.equals("")) {
            throw new InvalidResourcePathVariable("Invalid resource path variable");
        }
        return pathVariable;
    }
}
