package com.coreteka.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(name = "CreateUserServlet", urlPatterns = "/createUser.html")
public class CreateUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><body><h3>Fill the form and click \"Create user\".</h3>");
        out.println("<form action='userCreated.html' method='POST' >");
        out.println("<ul>");
        out.println("<li>Username<br><input type='text' name='username' value='username"+ System.currentTimeMillis() + "'/> </li>");
        out.println("<li> Login <br> <input type='text' name='login' value='login"+ System.currentTimeMillis() + "'/> </li>");
        out.println("<li> Password <br> <input type='text' name='password' value='password"+ System.currentTimeMillis() + "'/> </li>");
        out.println("<li> e-mail   <br> <input type='text' name='email' value='email"+ System.currentTimeMillis() + "'/> </li>");
        out.println("</ul>");
        out.println("<br>");
        out.println("<input type='submit' value = 'Create user'/>");
        out.println("</body></html>");
        out.close();
    }
}
