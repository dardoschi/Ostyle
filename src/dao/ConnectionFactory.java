package dao;

import java.sql.*;
import main.Controller;

//get connection returns a Connection object		 how to use:Connection connection = ConnectionFactory.getConnection();
public class ConnectionFactory {
	
	private Controller ctrl;
	public static final String url = "jdbc:postgresql://localhost/Ostyle";
    public static final String username = "postgres";
    public static final String password = "password";
    
    
    public ConnectionFactory(Controller c) {
    	ctrl = c;
    }
    
    
    //single connection , reusable
    public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			}
		catch(ClassNotFoundException e1) {
			System.err.println("Classe non trovata");
		}
			Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,username, password);
			}
		catch(SQLException e2) {
			System.err.println("Errore di connessione");
		}
		return conn;
      }
}
