import com.coreteka.entities.User;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import org.junit.Test;




public class UserServiceImplTest {

    @Test
    public void createUser() {
        UserService userService = new UserServiceImpl();
        User user = new User("James", "james007", "qwerty007", "bond@mi6.co.uk");
        userService.create(user);
    }

    @Test
    public void getUserByUsername() {
        UserService userService = new UserServiceImpl();
        User user = userService.getByUsername("James");
        System.out.println("user = " + user);;
    }
}
