package com.coreteka.servlets;

import com.coreteka.entities.User;
import com.coreteka.exceptions.UserAlreadyExistsException;
import com.coreteka.exceptions.UserCouldNotBeFoundException;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class UserServlet extends HttpServlet {

    private Gson gson = new Gson();


    //GET/Tomcat1/users/
    //GET/Tomcat1/users/username
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        UserService userService = new UserServiceImpl();

        User user;

        if(pathInfo == null || pathInfo.equals("/")){

            List<User> allUsers = userService.getAll();

            sendAsJson(response, allUsers);

            return;
        }

        String[] splits = pathInfo.split("/");

        if(splits.length != 2) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

            return;
        }

        String username = splits[1];

        try {

            user = userService.getByUsername(username);

            sendAsJson(response, user);

            return;

        } catch (UserCouldNotBeFoundException ucnbfe){

            response.sendError(HttpServletResponse.SC_NOT_FOUND);

            return;
        }

    }

    //POST/Tomcat1/users
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){

            StringBuilder buffer = new StringBuilder();

            BufferedReader reader = request.getReader();

            String line;

            while ((line = reader.readLine()) != null) {

                buffer.append(line);

            }

            String data = buffer.toString();

            User user = gson.fromJson(data, User.class);

            UserService userService = new UserServiceImpl();

            try {

                User createdUser = userService.create(user);

                sendAsJson(response, createdUser);

                return;

            } catch (UserAlreadyExistsException uaee){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
        else {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

            return;
        }
    }

    //PUT/Tomcat1/users/username
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {

        response.setContentType("application/json");

        String res = gson.toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);

        out.flush();
    }

}
