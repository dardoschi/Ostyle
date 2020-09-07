package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import people.User;

public class UserDao {
	
	private Connection connection = DBConnection.getDBConnection();
	
	
	public User UserLogin(String Username, String Password) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("SELECT * from users WHERE username = ? and password = ?");
			st.setString(1, Username);
			st.setString(2, Password);
		    ResultSet rs = st.executeQuery();
		    
		    if (rs.next()) {
		    	User user = new User(rs.getString("name"),rs.getString("surname"),rs.getString("username"),rs.getString("password"),rs.getString("email"), rs.getInt("codu"), rs.getString("address"),rs.getInt("cardn"));
		    	return user;
		    }else return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int RegisterNewUser(String Name, String Surname, String Username, String Password, String Email, String Address, int CardN) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("insert into users (name, surname, username, password, email, address, cardn) (values(?,?,?,?,?,?,?));");
			st.setString(1, Name);
			st.setString(2, Surname);
			st.setString(3, Username);
			st.setString(4, Password);
			st.setString(5, Email);
			st.setString(6, Address);
			st.setInt(7, CardN);
			ResultSet rs = st.executeQuery();
			return 0;
		}catch(SQLException ex) {
			String Error = ex.getMessage();
			if(Error.contains("username")) {
				return 1;
			}else if(Error.contains("email")) {
				return 2;
			}else{
					return 0;
			}
		}
	}
}
