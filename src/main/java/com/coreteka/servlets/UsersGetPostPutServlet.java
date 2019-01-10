package com.coreteka.servlets;

import com.coreteka.entities.User;
import com.coreteka.exceptions.*;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import com.coreteka.util.UserServletUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersGetPostPutServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private UserServletUtil userServletUtil = new UserServletUtil();

    //GET/Tomcat/users
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            List<User> allUsers = userService.getAll();
            userServletUtil.sendAsJson(response, allUsers);
    }

    //POST/Tomcat/users
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            User newUser = userServletUtil.getUserFromRequest(request);
            User createdUser = userService.create(newUser);
            userServletUtil.sendAsJson(response, createdUser);
        } catch ( InvalidUserAttributeNameException
                | DuplicateUserAttributeValueException
                | NullUserAttributeValueException e ) {
            userServletUtil.sendResponseMessage(response, 400, e.getMessage());
        }
    }

    //PUT/Tomcat/users
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User updatingUser = userServletUtil.getUserFromRequest(request);
            userService.update(updatingUser);
            userServletUtil.sendAsJson(response, updatingUser);
        } catch ( InvalidUserAttributeNameException
                | UserNotFoundException
                | NullUserAttributeValueException
                | DuplicateUserAttributeValueException e ) {
            userServletUtil.sendResponseMessage(response, 400, e.getMessage());
        }
    }
}