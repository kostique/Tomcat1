package com.coreteka.servlets;

import com.coreteka.entities.User;
import com.coreteka.exceptions.InvalidUserAttributeNameException;
import com.coreteka.exceptions.InvalidUserAttributeValueException;
import com.coreteka.exceptions.InvalidUserAttributeNumberException;
import com.coreteka.exceptions.UserNotFoundException;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class UsersGetPutDeleteServlet extends HttpServlet {

//    private Gson gson = new Gson();//todo jackson
//    private UserService userService = new UserServiceImpl();
    private UserService userService = new UserServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsernameFromPath(request);
        try {
            User user = userService.getByUsername(username);
            sendAsJson(response, user);
        } catch (UserNotFoundException e){
            sendResponseMessage(response, 404, "User not found.");
        }
    }



    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //String username = getUsernameFromPath(request);

        User updatingUser = getUserFromRequest(request);
        //todo: update user by id with all validations in Service Layer;
//        try {
//            databaseUser = userService.getByUsername(username);
//        } catch (UserNotFoundException unfe){
//            sendResponseMessage(response, 404, "User not found.");
//            return;//todo
//        }
//        User requestUser = getUserFromRequest(request);
//        databaseUser.setLogin(requestUser.getLogin());
//        databaseUser.setPassword(requestUser.getPassword());
//        databaseUser.setEmail(requestUser.getEmail());

        try {
            userService.update(updatingUser);
        }catch (InvalidUserAttributeValueException e){
            sendResponseMessage(response, 400, e.getMessage());
            return;
        }
        sendAsJson(response, databaseUser);
    }

    //DELETE/Tomcat/users/username
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsernameFromPath(request);//todo request params
        if(username == null){
            sendResponseMessage(response, 400, "Invalid resource path." );
            return;
        }
        try {
            userService.delete(username);
        } catch (UserNotFoundException unfe){
            sendResponseMessage(response, 404, "User not found." );
            return;//todo
        }
        sendResponseMessage(response, 200, "User deleted successfully." );
    }

    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        String res = mapper.writeValueAsString(obj);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }

    private String getUsernameFromPath(HttpServletRequest request) {
        //Take away the first pathInfo character which is "/"
        return request.getPathInfo().substring(1);
    }

    private User getUserFromRequest(HttpServletRequest request) throws IOException {
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

    private void sendResponseMessage(HttpServletResponse response, int sc, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setStatus(sc);
        PrintWriter out = response.getWriter();
        out.print(message);
        out.flush();
    }
}