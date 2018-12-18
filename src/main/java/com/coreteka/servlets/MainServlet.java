package com.coreteka.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainServlet", urlPatterns = {"", "/home.html"})
public class MainServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body><h1>User database</h1><ul style=\"list-style-type:none\">");
        out.println("<br><li><a href='createUser.html' >Create user</a></li>");
        out.println("<br><li><a href='showUser.html' >Get user by username</a></li>");
        out.println("<br><li><a href='showUsers.html' >Get all users</a></li>");
        out.println("<br><li><a href='updateUser.html' >Update user by username</a></li>");
        out.println("<br><li><a href='deleteUser.html' >Delete user by username</a></li>");
        out.println("</ul></body></html>");
        out.close();
    }

    //todo CRUD User
    //todo JPA
    //todo gradle
}
