package com.coreteka.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FindUserServlet", urlPatterns = "/showUser.html")
public class FindUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><body><h3>Enter username and click \"Find user\".</h3>");
        out.println("<form action='userFound.html' method='GET' >");

        out.println("<input type='text' name='username'");
        out.println("<br>");
        out.println("<input type='submit' value = 'Find user'/>");
        out.println("</body></html>");
        out.close();
    }
}
