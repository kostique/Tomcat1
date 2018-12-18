package com.coreteka.servlets;

import com.coreteka.entities.User;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FindAllUsersServlet", urlPatterns = "/showUsers.html")
public class FindAllUsersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = new UserServiceImpl();
        List<User> allUsers = userService.getAll();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><body><h3>Here are all users from database:</h3>");

        out.println("<ul style=\"list-style-type:square\">");
        for(User user: allUsers){
            out.println("<li>" + user.toString() +  "</li>");
        }
        out.println("<ul>");

        out.println("<br><br>");
        out.println("<a href='home.html'>Back to main menu</a>");

        out.println("</body></html>");
        out.close();
    }
}
