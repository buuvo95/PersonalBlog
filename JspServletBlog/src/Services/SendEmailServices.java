package Services;

import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.UserDAO;
import entity.User;

public class SendEmailServices {
	
	private UserDAO userDAO;
	
	public SendEmailServices() {
		this.userDAO = new UserDAO();
	}
	
	// generate hashcode
	final String uuid = UUID.randomUUID().toString().replace("-", "");
	
	public boolean verifyUser(User newUser) {
		
		newUser.setHashcode(uuid);
		
		try {
			boolean insertedUser = userDAO.insertUser(newUser);
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
	        // below mentioned mail.smtp.port is optional 
	        props.put("mail.smtp.port", "587");		
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
			
	        Session session = Session.getInstance(props,new javax.mail.Authenticator()
	        {
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	  	 	         return new PasswordAuthentication("lightofdeity@gmail.com","VGFsYWRhaWNhMQ==18"); 
	            }
	        });
	        
	        try {
	        	MimeMessage message = new MimeMessage(session);
	        	message.setFrom(new InternetAddress("lightofdeity@gmail.com"));
	            message.addRecipient(Message.RecipientType.TO,new InternetAddress(newUser.getEmail()));
	            message.setSubject("Validation Email");
	            message.setText("Please click here for activate user with email: " + newUser.getEmail() + " http://localhost:8080/JspServletBlog/activation?hashcode="+uuid);

	            /* Transport class is used to deliver the message to the recipients */
	           
	            Transport.send(message);
	            
	            return true;
	        } catch(Exception e) {
	   	     return false;
	       }				
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

}
