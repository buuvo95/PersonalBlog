package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.User;

public class UserDAO extends AbstractConnection {
		
	public boolean insertUser(User user) throws SQLException {
        
		if(user.getRegisterAt() == null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			user.setRegisterAt(dateFormat.format(date));
		}
		
		String sql = "INSERT INTO user (firstname, lastname, mobile, email, password, registerAt, activate, hashcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getFirstname());
        statement.setString(2, user.getLastname());
        statement.setString(3, user.getMobile());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        statement.setString(6, user.getRegisterAt());
        statement.setString(7, "0");
        statement.setString(8, user.getHashcode());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<User> listAllUsers() throws SQLException {
        List<User> listBook = new ArrayList<>();
         
        String sql = "SELECT * FROM user";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            long id = resultSet.getInt("id");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String mobile = resultSet.getString("mobile");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String registerAt = resultSet.getString("registerAt");
            String lastLogin = resultSet.getString("lastLogin");
            
            User user = new User(id, firstname, lastname, mobile, email, password, registerAt, lastLogin);
            listBook.add(user);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listBook;
    }
     
    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setLong(1, user.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET firstname = ?, lastname = ?, mobile = ?, email = ?, "
        		+ "password = ?, registerAt = ?"
        		+ "  WHERE id = ?";

        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getFirstname());
        statement.setString(2, user.getLastname());
        statement.setString(3, user.getMobile());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        statement.setString(6, user.getRegisterAt());
        statement.setLong(7, user.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public User getUser(long id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setLong(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String mobile = resultSet.getString("mobile");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String registerAt = resultSet.getString("registerAt");
            String lastLogin = resultSet.getString("lastLogin");
             
            user = new User(id, firstname, lastname, mobile, email, password, registerAt, lastLogin);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkLogin(String email, String password) throws SQLException {

    	String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
    	
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	statement.setString(1, email);
    	statement.setString(2, password);
    	
    	ResultSet resultSet = statement.executeQuery();
    	
    	if (resultSet.next()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public User getUserByEmail(String email) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, email);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	long id = resultSet.getLong("id");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String mobile = resultSet.getString("mobile");
            String password = resultSet.getString("password");
            String registerAt = resultSet.getString("registerAt");
            String lastLogin = resultSet.getString("lastLogin");
             
            user = new User(id, firstname, lastname, mobile, email, password, registerAt, lastLogin);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public String findUserEmailByHashcode(String hashcode) throws SQLException {
    	String email = null;
    	
    	String sql = "SELECT * FROM user WHERE hashcode = ?";
    	connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, hashcode);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            email = resultSet.getString("email");
        }
         
        resultSet.close();
        statement.close();
    	return email;
    }
    
    public void activateUser(User user) throws SQLException {
    	if (user.getActivate() != "1") {
    		String sql = "UPDATE user SET activate = ?"
            		+ "  WHERE id = ?";
    		connect();
            
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, "1");
            statement.setLong(2, user.getId());
            statement.executeUpdate();
            statement.close();
            disconnect();
    	}
    }
    
}
