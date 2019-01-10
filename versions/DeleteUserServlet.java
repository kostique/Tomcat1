import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteUserServlet", urlPatterns = "/deleteUser.html")
public class DeleteUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><body><h3>Enter username and click \"Delete user\".</h3>");
        out.println("<form action='userDeleted.html' method='POST'>");

        out.println("<input type='text' name='username'");
        out.println("<br>");
        out.println("<input type='submit' value = 'Delete user'/>");
        out.println("</body></html>");
        out.close();
    }
}
