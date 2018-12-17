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

@WebServlet(name = "FoundUserServlet", urlPatterns = "/userFound.html")
public class FoundUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        User user = userService.getByUsername(username);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body><h3>  Information on "+ username +" </h3>");

        out.println(user.toString());
        out.println("<br><br>");
        out.println("<a href='home.html'>Back to main menu</a>");
        out.println("</body></html>");
        out.close();
    }
}
