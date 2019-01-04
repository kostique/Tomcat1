import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.exceptions.InvalidUserAttributeValueException;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;


public class UserServiceImplTest {

    @Test
    public void createUser() {
        UserService userService = new UserServiceImpl();
        User user = new User("askor1", "james008", "qwerty008", "bond@mi46.co.uk");
        try {
            userService.create(user);
        }catch (ConstraintViolationException e){
            //System.out.println(e.getMessage());
            throw new InvalidUserAttributeValueException("Invalid user attributes");
        }
    }

    @Test
    public void getUserByUsername() {
        UserService userService = new UserServiceImpl();
        User user = userService.getByUsername("James");
        System.out.println("user = " + user);;
        UserDAO userDAO = new UserDAOImpl();
        userDAO.isUserExist("username1545121646442");
    }

    @Test
    public void createUserByStatic(){
        User user = new User();
        fillUserAttributes(user);
        System.out.println(user);
    }

    private static void fillUserAttributes(User user) {
    }
}
