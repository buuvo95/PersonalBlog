package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;


public class jdbcController extends HttpServlet{
	//private String jdbcURL = getServletContext().getInitParameter("jdbcURL");
	//private String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
	//private String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
    //protected Connection jdbcConnection;
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/personalblog";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
    protected Connection jdbcConnection;
    
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}