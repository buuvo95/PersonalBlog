package Services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.frontend.user.VerifyUtils;
import dao.UserDAO;
import entity.User;

public class UserServices {
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse reponse;
	
	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.reponse = response;
		userDAO = new UserDAO();
	}
	
	public void listUser() throws ServletException, IOException {
		try {
			List<User> listUser = userDAO.listAllUsers();
			request.setAttribute("listUsers", listUser);
			
			String listPage = "user_list.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
			
			requestDispatcher.forward(request, reponse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createUser() throws ServletException, IOException {	
		User newUser = new User();
		newUser.setFirstname(request.getParameter("firstname"));
		newUser.setLastname(request.getParameter("lastname"));
		newUser.setMobile(request.getParameter("mobile"));
		newUser.setEmail(request.getParameter("email"));
		newUser.setPassword(request.getParameter("password"));
	
		try {
			boolean createdUser = userDAO.insertUser(newUser);
			listUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editedUser() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			User existUser = userDAO.getUser(id);
			request.setAttribute("user", existUser);
			String editPage = "user_form.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
			requestDispatcher.forward(request, reponse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			User existUser = userDAO.getUser(id);
			existUser.setFirstname(request.getParameter("firstname"));
			existUser.setLastname(request.getParameter("lastname"));
			existUser.setMobile(request.getParameter("mobile"));
			existUser.setEmail(request.getParameter("email"));
			existUser.setPassword(request.getParameter("password"));
			
			boolean updatedUser = userDAO.updateUser(existUser);
			listUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			User existUser = userDAO.getUser(id);
			boolean deletedUser = userDAO.deleteUser(existUser);
			listUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			boolean logined = userDAO.checkLogin(email, password);
			
			if(logined) {
				request.getSession().setAttribute("useremail", email);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/list_user");
				dispatcher.forward(request, reponse);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, reponse);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void userProfile(String email) throws ServletException, IOException {
		try {
			User userByEmail = userDAO.getUserByEmail(email);
			
			request.setAttribute("user", userByEmail);
			
			String profilePage = "user_profile.jsp"; 
			RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
			dispatcher.forward(request, reponse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void registerUser() throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		
		User newUser = new User();
		newUser.setFirstname(firstname);
		newUser.setLastname(lastname);
		newUser.setEmail(email);
		newUser.setMobile(mobile);
		newUser.setPassword(password);
		
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean valid = VerifyUtils.verify(gRecaptchaResponse);
		
		if (valid) {
			try {
				User userByEmail = userDAO.getUserByEmail(email);
				if (userByEmail != null) {
					// Redirect to error page
				} else {
					SendEmailServices emailServices = new SendEmailServices();
					boolean verified = emailServices.verifyUser(newUser);
					if (verified) {
						// Regirect to index page
						RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, reponse);
					} else {
						// Redirect to Error Page
						RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, reponse);
					}
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

	public void activateuser() throws ServletException, IOException {
		String hashcode = request.getParameter("hashcode");
		
		try {
			String unActivateEmail = userDAO.findUserEmailByHashcode(hashcode);
			User user = userDAO.getUserByEmail(unActivateEmail);
			userDAO.activateUser(user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.jsp");
			dispatcher.forward(request, reponse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
