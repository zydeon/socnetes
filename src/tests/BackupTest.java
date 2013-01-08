import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.runners.*;
import java.util.Arrays;
import socnet.Backup;
import socnet.User;
import socnet.Client;
import org.junit.runner.RunWith;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(value = Parameterized.class)
public class BackupTest {

	private User user;
	public static ConcurrentHashMap<String, User> users;

	public static void main(String args[]) {
		  org.junit.runner.JUnitCore.main("BackupTest");
	}

	public BackupTest(String login, String pass, String NIB) {
		if(NIB.equals("")){
			user = new User(login, pass);
		}
		else{
			user = new Client(login, pass, Long.parseLong(NIB));
		}
		
		users.put(login, user);
	}

    @Parameterized.Parameters
    public static Collection <Object[]> inputCredentials(){
        Object[][] inputs = new Object[][] { {"1", "1", ""},
        									 {"2", "2", ""},
        									 {"3", "3", "123"},
        									 {"4", "4", "456"} };
        return Arrays.asList(inputs);
    }	
	
	@BeforeClass
	public static void setUpClass() {
		users = new ConcurrentHashMap<String, User>();
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testSaveUsers() throws Exception {
		System.out.println("Save users");
		assertTrue( "Users on '"+user.getLogin()+"' time were not correctly saved!", Backup.saveUsers(users) );
	}
}
