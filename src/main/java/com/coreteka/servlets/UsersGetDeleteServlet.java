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


public class UsersGetDeleteServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private UserServletUtil userServletUtil = new UserServletUtil();

    //GET/Tomcat/users/login
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String login = userServletUtil.getPathVariable(request);
            User user = userService.getByLogin(login);
            userServletUtil.sendAsJson(response, user);
        } catch (UserNotFoundException e) {
            userServletUtil.sendResponseMessage(response, 404, e.getMessage());
        } catch (InvalidResourcePathVariable e) {
            userServletUtil.sendResponseMessage(response, 400, e.getMessage());
        }
    }

    //DELETE/Tomcat/users/id
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            long id = Long.valueOf(userServletUtil.getPathVariable(request));
            userService.delete(id);
            userServletUtil.sendResponseMessage(response, 200, "User deleted successfully.");
        } catch (UserNotFoundException e) {
            userServletUtil.sendResponseMessage(response, 404, e.getMessage());
        } catch (InvalidResourcePathVariable e) {
            userServletUtil.sendResponseMessage(response, 400, e.getMessage());
        }
    }
}