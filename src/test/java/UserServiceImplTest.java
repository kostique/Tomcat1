import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import org.junit.Test;




public class UserServiceImplTest {

    @Test
    public void createUser() {
        UserService userService = new UserServiceImpl();
        User user = new User("", "james007", "qwerty007", "bond@mi6.co.uk");
        userService.create(user);
    }

    @Test
    public void getUserByUsername() {
        UserService userService = new UserServiceImpl();
        User user = userService.getByUsername("Jamess");
        System.out.println("user = " + user);;
    }

    @Test
    public void some(){
        UserDAO userDAO = new UserDAOImpl();
        //User user = userDAO.getByUsername("username154512164644");
        System.out.println(((UserDAOImpl) userDAO).isUserExist("username1545121646442"));
    }
}
