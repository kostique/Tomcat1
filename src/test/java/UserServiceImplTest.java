import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.exceptions.InvalidUserAttributesException;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;

import javax.persistence.PersistenceException;


public class UserServiceImplTest {

    @Test
    public void createUser() {
        UserService userService = new UserServiceImpl();
        User user = new User("askor1", "james008", "qwerty008", "bond@mi46.co.uk");
        try {
            userService.create(user);
        }catch (ConstraintViolationException e){
            //System.out.println(e.getMessage());
            throw new InvalidUserAttributesException("Invalid user attributes");
        }
    }

    @Test
    public void getUserByUsername() {
        UserService userService = new UserServiceImpl();
        User user = userService.getByUsername("James");
        System.out.println("user = " + user);;
    }

    @Test
    public void some(){
        UserDAO userDAO = new UserDAOImpl();
        //User user = userDAO.getByUsername("username154512164644");
        System.out.println(((UserDAOImpl) userDAO).isUserExist("username1545121646442"));
    }
}
