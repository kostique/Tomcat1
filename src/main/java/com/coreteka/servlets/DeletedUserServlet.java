package com.coreteka.servlets;

import com.coreteka.entities.User;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeletedUserServlet", urlPatterns = "/userDeleted.html")

public class DeletedUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");

        try {

            userService.delete(username);
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body><h3>User <u><font color=\"blue\">" + username + "</font></u> deleted successfully.</h3>");
            out.println("<a href='home.html'>Back to main menu</a>");
            out.println("</body></html>");
            out.close();

        } catch (PersistenceException dsf){
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body><h3>Could not delete user with username: " + username + "</h3>");
            out.println("<a href='home.html'>Back to main menu</a>");
            out.println("</body></html>");
            out.close();
            return;
        }
    }

}


