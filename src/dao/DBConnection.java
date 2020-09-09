package dao;
import java.sql.*;
import main.Controller;

public class DBConnection {
	
	public static final String url = "jdbc:postgresql://localhost/Ostyle4";
    public static final String username = "postgres";
    public static final String password = "password";
    
    static Connection conn = null;
    
    public static Connection getDBConnection() {
    	try {
    		if(conn == null) {
    			Class.forName("org.postgresql.Driver");
    			conn = DriverManager.getConnection(url,username, password);
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return conn;
    }
}
