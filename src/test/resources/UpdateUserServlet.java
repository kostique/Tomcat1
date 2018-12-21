import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateUserServlet", urlPatterns = "/updateUser.html")
public class UpdateUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><body><h3>Update user:</h3>");

        out.println("<form action='userUpdated.html' method='POST' >");
        out.println("Enter username of the user to be updated: <br>");
        out.println("<input type='text' name='old_username'/>");
        out.println("<br>");
        out.println("<br>");

        out.println("<ul>");
        out.println("<li>New username:<br> <input type='text' name='new_username'</li>");
        out.println("<li>New login    <br> <input type='text' name='new_login'/> </li>");
        out.println("<li>New password <br> <input type='text' name='new_password'/> </li>");
        out.println("<li>New e-mail   <br> <input type='text' name='new_email'/> </li>");
        out.println("</ul>");
        out.println("<br>");
        out.println("<input type='submit' value = 'Update user'/>");
        out.println("</form>");
        out.println("</body></html>");
        out.close();

    }
}
