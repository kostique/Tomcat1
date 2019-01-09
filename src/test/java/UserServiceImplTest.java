import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.exceptions.InvalidUserAttributeValueException;
import com.coreteka.service.UserService;
import com.coreteka.service.impl.UserServiceImpl;
import com.coreteka.util.PersistenceUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UserServiceImplTest {

//    @Test
//    public void createUser() {
//        UserService userService = new UserServiceImpl();
//        User user = new User("askor1", "james008", "qwerty008", "bond@mi46.co.uk");
//        try {
//            userService.create(user);
//        }catch (ConstraintViolationException e){
//            //System.out.println(e.getMessage());
//            throw new InvalidUserAttributeValueException("Invalid user attributes");
//        }
//    }

//    @Test
//    public void getUserByUsername() {
//        UserService userService = new UserServiceImpl();
//        User user = userService.getByUsername("James");
//        System.out.println("user = " + user);;
//        UserDAO userDAO = new UserDAOImpl();
//        userDAO.isUserExist("username1545121646442");
//    }

    @Test
    public void createUserByStatic(){
        User user = new User();
//        user.setId(0);
        user.setUsername("c21");
        user.setPassword("2c1");
        user.setEmail("521a11111");
        user.setLogin("user21a11111");
        UserService userService = new UserServiceImpl();
        userService.create(user);


        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();*/
    }


}
