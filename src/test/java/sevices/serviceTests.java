package sevices;

import com.revature.hibernate.users.UserService;
import com.revature.models.User;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class serviceTests {
    private User testUser;
    private UserService testUserService;

    @Before
    public void setUp() {
        testUser = new User();
        testUser.setUserId(100);
        testUser.setUserRole(1);
        testUser.setFirstname("fname");
        testUser.setLastname("lname");
        testUser.setUsername("uname");
        testUser.setPassword("pass");
        testUser.setEmail("email");
        testUserService = new UserService();
    }

    @Test
    public void createTest() {
        testUserService.authenticate(testUser.getUsername(), testUser.getPassword());
        assertEquals("User Not Valid", true, testUserService.isUserValid(testUser));

    }

    @Test
    public void checkUserIsValid() {
        testUserService.AddUser(testUser);
        assertEquals("User Not Valid", true, testUserService.isUserValid(testUser));
    }

    @After
    public void tearDown() {
        testUserService.deleteUser(100);

        try {
            List<User> queryRead = testUserService.viewAllUsers();
            System.out.println("<-----------------LISTING READ QUERY RESULTS--------------------->");
            for (User user : queryRead) {
                System.out.println("<-----" + user.getUsername() + "------->");
                System.out.println("firstname: " + user.getFirstname());
                System.out.println("lastname: " + user.getLastname());
                System.out.println("id: " + user.getUserId());
                System.out.println("role: " + user.getUserRole());
                System.out.println("email: " + user.getEmail());
            }
        } catch (RuntimeException e) {
            System.out.println("User removed during tear down as expected :)");
        }


    }
}
