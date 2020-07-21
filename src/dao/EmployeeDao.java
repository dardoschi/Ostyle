package dao;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Controller;
import main.Employee;

public class EmployeeDao {
	
	private Controller ctrl;
	private Connection conn = ConnectionFactory.getConnection();
	
	public EmployeeDao(Controller c) {
		ctrl = c;
	}

	public Employee Login(String Username, String Password) {
		PreparedStatement st;
		try {
			st = conn.prepareStatement("SELECT * from employees WHERE username = ? and password = ?");
				 st.setString(1, Username);
				 st.setString(2, Password);
			     ResultSet rs = st.executeQuery(); 
			     // if this account exist returns true else returns false 
			     if ( rs.next() ) {    	
			    	 Employee user = new Employee(rs.getString("username"), rs.getBoolean("admin"));
			    	 return user;
			    	}
			     else 
			    	 return null;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	//register user
	public boolean RegisterNewUser(String Username, String Password, boolean Admin) {
		PreparedStatement st;
		try {
				st = conn.prepareStatement("insert into employees(values(?,?,?));");
				st.setString(1, Username);
				st.setString(2, Password);
				st.setBoolean(3, Admin);
				ResultSet rs = st.executeQuery();
				return true;
		}
		catch (SQLException e) {
			String Error = e.getMessage();
			if(Error.contains("duplicate key"))
				return false;
			///else 
				//if( Error.contains("No results"))
					//return true;
				else return true;
		}
	}
	 

}
