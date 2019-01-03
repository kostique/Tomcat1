package com.coreteka.servlets;

import com.coreteka.entities.User;
import com.coreteka.exceptions.InvalidUserAttributesException;
import com.coreteka.exceptions.InvalidUserAttributesNumberException;
import com.coreteka.exceptions.UserAlreadyExistsException;
import com.coreteka.exceptions.UserNotFoundException;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class UserServlet extends HttpServlet {

    private Gson gson = new Gson();//todo jackson
    private UserService userService = new UserServiceImpl();

    //GET/Tomcat/users
    //GET/Tomcat1/users/username
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo == null || pathInfo.equals("/")){
            List<User> allUsers = userService.getAll();
            sendAsJson(response, allUsers);
            return;
        }
        String[] splits = pathInfo.split("/");
        if (splits.length != 2){
            sendResponseMessage(response, 400, "Invalid resource path.");
            return;
        }
        String username = splits[1];

        try {
            User user = userService.getByUsername(username);
            sendAsJson(response, user);
        } catch (UserNotFoundException unfe){
            sendResponseMessage(response, 404, "User not found.");
        }
    }

    //POST/Tomcat/users
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pathInfo = request.getPathInfo();

        PathException.check();
        if (checkPath()) {

        }
        if(pathInfo == null || pathInfo.equals("/")){
            try {
                PathException.check();
                User requestUser = getUserFromRequest(request);
                User createdUser = userService.create(requestUser);
                sendAsJson(response, createdUser);
            } catch (UserAlreadyExistsException | InvalidUserAttributesNumberException | InvalidUserAttributesException bre) {
                sendResponseMessage(response, 400, bre.getMessage());
            }
        }
        else {
            sendResponseMessage(response, 400, "Invalid resource path.");
        }
    }

    //PUT/Tomcat/users/username
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsernameFromPath(request);
        if(username == null){
            sendResponseMessage(response, 400, "Invalid resource path.");
            return;
        }
        User databaseUser;

        try {
            databaseUser = userService.getByUsername(username);
        } catch (UserNotFoundException unfe){
            sendResponseMessage(response, 404, "User not found.");
            return;//todo
        }
        User requestUser = getUserFromRequest(request);
        databaseUser.setLogin(requestUser.getLogin());
        databaseUser.setPassword(requestUser.getPassword());
        databaseUser.setEmail(requestUser.getEmail());

        try {
            userService.update(databaseUser);
        }catch (InvalidUserAttributesException bre){
            sendResponseMessage(response, 400, bre.getMessage());
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
        String res = gson.toJson(obj);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }

    private String getUsernameFromPath(HttpServletRequest request){
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            return null;
        }

        String[] splits = pathInfo.split("/");

        if(splits.length != 2){
            return null;
        }
        return splits[1];
    }

    private User getUserFromRequest(HttpServletRequest request) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        int counter = 0;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            counter++;
        }

        if (counter != 6){
            throw new InvalidUserAttributesNumberException("Invalid user attributes number");
        }

        String data = builder.toString();
        return gson.fromJson(data, User.class);
    }

    private void sendResponseMessage(HttpServletResponse response, int sc, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setStatus(sc);
        PrintWriter out = response.getWriter();
        out.print(message);
        out.flush();
    }
}

//      reader.lines().forEach(System.out::println);