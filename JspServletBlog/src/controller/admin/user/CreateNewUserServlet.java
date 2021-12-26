package controller.admin.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.UserServices;

@WebServlet("/admin/new_user")
public class CreateNewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateNewUserServlet() {
        super();
    }
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices userServices = new UserServices(request, response);
		userServices.createUser();
	}

}
