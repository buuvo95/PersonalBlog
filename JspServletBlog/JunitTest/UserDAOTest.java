import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

//import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import dao.UserDAO;
import entity.User;

class UserDAOTest {
	
	private static UserDAO userDAO;
	
//	@BeforeClass
//	public static void setUpClass() throws Exception {
//		userDAO = new UserDAO();
//	}
	
	@Test
	void testCreateUser() throws SQLException {
		userDAO = new UserDAO();
		User user = new User();
		user.setFirstname("First");
		user.setLastname("Last");
		user.setMobile("0123456789");
		user.setEmail("Test@gmail.com");
		user.setPassword("123123");
		boolean created = userDAO.insertUser(user);
				
		assertEquals(true, created);
	}
	
	@Test
	void testListUser() throws SQLException {
		userDAO = new UserDAO();
		
		List<User> listUser = userDAO.listAllUser();
	
		assertTrue(listUser.size() > 0);
	}
	
}
