import com.coreteka.entities.User;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreatedUserServlet", urlPatterns = "/userCreated.html")
public class CreatedUserServlet extends HttpServlet {
    //todo REST
    //curl
    //postman
    //Intellij rest client
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User emptyUser = new User();

        UserService userService = new UserServiceImpl();
        User user = new User();

        user.setUsername(request.getParameter("username"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));

        //User createdUser = userService.save(user);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

//        if (createdUser.equals(emptyUser)){
//            out.println("<html><body><h3>Could not save user.</h3>");
//        } else {
//            out.println("<html><body><h3>User created successfully</h3>");
//        }
//        out.println("<a href='home.html'>Back to main menu</a>");
//        out.println("</body></html>");
//        out.close();


//        try {
//            userService.save(user);
//        } catch (javax.persistence.PersistenceException pe){//todo
//            pe.printStackTrace();
//            PrintWriter out = response.getWriter();
//            response.setContentType("text/html");
//            out.println("<html><body><h3>Could not save user.</h3>");
//            out.println("<a href='home.html'>Back to main menu</a>");
//            out.println("</body></html>");
//            out.close();
//            return;
//        }

//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html");
//        out.println("<html><body><h3>User created successfully</h3>");
//        out.println("<a href='home.html'>Back to main menu</a>");
//        out.println("</body></html>");
//        out.close();

    }
}
