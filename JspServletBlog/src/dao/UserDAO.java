package dao;

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

public class UserDAO {   
    
	jdbcController jdbcController = new jdbcController();
	
    public boolean insertUser(User user) throws SQLException {
    	String sql = "INSERT INTO user (firstname, lastname, mobile, email, password, image, registerAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcController.connect();
		
		PreparedStatement statement = jdbcController.jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getFirstname());
		statement.setString(2, user.getLastname());
		statement.setString(3, user.getMobile());
		statement.setString(4, user.getEmail());
		statement.setString(5, user.getPassword());
		if (user.getImage() != null) {
			statement.setBytes(6, user.getImage());
		} else {
			statement.setBytes(6, null);
		}
		if (user.getRegisterAt() != null) {
			statement.setString(7, user.getRegisterAt());
		} else {
			Date date = new Date();
			String pattern = "MM/dd/yyyy HH:mm:ss";
			DateFormat df = new SimpleDateFormat(pattern);
			String todayAsString = df.format(date);
			statement.setString(7, todayAsString);
		}
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		jdbcController.disconnect();
    	return rowInserted;
    }
    
    public List<User> listAllUser() throws SQLException {
    	List<User> listUser  = new ArrayList<>();
    	String sql = "SELECT * FROM user";
    	
    	jdbcController.connect();
    	
    	Statement statement = jdbcController.jdbcConnection.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	while(resultSet.next()) {
    		long id = resultSet.getInt("id");
    		String firstname = resultSet.getString("firstname");
    		String lastname = resultSet.getString("lastname");
    		String mobile = resultSet.getString("mobile");
    		String email = resultSet.getString("email");
    		String password = resultSet.getString("password");
    		byte[] image = resultSet.getBytes("image");
    		String registerAt = resultSet.getString("registerAt");
    		String lastLogin = resultSet.getString("lastLogin");
    		
    		User user = new User(id, firstname, lastname, mobile, email, password, image, registerAt, lastLogin);
    		listUser.add(user);
    	}
    	
    	resultSet.close();
    	statement.close();
    	
    	jdbcController.disconnect();
    	
    	return listUser;
    }
    
    public boolean updateUser(User user) throws SQLException {
    	String sql = "UPDATE user SET firstname = ?, lastname = ?, mobile = ?, email = ?, password = ?, image = ?";
    	sql += "WHERE id = ?";
    	jdbcController.connect();
    	
    	PreparedStatement statement = jdbcController.jdbcConnection.prepareStatement(sql);
    	statement.setString(1, user.getFirstname());
		statement.setString(2, user.getLastname());
		statement.setString(3, user.getMobile());
		statement.setString(4, user.getEmail());
		statement.setString(5, user.getPassword());
		statement.setBytes(6, user.getImage());
		statement.setLong(7, user.getId());
    	
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		jdbcController.disconnect();
    	return rowUpdated;
    }
    
    public boolean deleteUser(User user) throws SQLException {
    	String sql = "DELETE FROM user WHERE id = ?";
    	jdbcController.connect();
    	
    	PreparedStatement statement = jdbcController.jdbcConnection.prepareStatement(sql);
    	statement.setLong(1, user.getId());
    	
    	boolean rowDeleted = statement.executeUpdate() > 0;
    	statement.close();
    	jdbcController.disconnect();
    	return rowDeleted;
    }
    
    public User getUser(int id) throws SQLException {
    	User user = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";
         
        jdbcController.connect();
         
        PreparedStatement statement = jdbcController.jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
    		String firstname = resultSet.getString("firstname");
    		String lastname = resultSet.getString("lastname");
    		String mobile = resultSet.getString("mobile");
    		String email = resultSet.getString("email");
    		String password = resultSet.getString("password");
    		byte[] image = resultSet.getBytes("image");
    		String registerAt = resultSet.getString("registerAt");
    		String lastLogin = resultSet.getString("lastLogin");
             
    		user = new User(id, firstname, lastname, mobile, email, password, image, registerAt, lastLogin);
        }
         
        resultSet.close();
        statement.close();
        jdbcController.disconnect();
         
        return user;
    }
}
