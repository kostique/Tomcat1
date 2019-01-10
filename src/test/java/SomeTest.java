import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.util.PersistenceUtil;
import org.junit.Test;

import javax.persistence.TypedQuery;

public class SomeTest {

    @Test
    public void split(){
        String path = "/path/to/info";
        String[] split = path.split("/");
        for(String pathBit: split){
            System.out.println(pathBit);
        }
    }

    @Test
    public void checkUserAttributes() {
        //System.out.println(isUserExist("username", "askor"));
        System.out.println(isEntryExist("login", "u1", 317));

    }


    private boolean isEntryExist(String columnName, String value){
        String qlString = "SELECT COUNT(u) FROM User u WHERE u." + columnName + " = :" + columnName;
        TypedQuery<Long> query = PersistenceUtil.
                getEntityManager().
                createQuery(qlString, Long.class);
        query.setParameter(columnName, value);
        return (query.getSingleResult() != 0L);
    }

    public boolean isEntryExist(String email){

        String qlString = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
        TypedQuery<Long> query = PersistenceUtil.
                getEntityManager().
                createQuery(qlString, Long.class);
        query.setParameter("email", email);
        return (query.getSingleResult() != 0L);
    }

    private boolean isEntryExist(String columnName, String value, long id){
        String qlString = "SELECT COUNT(u) FROM User u WHERE u." + columnName + " = :" + columnName + " AND u.id != :id";
        System.out.println("qlString = " + qlString);

        TypedQuery<Long> query = PersistenceUtil.
                getEntityManager().
                createQuery(qlString, Long.class);
        query.setParameter(columnName, value);
        query.setParameter("id", id);
        return (query.getSingleResult() != 0L);
    }


}
