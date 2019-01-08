package com.coreteka.servlets;

import com.coreteka.entities.User;
import com.coreteka.exceptions.*;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import com.coreteka.util.UserServletUtil;
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


public class UsersGetDeleteServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private UserServletUtil userServletUtil = new UserServletUtil();

    //GET/Tomcat/users/username
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String username = userServletUtil.getPathVariable(request);
            User user = userService.getByUsername(username);
            userServletUtil.sendAsJson(response, user, mapper);
        } catch (UserNotFoundException | InvalidResourcePathVariable e){
            userServletUtil.sendResponseMessage(response, 404, e.getMessage());
        }
    }

    //DELETE/Tomcat/users/id
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            long id = Long.valueOf(userServletUtil.getPathVariable(request));
            userService.delete(id);
            userServletUtil.sendResponseMessage(response, 200, "User deleted successfully." );
        } catch (UserNotFoundException | InvalidResourcePathVariable e){
            userServletUtil.sendResponseMessage(response, 404, e.getMessage());
        }
    }
}