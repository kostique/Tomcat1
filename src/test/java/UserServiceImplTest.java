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
}
