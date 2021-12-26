package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractConnection {
	private String jdbcURL = "jdbc:mysql://localhost:3306/personalblog";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	protected Connection jdbcConnection;
	
	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		jdbcConnection = DriverManager.getConnection(
                jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}
