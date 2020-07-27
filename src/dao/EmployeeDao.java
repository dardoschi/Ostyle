package dao;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Controller;
import people.Employee;

public class EmployeeDao {
	
	//private Controller ctrl;
	private Connection conn = DBConnection.getDBConnection();
	
	//public EmployeeDao(Controller c) {
	//	ctrl = c;
	//}

	
	public Employee Login(String Username, String Password) {
		PreparedStatement st;
		try {
			st = conn.prepareStatement("SELECT * from employees WHERE username = ? and password = ?");
				 st.setString(1, Username);
				 st.setString(2, Password);
			     ResultSet rs = st.executeQuery(); 
			     // if this account exist returns true else returns false 
			     if ( rs.next() ) {    	
			    	 Employee user = new Employee(rs.getString("name"),rs.getString("surname"),rs.getString("username"),rs.getString("password"),rs.getInt("codi"), rs.getBoolean("admin"));
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
	public boolean RegisterNewEmployee(String Username, String Password, boolean Admin, String Name, String Surname, int CodI) { //
		PreparedStatement st;
		try {
				st = conn.prepareStatement("insert into employees(values(?,?,?,?,?,?));");
				st.setString(1, Username);
				st.setString(2, Password);
				st.setBoolean(3, Admin);
				st.setString(4, Name);
				st.setString(5, Surname);
				st.setInt(6, CodI);
				ResultSet rs = st.executeQuery();
				return true;
		}
		catch (SQLException e) {
			String Error = e.getMessage();
			if(Error.contains("valore chiave duplicato")) {
				return false;
			}else {
					return true;
			}
		}
	}
	 

}
