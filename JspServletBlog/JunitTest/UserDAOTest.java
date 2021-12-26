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
		user.setFirstname("First1");
		user.setLastname("Last1");
		user.setMobile("01234567891");
		user.setEmail("Test1@gmail.com");
		user.setPassword("123123");
		//user.setImage(null);
		user.setRegisterAt("12212021");
		boolean created = userDAO.insertUser(user);
				
		assertEquals(created, true);
	}
	
	@Test
	void testListUser() throws SQLException {
		userDAO = new UserDAO();
		
		List<User> listUser = userDAO.listAllUsers();
		
		assertTrue(listUser.size() > 0);
	}
	
	@Test
	void testDeleteUser() throws SQLException {
		long id = 6;
		
		userDAO = new UserDAO();
		User existUser = userDAO.getUser(id);
		boolean deletedUser = userDAO.deleteUser(existUser);
		
		assertEquals(deletedUser, true);
		
	}
	
}
