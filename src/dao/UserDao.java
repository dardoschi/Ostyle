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
			st = connection.prepareStatement("SELECT * from user WHERE username = ? and password = ?");
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
	
	
	public boolean RegisterNewUser(String Name, String Surname, String Username, String Password, String Email, int CodU, String Address, int CardN) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("insert into user(values(?,?,?,?,?,?,?,?));");
			st.setString(1, Name);
			st.setString(2, Surname);
			st.setString(3, Username);
			st.setString(4, Password);
			st.setString(5, Email);
			st.setInt(6, CodU);
			st.setString(7, Address);
			st.setInt(8, CardN);
			ResultSet rs = st.executeQuery();
			return true;
		}catch(SQLException ex) {
			String Error = ex.getMessage();
			if(Error.contains("valore chiave duplicato")) {
				return false;
			}else {
				return true;
			}
		}
	}
}
