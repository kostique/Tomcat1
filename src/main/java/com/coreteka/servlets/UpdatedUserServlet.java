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

@WebServlet(name = "UpdatedUserServlet", urlPatterns = "/userUpdated.html")
public class UpdatedUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();

        String oldUsername = request.getParameter("old_username");

        try {
            User existedUser = userService.getByUsername(oldUsername);

            existedUser.setUsername(request.getParameter("new_username"));
            existedUser.setLogin(request.getParameter("new_login"));
            existedUser.setPassword(request.getParameter("new_password"));
            existedUser.setEmail(request.getParameter("new_email"));

            userService.update(existedUser);

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body><h3>User updated successfully.</h3>");
            out.println("<a href='home.html'>Back to main menu</a>");
            out.println("</body></html>");
            out.close();

        } catch (PersistenceException dsf){
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body><h3>Could not update user with username: " + oldUsername + "</h3>");
            out.println("<a href='home.html'>Back to main menu</a>");
            out.println("</body></html>");
            out.close();
            return;
        }
    }
}
