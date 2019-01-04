package com.coreteka.servlets;

import com.coreteka.entities.User;
import com.coreteka.exceptions.*;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UsersGetPostServlet extends HttpServlet {

    //private Gson gson = new Gson();//todo jackson
    private UserService userService = new UserServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            List<User> allUsers = userService.getAll();
            sendAsJson(response, allUsers);
    }

    //POST/Tomcat/users
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    /*  PathException.check(); todo
        if (checkPath())
        {}
        PathException.check();  */

        try {
            User newUser = getUserFromRequest(request, response);
            User createdUser = userService.create(newUser);
            sendAsJson(response, createdUser);
        } catch (UserAlreadyExistsException
                | NullUserAttributeException
                | DuplicateUserAttributeValueException
                | InvalidUserAttributeNameException e) {
            sendResponseMessage(response, 400, e.getMessage());
        }
    }

    private User getUserFromRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String data = builder.toString();
        User requestUser;
        try {
            requestUser = mapper.readValue(data, User.class);
        } catch (UnrecognizedPropertyException e){
            throw new InvalidUserAttributeNameException("Invalid user attribute name.");
        }

        return requestUser;
    }

    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        String res = mapper.writeValueAsString(obj);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }

    private void sendResponseMessage(HttpServletResponse response, int sc, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setStatus(sc);
        PrintWriter out = response.getWriter();
        out.print(message);
        out.flush();
    }
}