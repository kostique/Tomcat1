import com.coreteka.entities.User;
import org.junit.Test;

public class SomeTest {

    @Test
    public void split(){
        String path = "/path/to/info";
        String[] split = path.split("/");
        for(String pathBit: split){
            System.out.println(pathBit);
        }
    }
    private static void fillUserAttributes(User user) {
        user.setLogin("_login_");
        user.setPassword("_password_");
    }
}
