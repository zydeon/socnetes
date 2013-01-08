import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.runners.*;
import java.util.Arrays;
import socnet.Socnet;
import org.junit.runner.RunWith;

@RunWith(value = Parameterized.class)
public class SocnetTest {

	private String userLogin;
	private String password;
	private String NIB;

	public static void main(String args[]) {
		  org.junit.runner.JUnitCore.main("SocnetTest");
	}

	public SocnetTest(String login, String pass, String NIB) {
		this.userLogin = login;
		this.password = pass;
		this.NIB = NIB;
	}

    @Parameterized.Parameters
    public static Collection <Object[]> inputCredentials(){
        Object[][] inputs = new Object[][] { {"q", "q", ""},
        									 {"q2", "q2", ""},
        									 {"a", "a", "1111111"},
        									 {"a2", "a2", "1111112"},
        									 {"dss", "akshbdhasjbd", ""}, 
        									 {"dss2", "akshbdhasjbd2", ""}, 
        									 {"asd", "ds", "000"},
        									 {"asd2", "ds2", "001"} };
        return Arrays.asList(inputs);
    }	
	
	@BeforeClass
	public static void setUpClass() {
		Socnet.init();
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
	public void testRegisterUser() throws Exception {
		System.out.println("Register user");
		assertTrue( "User '"+userLogin+"' already exists!", Socnet.registerUser(userLogin, password, NIB) );
	}

	@Test
	public void testUserExists() throws Exception {
		System.out.println("User was registered");
		assertTrue( "User '"+userLogin+"' is not registered!", Socnet.userExists(userLogin) );
	}
}
