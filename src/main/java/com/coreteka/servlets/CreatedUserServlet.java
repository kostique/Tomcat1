package com.coreteka.servlets;

import com.coreteka.entities.User;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreatedUserServlet", urlPatterns = "/userCreated.html")
public class CreatedUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserService userService = new UserServiceImpl();
        User user = new User();

        user.setUsername(request.getParameter("username"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));

        userService.create(user);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body><h3>User created successfully</h3>");
        out.println("<a href='home.html'>Back to main menu</a>");
        out.println("</body></html>");
        out.close();

    }
}
